#정수형(int)는 소수점이 없는 숫자 의미 (ex. 100, -100 등)
def pprint(value): #pprint를 아래처럼 정의한것
    print('value:',value,sep='',end='')
    print('type:',type(value),sep='')

a=100
pprint(a)#value:100type:<class 'int'>

#파이썬3에서 정수/정수=실수(파이썬2에서는 =정수)
b=10/5
pprint(b)#value:2.0type:<class 'float'>

#int()함수: 실수를 정수로 변환하는 함수 - 소수점 이하를 버린다
c=int(b)
pprint(c)#value:2type:<class 'int'>
'''
※정수는 10진수 이외에도 2진수, 8진수, 16진수로 표현가능
2진수: 숫자 앞에 0b(B)를 붙이며 0과 1을 사용 - Binary
8진수: 숫자 앞에 0o(O)를 붙이며 0부터 7까지 사용 - Octal
16진수: 숫자 앞에 0x(X)를 붙이며 0부터 9, A부터 F까지 사용(소문자 a부터 f도 가능) - Hexa
'''
print('[각 진수로 숫자 표현하기]')
print('2진수:',0b10)#1*2^1 + 0*2^0 = 2+0
print('8진수:',0o10)#1*8^1 + 0*8^0 = 8+0
print('16진수:', 0x10)#1*16^1 + 0*16^0 = 16
d=111111111111111111111111111111111111111111111111111111111111111111111111111
print(d)#자바에서 int 21억 제한, 파이썬3는 제한 없음(파이썬2에서 long형 있었으나 int로 통합됨)

#실수형(float)는 소수점이 있는 숫자 의미(ex. 10.0, -3.14 등)
#실수와 정수 연산결과는 실수
a=10
b=3.5
pprint(a+b)#value:13.5type:<class 'float'>
a=0.1
b=0.2
print(a+b)#0.30000000000000004 자바처럼 부동소수점 에러 발생
print(a+b == 0.3)#false

#float()함수: 정수를 실수로 변환하는 함수
pprint(float(1+2))#value:3.0type:<class 'float'>
#float클래스의 float() 로 플로트 생성자를 쓰면 정수를 실수로 변환, 원래 3인데 3.0














