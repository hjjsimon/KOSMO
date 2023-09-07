#남다 표현식(함수)
print('[람다함수를 사용하지 않고 def로 함수 정의]')
def plus(arg):
    return arg+100

print(f'value:{plus},type:{type(plus)}')
print(list(map(plus,list(range(1,6)))))

print('[람다함수 정의]')
plus_ = lambda arg : arg+100
print(f'value:{plus_},type:{type(plus_)}')
print(list(map(plus_,list(range(1,6)))))
print(list(map(lambda arg : arg+100,list(range(1,6)))))

#리스트의 요소로 람다식 저장
list_ = [lambda x,y:x+y,lambda x,y:x-y,lambda x,y:x*y,lambda x,y:x/y]
print(list_[0])
print(list_[0](10,20))
op = '+','-','*','/'
for index,element in enumerate(list_):
    print(f'10 {op[index]} 20 = {element(10,20)}')

#딕셔너리의 값으로 람다식 저장(연산자를 키로 해서)
dict_=dict(zip(op,list_))
print(dict_)

for key,value in dict_.items():
    print(f'10 {key} 20 = {value(10,20)}')

#남다 표현식에서는 할당 연산자를 사용할 수 없다
#lambda a : a*=2#SyntaxError: 'lambda' is an illegal expression for augmented assignment

#plus=lambda a : a+b
#print(plus(10))#NameError: name 'b' is not defined

#람다식의 매개변수에 가변인수(위치 및 키워드) 지정
#람다식에 if 를 사용하려면 반드시 조건부 표현식 즉 "참일때 식 if 조건 else 거짓일때 식"
#else문이 빠지면 안되고 elif가 포함되면 에러
lambda_ = lambda  a,*args : a if len(args) >=2 else args
print(lambda_(100))
print(lambda_(100,200))
print(lambda_(100,200,300))
#인수로 받은 고정인수 및 가변인수 리스트 만들어서 반환
lambda_ = lambda a,*args,**kwargs : [a,args,kwargs]
print(lambda_(100))
print(lambda_(100,200))
print(lambda_(100,200,300,**{'username':'코스모','password':'1234'}))








