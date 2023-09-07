def pprint(obj):
    # bool 타입은 int 타입으로도 체크 가능 (True=1, False=0 이기 때문)
    end = '\n' if isinstance(obj,int) or isinstance(obj,float) else ''
    # isinstance(a,타입): a의 타입이 맞으면 True 반환
    # 타입 맞으면 줄바꿈 아니면 '' 반환시킴
    print(f'객체:{obj},타입:{type(obj)}',end = end)
    if not isinstance(obj,int) and not isinstance(obj,float):
        print(f',요소 수:{len(obj)}')
        # obj int나 float면 len()에러나므로 이렇게 처리

print('[리스트 생성 첫번째 : 빈 리스트]') #동적으로 요소 추가시 주로 사용

# 방법1 - [] (권장)
a = []
pprint(a) #객체:[],타입:<class 'list'>,요소 수:0

#방법2 - list(): list클래스의 생성자
a = list()
pprint(a) #객체:[],타입:<class 'list'>,요소 수:0

print('[리스트 생성 두번째 : 같은 타입의 객체(요소) 저장]')
b = [1,2,3,4,5] # 패킹: 변수 하나에 값 여러개 데이터 저장
pprint(b) #객체:[1, 2, 3, 4, 5],타입:<class 'list'>,요소 수:5

print('[리스트 생성 세번째 : 다른 타입의 객체(요소) 저장]')
c = ['가길동', 20, 3.14, True] # []는 list
pprint(c) # 객체:['가길동', 20, 3.14, True],타입:<class 'list'>,요소 수:4
d = '가길동', 20, 3.14, True # 나열시 tuple
pprint(d) # 객체:('가길동', 20, 3.14, True),타입:<class 'tuple'>,요소 수:4

print('[리스트 언패킹(구조분해)]')
# 리스트의 각 요소를 여러 변수에 나눠담는 것 : 언패킹(구조분해)
# 단, 변수의 개수가 요소의 개수와 정확히 일치해야한다
c1, c2, c3, c4 = c
pprint(c1) # 객체:가길동,타입:<class 'str'>,요소 수:3
pprint(c2) # 객체:20,타입:<class 'int'> /int라 요소 안나오게 메소드 정의함
pprint(c3) # 객체:3.14,타입:<class 'float'> /float라 요소 안나오게 메소드 정의함
pprint(c4) # 객체:True,타입:<class 'bool'> /bool은 True = 1, False = 0 처리되므로 int로 처리되기도 한다

print('[리스트 생성 네번째 : list(str객체)]')
# str을 한글자씩 잘라서 list에 담음
list_ = list('PYTHON')
pprint(list_) # 객체:['P', 'Y', 'T', 'H', 'O', 'N'],타입:<class 'list'>,요소 수:6

print('[리스트 생성 다섯번째 : list(range객체)]')
# range 범위를 하나씩 잘라서 list에 담음
list_ = list(range(5))
pprint(list_) # 객체:[0, 1, 2, 3, 4],타입:<class 'list'>,요소 수:5

print('[요소 추가하기 : append()]')
# ※ 빈 리스트에 값(요소) 할당시 반드시 append() 사용 (변수명[인덱스] = 값, 으로 할당시 에러)
# a[0] = '가길동' # 불가, 빈 리스트라 불가
a.append('가길동') # 요소 정상적으로 추가됨
a.append(20)
a.append('송파구')
pprint(a) # 객체:['가길동', 20, '송파구'],타입:<class 'list'>,요소 수:3

print('[요소 변경하기 : 리스트객체[인덱스] = 새로운 요소]')
# ※ 반드시 기존에 빈 리스트가 아니어야한다
a[1] = 40
a[2] = '금천구'
pprint(a) # 객체:['가길동', 40, '금천구'],타입:<class 'list'>,요소 수:3
# a[3] = 'kosmo' # 범위가 넘는 인덱스는 에러,  append() 사용해야함
a.append('kosmo')
pprint(a) # 객체:['가길동', 40, '금천구', 'kosmo'],타입:<class 'list'>,요소 수:4

print('[인덱싱 : 리스트객체[인덱스]]')
print(f'a[0]:{a[0]}, a[1]:{a[1]}, a[2]:{a[2]}') # a[0]:가길동, a[1]:40, a[2]:금천구
print(f'요소 수 얻기: {len(a)}') # 요소 수 얻기: 4

print('[슬라이싱: 인덱스를 사용해서 특정 범위의 요소를 추출(중요)]')
#[시작인덱스:끝인덱스] - 시작인덱스부터 끝인덱스-1 까지
print(a[1:1]) # 빈 리스트 []
print(a[1:3]) # [40, '금천구']
print(a[1:-3]) # 빈 리스트 []
print(a[-1:-3]) # 빈 리스트 []

#[:끝인덱스] - 처음부터 끝인덱스-1 까지
print(a[:0]) # 빈 리스트 []
print(a[:-1]) # ['가길동', 40, '금천구']

#[시작인덱스:] - 시작인덱스부터 끝까지
print(a[0:]) # ['가길동', 40, '금천구', 'kosmo']
print(a[-len(a):]) # ['가길동', 40, '금천구', 'kosmo']

#[:] - 모든 요소 슬라이싱
print(a[:]) # ['가길동', 40, '금천구', 'kosmo']

f = ['가', '나', '다', '라'] #반복가능 객체
for e in f:
    print(e)
# 가
# 나
# 다
# 라

for index in range(len(f)):
    print(f'인덱스:{index}, 요소:{f[index]}')
# 인덱스:0, 요소:가
# 인덱스:1, 요소:나
# 인덱스:2, 요소:다
# 인덱스:3, 요소:라
print('-' * 50)

for index, ele in enumerate(f): # enumerate는 인덱스와 요소 같이 꺼냄
    print(f'인덱스:{index}, 요소:{ele}')
# 인덱스:0, 요소:가
# 인덱스:1, 요소:나
# 인덱스:2, 요소:다
# 인덱스:3, 요소:라







