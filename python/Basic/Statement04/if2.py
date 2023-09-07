num1 = int(input('숫자 입력?'))
print('[if문 형식 첫번째로 짝/홀수 판단]') #if문 연산 2회 시행
if num1 % 2 ==0:
    print('짝수')
if num1 % 2 !=0:
    print('홀수')
print('[if문 형식 두번째로 짝/홀수 판단]') #if문 연산 1회만 시행
if num1 % 2 ==0:
    print('짝수')
else:
    print('홀수')

#[조건부 표현식 - 다른 언어의 삼항연산자와 동일]
'''
if ~else문과 동일하지만 더 짧다
구문] 변수 = 값 if 조건문 else 값
-> 조건문이 참이면 if 앞의 값이 변수에 대입
-> 조건문이 거짓이면 else 뒤의 값이 변수에 대입
'''
print('[조건부 표현식(삼항연산자)로 짝/홀수 판단]')
print('짝수' if num1 % 2 == 0 else '홀수')
if num1 % 2 == 0:
    print('짝수')
pass
'''
아래 else는 짝을 이루는 if문이 없음
else:
    print('홀수')
'''
# 문] 한 문자를 입력받아 숫자인지 아닌지
# if~else문과 조건부 표현식을 이용하여 판단하여라
ch = input('한 문자를 입력?')
print('[if~else문]')
if '0' <= ch <= '9':
    print('숫자')
else:
    print('숫자가 아님')
print('[조건부 표현식]')
print('숫자' if '0' <= ch <= '9' else '숫자가 아님') # 한줄밖에 안됨


