#파이썬에서 문자열 만드는 방법 4가지
# '', "", '''''', """""" 로 감싸면 문자열이 된다
print('Hello World')#Hello World 이하 동일
print("Hello World")
print('''Hello World''')
print("""Hello World""")

#줄바꿈해서 출력하는 방법
#아래줄 닫히지 않아 에러 발생
# print('Hello'
#       World)
#''' 또는 """ 사용해야 에러 안남
print('''Hello
        World''')#World 앞의 공백도 출력됨
print("""
Hello
    World""")

#'''와 """안에는 '와 " 둘 다 넣을 수 있다
print('''
올해 2023's의 목표
    1. '80kg이상 증량'
    2. "박사진학"
''')#
s = 'Hello'
print('value:',s,',type:',type(s))#value: Hello ,type: <class 'str'>

#len()함수로 문자열 길이 구하기
print(len(s))#5, 위의 Hello 5글자
print(len('안녕'))

'''
※ 유니코드 문자열(ex.한글)을 인코딩 없이 그대로 파일에 쓰거나 
    다른 시스템으로 네트워크 전송을 할 수는 없다
    유니코드 문자열은 단순히 문자셋의 규칙이기 때문
    파일에 쓰거나 다른 시스템으로 유니코드 문자열 전송을
    위해서는 바이트로 변환해야한다
'''
#encode('문자셋'):
#유니코드 문자열을 바이트 문자열로 만드는 함수(str클래스의 메소드)
#문자셋을 생략해도 디폴트 값인 utf-8로 동작
print(s)#Hello : str 타입 문자열
print(s.encode(encoding='utf-8'),type(s.encode(encoding='utf-8')))
#b'Hello' : bytes 타입의 바이트 문자열(바이트 문자열은 앞에 b로 표현)
print('안녕')#안녕 : str 타입 문자열
print('안녕'.encode())#b'\xec\x95\x88\xeb\x85\x95'
#16진수 형태로 나옴, 네트워크 전송시 byte문자열로 바꿔서 전송해야함
print(len(b'\xec\x95\x88\xeb\x85\x95'))#길이 6출력, 6바이트
#자바에서는 한글1글자가 2byte, 파이썬에서는 한글1글자가 3byte 차지함

#decode():인코딩된 바이트 문자열을 (유니코드)문자열로 변환하는 함수(bytes클래스의 메소드)
a = b'\xec\x95\x88\xeb\x85\x95'.decode('utf-8')#인자 없어도 디폴트 utf-8
print(a)#안녕
print(b'HELLO'.decode())#HELLO

#문자열 연결하기: + 사용
print('HELLO '+'WORLD')#HELLO WORLD
#print('HELLO '+'WORLD' + 2023)#에러발생, 자바처럼 그대로 String으로 붙여주지 않음
print('HELLO '+'WORLD ' + str(2023))#HELLO WORLD 2023

#문자열 반복하기: * 사용
#0또는 음수 곱하면 빈 문자열 출력(단, 실수 float는 곱하면 에러)
print('파이썬' * 20)




































