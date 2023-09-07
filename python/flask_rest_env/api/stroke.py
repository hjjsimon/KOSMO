from flask import Flask, request, jsonify
from flask_restful import Resource, Api
from flask_cors import CORS
import os
import xgboost as xgb
import numpy as np
import pandas as pd
from sklearn.preprocessing import MinMaxScaler, StandardScaler, LabelEncoder

app = Flask(__name__)
CORS(app)
api = Api(app)

# 데이터셋 불러오기
script_dir = os.path.dirname(os.path.abspath(__file__))
data_path = os.path.join(script_dir, 'healthcare-dataset-stroke-data.csv')
data = pd.read_csv(data_path)

# 전처리 객체
ms_age_glucose = MinMaxScaler()  # 나이와 혈당을 위한 MinMax 스케일러
ss = StandardScaler()  # 범주형 변수를 위한 Standard 스케일러

# 범주형 변수를 변환하기 위한 LabelEncoder들의 딕셔너리
label_encoders = {
    "gender": LabelEncoder(),
    "ever_married": LabelEncoder(),
    "work_type": LabelEncoder(),
    "Residence_type": LabelEncoder()
}

# 전처리 객체 초기화
def initialize_preprocessing_objects():
    # 각각의 LabelEncoder를 데이터에 맞춰 학습
    for col, encoder in label_encoders.items():
        encoder.fit(data[col])  
    # 나이와 혈당을 학습하기 위해 MinMax 스케일러 사용
    ms_age_glucose.fit(data[["age", "avg_glucose_level"]])
    # 범주형 변수를 라벨 인코딩한 후, 스케일링을 위해 StandardScaler 학습
    label_encoded_data = data[["gender", "ever_married", "work_type", "Residence_type"]].apply(LabelEncoder().fit_transform)
    ss.fit(label_encoded_data)

initialize_preprocessing_objects()

# 입력 데이터 전처리 함수
def preprocess_input_data(input_data):
    input_df = pd.DataFrame([input_data])
    for col, encoder in label_encoders.items():
        input_df[col] = encoder.transform(input_df[col])
    input_df[["age", "avg_glucose_level"]] = ms_age_glucose.transform(input_df[["age", "avg_glucose_level"]])
    input_df[["gender", "ever_married", "work_type", "Residence_type"]] = ss.transform(input_df[["gender", "ever_married", "work_type", "Residence_type"]])
    return input_df.iloc[0].to_dict()

# 저장된 XGBoost 모델 로딩
model = xgb.Booster()
model.load_model(os.path.join(os.path.dirname(os.path.dirname(__file__)), 'api', 'stroke_gold_model.xgb'))

# 뇌졸중 예측 모델 리소스 정의
class StrokeModel(Resource):
    def post(self):
        json_data = request.get_json()
        preprocessed_data = preprocess_input_data(json_data)
        print(preprocessed_data)
        dmatrix = xgb.DMatrix(np.array(list(preprocessed_data.values())).reshape(1, -1))
        prediction = model.predict(dmatrix)
        print(prediction.tolist())
        return jsonify({'prediction': prediction.tolist()})

# API 리소스 추가
api.add_resource(StrokeModel, '/StrokeModel')

# 메인 실행
if __name__ == '__main__':
    app.run(port=5000)
