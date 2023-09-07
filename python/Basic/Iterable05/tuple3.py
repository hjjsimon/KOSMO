#리스트는  a=[표현식] 혹은 a=list(표현식)
#튜플은   a=tuple(표현식)형식으로 하자
#a=(표현식)은 튜플이 아니고 <generator object <genexpr> at 0x00D15DB0>즉 제너레이터객체 반환
print('[튜플 표현식(축약,내포) 첫번째 : tuple(표현식)')
a=(i for i in range(5))
print(f'value:{a},type:{type(a)}')

#방법1 - tuple생성자 사용
a=tuple(i for i in range(5))#tuple(range(5))와 같다
print(f'value:{a},type:{type(a)}')


a=tuple(i+2 for i in range(5))#tuple(range(5))로는 불가능.map을 사용해야 한다
print(a)
a= tuple(map(lambda x:x+2,range(5)))#tuple(i+2 for i in range(5))와 같다
print(a)
mask=tuple(i % 2==0 for i in range(5))#(True, False, True, False, True)
print(mask)
print(tuple(v for v,m in zip(a,mask) if m))#mask용 튜플을 사용해서 a의 요소중 짝수만으로 튜플 생성

print('[튜플 표현식(축약,내포) 두번째 : tuple(식 for 변수 in 반복가능한객체 if 조건식)]')

a=tuple(i for i in range(10) if i % 2==0)#else문 불가
print(a)
a=tuple(i for i in range(10) if i % 2==0 and i > 5)
print(a)

b=[]
for i in range(2,10):
    for k in range(1,10):
        b.append(i*k)
print(tuple(b))

#위를 튜플 표현식으로
#for i in range(2,10)는 바깥 for문 .for k in range(1,10)는 안쪽 for문이 된다
a=tuple(i*k for i in range(2,10) for k in range(1,10))
print(a)

#먼저 튜플 표현식부터 작성
a=tuple(i * k for i in range(2,10) if i==9 for k in range(1,10) if k==9)
print(a)

#위 튜플 표현식을 for으로 풀면
a=[]
for i in range(2,10):
    if i == 9:
        for k in range(1, 10):
            if k == 9:
                a.append(i*k)
print(tuple(a))






