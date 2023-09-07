import sys,math
from decimal import Decimal
#sys, math 모듈 임포트
'''
파이썬은 부동소수점 방식으로 실수를 표현
고정소수점 방식보다 넓은 범위의 수를 나타낼 수 있어
과학기술 계산에 많이 쓰이지만
고정소수점 방식보다 연산속도가 느리고, 근사값으로 표현된다
ex1) 고정소수점 방식: 0.1 + 0.2 = 0.3
ex2) 부동소수점 방식: 0.1 + 0.2 = 0.300000004
'''
a=0.1
b=0.2
'''
실수를 근사값으로 표현하면서 발생하는 오차를 부동소수점 반올림 오차라고 한다
따라서 실수를 비교시 연산값과 비교할 값의 차이를 구한 뒤 epsilon과 비교한다
아주 작은 값인 sys.float_info.epsilon 보다 작거나 같으면 매우 작은 차이이므로 무시가 가능하다 
'''
print(a+b==0.3)
#부동 소수점 반올림 오차 해결방법1
'''
머신 입실론(sys.float_info.epsilon)값(10^-16의미) = 2.220446049250919e-16
연산한 값(a+b)와 비교할 값(0.3) 차이가 머신 입실론보다 작거나 같으면 두 값은 같다고 본다
'''
print(sys.float_info.epsilon)
print(math.fabs(a+b - 0.3) <= sys.float_info.epsilon)#true, 차이값은 머신 입실론보다 작거나 같은 값임

#부동 소수점 반올림 오차 해결방법2(권장)
#위 방법은 귀찮으므로 파이썬 3.5이상부터는 두 실수가 같은지 판단시 math.isclose함수를 사용한다
print(math.isclose(a+b,0.3))#true

#부동 소수점 반올림 오차 해결방법3
#decimal 모듈(클래스)의 Decimal로 고정소수점으로 변환
#고정소수점은 부동소수점처럼 반올림오차가 없다
print(Decimal('0.1')+Decimal('0.2') == Decimal('0.3'))#true





