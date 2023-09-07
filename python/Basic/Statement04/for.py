'''
for 변수  in 반복가능한 객체(아무나 못옴):

반복가능한 객체(Iteratable):요소가 여러개 있으면 됨. 그리고 하나씩 꺼내올 수 있음.
반복가능한 객체 인지는 dir(객체)를 통해서 알 수 있다
__iter__라는 함수가 있다면 그것은 반복 가능한 객체다

반복기(Iterator): 반복가능한 객체의 __iter__()함수 호출시 반환된 객체.
                 반복기의 __next__()함수를 통해 요소를 하나씩 꺼내온다

Iterator를 만드는 방법 두가지
                1.class로 만든다(__iter__(self),__next__(self)함수 오버라이딩)
                2.함수로 만들거나(yield키워드로 요소를 반환하는 함수)

[for문 작동방식]
for 변수 in 반복가능한객체:
    pass
1. 반복가능한객체의 __iter__()함수가 실행되어 iterator객체 생성
2. 반복때마다 iterator객체.__next__()함수가 호출되어  요소를 하나씩 꺼내와서 변수에 저장
3. 모든 요소를 다 꺼내오면 Stopiteration발생하여 for문 중지
'''
print('[문자열에서 한 문자씩 꺼내오기]')
for element in 'PYTHON':
    print(element)
# P
# Y
# T
# H
# O
# N

print('[문자열 길이만큼 반복은 하고 꺼내온 값을 사용하지 않을때]')
#문자열 길이만큼 HELLO 6번 출력
#아래 _는 스트링 객체에서 꺼내온 값을 무시하고자할때 사용함
for _ in 'PYTHON':# 특수문자 중 _는 언제나 잘 된다~, _는 사용하지 않겠다. 이럴 때 씀
    print('HELLO')

print(_) # _만 입력시 N, 꺼냈던 맨 마지막의 값 출력됨

#range() 생성자함수는 Iterable객체를 반환한다(range 타입, 매우 중요, 자주 씀)
a = range(5) #0~4 범위 설정

print(dir(a)) # a의 네임스페이스, a로 접근 가능한 것들 나옴, __iter__ 있으니 반복가능 객체
#['__bool__', '__class__', '__contains__', '__delattr__', '__dir__', '__doc__',
# '__eq__', '__format__', '__ge__', '__getattribute__', '__getitem__', '__getstate__',
# '__gt__', '__hash__', '__init__', '__init_subclass__', '__iter__', '__le__', '__len__',
# '__lt__', '__ne__', '__new__', '__reduce__', '__reduce_ex__', '__repr__', '__reversed__',
# '__setattr__', '__sizeof__', '__str__', '__subclasshook__', 'count', 'index', 'start', 'step', 'stop']

print('value:{}, type:{}' .format(a,type(a))) #value:range(0, 5), type:<class 'range'>
it = a.__iter__() #이터레이터 객체가 it로 반환
print(it.__next__()) # 0 꺼냄
print(it.__next__()) # 1, 0 다음 1 꺼냄 4까지 꺼낼 수 있음
#print(it.__next__()) # 2
#print(it.__next__()) # 3
#print(it.__next__()) # 4
#print(it.__next__()) # 꺼낼거 없어서 출력안됨

for i in a:
    print(i)
# 0
# 1
# 2
# 3
# 4

print('[문자열 길이만큼 반복하기 - len()함수 사용]')
for i in range(len('PYTHON')):
    print('i가 %d일때 : %s' % (i, 'HELLO')) # PYTHON 6글자라 0~5 범위
# i가 0일때 : HELLO
# i가 1일때 : HELLO
# i가 2일때 : HELLO
# i가 3일때 : HELLO
# i가 4일때 : HELLO
# i가 5일때 : HELLO

# range() 함수
print('[range(숫자)]')
for index in range(5):
    print('HELLO',index)
# HELLO 0
# HELLO 1
# HELLO 2
# HELLO 3
# HELLO 4

print('[range(시작숫자, 끝숫자)]')
for index in range(5,10):
    print('HELLO',index) # 끝숫자 -1 까지 출력
# HELLO 5
# HELLO 6
# HELLO 7
# HELLO 8
# HELLO 9

print('[range(시작숫자, 끝숫자, 증감폭(양수)]')
for index in range(5,10,2):
    print('HELLO',index) # 5부터 9까지 2씩 증가
# HELLO 5
# HELLO 7
# HELLO 9

print('[range(시작숫자, 끝숫자, 증감폭(음수)]')
for index in range(10,5,-2):
    print('HELLO',index) # 10부터 5까지 2씩 감소
# HELLO 10
# HELLO 8
# HELLO 6

# 반복문을 이용해서 1부터 10까지 누적합: 1+2+3+4+5+6+7+8+9+10
sum = 0
for i in range(1,11):
    sum += i
print('{}부터 {}까지 누적합:{}' .format(1, 11-1, sum)) #1부터 10까지 누적합:55

# 0부터 10까지 숫자중 2의 배수의 합: 2+4+6+8+10
# 방법1] 2씩 증가하도록 증감식 작성
sum = 0
for i in range(0, 11, 2):
   sum += i
print('{}부터 {}까지 2의 배수 누적합:{}' .format(1, 11-1, sum)) #1부터 10까지 2의 배수 누적합:30

# 방법2] 1부터 1씩 증가하면서 10까지 반복, i의 값이 2의 배수인 경우에만 누적
sum = 0
for i in range(1,11):
    if i % 2 == 0:
        sum += i
print('{}부터 {}까지 2의 배수 누적합:{}' .format(1, 11-1, sum)) #30
print('for문이 끝난 후 i의 값',i) #for문이 끝난 후 i의 값 10

# 문] 1~100까지 숫자중 3의 배수 또는 5의 배수인 숫자의 합을 구하라
# 단, 3과 5의 공배수는 누적합에 한번만 포함시켜라
sum = 0
for i in range (1,101):
    if i % 3 == 0 or i % 5 ==0:
        sum += i
print(sum)

# 문] 1부터 100까지 숫자중 3의 배수 또는 5의 배수인 숫자의 합을 구해라
# 단, 3과 5의 공배수는 제외시켜라
# sum = 0
# for i in range (1,101):
#     if i % 3 == 0 or i % 5 ==0:
#         sum += i
#     if i % 3 == 0 and i % 5 ==0:
#         sum -= i
# print(sum)

# 방법1] OR, AND 연산 사용
sum = 0
for i in range(1,101):
    if (i % 3 == 0 or i % 5 == 0) and i % 15 != 0:
        sum += i
print('{}부터 {}까지 3 및 5의 배수의 누적합(공배수 제외): {}' .format(1, 100, sum))

# 방법2] ^(XOR) 연산 사용
sum = 0
for i in range(1,101):
    if (i % 3 == 0) ^ (i % 5 == 0):
        sum += i
print('{}부터 {}까지 3 및 5의 배수의 누적합(공배수 제외): {}' .format(1, 100, sum))

'''
이중 for문 : 바깥for = 행 / 안쪽for = 열
'''
repeat = 0
for i in range(3):
    for k in range(3): #안쪽 for 다 끝나야 밖 for index 1 증가
        repeat += 1
print('HELLO',repeat)
# HELLO 1
# HELLO 2
# HELLO 3
# HELLO 4
# HELLO 5
# HELLO 6
# HELLO 7
# HELLO 8
# HELLO 9

'''
1000
0100
0010
0001
0000
'''
for i in range(5): #5행
    for k in range(4): #4행
        if i==k:
            print(1,' ',end='') #줄바꿈하면 안됨, end키워드 디폴트 \n이므로 ''처리해줌
        else:
            print(0,' ',end='')
    print() #안쪽 for문 끝나면 줄바꿈
'''
0001
0010
0100
1000
'''
print('-'*50)
#방법1
for i in range(4):
    for k in range(4):
        if i+k == 3:
            print(1, ' ', end='')
        else:
            print(0, ' ',end='' )
    print()
print('-'*50)
#방법2(더 좋은 방법)
for i in range(1,5):
    for k in range(4,0,-1):
        if i==k:
            print(1, ' ', end='')
        else:
            print(0, ' ', end='')
    print()
'''
*
**
***
****
*****
'''
print('-'*50)
for i in range(5):
    for k in range(5):
        if i >= k :
            print('*', ' ', end='')
    print()

print('-'*50)
for i in range(5):
    for k in range(5):
        if i >= k :
            print('%-2c' % '*', end='')
    print()

# 문] 구구단
print('-' * 50)
for i in range(1,10):
    for k in range(2,10):
        print('%-2d * %-2d = %-4d' % (k,i,k*i),end='')
    print()















