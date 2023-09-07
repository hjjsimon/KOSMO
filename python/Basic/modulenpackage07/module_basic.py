'''
모듈 도큐먼트화 주석
현재 모둘(파일)에 대한 소개글을 적는다
'''
print('[현재 모듈의 이름공간(Namespace) 출력]')
x=10#x라는 변수명으로 이름공간에 추가됨
print(dir())#현재 모듈의 이름공간을 리스트로 반환
print(__file__)#현재 파일(혹은 모듈)의 위치
print(__doc__)#현재 모듈의 도큐먼트화 주석 출력
print(__name__)#현재 파일 실행시는 __main__ ,다른 스크립트 파일(.py)에서 import해서 모듈로 사용시는 파일명(.py제외)
'''
import시 모듈 읽어오는 순서
1.현재 디렉토리
2.환경변수
3.sys.path에 있는 내장 모듈 및 표준 라이브러리 경로
'''
import sys,os
print(dir(sys))
#print(sys.__file__)#__file__이 없다 즉 sys라는 모듈는  C언어로 만들어져 파이썬에 내장된 모듈로 .py파일(모듈형태)로 제공되지 않는다
print(sys.__doc__)
print(sys.__name__)#sys
print(dir(os))
print(os.__file__)
print(os.__doc__)
print(os.__name__)#os
print(sys.path)