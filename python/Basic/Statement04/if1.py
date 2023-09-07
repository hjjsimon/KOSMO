#0, 0.0, 0+0j는 False와 같고 None, ''(빈문자열), 빈 이터레이터 객체([],{},(),set() 등)은
#조건식으로 판단시 False로 간주
if not 0: #0은 False니까 True됨
    print('0은 False다')#출력됨
if not None:
    print('None은 False다')#출력됨
if not '':
    print('\'\'은 False다')#출력됨
if not []:
    print('[]은 False다')#출력됨

#조건식은 모든 식이 될 수 있다(자스와 동일)
num1 = 10
if num1: #num1은 0 아니므로 True
    print('num1은',num1)#출력됨
if num1 % 2:
    print('num1 % 2의 값은(산술식)',num1 % 2)#계산값 0이라 False, 출력안됨
if num1 % 2==0 and num1 >= 10:#논리연산자 들어갔으니 전체적으로 논리식 됨
    print('%d는 짝수고 10보다 크거나 같은 수이다' % num1)#출력됨

#파이썬에서 조건식을 수학에서 사용하는 방식으로 간편화 할 수도 있다.
#if num1 > 5 and num1 < 15 이걸 아래처럼 가능
if 5 < num1 < 15:
    print('{}는 5보다 크고 15보다 작다' .format(num1))#출력됨
if num1 % 2 != 0:
    pass #여기에 쓸 코드가 없어도 무조건 들어가야함, 들여쓰기 오류 발생, 그럴 때 pass 사용
if num1 % 2 == 0:
    print('num1은 %d다' % num1)
    print('{}는 짝수다' .format(num1))

# ord(문자) : 문자의 아스키코드나 유니코드값을 반환
# chr(숫자) : 숫자(아스키코드값 혹은 유니코드값)에 대응하는 문자를 반환
print('[문자를 아스키(유니)코드로 변환]',ord('가'),sep='\n') #44032
print('[아스키(유니)코드를 문자로 변환]',chr(44032),sep='\n') #가

code = ord(input('한 문자를 입력하세요?'))
print(code)

# 문] 사용자가 입력한 문자가 숫자인지 판단
# 숫자면 "숫자입니다", 숫자가 아니면 "숫자가 아닙니다" 출력
'''
code = ord(input('한 문자를 입력하세요?'))
print(code)#0은 아스키코드 48, 9는 57
if 48 <= code <= 57:
    print("숫자입니다")
else:
    print("숫자가 아닙니다")
'''
isNumber = '0' <= chr(code) <= '9'
if isNumber:
    print('숫자입니다')
if not isNumber:
    print('숫자가 아닙니다')

'''
문] 사용자가 입력한 값이 숫자인지 먼저 판단, 숫자면 2의 배수인지 판단
2의 배수면 '2의 배수입니다', 아니면 '2의 배수가 아닙니다' 출력
'''
# if isNumber:
#     pass
# if code % 2 ==0:
#     print('2의 배수입니다')
# if code % 2 !=0:
#     print('2의 배수가 아닙니다')

# 방법1] 하나의 조건식안에서 논리 연산으로 처리
isMultiple = '0' <= chr(code) <= '9' and (code-ord('0')) % 2 == 0
if isMultiple:
    print('2의 배수입니다')
if '0' <= chr(code) <= '9' and (code-ord('0')) % 2 != 0:
    print('2의 배수가 아닙니다')

# 방법2] if문 안의 if문으로 처리
if '0'<=chr(code) <= '9':
    isMultiple = (code - ord('0')) %2 ==0
    if isMultiple:
        print('2의 배수입니다')
    if not isMultiple:
        print('2의 배수가 아닙니다')









