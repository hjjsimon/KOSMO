#w+,r+,a+ 모드 : +는 읽기 나 쓰기 기능이 플러스됬다는 의미
#w+모드 : 기존 내용 지워지고 새로 쓰나 읽기도 가능하다
#w_plus.txt파일 생성후에
#안녕하세요
#HELLO 입력하자
with open('w_plus.txt','w+',encoding='utf-8') as f:
    print(f'읽기 가능:{f.readable()},쓰기 가능:{f.writable()}')
    f.write('123456789')
    f.seek(0)# 커서를 파일 맨 앞으로 옮겨야 한다
    print(f.read())
#r+모드 : 읽기모드고 쓰기도 가능하다.파일이 없으면 에러
#r_plus.txt파일 생성후에
#안녕하세요
#HELLO 입력하자
with open('r_plus.txt','r+',encoding='utf-8') as f:
    print(f'읽기 가능:{f.readable()},쓰기 가능:{f.writable()}')
    print(f.read())
    f.write('\n파이썬')
#a+모드 : 쓰기모드고 읽기도 가능하다.파일이 없으면 생성.있으면 기존 내용 뒤에 추가
#a_plus.txt파일 생성후에
#안녕하세요
#HELLO 입력하자
with open('a_plus.txt','a+',encoding='utf-8') as f:
    print(f'읽기 가능:{f.readable()},쓰기 가능:{f.writable()}')
    f.write('\n파이썬')
    f.seek(0)
    print(f.read())