'''
try:
    예외가 발생할 만한 코드
except:
    예외가 발생했을 때 처리할 코드
'''
#예외처리를 하지 않은 경우
'''
years = int(input('나이를 입력하세요?'))#숫자 아닐때 ValueError
print('당신의 10년후 나이는',10+years)
'''
#예외처리를 하는 경우
try:
    years = int(input('나이를 입력하세요?'))  # 숫자 아닐때 ValueError
    print('당신의 10년후 나이는', 10 + years)
#except:
except ValueError as e:
    print('나이는 숫자만...')
    print(f'에러메시지:{e}\n자료형:{type(e)}')
