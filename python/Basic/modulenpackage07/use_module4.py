#use_module4.py: import가 아닌 from절을 사용해서  module4 사용하기
#from 패키지.모듈명 import * 혹은 변수,함수,클래스 바로 이름(변수명,함수명,클래스명)으로 접근
#즉 패키지 초기화 파일 설정 불필요
'''
from date.module4 import *
print(dir())
print(year(),month(),date(),sep='-')
'''

#from 패키지 import * 혹은 변수,함수,클래스
from date import *
print(dir())#이름공간에 아무 것도 없다. 즉 패키지 초기화 파일 설정 필요
            #import  패키지명,from 패키지명 형식으로 불러올때는 반드시 패키지 초기화 파일에
            #설정을 추가해야 한다
#print(year(),month(),date(),sep='-')#NameError: name 'year' is not defined
print(year(),month(),sep='-')#초기화 설정 후



