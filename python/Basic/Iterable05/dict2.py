print('[딕셔너리의 주요 메소드]')
print('1. setdefault("키") 혹은 setdefault("키",기본값): 키 추가 메소드')
a={'name':'가길동','age':20,'addr':'천호동'}
a.setdefault('tall')#a['tall']=None와 같다
a.setdefault('weight',45)#a['weight']=45
#a.setdefault('age',30)#기존 키가 존재하는 경우 의미 없다(변화 없다)
a['age']=30#기존 키 존재하는 경우 수정시에는 setdefault('age',30)가 아니고 [기존 키]= 수정값
print(a)
print('2. update(키=수정값):값 수정용 메소드')
a.update(age=45)#a['age']=45와 같다
a.update(tel='010-1234-5678')#a['tel']='010-1234-5678'와 같다.즉 없는 키일때는 추가된다
print(a)
print('3. pop("키") 혹은 pop("키",기본값):키에 해당 하는 요소 삭제 메소드')
#삭제한 키의 밸류값 반환
#pop(키) : 키에 해당하는 요소 삭제 만약 키가 없다면 에러발생(KeyError)
#pop(키,기본값):키가 없으면 에러 발생하지 않고 그냥 기본값 반환
print(a.pop('weight'))#del a['weight']
del a['tel']
print(a)
#print(a.pop('weight'))#KeyError: 'weight'
print(a.pop('weight','키가 없어요'))
print('4. popitem():마지막 요소 삭제')
#삭제된 요소를 튜플로 반환:(키,값)
print(a.popitem())#('tall', None)
print('5.clear():전체 요소 삭제')
a.clear()
print(a)#{}
b=dict(institue='한국 소프트웨어 인재개발원',loc='가산디지털단지역',course='AI')
print(b)
print('6. copy() : 딕셔너리 복제')
c = b.copy()
print(f'b의 주소:{id(b)},c의 주소:{id(c)}')
print(c)
print('7.get(키) 혹은 get(키,기본값) : 키로 값 가져오기')
print(b.get('loc'))
print(b.get('location'))#None반환
print(b.get('location','키가 없어요'))
print('8. items()/keys()/values() 함수')
print(type(b.items()))#<class 'dict_items'>
print(b.items())
print(list(b.items()))
for key,value in b.items():
    print(key,value,sep=':')
print(b.keys())
for key in b.keys():
    print(key)
print(b.values())
for value in b.values():
    print(value)

print('9. dict.fromkeys(리스트나 튜플) 혹은 dict.fromkeys(리스트나 튜플,값)')
#리스트나 튜플의 요소를 키로 사용하여 딕셔너리를 만드는 함수(클래스 메소드)
#dict.fromkeys(리스트나 튜플)는 키에따른 값은 모두 None
#dict.fromkeys(리스트나 튜플,값)는 키에따른 값은 모두 두번째 인자에 지정한 값
print(dict.fromkeys(list(range(1,6))))
print(dict.fromkeys(list(range(1,6)),100))






