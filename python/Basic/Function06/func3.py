print('[함수형식3 - 매개변수 YES,반환값 NO]')
#start부터 end까지 누적합 구하는 함수
def sum_(start,end):
    total=0
    for i in range(start,end+1):
        total+=i
    print('%d부터 %d까지 누적합:%d' % (start,end,total))
# 함수 호출
# 변수에 저장후 변수 전달
start,end = 1,10
sum_(start,end)
sum_(1,100)
'''문] 국영수 세 과목의 점수를 매개변수로 전달받아
    평균을 구하고 학점을 출력하는 함수를 정의해라
    그리고 함수를 호출하여 결과를 확인해라.'''
def makeGrade(kor,eng,math):
    avg = (kor+eng+math)//30
    {10:lambda:print('A학점'),
     9:lambda:print('A학점'),
     8: lambda: print('B학점'),
     7: lambda: print('C학점'),
     6: lambda: print('D학점'),
     }.get(avg,lambda:print('F학점'))()
makeGrade(99,88,77)
score =[(90,78,99),(66,78,55),(80,55,90),(100,99,100),(40,35,20)]
#문]위에 정의한 makeGrade()함수를 호출하여
#2차원 리스트 score에 저장된 5명의 학생의 학점을 출력하여라.
for i in range(len(score)):
    print(f'[{i+1}번째 학생의 성적]')
    makeGrade(score[i][0],score[i][1],score[i][2])
'''
  문] 숫자 두개를 매개변수로 입력받아서 즉 시작단 과 끝단을 
      매개변수에 입력받아  해당 구구단을 출력하는 함수를 정의하고
      함수를 호출하여라. 
'''
def makeGugudan(start,end):
    for k in range(1,end+1):
        for j in range(start,end+1):
            print('%-2d * %-2d = %-4d' % (j,k,k*j),end='')
        print()

makeGugudan(2,16)
print('[함수형식4 - 매개변수 YES,반환값 YES]')
'''
 인원수를 매개변수로 전달받아
 인원수만큼 나이를 사용자로부터 입력받고
 그 나이를 합을 반환하는 함수 정의 
'''
def makeAgeOfTotal(personCount):
    list_=[]
    for i in range(personCount):
        list_.append(int(input(f'{i+1}번째 학생의 나이 입력?')))
    return sum(list_)


#print(makeAgeOfTotal(3))
'''
문]여러개의 숫자를 입력받아서 그 중에 최대값을 구하는 함수를 정의하자
단, 숫자의 개수는 매개변수로 입력받고 숫자의 개수 만큼 사용자로부터 숫자를
입력받아 최대값을 구해 그 최대값을 반환하는 함수이다.
'''
def makeMax(numberCount):
    list_=[]
    for i in range(numberCount):
        list_.append(int(input(f'{i+1}번째 숫자 입력?')))
    return max(list_)
print('최대값:',makeMax(3))