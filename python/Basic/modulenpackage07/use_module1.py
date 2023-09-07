#use_module1.py:모듈 module1.py를 import해서 사용하기
#모듈 불러오기:import 모듈명(.py를 제외한 파일명)
import module1
print('-' * 30)
import module1#최초 import한것 만 유효하다.의미 없다.즉 한번만 import하면 된다
print(dir(module1))#module1이라는 모듈명으로 접근할 수 있는 이름(변수,함수,클래스등) 출력
#print(PI)#NameError: name 'PI' is not defined
print(module1.PI)#모듈명.변수