#use_module3.py:패키지 mathmathics를 import해서 사용하기

'''
#1. import 패키지명.모듈명 : 이때는 패키지 초기화 파일 설정 불필요
import mathmathics.module3
print(dir())#'mathmathics'만 보임 module3는 없름.즉 패키지명.모듈명.(mathmathics.module3.)으로 접근해야 한다
print(mathmathics.module3.add(10,5))
print(mathmathics.module3.minus(10,5))
'''
#2. import 패키지명
#패키지 초기화 파일(__init__.py) 설정 전에는 mathmatics를 모듈로 인식한다 즉(mathmatics.py)로 인식
#즉 modulepackage07패키지안에서 mathmathics.py를 찾게된다
import mathmathics
print(dir())
#print(mathmathics.module3.add(10,5))#AttributeError: module 'mathmathics' has no attribute 'module3'
#※ "import 패키지명" 을 사용하려면 패키지 초기화 파일에 설정을 해야 한다
#   즉 패키지명를 패키지로 인식하도록
print(mathmathics.module3.add(10,5))
print(mathmathics.module3.minus(10,5))
