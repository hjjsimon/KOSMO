#구글에 자동으로 검색하기

#웹 드라이버 생성을 위한 모듈
from selenium import webdriver
#웹드라이버 생성을 위한 서비스 클래스
from selenium.webdriver.chrome.service import Service
#키보드 키값이 정의된 클래스
from selenium.webdriver.common.keys import Keys
#위치 지정자(셀렉터)를 위한 클래스
from selenium.webdriver.common.by import  By
#지정한 시간동안 요소를 못 찾을때 발생하는 예외
from selenium.common.exceptions import NoSuchElementException
import os,random#표준 라이브러리
#크롬드라이버(chromedriver.exe)가 위치한 경로(절대 경로)
print(__file__)#현재 모듈의 물리적 위치
print(os.path.realpath(__file__))#현재 운영체제에 맞는 디렉토리 표기
dirname=os.path.dirname(os.path.realpath(__file__))
print(dirname)
print(f'{os.path.join(dirname,"chromedriver.exe")}')

try:
    # 1.WebDriver객체 생성
    driver_path=f'{os.path.join(dirname,"chromedriver.exe")}'
    service = Service(executable_path=driver_path)
    options=webdriver.ChromeOptions()
    options.add_experimental_option("detach",True)#자동종료 막기
    driver= webdriver.Chrome(service=service,options=options)
    '''
    ※selenium은 기본적으로 웹 자원들이 모두 로드될때까지 
    기다린다
    또한 implicitly_wait메소드를 통해 모든 자원이 로드될때 까지 
    기다리게 하는 시간을 직접 설정할 수도 있다.
    '''

    # 2.implicitly_wait(초)로 최대 지정한 초까지 요소가 나타날때까지 조건없이 기다리기(지정 초까지 요소 못찾을시 예외처리하고자 하는경우)
    driver.implicitly_wait(random.randint(3,5))#랜덤하게 3~5초 사이의 초로 지연 설정
    # 3.get('사이트 주소')함수로 1번에서 실행된 크롬 브라우저에 사이트 띄우기
    driver.get('https://www.google.com')
    '''
    # [요소 하나 찾기]
    # 4.find_element(By.셀렉터,셀렉터 문자열) : 발견된 요소 첫번째 반환(WebElement).요소를 못찾을때는 에러
    #   Beautifulsoup 는 find() : 발견된 요소 첫번째 반환(Tag)
    element = driver.find_element(by=By.NAME,value='q')#예외 발생시키기위해 name속성 아무거나 지정
    # 5.찾은 요소에 send_keys('텍스트')함수로 찾은 요소에 값 보내기
    element.send_keys('selenium')#자동으로 입력상자에 selenium라는 검색어를 넣는다
    # 6. Enter 키 전송하기
    element.send_keys(Keys.RETURN)#혹은 Keys.ENTER
    '''
    # [요소 여러개 찾기]
    # 4.find_elements계열로 찾은 경우:리스트 반환 [WebElement,.....].요소를 못찾을때는 [] 반환
    #   Beautifulsoup 는 find_all() : ResutSet반환 [Tag,.....]
    elements = driver.find_elements(by=By.NAME, value='q1')
    print(elements,type(elements),sep=' | ')#[WebElement] input 요소 즉 name속성값이 'q'가 하나니까
    if elements:
        # 5.찾은 요소에 send_keys('텍스트')함수로 찾은 요소에 값 보내기
        elements[0].send_keys('셀레니엄')
        # 6. Enter 키 전송하기
        elements[0].send_keys(Keys.ENTER)
    else:
        print('요소를 찾을 수 없어요')

#implicitly_wait는 NoSuchElementException 발생
#explicitly_wait는 TimeoutException예외가 발생
except NoSuchElementException as e:
    print('지정한 요소를 찾을 수 없어요')
finally:
    pass
    #브라우저 닫기
    #driver.quit()

