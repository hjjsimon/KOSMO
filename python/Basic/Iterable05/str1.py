'''
순서가 있는 자료형(시퀀스 객체라고함): str, list, tuple, range
시퀀스객체 공통기능
1. (not) in 연산자로 요소(객체) 존재 파악
2. 인덱싱([인덱스]) 및 슬라이싱([시작인덱스 : 끝인덱스])
3. + 또는 * 연산자로 시퀀스객체를 연결(+)하거나 같은 시퀀스객체를 배수(*)로 늘릴 수 있다
    단, range 객체는 list로 변환 후 +,*를 적용해야한다 (list는 []로 감싸짐)
    단, tuple은 읽기전용이라 불가, 요소가 늘거나 줄어들 수 없음
    그리고 * 연산자 사용시 0 또는 음수를 곱하면 빈 객체가 된다
4. len(시퀀스객체)함수는 저장된 요소의 개수 반환
'''

print('[빈 문자열 만들기]')
a='' #"", '''''', """""" 동일
#len(반복가능한 객체)는 모든 반복가능한 객체의 요소수를 반환
print(f'value:{a},len:{len(a)},type:{type(a)}') #value:,len:0,type:<class 'str'>

print('[NOT 빈문자열 만들기]')
a='PYTHON' #그냥 아무거나 쓰면 됨
print(f'value:{a},len:{len(a)},type:{type(a)}') #value:PYTHON,len:6,type:<class 'str'>

#시퀀스객체[인덱스]
#순서가 있는 자료형(str,list,tuple,range)들은 각 요소의 인덱스로 접근 가능
#인덱스는 양수(인덱스는 0부터 시작하고 왼쪽기준) 혹은 음수(인덱스는 -1부터 시작하고 오른쪽 기준)
#[]로 둘러싸인 list에서 ['가','나','다'] 일 때 양수기준 0, 1, 2 / 음수기준 -3, -2, -1
'''
print('[인덱싱:인덱스로 요소에 접근]')
index = int(input(f'추출할 문자의 인덱스를 입력(단, 최대값은 {len(a)-1})')) #인덱스 0 시작이니까 -1해줌
print(a[index])
'''
# 추출할 문자의 인덱스를 입력(단, 최대값은 5)5
# N (PYTHON 에서 가져온 N)

print('[문자열에 for문 적용하기]')
for index in range(len(a)):
    print(f'인덱스:{index}, 요소{a[index]}')
# 인덱스:0, 요소P
# 인덱스:1, 요소Y
# 인덱스:2, 요소T
# 인덱스:3, 요소H
# 인덱스:4, 요소O
# 인덱스:5, 요소N

# enumerate(순서가 있는 자료형 즉 시퀀스 객체): 리스트, 튜플, 문자열객체, range객체의 요소 순서(인덱스)와 요소를
# 튜플로 묶어서 enumerate 객체(반복가능한 객체)로 반환
# ※ 보통 enumerate 함수는 for문과 함께 쓴다

print('-' * 50)
for element in enumerate(a):
    print(element)
# (0, 'P')
# (1, 'Y')
# (2, 'T')
# (3, 'H')
# (4, 'O')
# (5, 'N')

for index,element in enumerate(a):
    print(f'인덱스:{index}, 요소{element}') #튜플을 구조분해(언패킹)해서 각 변수에 저장
# 인덱스:0, 요소P
# 인덱스:1, 요소Y
# 인덱스:2, 요소T
# 인덱스:3, 요소H
# 인덱스:4, 요소O
# 인덱스:5, 요소N

#시퀀스객체[인덱스] = 새로운 요소로 변경하기
# a[0] = 'B' # 에러남
# str은 불변(immutable), 자바에서 원본문자열 바꾸려면 스트링빌더, 스트링버퍼 쓰던 것과 동일
# 값을 변경하려면 list로 변환 후 변경 필요(list는 변경가능, tuple은 변경불가)

# 1. 먼저 문자열을 리스트로 변환한다
# list 변환: list(객체), tuple 변환: tuple(객체)
b = list(a)
print(f'value:{b},len:{len(b)},type:{type(b)}')
# value:['P', 'Y', 'T', 'H', 'O', 'N'],len:6,type:<class 'list'>

# 2. 특정 인덱스의 문자열을 변경한다
b[0] = 'B'
print(f'value:{b},len:{len(b)},type:{type(b)}')
# value:['B', 'Y', 'T', 'H', 'O', 'N'],len:6,type:<class 'list'>
c = str(b)
print(f'value:{c},len:{len(c)},type:{type(c)}')
# value:['B', 'Y', 'T', 'H', 'O', 'N'],len:30,type:<class 'str'>
# 이거 길이 왜 30??? ['B', 'Y', 'T', 'H', 'O', 'N'] -> 한자한자 새면 30자!

# 방법1] join()함수 사용(권장)
c = ''.join(b)
print(f'value:{c},len:{len(c)},type:{type(c)}')
# value:BYTHON,len:6,type:<class 'str'>

# 방법2] 리스트의 각 요소를 문자열에 누적
c = '' #우선 빈문자열 세팅
for e in b: # b에서 하나씩 꺼내 e에 누적
    c += e
print(f'value:{c},len:{len(c)},type:{type(c)}')
# value:BYTHON,len:6,type:<class 'str'>

print('[슬라이싱: 인덱스를 사용해서 특정 범위의 요소를 추출]')
# 슬라이싱은 시퀀스 객체에만 적용됨
# 집합, 딕셔너리는 순서가 없어서 슬라이싱 적용 불가(시퀀스 객체 아님)
# ※ 슬라이싱은 항상 오른쪽 방향으로 나아가게 슬라이싱 돼야한다
d = 'ABCDEFG'

#[시작인덱스:끝인덱스] - 시작인덱스부터 끝인덱스-1 까지
print(d[1:1]) #빈문자열, 시작이 1인데 0으로 가야함, 방향이 왼쪽이라 추출 불가
print(d[1:3]) #BC
print(d[1:-3]) #BCD, -4 음수는 오른쪽부터, G, F, E, D! -> B~D 출력
print(d[-1:-3]) #빈문자열

#[:끝인덱스] - 처음부터 끝인덱스-1 까지
print(d[:0]) #빈문자열, 시작부터 -1까지? 불가
print(d[:-1]) #ABCDEF

#[시작인덱스:] - 시작인덱스부터 끝까지
print(d[0:]) #ABCDEFG
print(d[-len(d):]) #ABCDEFG

#[:] - 모든 요소 슬라이싱
print(d[:]) #ABCDEFG

# in (not in) 연산자 : 모든 시퀀스 객체에 공통으로 적용될 수 있는 연산자
#                     객체 안의 특정 요소의 존재여부를 파악할 수 있는 연산자
#                     (대소문자 구분함)

# 찾을 객체 in 반복가능한 시퀀스 객체
e = 'JAVA'
print('A' in e) #True
print('E' in e) #False
print('a' not in e) #True

while True: # 출력 후 True 줘서 계속 입력받게됨
    email = input('이메일 주소를 입력하세요?')
    if '@' not in email:
        print('이메일 형식이 아닙니다')
    else:
        break

# * 및 + 문자열 연결
print('HELLO ' + 'WORLD') # HELLO WORLD
print('HELLO ' * 10) # 10번 HELLO 반복














