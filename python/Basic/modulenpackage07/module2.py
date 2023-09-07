import math
#변수
PI=math.pi
#함수
def add(*args):
    return f'{args[0]}부터 {args[len(args)-1]}까지 누적합:{sum(args)}'
#클래스
class MyClass:
    def hello(self):
        return '클래스의 메소드-hello'

if __name__ =='__main__':#현재 파일을 모듈로 사용하지 않고 실행한다면
    print(PI)
    print(add(*list(range(1,11))))
    myclass = MyClass()
    print(myclass.hello())