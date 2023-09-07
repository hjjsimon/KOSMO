# 1부터 10까지 누적합: while문 사용
sum = 0
i = 1 #초기식
while i <= 10: #반복조건
    sum += i #실행문
    i+=1
print('1부터 10까지 누적합:',sum, sep='')

#문] 1~1000까지 숫자중 3의 배수나 5의 배수인 숫자의 합을 구하라
# 단 3, 5의 공배수는 제외
# for문은 반복횟수 정해진경우, while은 안정해진 경우 사용
sum = 0
i = 1
while i <= 1000:
    #if i % 3 == 0 or i % 5 == 0:
    if (i%3==0) ^ (i%5==0):
        sum += i
    i += 1
print('1부터 1000까지 3과 5의 배수의 누적합(공배수 제외):',sum,sep='')

#반복횟수 미정은 while사용
import random
i = 1
while i != 5:
    i = random.randint(1,10) #1~10까지 난수 생성
    print('i는',i) #랜덤숫자 나오는데 5돼야 while문 빠져나가 끝남
'''
1000
0100
0010
0001
'''
i=1 #바깥 while 초기식
k=1 #안쪽 while 초기식
while i<5: #바깥 while(행)
    while k<5: #안쪽 while
        if i==k:
            print('%-2d' % 1, end='')
        else:
            print('%-2d' % 0, end='')
        k+=1 #안쪽 while의 증감식
    i+=1 #바깥 while의 증감식
    k=1 #k를 1로 초기화
    print()

'''
*
**
***
****
*****
'''
print('-' * 50)
i=0 #바깥 while 초기식
while i <= 5: #바깥 while
    k=0
    while k<5:
        if i>k:
            print('%-2s' % '*', end='')
        k+=1 #안쪽 while
    i+=1 #바깥 while
    print()

# 문] 구구단
i=1
while i < 10: #바깥 while
    k=2
    while k < 10: #안쪽 while
        print('%-2s * %-2s = %-4s' %(k,i,i*k),end='')
        k+=1
    i+=1
    print()

# 무한반복
while True:
    print('HELLO')
print('프로그램 끝') #이거 언리처블 코드인데 파이썬은 걍 됨, HELLO 계속 출력







































