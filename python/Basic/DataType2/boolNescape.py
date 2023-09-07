#bool형: True(연산시 1), False(연산시 0) 두 값만 가진다(자바스크립트 동일)
#0, 0.0, 0+0j 는 False와 같고 None, ''(빈문자), 빈 객체([],(),{},set())는 조건식으로 판단시 False로 간주한다
print('[bool형]')
b1 = True
b2 = False
print('value:',b1,',type',type(b2))#value: True ,type <class 'bool'>
print(b1 + b2)#산술연산[O], 1
print(b1 > b2)#비교연산[O], True
print(b1 and b2)#논리연산[O], False(둘 다 True나와야 True)
print('[escape문자]')

#1] \n: 줄바꿈
#print는 자바 println처럼 줄바꿈 자동 지원
print('Welcome To Python')#Welcome To Python
print('Welcome \nTo Python')#Welcome 다음 줄에서 To Python
print('Let\'s Do it Go Fighting')#Let's Do it Go Fighting(어퍼스트로피 쓰려면 \' 써서 ' 탈출시킴)

#2] \r: 커서를 해당 줄에서 맨 처음으로(carrige return), 자바에서는 줄바꿈 동일기능이었음
print('My Nick is Superman\rXX')#XX, XX가 맨 앞으로 땡겨져서 My~ 다 지워짐, XX만 출력

#3] \t: 탭키만큼 띄어쓰기 기능
print('국어\t영어\t수학')#국어  영어  수학

#4] \': single quotation 표시
#문자열을 싱글쿼테이션으로 감싸도 되는 곳에서 유의미
#print('나의 닉은 '슈퍼맨' 입니다') 에러
print('나의 닉은 "슈퍼맨" 입니다')#나의 닉은 "슈퍼맨" 입니다
print('나의 닉은 \'슈퍼맨\' 입니다')#나의 닉은 '슈퍼맨' 입니다

#5] \": double quotation 표시
print("나의 닉은 \"슈퍼맨\" 입니다")#나읜 닉은 "슈퍼맨" 입니다

#6] \\: 역슬래시 표시
print('D:\\nDrive\\table')#D:\nDrive\table


