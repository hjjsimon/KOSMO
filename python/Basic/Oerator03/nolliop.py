# - 논리연산자(이항연산자)의 결과값은 True, False 이다
# and: and연산자(논리곱) 두 항이 참일때만 참(~그리고)
# or: or연산자(논리합) 두 항 중 하나라도 참이면 참(~또는)
# not(단항연산자): not연산자(논리부정) 단항이 참이면 거짓, 거짓이면 참(~아니다)
#
# - 논리연산자의 우선순위(단항>이항): not > and > or
# ※ 단락평가(short-circuit evaluation)
#     단락 평가는 첫번째 항만으로 결과가 확실할때 두번째항은 계산하지 않는 방법
#     and는 첫째항이 거짓이면 두번째항은 계산 안한다
#     or는 첫째항이 참이면 두번째항을 계산 안한다
#
# - 산술 > 비교 > 논리연산자순으로 우선순위 적용

print('[논리 연산자]')
num1,num2 = 15,10
b = num1 > num2 and num1 == num2
print('{} > {} and {} == {}의 결과: {}'.format(num1,num2,num1,num2,b)) #true니까 and 뒤에도 계산함
#15 > 10 and 15 == 10의 결과: False
b = True or False
print('{} or {}의 결과:{}'.format(True,False,b)) #True or False의 결과:True

print('[연산자 우선순위]')
b= 5 *2 % 3 > 6-2*2 and 10 <=6 *2 or 5 * 3 !=10
#1] 52 : 10
#2] 1]의 결과 % 3 = 10 % 3 :1
#3] 22 :4
#4] 62 :12
#5] 53 :15
#중간정리] 1 > 6-4 and 10 <=12 or 15 != 10
#6] 6-4 : 2
#7] 1 > 6의 결과] = 1 > 2 : False
#중간정리]False and 10 <=12 or 15 !=10
#8] 10 <=12 : True
#9] 15 != 10: True
#최종] False and True or True
#10] False and True : False
#11] False or True : True
print('5 *2 %% 3 > 6-2*2 and 10 <=6 *2 or 5 * 3 !=10의 결과:',b,sep='')

# ※파이썬에는 xor만 쓰는 xor연산자가 따로 없다 xor연산을 하려면 ^를 쓴다
# 방법1] bool(값) ^ bool(값)
print('[파이썬의 XOR연산 첫번째]')
print( 3 > 2 ^ 3 > 1) #둘 다 True로 같으니까 False
print( 3 < 2 ^ 3 > 1)
print( bool(1) ^ bool(0)) #True, False로 다르니까 True

# 방법2] operator모듈의 xor함수 사용
print('[파이썬의 xor연산 두번째]')
import operator#파이썬 처음 설치할때 설치된 모듈operator, .py를 제공, .py 스크립트 안의 함수 변수 등을 사용가능
print(operator.__file__) #모듈 경로 출력
#C:\Users\kosmo\AppData\Local\Programs\Python\Python311\Lib\operator.py
#여기 가면 변수, 함수 볼 수 있음
print(operator.__doc__) #주석 출력
# This module exports a set of functions implemented in C corresponding
# to the intrinsic operators of Python.  For example, operator.add(x, y)
# is equivalent to the expression x+y.  The function names are those
# used for special methods; variants without leading and trailing
# '__' are also provided for convenience.
print(operator.lt(10,2))# 10<2 ? False나옴
print(dir(operator)) #모듈에 포함된 API List 형태로 [변수들] 출력
print(operator.xor(3>2,3>1))#False
print(operator.xor(bool(1),bool(0)))



















