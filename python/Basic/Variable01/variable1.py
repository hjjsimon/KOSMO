#변수 선언 방법1] 변수명 = 데이터
a=10
print(a)
print(type(a))#type()타입 알려줌, <class 'int'>, 사실 int가 클래스, 파이썬 3는 다 클래스 처리함
#type(변수 혹은 값): 자료형 반환
print(a,type(a),sep=',')#값과 타입을 한줄로 출력 -> 10,<class 'int'> -> , 콤마로 세퍼레이트

#변수 선언 방법2] 변수명1, 변수명2, 변수명3 .. = 반복가능한 객체(이터레이터)
#구조분해라고 함(destructure), 객체에 있는 요소를 다 분해해서 왼쪽의 변수에 하나하나 담음
#이터레이터의 요소를 여러 변수에 나눠 담는 것을 언패킹(unpacking)또는 구조분해(destructuring)라고 한다
#변수명1, 변수명2, 변수명3 = 데이터1, 데이터2, 데이터3 혹은 (데이터1, 데이터2, 데이터3)
a,b,c = "파이썬",'10',20#("파이썬",'10',20) 감싸도 됨, 각 변수에 값을 할당
print('a의 값:%s, a의 자료형:%s' % (a,type(a)))#a의 값:파이썬, a의 자료형:<class 'str'>
print('b의 값:%s, b의 자료형:%s' % (b,type(b)))#b의 값:10, b의 자료형:<class 'str'> '' 해서 문자열 됨
print('c의 값:%s, c의 자료형:%s' % (c,type(c)))#c의 값:20, c의 자료형:<class 'int'> '' 없어서 그냥 숫자

#변수 선언 방법3] 변수명 = 반복 가능한 객체(이터레이터)
#여러개의 데이터를 이터레이터를 이용해 하나의 변수에 묶어 담는 것을 패킹(packing)
#변수명 = 데이터1, 데이터2, 데이터3 혹은 (데이터1, 데이터2, 데이터3)
d=10,'20','파이썬'#값이 3개인데 변수 1개에 담긴다? 말이 안됨, 근데 됨, 패킹 이라고 함
#int,str,str객체를 튜플로 묶어서(패킹) 변수 d에 저장
print('d의 값:%s, d의 자료형:%s' % (d,type(d)))
#d의 값:(10, '20', '파이썬'), d의 자료형:<class 'tuple'>

#변수 선언 방법4] 변수명1 = None
z = None #변수 선언만 하고 값은 나중에 넣고싶음, 자바면 null 넣는 것과 동일
print('z의 값:%s, z의 자료형:%s' % (z,type(z)))
#z의 값:None, z의 자료형:<class 'NoneType'> None도 NoneType이라는 클래스
x,y=10,20
print('x=',x,',y=',y) #x= 10 ,y= 20
y,x = x,y #자바였으면 임시변수 temp에 담았다가 바꿈, 근데 이건 그대로 x,y바꾸기 가능
print('x=',x,',y=',y) #x= 20 ,y= 10

#변수명은 키워드 불가, 숫자시작 불가
import keyword
print(keyword.kwlist)
# 키워드들 출력됨
#['False', 'None', 'True', 'and', 'as', 'assert', 'async', 'await',
# 'break', 'class', 'continue', 'def', 'del', 'elif', 'else', 'except',
# 'finally', 'for', 'from', 'global', 'if', 'import', 'in', 'is', 'lambda',
# 'nonlocal', 'not', 'or', 'pass', 'raise', 'return', 'try', 'while', 'with', 'yield']

#변수 삭제
del x# 혹은 del(x), 연산자인데 함수처럼 써도 됨, 메모리에서 x삭제
#print(x)# 변수x는 정의 안됐다고 에러

'''
변수=값
s=f'{변수 혹은 표현식}'

f는 format-string(포맷 문자열)을 의미
파이썬 3.6부터 지원( .format과 동일기능이고 더 편리)
문자열 안에  변수 값을 삽입하기 위해 사용.
중괄호안에 변수나 표현식을 넣을수 있다.
'''
name = '가길동'
age = 20
s = f'이름은 {name}이고 {name}의 10년 후 나이는 {age + 10}살 입니다'
print(s)










