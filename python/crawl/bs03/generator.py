'''
함수안에서 yield키워드를 사용하면 그 함수는 제너레이터를 반환
제너레이터는 이터레이터를 생성하는 객체다
이터레이터는 next를 호출할때마다 값을 생성하는 객체다
'''
#return 키워드를 사용해 값을 반환하는 일반적인 함수
def general():
    print('첫번째 출력문')
    return 1
    print('두번째 출력문')
    return 2
    print('세번째 출력문')
    return 3
print(general,type(general),sep=' | ')
print(dir(general))#__iter__없다
print(general())
print(general())
print(general())
#yield 키워드를 사용해 값을 반환하는 함수(generator)
def generator():
    print('첫번째 출력문')
    yield 1
    print('두번째 출력문')
    yield 2
    print('세번째 출력문')
    yield 3
print(generator,type(generator),sep=' | ')
print(dir(generator))#__iter__없다
#print(generator())#<generator object generator at 0x000001A1BC0868F0>
gen= generator()
print(dir(gen))#__iter__ 있다
it = gen.__iter__()
print(it.__next__())#generator()함수를 첫번째 호출
print(it.__next__())#generator()함수를 두번째 호출
print(it.__next__())#generator()함수를 세번째 호출
#print(it.__next__())#더이상 yield할 값이 없으면 StopIteration발생

#yield 키워드를 사용하는 이유
def use_return(max_):
    index,list_ = 0,[]
    while index < max_:
        list_.append(index)
        index+=1

    return list_#max_에 100만을 전달시 100만개의 한꺼번에 메모리가 필요하다

useReturn = use_return(10)
print(useReturn)
print(list(range(10)))


#리스트 불필요.필요할때마다 숫자 생성
#즉 모든 요소를 메모리에 올려두지않고 next를 호출시 필요한 요소들을 생성
# 메모리 사용측면에서 유리
def use_yield(max_):
    index =0
    while index < max_:
        yield index
        index+=1

for index in use_yield(10):
    print(index)




