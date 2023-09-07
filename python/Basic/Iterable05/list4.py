print('[리스트와 함께 쓰는 빌트인 함수(내장함수)]')
'''
min(반복가능한 객체(iterable)) : 요소중 최소값 반환
max(반복가능한 객체(iterable)): 요소중 최대값 반환
sum(반복가능한 객체(iterable)): 요소의 총합 반환
※위 함수들을 사용할때 반복가능한 객체의 요소들은 반드시 숫자 나 bool값 이어야한다
map(함수, 반복가능한객체):반복 가능한 객체의 요소를 첫번째 인자로 지정된 함수로 처리한 뒤 
map객체(이터레이터)를 반환
예) list(map(str, [1, 2]))는 ['1', '2']반환
'''
a=[10,5,15,50,True,False,-5.5]
print('[요소 중 최대값 구하기-방법1]')
max_ =a[0]
for e in a[1:]:
    if e > max_:
        max_ = e
print('최대값:',max_)
print('[요소 중 최대값 구하기-방법2]')
a.sort(reverse=True)
print('최대값:',a[0])
print('[요소 중 최대값 구하기-방법3]')
print('최대값:',max(a))
print('최소값:',min(a))
print('총합:',sum(a))

b= [True,100,5,'5']
#print(sum(b))#TypeError: unsupported operand type(s) for +: 'int' and 'str'
#c=[int(i) for i in b]
c=map(int,b)
print(sum(c))
#1부터 100까지 숫자 중 짝수의 갯수는?
d=list(range(1,101))
mask = [i % 2==0 for i in d]
print('짝수의 갯수:',sum(mask))

f=[3.14,5.99,8.88,1.24]
#리스트의 모든 요소를 정수로 변환하기
#for문 시용 첫번째-원본 불 변경.새로운 리스트 생성
'''
e=[]
for element in f:
    e.append(int(element))
print(e)
'''
'''
#for문 시용 두번째-원본 변경
for index,element in enumerate(f):
    f[index]=int(element)
print(f)
'''
#리스트 축약-원본 불변.새로운 리스트 생성
g=[int(ele) for ele in f]
print(g)
#map함수 사용-원본 불변.새로운 리스트 생성
g=list(map(int,f))
print(g)

#세개의 숫자를  공백을 기준으로 input()함수로 입력받고,
s=input('세 개의 숫자를 입력(공백구분)?')
#각각의 입력받은 값을 공백을 기준으로  split(구분자)한다,
k=s.split()
#그리고 리스트의 각 요소를 int로 변환하고 list로 만들어,
k=list(map(int,k))
#언패킹을 이용해서 세개의 변수(x,y,z)에 저장하고,
x,y,z =k
#세 숫자의 합을 출력하여라
print('세 숫자의 합:',x+y+z)

print('세 숫자의 합:',sum(list(map(int,input('세 개의 숫자를 입력(공백구분)?').split()))))



