print('[함수형식1 - 매개변수 NO,반환값 NO]')
def function1():
    print('-' * 30)
    print('1.NEW 2.CONTINUE 3.EXIT')
    print('-' * 30)
function1()
print('[함수형식2 - 매개변수 NO,반환값 YES]')
def function2():
    sum_ =0
    for i in range(1,11):
        sum_+=i
    return sum_
#함수 호출
#방법1] 반환값 변수에 저장
sum_ = function2()
print(f'1부터 10까지 누적합:{sum_}')
#방법2] 변수 미 사용:반환값 바로 출력
print(f'1부터 10까지 누적합:{function2()}')
'''사용자로부터 국영수 점수를 입력받아
    평균을 구해서 학점을 반환하는 함수 정의
    예] 'A학점','B학점'...'''
def makeGrade():

    # 빌트인 함수(sum) 미사용
    '''
    subject='국어','영어','수학'
    total=0
    for index in range(len(subject)):
        total+=int(input(f'{subject[index]}점수를 입력하세요?'))
    avg = total/len(subject)
    if avg >=90: return 'A학점'
    elif avg >=80: return 'B학점'
    elif avg >=70: return 'C학점'
    elif avg >=60: return 'D학점'
    else: return 'F학점'
    '''
    list_ = list(map(int,input('국영수 입력?(공백구분)').split()))
    avg = sum(list_)/len(list_)
    return {10:'A학점',9:'A학점',8:'B학점',7:'C학점',6:'D학점'}.get(avg//10,'F학점')
#print(f'당신의 학점은 {makeGrade()}')
'''문]함수안에서 사용자로부터 숫자 두개와 
       산술 연산자를 입력받는 함수로
       산술 결과는 함수 안에서 바로 출력하고
       산술 연산자 기호를 반환하는 함수를 정의 해라.
       그리고 함수를  호출하여 사용자가 입력한 연산자를 출력하여라.'''
def makeOperator():
    num1,num2 = list(map(int,input('두개의 숫자 입력(공백구분)?').split()))
    op = input('연산자 입력?')
    ops = '+','-','*','/'
    if op not in ops:
        return f'잘못된 연산자입니다:{op}'

    '''
    if op=='+':
        print(f'{num1}+{num2}={num1+num2}')
    elif op=='-':
        print(f'{num1}-{num2}={num1 - num2}')
    elif op=='*':
        print(f'{num1}*{num2}={num1 * num2}')
    else:
        print(f'{num1}/{num2}={num1 / num2}')
    '''
    {'+':lambda:print(f'{num1}+{num2}={num1+num2}'),
     '-':lambda:print(f'{num1}-{num2}={num1 - num2}'),
     '*':lambda:print(f'{num1}*{num2}={num1 * num2}')
     }.get('+',lambda:print(f'{num1}/{num2}={num1 / num2}'))()

    return op
print(makeOperator())