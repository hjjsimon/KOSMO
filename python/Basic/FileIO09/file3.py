#읽기모드로 파일 열기.파일형식은 텍스트모드가 디폴트
#파일이 없는 경우:FileNotFoundError
#read()함수 - 파일 전체 내용을 읽는다
try:
    with open('line.txt','rt',encoding='utf-8') as f:
        #print(f.read())
        print(f.read(10))
        #f.seek(0)#파일 포인터를 0위치로 이동
        print(f.read(5))
except Exception as e:
    print('파일 읽기 오류:',e)
#파일 전체 내용을 읽어서 리스트로 변환하기(list.txt파일)

with open('list.txt','r',encoding='utf-8') as f:
    #print(f.read().split('\n'))#\n으로 split하면 끝에 ''요소가 포함된다.['한국 소프트웨어', '인재 개발원입니다', '파이썬 수업입니다', '자바보다 쉬워요', '화이팅!!!', '2023', '']
    # ----리스트에서 마지막 요소 ''  제거하기---
    # 방법1:끝에 \n을 strip()함수로 제거후 split()
    #print(f.read().strip().split('\n'))
    # 방법2. 슬라이싱 사용
    #print(f.read().split('\n')[:-1])
    # 방법3. 표준함수인 filter(함수,반복가능한 객체)함수 사용해서 '' 제거하기
    # filter에 전달하는 함수는 True나 False를 반환하는 함수여야한다.True인 요소만 필터링된다
    print(list(filter(lambda s : s !='',f.read().split('\n'))))
#readline()함수: 한 라인만 읽은 함수.만약 파일의 끝이면 empty string
with open('list.txt','r',encoding='utf-8') as f:
    #print(f.readline())#첫번째 라인만 읽는다
    print(f.readline(8))#첫번째 라인에서 8글자만 읽는다
#readline()함수로 모든 라인을 읽어 보자(read()처럼)
with open('list.txt','r',encoding='utf-8') as f:
    while True:
        line=f.readline()
        if len(line)==0:
            break
        print(line,end='')#print함수의 줄바꿈을 제거

#한 라인씩 읽어서 리스트로 변환하기(list.txt파일)
list_=[]
with open('list.txt','r',encoding='utf-8') as f:
    while True:
        line=f.readline()
        if len(line)==0:
            break
        list_.append(line[:-1])#끝에 \n제거

print(list_)
#readlines():파일의 모든 라인을 한꺼번에 읽어서 각각의 라인(줄)을 요소로 갖는 리스트로 반환
with open('list.txt','r',encoding='utf-8') as f:
    #lines = f.readlines()
    #print(lines)#\n이 포함됨
    lines =list(map(lambda s:s[:-1],f.readlines()))
    print(lines)
