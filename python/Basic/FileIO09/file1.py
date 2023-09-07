#https://docs.python.org/ko/3/library/io.html?highlight=stringio#text-i-o
#쓰기모드로 파일 열기.파일형식은 텍스트 모드가 디폴트
#파일이 없는 경우 파일이 생성되고 있는 경우 기존파일 삭제후 다시 생성됨.
'''
아래는 TextIOWrapper(즉 f의 타입)의 속성들
readable() : 읽기 가능 여부 .읽기 가능시 True 아니면 False
writable() : 쓰기 가능 여부 .쓰기 가능시 True 아니면 False
속성:
closed : 파일객체가 닫혀 있으면 True,아니면 False
encoding : 파일 인코딩 설정 값
mode : mode설정 값
name : 파일명
'''
'''
try:
    f=open(file='line.txt',mode='wt',encoding='utf-8')
    print(f'value:{f},type:{type(f)}')
    print(dir(f))
    print(f.mode)
    print(f.encoding)
    print(f.readable())
    print(f.writable())
    print(f.name)
    print(f.closed)
    f.write('파일 쓰기 시작!')
    for i in range(1,11):
        textLine = f'\n{i}번째 라인 입니다'
        f.write(textLine)
        
    f.write('\n파일 쓰기 끝!')
    #f.read()#io.UnsupportedOperation: not readable
    # 읽기모드로 변경해서 파일 내용 읽기
    f=open('line.txt','r',encoding='utf-8')
    print('-' * 30)
    print(f.readable())
    print(f.read())
except Exception as e:
    print(e)
finally:
    f.close()
'''
#with절 사용 :※ with구문은 파이썬 2.5부터 지원
#파일객체를 close()불 필요. 자동으로 close()됨(finally절 불필요)
try:
    with open('line.txt','wt',encoding='utf-8') as f:
        f.write('Start Writing!')
        for i in range(1, 11):
            textLine = f'\n{i}\'th is line'
            f.write(textLine)
        f.write('\nEnd Writing!')
except Exception as e:
    print(e)
finally:
    print(f.closed)

