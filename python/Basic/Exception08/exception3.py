'''
try:
    예외가 발생할 만한 코드

except:
    예외가 발생했을 때 처리하는 코드
else:
    예외가 발생하지 않았을 때 실행할 코드
※except를 생략할 수 없다.


try:
    예외가 발생할 만한 코드

except:
    예외가 발생했을 때 처리하는 코드
else:
    예외가 발생하지 않았을 때 실행할 코드
finally:
    예외 발생 여부와 상관없이 항상 실행할 코드

※except와 else절 생략 가능

'''
try:
    fnum,snum,result =(None,None,None)
    fnum = int(input('첫번째 숫자 입력?'))
    snum = int(input('두번째 숫자 입력?'))
    result = fnum/snum
except:
    result='오류발생 결과값 저장 불가'
    print('오류가 발생했어요')
else:
    print('나누기 결과:',result)
finally:
    print(f'{fnum}/{snum}={result}')