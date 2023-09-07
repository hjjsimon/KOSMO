print('[리스트의 주요 메소드]')
print('1.append():마지막 요소 다음에 추가')
a=[1,(2),3]
a.append(4)
a.append(1)#중복 저장가능
a.append(['가','나'])
print(a)
a[len(a)-1]=['A','B']
print(a)
#print(a[len(a)])#IndexError: list index out of range
a[0]=10
print(a)
print(a[len(a):])#[] 수정시에는 out of range이나 인덱스 범위를 벗어나서 읽어올때는 []다
#a[len(a)]=10#index out of range
#a[len(a):]=10#TypeError: can only assign an iterable
a[len(a):]=[10,20]#정상.  -> 의미는 마지막방 다음에 새로운요소 추가. 구조분해되서 각 객체가 하나의 요소로 추가된다
print(a)
a.append([10,20])#리스트가 하나의 요소로 추가
print(a)
print('2.extend():리스트 확장')
b=[1,2,3]
b.extend([4,5])#기존 리스트를 확장한다 즉 이터러블한 객체의 하나의 요소가 하나의 요소가 된다.반드시 인자가 iterable 한 객체여야한다
print(b)
#b.extend(6)#TypeError: 'int' object is not iterable.append는 int형도 가능(즉 not iterable가능)
print('3.insert(인덱스,요소):기존 인덱스 위치에 요소 삽입')
c=[1,2,3]
c.insert(0,0)#0번 인덱스에 0요소 삽입
print(c)
c.insert(100,'가길동')#인덱스 범위를 벗어난 경우 무조건 마지막방 다음 즉 append()와 같다
print(c)
c.insert(2,(3.14,2.8))
print(c)
print('4.copy():리스트 복사')
'''
d=c
print(f'c의 주소:{id(c)},d의 주소:{id(d)}')
d[0]=100
print(c,d,sep=' : ')
'''
d=c.copy()
print(f'c의 주소:{id(c)},d의 주소:{id(d)}')
d[0]=100
print(c,d,sep=' : ')
print('5.remove(요소):요소 삭제')
e=[1,2,3,4,5,2]
print(e.remove(4))#반환값이 없다.None
print(e)
e.remove(2)#동일한 객체를 삭제하는 경우 인덱스가 작은 요소가 삭제됨
print(e)

for element in e:
    if element ==5:
        e.remove(5)
print('순회하면서 요소 비교후 삭제:',e)

print('6.pop([요소]):요소 삭제')
#pop()는 리스트의 마지막 요소삭제.반환값은 삭제한 요소
#pop(인덱스) : 인덱스에 헤당하는 요소 삭제.
f=[1,2,3,4,5,2]
print(f.pop())
print(f)
print(f.pop(2))
print(f)
print('7.del 리스트객체[슬라이싱]:특정 구간의 요소들 삭제')
#변수 삭제시 : del 변수명
#del f #f라는 이름의 메모리 삭제
#print(f)#NameError: name 'f' is not defined
#del f[1:3]
#print(f)
#del f[:]#f.clear()와 같다
f.clear()
print(f)#[]

print('8.index(요소):해당 요소의 인덱스 반환')
g=['가','나','다','나']
print(g.index('나'))
#list객체에는 rindex()메소드가 없다
#print(g.rindex('나'))#AttributeError: 'list' object has no attribute 'rindex'. Did you mean: 'index'?
#g.index('바')#ValueError: '바' is not in list
if '바' in g:
    print(g.index('바'))
print('9.count(요소):해당 요소의 빈도수 반환')
print(g.count('나'))
print('10.reverse():요소들을 거꾸로 재 배치')
g.reverse()#in place방식:원본이 변한다
print(g)
print('11.sort():요소들을 오름차순으로 정렬')
g.sort()#in place방식
print(g)#디폴트는 오름 차순
g.sort(reverse=True)#내림차순
print(g)
#리스트의 산술 연산 : +와 *만 가능
x=[1,2,3]
y=['가','나','다']
print(x+y)#원본은 변하지 않는다
print(x)
print(x.extend(y))#x가 확장되서 변한다
print(x)
print(y*3)
#in (not in)  연산자 : 리스트에 요소의 존재여부를 파악할 수 있다
z=[1,2,3]
print(4 not in z)
print(1 in z)










