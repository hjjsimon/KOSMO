# ※연산자 - 데이터를 처리하는 기호
# - 산술연산자(이항연산자, 좌우에 항 존재)의 결과는 다양하다
# - 산술연산자 내에서의 연산 우선순위(1순위 거듭제곱, 꼴등 +,-)
# ** > (*,%,/,//) > (+,-)
# - 우선 순위가 같은 경우 왼쪽에서 오른쪽으로 연산한다
# - 산술 연산자를 써서 식을 만들면 산술식
# - //(버림나눗셈 연산자)는 파이썬3에서 추가됨(소수점 이하는 버리고 정수로 표현)
# - 증감연산자(++,--)는 없다
print('[버림 나누기 연산자]')
print('5/2의 결과:',5/2)#2.5
print('5//2의 결과:',5//2)#2 버림나눗셈, 내림으로 정수만 출력
print('int(5/2)의 결과:',int(5/2))#2 위 코드 동일

# divmod(x,y)함수: x를 y로 나눈 몫과 나머지를 구해 튜플로 반환하는 함수 (몫, 나머지)
print('[몫과 나머지 동시에 구하기]')
a = divmod(5,2)
print('value:{}, type:{}'.format(a,type(a))) # value:(2, 1), type:<class 'tuple'>
print('몫:{}, 나머지:{}'.format(a[0],a[1])) # 몫:2, 나머지:1
div, mod = divmod(5,2)
print('몫:{}, 나머지:{}'.format(div,mod)) # 몫:2, 나머지:1

print('[거듭 제곱 연산자]')
print(2**5) # 2^5 = 32

print('[산술 연산자 우선순위]')
result = 5 % 2 - 6//3 * 5 + 3**2
# 3^2 = 9먼저 계산
# 1 - 2 * 5 + 9
# 1 - 10 + 9
print(result)














