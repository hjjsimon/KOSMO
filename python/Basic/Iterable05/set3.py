print('[집합 표현식 첫번째 - {표현식}')
print({i for i in 'HELLO'})#set('HELLO')와 같다
print({i * i for i in range(1,6)})
print('[집합 표현식 두번째 - set(표현식) 혹은 set({표현식})')
print(set({i for i in 'HELLO'}))
print(set({i * i for i in range(1,6)}))
print(set(i * i for i in range(1,6)))#딕셔너리는 dict(표현식)은 안되고 반드시 dict({표현식})
print('[집합 표현식 세번째 - {표현식 for 변수 in 딕셔너리객체 혹은 딕셔너리객체.values() 혹은 딕셔너리객체.keys()}')

a={'kor':90,'eng':80,'math':100}
print({key.upper() for key in a})
print({key.upper() for key in a.keys()})
print({value for value in a.values() if value>=90})

