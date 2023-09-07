'''
StringIO와 BytesIO는 모두 메모리 기반의 파일과 같은 객체(file-like object)를 생성하는데
사용되는 클래스이다
StringIO는 텍스트 데이터를 다룰때
BytesIO는 바이너리 데이터를 다룰때 사용한다. 이들은 io 패키지에서 제공

StringIO와 BytesIO의 주요 차이점은 데이터 형식이다.
StringIO는 텍스트 데이터를 처리하므로 문자열을 쓰고 읽을 수 있다.
반면에 BytesIO는 바이너리데이터를 처리하므로 바이트 문자열을 쓰고 읽는다.
StringIO는 문자열에 대한 작업을 수행하기 위해 내부적으로 유니코드 인코딩 및 디코딩을 수행하는 반면
BytesIO는 바이트 문자열열을 그대로 다룬다.
따라서 데이터의 형식에 따라 StringIO와 BytesIO 중 적절한 클래스를 선택하여 사용하면 된다.

※물리적인 파일에서 데이터를 읽고 쓰는 것보다
메모리에서 데이터를 읽고 쓰는게 속도가 더 빠르다
'''
from io import StringIO
# StringIO 객체 생성
s_io = StringIO()
#메모리에 텍스트 쓰기
s_io.write('안녕하세요.HELLO')
#메모리에 생성된 file-like object의 내용 읽기
s_io.seek(0) # 파일 포인터를 파일의 처음으로 이동
print(s_io.read())
# StringIO 객체 닫기
s_io.close()
print('[메모리로 텍스트 로드하기]')
with open('list.txt','r',encoding='utf-8') as f:
    s_io=StringIO(f.read())
    print(s_io.read())
    s_io.close()









