# -*- coding: utf-8 -*-
"""Untitled1.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1dP5AIwE9-onwoEAHqoDur-CZicJbDIUK
"""

# I | 필요한 라이브러리 불러오기
# 경고 메시지 무시
import warnings
warnings.filterwarnings('ignore')

# 판다스, 넘파이, 맷플롯립, 씨본 등 데이터 처리 및 시각화를 위한 라이브러리 불러오기
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

# OneHotEncoder와 3D 플로팅을 위한 라이브러리 불러오기
from sklearn.preprocessing import OneHotEncoder
from mpl_toolkits.mplot3d import Axes3D

# 데이터셋 로드하기
data = pd.read_csv('/content/drive/MyDrive/healthcare-dataset-stroke-data.csv')

# 출력
print(data.head)

# 1) 단변량 분석
# I | 목표 변수

# 모든 그래프의 스타일을 seaborn으로 설정
sns.set_style("whitegrid")

# 'stroke' 목표 변수를 그립니다.
plt.figure(figsize=(6, 6))
sns.countplot(x=data['stroke'])
plt.title('Distribution of Target Variable (Stroke)')
plt.show()

# II | 범주형 변수

# 범주형 변수의 목록
# 범주형 변수는 '성별', '고혈압', '심장 질환', '결혼 여부', '직업 유형', '거주 유형', '흡연 상태' 입니다.
categorical_variables = ['gender', 'hypertension', 'heart_disease', 'ever_married', 'work_type', 'Residence_type', 'smoking_status']

# 막대 그래프 그리기
fig, axs = plt.subplots(nrows=4, ncols=2, figsize=(15, 20))

# 각 변수와 서브플롯에 대해, 데이터에서 변수의 카운트 플롯을 그립니다. 또한 각 서브플롯의 x축 레이블을 회전시켜 가독성을 높입니다.
for var, subplot in zip(categorical_variables, axs.flatten()):
    sns.countplot(x=var, data=data, ax=subplot)
    for label in subplot.get_xticklabels():
        label.set_rotation(30)

plt.tight_layout()
plt.show()

# III | 연속형 변수

# 연속형 변수 목록
continuous_variables = ['age', 'avg_glucose_level', 'bmi']

# 연속형 변수에 대한 히스토그램 그리기
fig, axs = plt.subplots(1, 3, figsize=(20, 5))

for var, subplot in zip(continuous_variables, axs.flatten()):
    sns.histplot(data[var], kde=True, ax=subplot)

plt.tight_layout()
plt.show()

# 연속형 변수에 대한 박스플롯 그리기
fig, axs = plt.subplots(1, 3, figsize=(20, 5))

for var, subplot in zip(continuous_variables, axs.flatten()):
    sns.boxplot(data[var], ax=subplot)

plt.tight_layout()
plt.show()

# 2) 이변량 분석

# '뇌졸중'이라는 목표 변수에 대해 범주형 변수들을 그래프로 그려봅니다.
fig, axs = plt.subplots(3, 3, figsize=(20, 20))

for var, subplot in zip(categorical_variables, axs.flatten()):
    sns.countplot(x=data[var], hue=data['stroke'], ax=subplot)
    for label in subplot.get_xticklabels():
        label.set_rotation(90)

plt.tight_layout()
plt.show()

# '뇌졸중'이라는 목표 변수에 대해 연속형 변수들을 그래프로 그려봅니다.
fig, axs = plt.subplots(1, 3, figsize=(20, 5))

for var, subplot in zip(continuous_variables, axs.flatten()):
    sns.violinplot(x=data['stroke'], y=data[var], ax=subplot)

plt.tight_layout()
plt.show()

# 3) 다변량 분석

# 원본 데이터셋 다시 로드하기
data_original = pd.read_csv('/content/drive/MyDrive/healthcare-dataset-stroke-data.csv')
data_original = data_original.drop("id",axis=1)

# 범주형 열 선택하기
categorical_cols = ['gender', 'ever_married', 'work_type', 'Residence_type', 'smoking_status']

# 범주형 열에 원-핫 인코딩 적용하기
one_hot_encoder = OneHotEncoder(drop='first', sparse=False)
one_hot_encoded = one_hot_encoder.fit_transform(data_original[categorical_cols])

# 원-핫 인코딩된 배열을 데이터프레임으로 변환하기
one_hot_encoded_df = pd.DataFrame(one_hot_encoded, columns=one_hot_encoder.get_feature_names_out(categorical_cols))

# 원본 범주형 열을 데이터셋에서 삭제하기
data_original.drop(categorical_cols, axis=1, inplace=True)

# 원-핫 인코딩된 데이터프레임과 원본 데이터프레임 결합하기
data_one_hot_encoded = pd.concat([data_original, one_hot_encoded_df], axis=1)

# 원-핫 인코딩된 데이터에 대한 상관 행렬 계산하기
correlation_matrix_one_hot = data_one_hot_encoded.corr()

# 전체 상관 행렬에 대한 히트맵 그리기
plt.figure(figsize=(15, 10))
sns.heatmap(correlation_matrix_one_hot, annot=False, cmap='coolwarm', linewidths=0.5)
plt.title("Correlation Matrix Heatmap (One-Hot Encoded Data)")
plt.show()

# 각 특성과 목표 변수 'stroke'와의 상관관계 계산하기
target_corr_one_hot = correlation_matrix_one_hot['stroke'].drop('stroke')

# 상관 값들을 내림차순으로 정렬하기
target_corr_sorted_one_hot = target_corr_one_hot.sort_values(ascending=False)

# 목표 열과의 상관관계에 대한 히트맵 그리기
plt.figure(figsize=(5, 10))
sns.set(font_scale=0.8)
sns.set_style("white")
sns.set_palette("PuBuGn_d")
sns.heatmap(target_corr_sorted_one_hot.to_frame(), cmap="coolwarm", annot=True, fmt='.2f', cbar=False)
plt.title('Correlation with Stroke (One-Hot Encoded Data)')
plt.show()

# 'age'(연령)와 'work_type'(직업 유형)의 관계를 상자 그림(boxplot)으로 시각화합니다.
plt.figure(figsize=(10, 6))
sns.boxplot(x=data['work_type'], y=data['age'], hue=data['stroke'])
plt.title('Age vs Work Type')
plt.xticks(rotation=90)
plt.show()

# 'avg_glucose_level'(평균 혈당 수준)과 'smoking_status'(흡연 상태)의 관계를 상자 그림으로 시각화합니다.
plt.figure(figsize=(10, 6))
sns.boxplot(x=data['smoking_status'], y=data['avg_glucose_level'], hue=data['stroke'])
plt.title('Average Glucose Level vs Smoking Status')
plt.xticks(rotation=90)
plt.show()

# 'bmi'(체질량 지수)와 'gender'(성별)의 관계를 상자 그림으로 시각화합니다.
plt.figure(figsize=(10, 6))
sns.boxplot(x=data['gender'], y=data['bmi'], hue=data['stroke'])
plt.title('BMI vs Gender')
plt.xticks(rotation=90)
plt.show()

# 'age', 'avg_glucose_level', 'bmi', 'stroke' 변수를 포함하는 데이터의 부분집합(subset)을 생성합니다
subset = data[['age', 'avg_glucose_level', 'bmi', 'stroke']]

# 'stroke' 변수를 범주형 변수(category)로 변환합니다. 이것은 pairplot 생성에 필요한 전처리 단계입니다.
subset['stroke'] = subset['stroke'].astype('category')

# 생성된 부분집합을 바탕으로 pairplot을 생성합니다.
sns.pairplot(subset, hue='stroke', plot_kws={'alpha': 0.5})
plt.show()

# 'stroke' 변수에 대한 색상 맵을 생성합니다.
colors = data['stroke'].map({0:'blue', 1:'red'})

fig = plt.figure(figsize=(10, 8))
ax = fig.add_subplot(111, projection='3d')

# 3D 산점도를 생성합니다.
ax.scatter(data['age'], data['avg_glucose_level'], data['bmi'], c=colors, alpha=0.6, edgecolors='w')

ax.set_xlabel('Age')
ax.set_ylabel('Average Glucose Level')
ax.set_zlabel('BMI')
plt.show()

# EDA 결과 & 토의

# 결과:
# 타겟 변수인 중품증은 클래스 0 (뇌졸중 없음)이 클래스 1 (뇌졸중 있음)보다 월등히 많아 매우 불균형한 분포를 보였습니다. 이는 기계 학습 모델의 선택과 평가 지표에 영향을 미칠 중요한 관찰 결과입니다. ❗

# 성별, 고혈압, 심장 질환, 결혼 여부, 직업 유형, 거주 유형, 흡연 상태 등의 범주형 변수는 다양한 분포를 보였습니다. 특히, 고혈압과 심장 질환은 뇌졸중 환자들 사이에서 더 자주 발견되었습니다. 👥🩺❤️

# 연령, 평균 글루코스 레벨, BMI와 같은 연속 변수들은 각각 다른 분포를 보였습니다. 뇌졸중 환자들에서는 나이와 평균 글루코스 레벨이 더 높았지만, 뇌졸중 환자와 비뇌졸중 환자 사이에서 BMI에는 큰 차이가 없었습니다. 📊🔍

# 이변량 및 다변량 분석 결과, 뇌졸중 환자들이 보통 나이가 많고 글루코스 레벨이 높은 경향이 있으므로, 나이와 평균 글루코스 레벨이 뇌졸중의 강력한 예측 변수가 될 수 있음을 보여주었습니다. 👴🩸

# 또한 이러한 분석을 통해 BMI는 뇌졸중의 강력한 예측 변수가 아닐 수 있음을 시사하였습니다. 뇌졸중 환자와 비뇌졸중 환자 사이에서 BMI의 분포는 비슷하였습니다. ⚖️

# 고급 시각화 (FacetGrid 및 평행 좌표 플롯)에서는 나이 많은 환자들, 특히 자영업자 또는 민간 기업에 종사하는 사람들이 뇌졸중을 더 자주 겪는 것으로 관찰되었습니다. 또한, 뇌졸중 환자들은 일반적으로 그들의 직업 유형과 성별에 관계없이 더 높은 글루코스 레벨을 가지고 있었습니다. 📊👥👨‍💼👩‍💼👴🩸

# 토론:
# 탐색적 데이터 분석(EDA)은 뇌졸중과 관련된 요인에 대한 귀중한 통찰을 제공했습니다. 나이, 고혈압, 심장 질환, 평균 글루코스 레벨이 중요한 요인으로 보이며, 반면 BMI는 중요한 예측 변수가 아닐 수 있습니다. 이 정보는 특성 선택과 모델링 과정을 안내할 수 있습니다. 그러나, 타겟 변수의 불균형은 예측 모델을 만드는 데 어려움을 줄 수 있습니다. 이 불균형을 처리하기 위해 소수 클래스의 오버샘플링, 다수 클래스의 언더샘플링 또는 둘 다를 사용하는 SMOTE와 같은 기법이 필요할 수 있습니다. 📈🔍

# 또한, EDA가 몇 가지 귀중한 통찰을 제공했지만, 상관 관계가 인과 관계를 의미하지는 않습니다. 이러한 요인들과 뇌졸중 간의 인과 관계를 결정하기 위해 보다 철저한 통계 분석 또는 실험 연구가 필요할 수 있습니다. 📚🔬

# 마지막으로, EDA는 사용 가능한 데이터와 변수에 한정되었습니다. 이 데이터 세트에 포함되지 않은 뇌졸중과 관련된 다른 중요한 요인들, 예를 들어 뇌졸중의 가족력, 식단, 신체 활동, 알코올 섭취, 스트레스 수준 등이 있을 수 있습니다. 이러한 요인들을 포함하면 보다 종합적인 분석이 가능해질 것입니다. 📊🔬📝

# 데이터 정제
# 데이터 클렌징 과정은 다음 단계를 포함해야 합니다:
# 결측치 확인 및 처리: 'bmi'와 같은 열에는 채워야 하거나 행을 제거해야 하는 결측치가 있을 수 있습니다. 이는 상황에 따라 달라집니다. ❗

# 중복값 확인 및 처리: 데이터셋에 중복된 행이 없는지 확인해야 합니다. 중복이 있다면, 데이터의 특성과 연구 질문에 따라 하나를 유지하거나 모든 중복을 유지하거나 중복의 평균을 계산해야 할 수 있습니다. 🔍

# 이상치 확인 및 처리: 'avg_glucose_level'과 'bmi'와 같은 몇몇 열은 수치형이며 분석을 왜곡할 수 있는 이상치를 포함하고 있을 수 있습니다. 이를 제거하거나 그 영향을 줄이기 위해 변환을 적용하는 등 이상치를 처리하는 방법을 결정해야 합니다. 📈

# 범주형 변수를 적절한 데이터 유형으로 변환: 'gender', 'ever_married', 'work_type', 'Residence_type', 'smoking_status' 같은 열은 범주형입니다. 분석에 따라 이를 더미 변수로 변환해야 할 수 있습니다. 📊📝

# 잘못된 값 확인 및 처리: 예를 들어, 'age'는 음수가 아니어야 하며, 'hypertension'과 'heart_disease'는 0 또는 1이어야 하며, 'gender'는 'Male' 또는 'Female'이어야 합니다. ⚠️

# 결측치

# 데이터 로드
data = pd.read_csv('/content/drive/MyDrive/healthcare-dataset-stroke-data.csv')

# 결측치 확인
data.isnull().sum()

from sklearn.neighbors import KNeighborsRegressor

def knn_impute(df, na_target):
    df = df.copy()

    numeric_df = df.select_dtypes(include=[np.number])
    non_na_columns = numeric_df.loc[: ,numeric_df.isna().sum() == 0].columns

    y_train = numeric_df.loc[numeric_df[na_target].isna() == False, na_target]
    X_train = numeric_df.loc[numeric_df[na_target].isna() == False, non_na_columns]
    X_test = numeric_df.loc[numeric_df[na_target].isna() == True, non_na_columns]

    knn = KNeighborsRegressor()
    knn.fit(X_train, y_train)

    y_pred = knn.predict(X_test)

    df.loc[df[na_target].isna() == True, na_target] = y_pred

    return df

na_cols = [col for col in data.columns if data[col].isnull().sum()!=0]

for col in na_cols:
    data = knn_impute(data, col)

# 결측치를 다시 확인하여 검증합니다
data.isnull().sum()

# ID 열 제거
data = data.drop('id',axis=1)

# 중복 행 확인
duplicate_rows = data.duplicated()

# 중복 행 수 계산
num_duplicate_rows = duplicate_rows.sum()

num_duplicate_rows
# 0

# IQR 방법을 사용하여 이상치 수를 계산하는 함수 정의
def count_outliers(column):
    Q1 = column.quantile(0.25)
    Q3 = column.quantile(0.75)
    IQR = Q3 - Q1

    lower_bound = Q1 - 1.5 * IQR
    upper_bound = Q3 + 1.5 * IQR

    return ((column < lower_bound) | (column > upper_bound)).sum()

# 'age', 'avg_glucose_level', 'bmi'에서 이상치 확인
outliers = {column: count_outliers(data[column]) for column in ['age', 'avg_glucose_level', 'bmi']}

outliers
# {'age': 0, 'avg_glucose_level': 627, 'bmi': 117}

# 'avg_glucose_level'과 'bmi'에서 0 또는 음수 값 확인
zero_or_negative_values = {column: (data[column] <= 0).sum() for column in ['avg_glucose_level', 'bmi']}

zero_or_negative_values
# {'avg_glucose_level': 0, 'bmi': 0}

# 'avg_glucose_level'과 'bmi' 열에는 0 또는 음수 값이 없으므로 로그 변환을 안전하게 적용할 수 있습니다.

# 이러한 열에 로그 변환을 적용하고 다시 이상치를 확인해봅시다.

# 로그 변환

# 로그 변환 적용
data['avg_glucose_level'] = np.log(data['avg_glucose_level'])
data['bmi'] = np.log(data['bmi'])

# 변환 후 'avg_glucose_level'과 'bmi'에서 이상치 확인
outliers_transformed = {column: count_outliers(data[column]) for column in ['avg_glucose_level', 'bmi']}

print(outliers_transformed)

# 연속 변수 목록
continuous_variables = ['avg_glucose_level', 'bmi']

# 연속 변수의 히스토그램 플롯
fig, axs = plt.subplots(1, 2, figsize=(20, 5))

for var, subplot in zip(continuous_variables, axs.flatten()):
    sns.histplot(data[var], kde=True, ax=subplot)

plt.tight_layout()
plt.show()

# 연속 변수의 박스플롯 플롯
fig, axs = plt.subplots(1, 2, figsize=(20, 5))

for var, subplot in zip(continuous_variables, axs.flatten()):
    sns.boxplot(data[var], ax=subplot)

plt.tight_layout()
plt.show()
# {'avg_glucose_level': 380, 'bmi': 73}

# 범주형 열에서 유일한 값을 확인
unique_values_categorical = {column: data[column].unique() for column in ['gender', 'ever_married', 'work_type', 'Residence_type', 'smoking_status']}

# '성별'에서 '기타' 인스턴스와 '흡연상태'에서 '알 수 없음' 인스턴스 수를 세기
other_gender_count = (data['gender'] == 'Other').sum()
unknown_smoking_status_count = (data['smoking_status'] == 'Unknown').sum()

other_gender_count, unknown_smoking_status_count
# (1, 1544)

# '성별'에서 '기타'에 해당하는 행을 삭제
data = data[data['gender'] != 'Other']

# 범주형 변수를 원-핫 인코딩을 사용하여 숫자 형식으로 변환
data_encoded = pd.get_dummies(data, columns=['gender', 'ever_married', 'work_type', 'Residence_type', 'smoking_status'])

# '고혈압', '심장질환', '뇌졸중'에서 유일한 값을 확인
unique_values_binary = {column: data[column].unique() for column in ['hypertension', 'heart_disease', 'stroke']}

unique_values_binary
# {'hypertension': array([0, 1]),
# 'heart_disease': array([1, 0]),
# 'stroke': array([1, 0])}

# 데이터셋 로드
df = pd.read_csv('/content/drive/MyDrive/healthcare-dataset-stroke-data.csv')

# 이전에 수행한 데이터 클렌징 과정 적용
df['bmi'].fillna(df['bmi'].median(), inplace=True)

# 범주형 열
categorical_columns = ['gender', 'work_type', 'Residence_type', 'smoking_status']

# 'gender' 열에서 가장 빈번한 카테고리 찾기
most_frequent_gender = df['gender'].value_counts().idxmax()

# 'Other'를 가장 빈번한 카테고리와 합치기
df['gender'] = df['gender'].replace('Other', most_frequent_gender)

# 로그 변환 적용
df['avg_glucose_level'] = np.log(df['avg_glucose_level'])
df['bmi'] = np.log(df['bmi'])

# 값 매핑하고 'ever_married' 열에 적용
df['ever_married'] = df['ever_married'].map({'No': 0, 'Yes': 1})

# 범주형 변수에 원-핫 인코딩 수행
df_encoded = pd.get_dummies(df, columns=categorical_columns)

# 'id' 열 삭제
df_encoded = df_encoded.drop(columns='id')

# 특성 중요도
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier

# 피처 행렬 X와 타겟 y 정의
X = df_encoded.drop(columns='stroke')
y = df_encoded['stroke']

# 데이터를 훈련용과 테스트용으로 분리
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# 랜덤 포레스트 분류기 생성
rf = RandomForestClassifier(n_estimators=100, random_state=42)

# 모델을 훈련 데이터에 적합시키기
rf.fit(X_train, y_train)

# 피처 중요도 얻기
feature_importances = rf.feature_importances_

# 시각화를 위한 데이터프레임 생성
importances_df = pd.DataFrame({
'Feature': X.columns,
'Importance': feature_importances
})

#중요도를 내림차순으로 정렬
importances_df = importances_df.sort_values(by='Importance', ascending=False)

# 피처 중요도 플로팅
plt.figure(figsize=(10, 8))
sns.barplot(data=importances_df, x='Importance', y='Feature', color='b')
plt.title('Feature Importance from Random Forest')
plt.xlabel('Importance')
plt.ylabel('Feature')
plt.show()

# BMI를 분류하는 함수 정의
def categorize_bmi(bmi):
    if bmi < 18.5:
        return '체중 부족'
    elif 18.5 <= bmi < 25:
        return '정상 체중'
    elif 25 <= bmi < 30:
        return '과체중'
    else:
        return '비만'

# 'bmi' 열에 함수를 적용하여 새로운 'bmi_category' 열 생성
df_encoded['bmi_category'] = df_encoded['bmi'].apply(categorize_bmi)

# 나이를 분류하는 함수 정의
def categorize_age(age):
    if age < 18:
        return '아동'
    elif 18 <= age < 65:
        return '성인'
    else:
        return '노인'

# 'age' 열에 함수를 적용하여 새로운 'age_category' 열 생성
df_encoded['age_category'] = df_encoded['age'].apply(categorize_age)

# 건강 위험 점수를 계산하는 함수 정의
def compute_health_risk_score(row):
    score = 0
    # 개인이 가진 각 위험 요인마다 점수에 1을 더함
    if row['age_category'] == '노인':
        score += 1
    if row['hypertension'] == 1:
        score += 1
    if row['heart_disease'] == 1:
        score += 1
    if row['bmi_category'] in ['과체중', '비만']:
        score += 1
    if row['smoking_status_smokes'] == 1:
        score += 1
    return score

# 함수를 전체 DataFrame에 적용하여 새로운 'health_risk_score' 열 생성
df_encoded['health_risk_score'] = df_encoded.apply(compute_health_risk_score, axis=1)

# 업데이트된 DataFrame의 처음 몇 행을 표시
df_encoded.head()
# age hypertension heart_disease ever_married avg_glucose_level bmi stroke gender_Female gender_Male work_type_Govt_job ... work_type_children Residence_type_Rural Residence_type_Urban smoking_status_Unknown smoking_status_formerly smoked smoking_status_never smoked smoking_status_smokes bmi_category age_category health_risk_score

# 새로운 범주형 변수에 대해 원-핫 인코딩 수행
df_encoded = pd.get_dummies(df_encoded, columns=['bmi_category', 'age_category'])

# 업데이트된 DataFrame의 처음 몇 행을 표시
df_encoded.head()
# age hypertension heart_disease ever_married avg_glucose_level bmi stroke gender_Female gender_Male work_type_Govt_job ... Residence_type_Urban smoking_status_Unknown smoking_status_formerly smoked smoking_status_never smoked smoking_status_smokes health_risk_score bmi_category_체중 부족 age_category_성인 age_category_아동 age_category_노인

# 비용-민감 학습
# 요약

# 필요한 라이브러리 불러오기
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
import matplotlib.pyplot as plt
import seaborn as sns

# 데이터셋 불러오기
df = pd.read_csv('/content/drive/MyDrive/healthcare-dataset-stroke-data.csv')

##-------데이터 정제-------##
na_cols = [col for col in df.columns if df[col].isnull().sum()!=0]

for col in na_cols:
    df = knn_impute(df, col)

# 'bmi' 열의 결측값을 중앙값으로 채우기
df['bmi'].fillna(df['bmi'].median(), inplace=True)

# 'gender' 열에서 'Other'를 가장 빈도가 높은 카테고리로 합치기
most_frequent_gender = df['gender'].value_counts().idxmax()
df['gender'] = df['gender'].replace('Other', most_frequent_gender)

##-------데이터 준비-------##

# 'ever_married' 열의 값을 매핑하고 바꾸기
df['ever_married'] = df['ever_married'].map({'No': 0, 'Yes': 1})

# 범주형 변수에 원-핫 인코딩 수행
categorical_columns = ['gender', 'work_type', 'Residence_type', 'smoking_status']
df_encoded = pd.get_dummies(df, columns=categorical_columns)

# 'id' 열 삭제
df_encoded = df_encoded.drop(columns='id')

#-------특성 엔지니어링------#

# 특성 엔지니어링을 위한 함수 정의
def categorize_bmi(bmi):
    if bmi < 18.5:
        return 'Underweight'
    elif 18.5 <= bmi < 25:
        return 'Normal weight'
    elif 25 <= bmi < 30:
        return 'Overweight'
    else:
        return 'Obese'

def categorize_age(age):
    if age < 18:
        return 'Child'
    elif 18 <= age < 65:
        return 'Adult'
    else:
        return 'Senior'

def compute_health_risk_score(row):
    score = 0
    if row['age_category'] == 'Senior':
        score += 1
    if row['hypertension'] == 1:
        score += 1
    if row['heart_disease'] == 1:
        score += 1
    if row['bmi_category'] in ['Overweight', 'Obese']:
        score += 1
    if row['smoking_status_smokes'] == 1:
        score += 1
    return score

# 함수를 적용하여 새로운 특성 생성
df_encoded['bmi_category'] = df_encoded['bmi'].apply(categorize_bmi)
df_encoded['age_category'] = df_encoded['age'].apply(categorize_age)
df_encoded['health_risk_score'] = df_encoded.apply(compute_health_risk_score, axis=1)

# 새로운 범주형 변수에 원-핫 인코딩 수행
df_encoded = pd.get_dummies(df_encoded, columns=['bmi_category', 'age_category'])

# 타겟 변수에서 클래스 분포 확인
df_encoded['stroke'].value_counts()
# 0 4861
# 1 249
# Name: stroke, dtype: int64

# 예측하기
import warnings
warnings.filterwarnings('ignore')

from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from imblearn.over_sampling import SMOTE
from imblearn.under_sampling import TomekLinks
from imblearn.pipeline import Pipeline
from sklearn.metrics import classification_report, confusion_matrix, roc_curve, auc, precision_recall_curve
from xgboost import XGBClassifier
from sklearn.model_selection import GridSearchCV
from sklearn.metrics import make_scorer, roc_auc_score
from sklearn.metrics import confusion_matrix, ConfusionMatrixDisplay
from sklearn.model_selection import learning_curve

# 특성과 타겟 정의
X = df_encoded.drop('stroke', axis=1)
y = df_encoded['stroke']

# 데이터를 훈련 세트와 테스트 세트로 분할
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# 파이프라인 정의

# 파이프라인 정의
resampling = SMOTE(sampling_strategy='minority') # 재샘플링 전략을 'minority'로 설정하여 소수 클래스 재샘플링
tomek = TomekLinks(sampling_strategy='majority') # 샘플링 전략을 'majority'로 설정하여 다수 클래스 언더샘플링
scaler = StandardScaler()
model = XGBClassifier(scale_pos_weight=sum(y==0)/sum(y==1), # 클래스 불균형 때문에 클래스 가중치 조정
eval_metric='logloss', # 성능 평가를 위해 logloss 사용
use_label_encoder=False) # 경고 메시지를 피하기 위해
pipeline = Pipeline([('StandardScaler', scaler), ('SMOTE', resampling), ('TomekLinks', tomek), ('Model', model)])

# 최적의 하이퍼파라미터로 모델 정의
model = XGBClassifier(
    colsample_bytree=0.7,
    gamma=0.2,
    learning_rate=0.01,
    max_depth=7,
    min_child_weight=5,
    n_estimators=100,
    subsample=0.5,
    scale_pos_weight=sum(y_train==0)/sum(y_train==1), # 클래스 불균형 때문에 클래스 가중치 조정
    eval_metric='logloss', # 성능 평가를 위해 logloss 사용
    use_label_encoder=False # 경고 메시지를 피하기 위해
)

# 모델 훈련
model.fit(X_train, y_train)

# 테스트 세트에서 예측하기
y_pred = model.predict(X_test)
y_score = model.predict_proba(X_test)[:,1]

# 분류 보고서

# 분류 보고서 출력
print(classification_report(y_test, y_pred))
# precision recall f1-score support

# 모델 적용하기

# 테스트 세트에서 예측하기
y_pred = model.predict(X_test)

# 혼동 행렬 계산
cm = confusion_matrix(y_test, y_pred, labels=model.classes_)

# ConfusionMatrixDisplay 객체 생성
disp = ConfusionMatrixDisplay(confusion_matrix=cm, display_labels=model.classes_)

# 혼동 행렬 플롯
disp.plot()
plt.show()

# 모델 적용하기

# ROC 곡선과 ROC 영역 계산
fpr_optimized, tpr_optimized, _ = roc_curve(y_test, y_pred)
roc_auc_optimized = auc(fpr_optimized, tpr_optimized)

# ROC 곡선 그리기
plt.figure()
plt.plot(fpr_optimized, tpr_optimized, color='darkorange', lw=2, label='ROC curve (area = %0.2f)' % roc_auc_optimized)
plt.plot([0, 1], [0, 1], color='navy', lw=2, linestyle='--')
plt.xlim([0.0, 1.0])
plt.ylim([0.0, 1.05])
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.title('Receiver Operating Characteristic')
plt.legend(loc="lower right")
plt.show()

# 모델 적용하기

# 정밀도-재현율 곡선 그리기
precision, recall, _ = precision_recall_curve(y_test, y_score)
plt.figure()
plt.plot(recall, precision, label='Precision-Recall curve')
plt.xlabel('Recall')
plt.ylabel('Precision')
plt.title('Precision-Recall curve')
plt.legend(loc="lower left")

plt.show()

# 학습 곡선

# 학습 곡선 그리기
train_sizes, train_scores, valid_scores = learning_curve(model, X, y, train_sizes=np.linspace(0.1, 1.0, 5), cv=5)
plt.figure()
plt.plot(train_sizes, train_scores.mean(axis=1), label='Training score')
plt.plot(train_sizes, valid_scores.mean(axis=1), label='Cross-validation score')
plt.xlabel('Training set size')
plt.ylabel('Score')
plt.title('Learning curve')
plt.legend(loc="lower right")
plt.show()

# 모델 저장

# XGBoost의 save_model() 메소드를 사용하여 학습된 모델을 저장합니다.
model.save_model('model.xgb')

# !pip install sklearn2pmml sklearn-pandas

from sklearn2pmml import sklearn2pmml, make_pmml_pipeline
from sklearn_pandas import DataFrameMapper

mapper = DataFrameMapper([(col, None) for col in X_train.columns])
pipeline = make_pmml_pipeline(mapper, model)
sklearn2pmml(pipeline, 'XGBoost_model.pmml', with_repr=True)