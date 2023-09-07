'''
input()함수: 표준입력(키보드)으로부터 사용자 입력을 받는 함수.(자바 scanner 같은 것)
            이 함수가 실행 전까지 Blocking됨
            입력값은 모두 문자열<class 'str'>로 반환된다

방법1] 한번에 하나의 데이터만 입력받기
변수 = input()
변수 = input('안내 메시지')
'''
# print('[input() 함수]')
# print('나이를 입력하세요?',end='') #end='' 추가시 입력하세요? 바로 옆에 입력가능하게됨, 없으면 다음 줄에서 입력
# age = input() #여기부터 블라킹
# print('age의 값:{}, age의 타입:{}' .format(age,type(age)))

# 위보다 나은 방법 (권장)
# age = input('나이를 입력하세요?')
# print('age의 값:{}, age의 타입:{}' .format(age,type(age)))

'''
변수1, 변수2,... = input().split()
변수1, 변수2,... = input().split(구분자)
변수1, 변수2,... = input('안내 메시지').split()
변수1, 변수2,... = input('안내 메시지').split(구분자)
'''
# multi = input('값을 여러개 입력하세요?(공백이 디폴트)').split()
# 인자 없으면 공백으로 쪼개니까 적어도 값 입력 중 한칸씩 띄워야함
# split()은 List반환, 파이썬은 [] 대괄호가 List
# print('multi의 값:{},multi의 타입:{}'.format(multi,type(multi)))
# multi의 값:['10', '20', '가', 'HELLO'],multi의 타입:<class 'list'>

# a,b = input('콤마를 기준으로 두 개의 숫자를 입력하세요?').split(',')
# ,콤마 기준으로 값이 나뉘어 순서대로 저장됨
# c = int(a) + int(b) # input은 str로 반환하므로 a, b 더하려면 int로 바꿔야함
# print(c)

# map() 함수를 쓰면 위 방식보다 더 편하다!
# step1) split으로 List 배열을 만듦
# step2) List에서 하나씩 꺼내 함수 또는 람다식에 적용

# a,b = map(int,['100','20'] ) #문자열 100,20은 숫자 100,20이 되고, 각각 a,b에 저장
# print('두 숫자의 합:',a+b) #120

# 방법1) 함수명만 쓰기
# a,b = map(int, input('콤마를 기준으로 두개의 숫자를 입력하세요?').split(','))
# print('두 숫자의 합:',a+b)
# 방법2) 람다식 쓰기
# a,b = map(lambda x:int(x)*2,input('콤마를 기준으로 두개의 숫자를 입력하세요?').split(','))
# 입력값을 숫자로 바꾸고 2를 곱해 반환
# print('두 숫자의 합(2씩 곱한 값):',a+b)

'''
※ 콤마로 구분해서 여러개 값을 출력할 수 있다
print(값1, 값2, 값3,..[,sep='문자 또는 문자열'][,end='문자 또는 문자열'][,file=파일포인터])
sep키워드 인수:콤마로 구분된 값을 여러 구분자로 결합하여 출력할 수 있다. (기본값은 공백)
end키워드 인수:출력시 줄바꿈 효과를 변경할 수 있다. (기본값은 \n)
    -> 기본값은 \n, 원래 \n붙어 println 기능, ''하면 \n 없어서 줄바꿈이 안일어남
    -> end 그냥 print 될 문자열의 끝에 '' 사이내용 추가하는 것
file키워드 인수:출력 결과를 파일, 표준 에러처리로 보낼 수 있다.
'''
print('[print()함수]')
#1. 여러개 동시에 출력하기: 공백(디폴트값)
a,b = input('공백 기준 두 개의 값 입력?').split()#split 인자 안줬으니 공백으로 스플릿
print(a,b,'공백')#2 7 공백 -> 2 뒤에, 7뒤에 디폴트값인 공백 붙음

#2. 여러개 동시에 출력하기: sep사용
print(a,b,'공백',sep='★')#2★7★공백
print(a,b,'공백',sep='\n')#2(줄바꿈)7(줄바꿈)공백

#3. 여러개 동시에 출력하기: sep 및 end 키워드 인수 사용
print(a,b,'공백',sep=',',end='')#3,9,공백 출력
print('입니다')#end=''으로 아무것도 없어서 줄바꿈 기능 사라짐, 입니다가 공백 뒤에 붙음 -> 공백입니다

#4. 여러개 동시에 출력하기: file키워드 인수 사용 - 표준에러 콘솔 출력
import sys
print(a,b,'공백',file=sys.stderr)#빨간색으로 2 7 공백 출력

#5. 여러개 동시에 출력하기: file키워드 인수 사용 - 파일로 출력
f = open('output.txt','w',encoding='utf-8')#output.txt 파일 생김
print(a,b,'공백',file=f, sep='\n')








