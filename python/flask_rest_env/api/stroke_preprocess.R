library(dplyr)
library(caret)
library(readr)
library(jsonlite)
library(base)

# 데이터셋 불러오기
data_path <- "D:\\HJJ\\Workspace\\python\\flask_env\\flask_rest_env\\api\\healthcare-dataset-stroke-data.csv"
data <- read_csv(data_path)

# Preprocessing 모델 생성
preProcess_range_model <- preProcess(data[, c("age", "avg_glucose_level")], method='range')

# 라벨 인코딩
data$gender <- as.integer(factor(data$gender, levels = unique(data$gender)))
data$ever_married <- as.integer(factor(data$ever_married, levels = unique(data$ever_married)))
data$work_type <- as.integer(factor(data$work_type, levels = unique(data$work_type)))
data$Residence_type <- as.integer(factor(data$Residence_type, levels = unique(data$Residence_type)))

# Standard Scaling 모델 생성
preProcess_standard_model <- preProcess(data[, c("gender", "ever_married", "work_type", "Residence_type")], method=c('center', 'scale'))

# 전처리 함수 정의
preprocess_json_input <- function(json_input) {
  # JSON 입력을 데이터프레임으로 변환
    json_data <- data.frame(fromJSON(json_input), stringsAsFactors = FALSE)

    # 범위 스케일링 모델 적용
    json_data[c("age", "avg_glucose_level")] <- predict(preProcess_range_model, json_data[, c("age", "avg_glucose_level")])

    # 라벨 인코딩
    json_data$gender <- as.integer(factor(json_data$gender, levels = unique(data$gender)))
    json_data$ever_married <- as.integer(factor(json_data$ever_married, levels = unique(data$ever_married)))
    json_data$work_type <- as.integer(factor(json_data$work_type, levels = unique(data$work_type)))
    json_data$Residence_type <- as.integer(factor(json_data$Residence_type, levels = unique(data$Residence_type)))

    # 표준 스케일링
    json_data_scaled <- predict(preProcess_standard_model, newdata = json_data[, c("gender", "ever_married", "work_type", "Residence_type")])

    # 결과 병합
    json_data[c("gender", "ever_married", "work_type", "Residence_type")] <- json_data_scaled

    # 결과 반환
    return(json_data)
}

# JSON 입력 예시
json_input <- '{
    "age": 30,
    "avg_glucose_level": 100,
    "gender": "Male",
    "ever_married": "Yes",
    "work_type": "Private",
    "Residence_type": "Urban"
}'

# 전처리된 JSON 데이터 출력
preprocessed_json_data <- preprocess_json_input(json_input)
print(preprocessed_json_data)
