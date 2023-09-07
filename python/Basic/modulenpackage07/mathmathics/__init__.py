#패키지 초기화 파일
#"import 패키지명" 으로만 import 할수 있도록 __init__.py 초기화 하기

#원리]현재 패키지(.) 즉 mathemathics디렉토리의  module3를 불러온다
#단 .(상대경로 표기법)는 실행 파일로 사용하는 모듈에서는  사용불가(에러발생)
#방법1]
#from . import module3
#방법2]
from .module3 import *