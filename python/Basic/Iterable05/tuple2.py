print('[튜플의 주요 메소드]')

c=1,2,3
print('1.copy():튜플 복사')
'''
d=c
print(f'c의 주소:{id(c)},d의 주소:{id(d)}')
print(c,d,sep=' : ')
'''
#d=c.copy()#tuple에는 copy()없다
print('2.index(요소):해당 요소의 인덱스 반환')
g='가','나','다','나'
print(g.index('나'))
if '바' in g:
    print(g.index('바'))
print('3.count(요소):해당 요소의 빈도수 반환')
print(g.count('나'))
#튜플에 없는 메소드 사용시에는 tuple을 리스트로 변경후 사용하자
g=list(g)#리스트로 변경
g.sort()#리스트의 메소드 적용
print('리스트로 변경후 정렬:',g)
#튜플의 산술 연산 : +와 *만 가능
x=1,2,3
y='가','나','다'
print(x+y)#원본은 변하지 않는다
print(x)
print(y*3)
print(y)
#in (not in)  연산자 : 튜플에 요소의 존재여부를 파악할 수 있다
z=1,2,3
print(4 not in z)
print(1 in z)










