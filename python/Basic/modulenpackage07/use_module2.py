#use_module2.py:모듈 module2.py를 import해서 사용하기
'''
#1. import 모듈명
import module2
print(dir())#현재 파일의 이름공간 출력-이름공간에 module2만 있으므로 module2.으로 접근
print(dir(module2))
print('변수:',module2.PI)
print('함수:',module2.add(*[i for i in range(1,11)]))
print('클래스:',module2.MyClass().hello())
print('math모듈 사용:',module2.math.pi)#math모듈 사용시 또 import불필요
'''

'''
#2. import 모듈명 as 별칭
import module2 as md2
print(dir())
#print('변수:',module2.PI)#NameError: name 'module2' is not defined
#별칭.변수 혹은 별칭.함수명() 별칭.클래스명()
print('변수:',md2.PI)
print('함수:',md2.add(*[i for i in range(1,11)]))
print('클래스:',md2.MyClass().hello())
print('math모듈 사용:',md2.math.pi)
'''

'''
#3. from 모듈명 import * 혹은 변수[,함수,클래스]
#모듈명 혹은 별칭 필요없이 바로 변수,함수명,클래스명으로 접근가능

from module2 import *
print(dir())
print('변수:',PI)
print('함수:',add(*[i for i in range(1,11)]))
print('클래스:',MyClass().hello())
print('math모듈 사용:',math.pi)
'''
'''
from module2 import add,MyClass
print(dir())
#print('변수:',PI)#NameError: name 'PI' is not defined
print('함수:',add(*[i for i in range(1,11)]))
print('클래스:',MyClass().hello())
'''
#4. from 모듈명 import 변수 as 별칭1[,함수 as 별칭2,클래스 as 별칭3]
from module2 import add as ad,MyClass as mc
print(dir())
#print('변수:',PI)#NameError: name 'PI' is not defined
print('함수:',ad(*[i for i in range(1,11)]))
print('클래스:',mc().hello())




