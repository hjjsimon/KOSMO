# - 비교연산자(이항연산자)의 결과는 True 또는 False
# - > : ~보다 크다
# - >= : ~보다 크거나 같다
# - < : ~보다 작다
# - <= : ~보다 작거나 같다
# - != : 같지 않다
# - == : 같다
# - 비교연산자는 우선순위 없음
# - 연산자간 우선순위: 산 > 비 > 논
# - 비교연산자 사용한 식은 비교식
from locale import format

print('[숫자 비교]')
num1 = num2 = 10
print(num1 >= num2) #True
b = num1 <= num2 #True, 우측부터 계산 후 결과를 왼쪽에 저장
print('%d <= %d의 결과:%s' %(num1,num2,b)) #10 <= 10의 결과:True
b = num1 != num2
print('%d != %d의 결과:%s' %(num1,num2,b)) #10 != 10의 결과:False
'''    
아스키 코드:1byte로 표현할 수 있는 문자
(영문자 와 숫자)
십진수로 정의한 값을 아스키 코드라 함.
예]A의 아스키 코드값:65(이진수:1000001)
키보드에서 A라고 치면 컴퓨터 메모리에 
1000001로 저장됨.
소문자 a는 아스키 코드값이 97

유니코드:1BYTE로 표현이 안되는 문자
(한글이나 한자등)
은 3BYTE가 필요하다.\\x16진수로 정의한 값을
유니코드라 한다.
'''
# 숫자뿐만 아니라 문자열도 ==와 !=연산자로 비교할 수 있다
# 물론 대소도 비교가능, 이때는 아스키코드 혹은 유니코드값으로 비교한다
# 아스키 코드: 1byte로 표현할 수 있는 문자(영문자, 숫자를 십진수로 정의한 값)
#######################
# 유니 코드: 1byte로 표현이 안되며 3byte가 필요한 문자
#           (한글, 한자 등 16진수로 정의한 값)

print('[문자열 비교]')
print('HELLO' == 'hello') #False, H와 h부터 아스키코드 비교, 다름
print('ABC' >= 'ABc') #False, A와 B는 동일, C보다 c가 큼

b = 15 % 3 * 2 + 4 > (10-2) *4 != True
#1](10-2) : 8
#2]15 % 3 : 0
#3] 2]의결과 * 2 : 0
#4] 1]의 결과 * 4 : 32
#중간정리: 0 + 4 > 32 != True
#5]0+ 4 : 4
#  4 > 32 != True
#6] 4 > 32 : False
#  False != True : True

print('15 %% 3 * 2 + 4 > (10-2) *4 != True의 결과:%s' % b)
# 문자열에 % 한개 인식불가 %%해줘야함


# ==, !=는 값 자체를 비교, is, is not은 객체(object)를 비교(객체, 값까지 비교하니 주소 같은지 확인과 동일)
# id()함수: 객체의 주소값 반환하는 함수
print('[==와 is연산자]')
a=1 #int
b=1.0 #float
print(a==b)#==로 비교시 true, 값만 비교함
print(a is b)#is()로 비교시 false, 값 같아도 타입 달라 다른 객체
print('a의 주소:{}, b의 주소:{}' .format(id(a),id(b)))#a의 주소:140728947962664, b의 주소:1930900717264

#bool()함수: 정수, 실수 혹은 문자열, 객체 등을 bool로 만들때는 사용(중요! 자주쓴다)
#0 혹은 0.0이 그리고 ''(빈문자열) 혹은 빈 객체를 제외한 모든 정수, 실수, 문자열, 객체 등은 True로 처리한다
#빈 리스트[]는 bool() 변환시 False
#(자스에서 0, '', "", null, undefined, NaN, 빈 배열 은 false / 이외에는 다 true였던것과 동일)
a=0.0
b=False
print('value:{}, type:{}'.format(a,type(a)))#value:0.0, type:<class 'float'>
print('value:{}, type:{}'.format(b,type(b)))#value:False, type:<class 'bool'>
print(a==b) #true, b=False=0 이므로 0.0 == 0 true
print(a is b) #false, 타입 다르니 주소 다름
print(bool(a)) #false, a는 0.0 이라 false
print(bool(a) is b) #true, bool()써서 불린타입으로 바꿈, 둘 다 False임, 객체 동일함

print('0:{}, 0.0:{}, 빈문자열:{}, 빈객체: {}'.format(bool(0),bool(0.0),bool(''),bool([])))
#0:False, 0.0:False, 빈문자열:False, 빈객체: False
print('-1:{},3.0:{},\'HELLO\':{},[1,2]:{}'.format(bool(-1),bool(3.0),bool('HELLO'),bool([1,2])))
#-1:True,3.0:True,'HELLO':True,[1,2]:True

# ※빈 리스트를 bool값과 비교시 bool()함수로 변환 후 비교(중요)
# not 변수 : bool값을 반환
# 단, 빈 리스트를 not 함께 쓸때는 bool()변환 불필요
a=[]
print(a==b)#[]는 0으로 처리 불가([] == False)
            #False, b는 False임, [] 비어있는건 false라매, 근데 바로 비교시에는 false아님
print(bool(a)==b)#True, False == False
print((not a)!=b)#True, True != False
                #not [] = True 나옴, 이 때는 이미 bool값이므로 bool(a)처럼 바꿔서 비교할 필요 없음











