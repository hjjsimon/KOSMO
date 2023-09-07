from flask import Flask, request, jsonify
from flask_restful import Resource, Api
from flask_cors import CORS
import os
import xgboost as xgb
import pandas as pd
from rpy2.robjects import r, pandas2ri
from rpy2.robjects.packages import importr

app = Flask(__name__)
CORS(app)  # CORS 설정
api = Api(app)  # API 초기화

# R 패키지 로드
base = importr('base')

# 데이터셋의 경로를 설정
script_dir = os.path.dirname(os.path.abspath(__file__))
data_path = os.path.join(script_dir, 'healthcare-dataset-stroke-data.csv')

# 원본 데이터 로드
data = pd.read_csv(data_path)

# R을 사용하여 전처리 로직을 정의
def preprocess_input_data_with_r(input_data):
    input_data = [input_data]  # 리스트 형태로 변환
    input_df = pd.DataFrame(input_data)  # 데이터프레임으로 변환

    pandas2ri.activate()
    r_input_data = pandas2ri.py2rpy(input_df)  # R 데이터프레임으로 변환
    r_original_data = pandas2ri.py2rpy(data)
    pandas2ri.deactivate()
    
    r.assign('input_data', r_input_data)  # R 변수에 할당
    r.assign('original_data', r_original_data)
    
    # R에서 데이터 전처리
    r('''
    library(scales)

    preprocess_input <- function(input_data, original_data) {
        data <- as.data.frame(input_data)

        # MinMax 스케일링
        min_age <- min(original_data$age)
        max_age <- max(original_data$age)
        data$age <- (data$age - min_age) / (max_age - min_age)

        min_glucose <- min(original_data$avg_glucose_level)
        max_glucose <- max(original_data$avg_glucose_level)
        data$avg_glucose_level <- (data$avg_glucose_level - min_glucose) / (max_glucose - min_glucose)

        # 라벨 인코딩
        for(column_name in c("gender", "ever_married", "work_type", "Residence_type")) {
            levels <- sort(unique(original_data[[column_name]]))
            data[[column_name]] <- as.numeric(factor(data[[column_name]], levels = levels)) - 1
        }

        # 표준 스케일링
        for(column_name in c("gender", "ever_married", "work_type", "Residence_type")) {
            mean_value <- mean(data[[column_name]])
            sd_value <- sd(data[[column_name]])
            data[[column_name]] <- (data[[column_name]] - mean_value) / sd_value
        }

        return(data)
    }

    processed_data <- preprocess_input(input_data, original_data)
    ''')
    
    return r['processed_data']  # 전처리된 데이터 반환

# XGBoost 모델 로드
model = xgb.Booster()
model.load_model(os.path.join(os.path.dirname(os.path.dirname(__file__)), 'api', 'stroke_gold_model.xgb'))

# 뇌졸중 예측 모델 리소스 정의
class StrokeModel(Resource):
    def post(self):
        json_data = request.get_json()
        preprocessed_data = preprocess_input_data_with_r(json_data)

        # R 데이터프레임을 Pandas 데이터프레임으로 변환
        preprocessed_df = pandas2ri.rpy2py_dataframe(preprocessed_data)

        # 예측 실행
        dmatrix = xgb.DMatrix(preprocessed_df.values)
        prediction = model.predict(dmatrix)
        
        return jsonify({'prediction': prediction.tolist()})  # 예측 결과 반환

# API 리소스 추가
api.add_resource(StrokeModel, '/StrokeModel')

# Flask 앱 실행
if __name__ == '__main__':
    app.run(port=5000, threaded=False)
