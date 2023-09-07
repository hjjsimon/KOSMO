'''
나만의 예외 만들기
1. Exception를 상속받는다

2. __init__메소드안에서 부모인 Exception의 __init__()메소드를 호출하면서
 인수로 예외메시지를 전달한다.

예]
방법1]
class 예외클래스명(Exception):
    def __init__(self):
        super().__init__(예외메시지)

-예외 발생시 킬때
raise 예외클래스명

혹은
방법2]
class 예외클래스명(Exception):
    pass

-예외 발생시 킬때
raise 예외클래스명(예외메시지)
'''
'''
#나만의 예외 클래스 만들기 첫번째
class NotEvenException(Exception):
    def __init__(self):
       super().__init__('짝수가 아니예요')

try:
    value = int(input('숫자 입력?'))
    if value % 2 !=0:
        raise NotEvenException#클래스명만
    print('{}는 짝수'.format(value))
except NotEvenException as e:
    print(e)
'''
#나만의 예외 클래스 만들기 두번째
class NotEvenException(Exception):
    pass

try:
    value = int(input('숫자 입력?'))
    if value % 2 !=0:
        raise NotEvenException('홀수는 안되요')
    print('{}는 짝수'.format(value))
except NotEvenException as e:
    print(e)

