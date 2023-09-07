print('[집합의 주요 메소드]')
a={i for i in range(1,6)}
print(a)
b={3,4,5,6,7}
print('1.합집합')
print( a | b)
print(a.union(b))
print('2.교집합')
print( a & b)
print(a.intersection(b))
print('3.차집합')
print( a - b)
print(a.difference(b))

print('4.대칭 차집합')#두 집합의 공통이 아닌 요소들
print( a ^ b)
print(a.symmetric_difference(b))
print('5. add(요소)')
a.add(6)
print(a)
print('6. remove(요소)')
a.remove(3)
print(a)
#a.remove(9)#없는 요소 제거 시 KeyError: 9

print('7.discard(요소)')
a.discard(4)
print(a)
print(a.discard(9))#없는 요소 삭제시 remove()와 달리 에러 안남
print(a)
print('8. clear()')
a.clear()
print(a)#set()
print('9. copy()')
c= b.copy()
print(c)
print('10. len()')
print(len(b))
#in (not in)  연산자 : 집합에 적용시 집합에 요소의 존재여부를 파악할 수 있다
a={'가','나','파이썬','자바'}
print('다' in a)
print('다' not in a)