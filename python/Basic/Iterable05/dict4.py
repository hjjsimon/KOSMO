#딕션너리 표현식:기존 딕션너리 객체로 새로운 딕션너리 객체를 생성할때 주로 사용한다
'''
{키:값  for 키[, 값 ] 혹은 [키,]값    in 기존딕션너리객체.items() }
{키:값  for 키[, 값] 혹은 [키,]값   in 기존딕션너리객체.items() if 조건식}

dict({키:값  for 키[, 값 ] 혹은 [키,]값  in 기존딕션너리객체..items()})
dict({키:값  for 키[, 값 ] 혹은 [키,]값  in 기존딕션너리객체..items() if 조건식})
※딕션너리 표현식으로
특정 값을 기준으로 딕션너리를 다시 생성하는 방식으로 삭제 효과를 구현할 수 있다
'''
print('[딕셔너리 표현식 첫번째 - {표현식}')
keys=list('ABCD')#['A', 'B', 'C', 'D']와 같다
a=dict(zip(keys,['Able','Banana','Card','Danger']))
print(a)
#무조건 모든 값을 'python으로 변경
b={key:'python' for key in a}
print(b)
#모든 값을 소문자_로 변경
b={key:value.lower() for key,value in a.items()}
print(b)
#값을 키로 사용하고 기존 값은 소문자로 변경해서 다시 값으로 사용
print({value:value.lower() for value in a.values()})
print('[딕셔너리 표현식 두번째 - dict({표현식})')
b=dict({key:value.lower() for key,value in a.items()})
print(b)
c=dict(zip(['name','age','addr','tel'],['홍길동',20,'가산동','010']))
#딕셔너리 표현식으로 삭제효과 구현하기(원본이 변경이 안됨)
#dict3.py의 여러 요소 삭제를 딕셔너리 표현식으로 똑같이 구현
print({key:value for key,value in c.items() if key =='tel'})
