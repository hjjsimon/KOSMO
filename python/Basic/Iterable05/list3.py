print('[리스트 표현식(축약,내포) 미 사용]')
#방법1 - for문 사용
a=[]
for k in range(5):
    a.append(k)
print(a)
#방법2 - list생성자 사용
#a=list(5)#TypeError: 'int' object is not iterable
#a=list(0,1,2,3,4)#TypeError: list expected at most 1 argument, got 5
#a=list((0,1,2,3,4))# 요소(int타입)가 5개인 리스트
a=list(range(5))# 요소(int타입)가 5개인 리스트
for e in a:
    print(e)
a=[range(5)]#[range(0, 5)] 요소(range타입)가 하나인 리스트
for e in a:
    #print(e)#range(0, 5)
    for r in e:
        print(r)
print(a)

print('[리스트 표현식(축약,내포) 첫번째 : [리스트 표현식]')
#순서:range(5) -> for문 안의 i -> for문 앞의 식 :i
#설명:숫자 0부터 4까지 생성(range(5))하여 for문 안의 i에 담고 그 i를 이용해서 식을 만들고
#그 식에 따라 리스트의 요소가 하나씩 생성된다
a=[i for i in range(5)]#list(range(5))와 동일하다
print(a)

a=[i+2 for i in range(5)]#list(range(5))로는 불가능.map을 사용해야 한다
print(a)
a= list(map(lambda x:x+2,range(5)))#[i+2 for i in range(5)]와 같다
print(a)
mask=[i % 2==0 for i in range(5)]#[True, False, True, False, True]
print(mask)
print([v for v,m in zip(a,mask) if m])#mask용 리스트를 사용해서 a의 요소중 짝수만으로 리스트 생성

print('[리스트 표현식(축약,내포) 두번째 : list(리스트 표현식)')
a=list(i+2 for i in range(5))
print(a)
mask=list(i % 2==0 for i in range(5))
print(list(v for v,m in zip(a,mask) if m))

print('[리스트 표현식(축약,내포) 세번째 : [식 for 변수 in 반복가능한객체 if 조건식]]')
#순서:range(10) -> for문 안의 i -> for문 다음의 if문 ->if문이 참인 경우 식이 실행되서
# 요소가 하나 만들어진다
#설명:숫자 0부터 9까지 생성하여 i에 저장하고 그 i가 2의 배수이면 for문 앞의
# 식이 실행되서 요소가 만들어진다
a=[i for i in range(10) if i % 2==0]#else문 불가
print(a)
a=[i for i in range(10) if i % 2==0 and i > 5]
print(a)

b=[]
for i in range(2,10):
    for k in range(1,10):
        b.append(i*k)
print(b)

#위를 리스트 표현식으로
#for i in range(2,10)는 바깥 for문 .for k in range(1,10)는 안쪽 for문이 된다
a=[i*k for i in range(2,10) for k in range(1,10)]
print(a)

#먼저 리스트 표현식부터 작성
a=[i * k for i in range(2,10) if i==9 for k in range(1,10) if k==9 ]
print(a)

#위 리스트 표현식을 for으로 풀면
a=[]
for i in range(2,10):
    if i == 9:
        for k in range(1, 10):
            if k == 9:
                a.append(i*k)
print(a)






