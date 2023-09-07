#hello()#NameError: name 'hello' is not defined
from pygame.draw_py import encode


#함수 정의
def hello():
    '''
    이 함수는 "HELLO WORLD"라는 문자열을 출력하는 함수이다.
    :return: 반환값은 없다
    '''
    print('HELLO WORLD')
#함수 호출
hello()
print(f'value:{hello},type:{type(hello)}')
print(dir(hello))#hello라는 이름으로 접근할 수 있는 모든 목록(객체의 이름공간들) 표시
'''
#함수 독스트링 : 함수에 대한 설명서
#설정 : 함수 구현부 맨 위에  '''''',나 """"""안에 함수에 대한 사용 설명서 작성
#보기 : print(함수명.__doc__)
'''
print(hello.__doc__)#함수 독스트링(미 정의시 None)
#빈 함수 정의하기
def empty():
    pass
print(empty())#반환값이 없을때 None

def print_(msg):
    '''
    함수 독스트링: 주석처럼 사용할 수 있으나 하나의 명령문이다
    문자열을 입력받아 출력하는 함수이다
    :param msg:춭력할 문자열
    :return:반환값 없다
    '''
    msg= f'출력할 문자열:{msg}'
    '''
    주석처럼 사용한 독 스트링
    '''
    print(msg)
print(print_.__doc__)
print_('함수 독 스트링 연습')

def add(x,y):
    result = x+y
    return result#함수는 return문을 만나면 모든 실행을 종료하고 빠져 나간다
    print('return문 이후의 출력문')#실행만 안된다

result= add(10,20)
print(result)
print(add(10,20))

#return 키워드 응용하기
def entrance(age):
    if not (20 <= age <=40):
        print('입장불가')
        return

    print('즐거운 시간')

entrance(45)
entrance(25)

def add(x,y):
    z= x+y
    return x,y,z#(x,y,z)와 같다 튜플 반환

r = add(1,2)
print(f'value:{r},type:{type(r)}')
x,y,z = add(1,2)#구조분해(언패킹)
print(f'{x}+{y}={z}')