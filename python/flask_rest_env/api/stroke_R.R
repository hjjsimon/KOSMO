library(dplyr)
library(tidyr)
library(caret)
library(jsonlite)

# 데이터셋 불러오기
data_path <- "healthcare-dataset-stroke-data.csv"
data <- read.csv(data_path)

# 전처리 객체들 초기화 함수
initialize_preprocessing_objects <- function() {
  
  # 라벨 인코더 학습
  data$gender <- as.factor(data$gender)
  data$ever_married <- as.factor(data$ever_married)
  data$work_type <- as.factor(data$work_type)
  data$Residence_type <- as.factor(data$Residence_type)
  
  # 스케일러 학습
  preProcValues <- preProcess(data[, c("age", "avg_glucose_level")], method = c("center", "scale"))
  
  return(preProcValues)
}

preProcValues <- initialize_preprocessing_objects()

# 입력 데이터 전처리 함수
preprocess_input_data <- function(input_data) {
  
  # 입력 데이터를 데이터 프레임으로 변환
  input_df <- fromJSON(input_data, flatten = TRUE) %>% as.data.frame()
  
  # 라벨 인코딩
  input_df$gender <- as.factor(input_df$gender)
  input_df$ever_married <- as.factor(input_df$ever_married)
  input_df$work_type <- as.factor(input_df$work_type)
  input_df$Residence_type <- as.factor(input_df$Residence_type)
  
  # 스케일링
  scaled_df <- predict(preProcValues, input_df)
  
  return(scaled_df)
}

# 예제 입력 데이터
input_data <- '{
    "age": 67,
    "avg_glucose_level": 228.69,
    "gender": "Male",
    "ever_married": "Yes",
    "work_type": "Private",
    "Residence_type": "Urban"
}'

preprocessed_data <- preprocess_input_data(input_data)
print(preprocessed_data)

