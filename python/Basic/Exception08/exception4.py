'''
예외 발생시키기
raise Exception([에러메시지])
'''
#함수 밖에서 raise키워드 사용 - try ~ except:절 미 사용
'''
value = int(input('숫자 입력?'))
if value % 2 !=0:
    raise Exception(f'{value}는 짝수가 아니예요')
    print('raise문 이후 출력문')#자바처럼 Unreachable code에러 발생안함
print(f'{value}는 짝수')
'''
'''
#함수 밖에서 raise키워드 사용 - try ~ except:절 사용
try:
    value = int(input('숫자 입력?'))
    if value % 2 !=0:
        raise Exception(f'{value}는 짝수가 아니예요')
    print(f'{value}는 짝수')
except Exception as e:
    print(e)
'''
#함수 안에서 raise키워드 사용.자바처럼 throws 예외명 불필요
def isEvan():
    value = int(input('숫자 입력?'))
    if value % 2 != 0:
        raise Exception(f'{value}는 짝수가 아니예요')#raise시 함수 빠져 나간다
    print(f'{value}는 짝수')
try:
    isEvan()
except Exception as e:
    print(e)

