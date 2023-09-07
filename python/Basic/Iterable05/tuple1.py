def pprint(obj):
    #bool타입은 int타입으로도 체크 가능
    end = '\n' if isinstance(obj,int) or isinstance(obj,float) else ''
    print(f"값:{obj},타입:{type(obj)}",end=end)
    if not isinstance(obj,int) and not isinstance(obj,float):
        print(f',요소 수:{len(obj)}')

print('[튜플 생성 첫번째 : 빈 튜플]')#동적으로 요소 추가시 주로 사용
#방법1-()list1.py
a=()
pprint(a)
#방법2-tuple():tuple클래스의 생성자
a=tuple()
pprint(a)
print('[튜플 생성 두번째 : 요소가 하나인 튜플]')
b=(1)
pprint(b)
b=(1,)#혹은 1,
pprint(b)
print('[튜플 생성 세번째 : 같은 타입의 객체(요소) 저장]')
b=1,2,3,4,5#혹은 b=(1,2,3,4,5) 즉 ()생략 가능.변수 하나에 여러개 데이타 설정:패킹
pprint(b)
print('[튜플 생성 네번째 : 다른 타입의 객체(요소) 저장]')
c='가길동',20,3.14,True
pprint(c)
print('[튜플 언 패킹(구조분해)]')
#튜플의 각 요소를 여러 변수에 나눠 담는 것:언패킹(구조분해)
#단, 변수의 개수가 요소의 개수와 정확히 일치해야 한다
c1,c2,c3,c4 = c
pprint(c1)
pprint(c2)
pprint(c3)
pprint(c4)#isinstance(a,int) int타입으로도 처리된다
print('[튜플 생성 다섯번째 : tuple(str객체)]')
tuple_=tuple('PYTHON')
pprint(tuple_)
print('[튜플 생성 여섯번째 : tuple(range객체)]')
tuple_ = tuple(range(5))
pprint(tuple_)

#c[0]=100#TypeError: 'tuple' object does not support item assignment

print('[인덱싱:튜플객체[인덱스]]')
print(f'b[0]:{b[0]},b[1]:{b[1]},b[2]:{b[2]}')
print(f'요소 수 얻기:{len(b)}')
print('[슬라이싱:인덱스를 사용해서 특정 범위의 요소들 추출]')
#[시작인덱스:끝인덱스] - 시작인덱스부터  끝인덱스-1까지
print(b[1:1])#빈 튜플.슬라이싱 방향이 왼쪽임으로(1부터 0으로)
print(b[1:3])#(1,2)
print(b[1:-3])#빈 튜플
print(b[-1:-3])#빈 튜플
#[:끝인덱스] -  처음부터 끝인덱스-1까지
print(b[:0])#빈 튜플
print(b[:-1])#(1,2,3,4)
#[시작인덱스:] -  시작인덱스부터 끝까지
print(b[0:])#(1,2,3,4,5)
print(b[-len(b):])#(1,2,3,4,5)
#[:] -  모든 요소 슬라이싱
print(b[:])#(1,2,3,4,5)

f='가','나','다','라'
for e in f:
    print(e)

for index in range(len(f)):
    print(f'인덱스:{index},요소:{f[index]}')
print('-'*30)
for index,ele in enumerate(f):
    print(f'인덱스:{index},요소:{ele}')

