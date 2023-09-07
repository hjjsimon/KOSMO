#파이썬은 함수 소코프이다
#지역변수:함수안에서 선언된 변수

def add(*args):
    if len(args) != 0:
        return f'{args[0]}부터 {args[len(args)-1]}까지 누적합:{sum(args)}'
    else:
        return '전달된 인수가 없어요'
print(add())
print(add(*[i for i in range(1,101)]))

#람다표현식으로 함수 정의
add_ = lambda *args : f'{args[0]}부터 {args[len(args)-1]}까지 누적합:{sum(args)}' if len(args) != 0 else '전달된 인수가 없어요'
print(add_())
print(add_(*[i for i in range(1,101)]))

#print(args)#NameError: name 'args' is not defined
#print(i)#NameError: name 'i' is not defined
#전역변수: 스크립트 파일(.py) 전체에서 사용할 수 있는 변수

for i in range(5):
    pass
print(i)#i는 현재 스크립트 파일(func7.py)에서 사용할 수 있다
def method():
    '''
    #i=100
    print('함수안 i는',i)
    '''
    #global i
    #i = 100
    print('함수안 i는', i)

method()
print('함수밖의 i는:',i)


