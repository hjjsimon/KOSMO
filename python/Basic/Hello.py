#coding:utf-8 파이썬 3.부터 한글세팅 무의미
print('Hello World')
print('안녕 파이썬')
#
#대소문자 엄격 구분
#자료형 선언이 없다
a=None#a만 쓸수는 없음 None써줘야함
print(a)
a=10
print(a)
#한 라인에 여러개 명령어 작성시 마지막 명령어 제외 ; 필수
b=20;c=a+b;print(c)
#파이썬 문자열은 '', "" 내맘
#result = '더하기 결과:'+c
#위코드 에러, c는 int라 str로 바꿔줘야함
result = '더하기 결과:' + str(c)
print(result)
#파이썬은 들여쓰기가 문법
    #주석은 무관
    #print(result) 이건 들여쓰기 에러
for i in range(5): #for블락 시작을 의미하는 콜론, 엔터시 아래 내려보면 자동으로 들여쓰기 됨
#print(i) 들여쓰기 에러
    print(i)#0~4 출력
if b % 2 == 0: #if블락 시작 콜론
    print(str(b)+'는 짝수')
else: #else블락 시작 콜론
    print(str(b)+'는 홀수')











