# str 객체의 주요 메소드
def pprint(val):
    print(f'val:{val},type:{type(val)}')

print('1. join(Iterable 객체)') #주로 리스트를 문자열로 반환
# ※단, 리스트의 모든 요소는 문자열이어야한다
# 리스트 요소중 하나라도 숫자인 경우 에러 발생(파이썬은 문자와 숫자 연결 불가)
# 이런 경우 map 함수를 같이 적용한다
a = ['한라산','설악산','대둔산','덕유산']
b = ''.join(a) # 빈문자열로 한라산, 설악산... 요소들 사이를 연결함, 따닥따닥 붙음
pprint(b) #val:한라산설악산대둔산덕유산,type:<class 'str'>
b = '★'.join(a)
pprint(b) #val:한라산★설악산★대둔산★덕유산,type:<class 'str'>
a. append(2023)
print(a) # ['한라산', '설악산', '대둔산', '덕유산', 2023]
# b = '●'.join(a) # expected str instance, int found -> str 나올줄 알았는데 int 있어서 문자열 연결 에러
b = '●'.join(map(str,a)) #a에서 하나하나 꺼내 str로 넣음
pprint(b) #val:한라산●설악산●대둔산●덕유산●2023,type:<class 'str'>

print('2. split([구분자]) : 특정 구분자로 split해서 리스트로 반환(구분자 디폴트값은 공백)')
c = b.split('●')
pprint(c) #val:['한라산', '설악산', '대둔산', '덕유산', '2023'],type:<class 'list'>
d = b.split() #디폴트 공백
pprint(d) #val:['한라산●설악산●대둔산●덕유산●2023'],type:<class 'list'>
          #구분자가 없는 경우 b라는 문자열 전체가 리스트 안에 하나의 요소로 들어감

print('3. replace(): 문자열 치환')
e = 'Hello World'
print(e.replace('World','Python')) #Hello Python
print(e) #Hello World, str객체는 불변!!!!!!!!!!!!!

print('4. lower(): 소문자로 변경')
print(e.lower()) #hello world

print('5. upper(): 대문자로 변경')
print(e.upper()) #HELLO WORLD

print('6. lstrip([제거할 문자열 집합]): 왼쪽의 공백 혹은 지정한 문자열 집합 제거')
# ※해당 값의 모든 조합으로 제거
# 제거할 문자열 집합은 제거할 단어를 넣거나
# 혹은 그 단어를 구성하는 알파벳을 넣자
# 단, 공백이 있으면 반드시 공백도 포함해야한다
f = '           Hello       World  '
print(f'X{f}Y') #X           Hello       World  Y
print(f'X{f.lstrip()}Y') #XHello       World  Y,    왼쪽만 공백 제거됨 left
print(f"X{f.lstrip(' Hello')}Y") #XWorld  Y,    이 때 Hello옆에 공백 많으니 제거하려면 띄어쓰기 필수
print(f"X{f.lstrip(' H e l o')}Y") #XWorld Y,   문자를 구성하는 알파벳만 있으면 완벽히 안맞아도 다 지워짐
print(f"X{f.lstrip(' World')}Y") #XHello       World  Y
print(f"X{f.lstrip(' HeloWrd')}Y") #XY

print('7. rstrip([제거할 문자열 집합]): 오른쪽의 공백 혹은 지정한 문자열 집합 제거')
print(f'X{f.rstrip()}Y') #X           Hello       WorldY

print('8. strip([제거할 문자열 집합]): 양쪽의 공백 혹은 지정한 문자열 집합 제거')
print(f'X{f.strip()}Y') #XHello       WorldY

print('9. zfill(자리수): 0으로 자리수 채우기')
print('9'.zfill(2)) #09,    두자리가 되도록 0을 채움
print('3.14'.zfill(7)) #0003.14,    7자가 되도록 0을 채움

print('10. find()/ rfind(): 찾은 문자열의 인덱스 반환(index()보다 권장)')
g = 'Hello! He is good'
print(g.find('He')) # 왼쪽부터 He 찾음, 인덱스 0(자바 indexOf)
print(g.rfind('He')) # 오른쪽부터 He 찾음, 인덱스 7(자바 lastIndexOf)
print(g.rfind('h2')) # he 없음, -1 반환함

print('11. index()/ rindex(): 찾은 문자열의 인덱스 반환') #find와 동일함(단, 없으면 아예 에러남)
print(g.index('He')) # 왼쪽부터 He 찾음, 인덱스 0(자바 indexOf)
print(g.rindex('He')) # 오른쪽부터 He 찾음, 인덱스 7(자바 lastIndexOf)
#print(g.rindex('h2')) # he 없음, 에러남

print('12. count(문자열): 문자열의 빈도수 반환')
print(g.count('He')) # 2,   He 두번 나옴
print(g.count('he')) # 0,   없는건 0 나옴
