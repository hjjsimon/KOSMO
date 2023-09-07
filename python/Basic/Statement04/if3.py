# kor,eng,math = map(int,input('세 개의 숫자 입력(공백구분)?').split())
# avg = (kor+eng+math)/3
# if avg >= 90:
#     print('A')
# elif avg >= 80:
#     print('B')
# elif avg >= 70:
#     print('C')
# elif avg >= 60:
#     print('D')
# else:
#    print('F') #마지막 else문은 없을수도~~

# 문] 사용자로부터 한 문자를 입력받아 숫자면 "숫자"
# 알파벳은 "알파벳", 둘 다 아니면 "기타" 출력
# word = input('한 문자를 입력?')
# if '0' <= word <= '9':
#     print('숫자')
# elif 'A' <= word <= 'Z' or 'a' <= word <= 'z':
#     print('알파벳')
# else:
#     print('기타')

# 문] 세 숫자 중 최대값을 구하는 로직 작성
kor,eng,math = map(int,input('세 개의 숫자 입력(공백구분)?').split())
max = kor
if max < eng:
    max = eng #참이면 max에 eng 들어있음
if max < math:
    max = math #참이면 max에 math 들어있음
print('최대값:',max)





