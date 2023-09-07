print('[2차원 튜플]')
#바깥쪽 ()는 튜플을 의미
#안쪽 ()의 수: 행의 수
#안쪽 ()의 값(요소)의 수 :열의 수
a=(
    (1,2),(3,4),[5,6]
)#3행 2열 리스트
print('value:{},type:{}'.format(a,type(a)))

#행의 수: len(튜플객체) 즉 요소 수
#열의 수: len(튜플객체[행인덱스]) 즉 요소인 튜플의 요소수
print(f'행의 수:{len(a)}')
print(f'0행의 수:{len(a[0])}')
print(f'1행의 수:{len(a[1])}')
print(f'2행의 수:{len(a[2])}')

print('[2차원 튜플의 각 요소 읽기(인덱싱)]')
print('0행 0열:',a[0][0])
print('0행 1열:',a[0][1])
print('2행 1열:',a[2][0])
print('2행 1열:',a[2][1])
print('[2차원 튜플의 각 요소 수정]')
#a[0][0]=10  요소가 튜플이니까
a[2][1]=60
print(a)
print('[2차원 튜플의 각 요소 읽기-for문 사용 첫번째]')
#각 행의 열의 수가 같을때(언패킹-요소수와 변수의 수가 같아야 한다)
for x,y in a:
    print(x,y)
print('[2차원 튜플의 각 요소 읽기-for문 사용 두번째]')
#각 행의 열의 수가 다를때
b=((1,2),(3,4,5,6),(5,6,7))
for x in b:
    for y in x:
        print(y,end=' ')
    print()
print('[2차원 튜플의 각 요소 읽기-for문 사용 세번째]')
#각 행의 열의 수가 다를때
for i in range(len(b)):#i는 행번호
    for k in range(len(b[i])):#k는 열번호
        print(f'{i}행{k}열:{b[i][k]}',end = ' ')
    print()
#문]위 2차원 튜플를 while문으로 출력하여라.

print(['while문-열의 수가 같을때(a)'])
i=0
while i < len(a):
    x,y = a[i]
    print(x,y)
    i+=1

print(['while문-열의 수가 다를때(b)'])
i=0
while i < len(b):
    j=0
    while j < len(b[i]):
        print(b[i][j],end=' ')
        j+=1
    i+=1
    print()
