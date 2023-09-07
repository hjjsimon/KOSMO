from io import BytesIO
# BytesIO 객체 생성
b_io = BytesIO()
print('안녕하세요'.encode())#b'\xec\x95\x88\xeb\x85\x95\xed\x95\x98\xec\x84\xb8\xec\x9a\x94'
#바이너리 데이터 메모리에 쓰기
b_io.write('안녕하세요'.encode())
#바이너리 데이터 메모리에서 읽기
b_io.seek(0)#포인터를 처음으로 이동
bytes=b_io.read()
print(f'value:{bytes}\ntype:{type(bytes)}')
#바이트 문자열(바이너리 데이타)을 디코딩
print(bytes.decode())
b_io.close()

import base64
print('[메모리로 바이너리 데이타 로드후 BASE64로 인코딩]')
with open('filters.jpg','rb') as f:
    print(f.read())
    f.seek(0)#파일 포인터를 처음으로 이동
    #BytesIO객체 생성
    with BytesIO(f.read()) as b_io:
        bytes=b_io.read()#바이트 문자열(바이너리 데이타) 메모리로 로드
        print(bytes)#f.read()와 같다
        #바이트 문자열을 BASE64 문자열로 인코딩
        base64_encode=base64.b64encode(bytes)
        print(base64_encode)
        #BASE64 문자열을 디코딩
        print(base64_encode.decode())
        #b_io.close()

print('[파일에서 바이너리 데이타 로드후 BASE64로 인코딩]')
with open('filters.jpg','rb') as f:
    base64_encode=base64.b64encode(bytes)
    print(base64_encode)
    #BASE64 문자열을 디코딩
    print(base64_encode.decode())
    b_io.close()




