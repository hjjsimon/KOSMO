#페이스북에 자동으로 로그인하기
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import  By
from selenium.common.exceptions import NoSuchElementException,TimeoutException

#Exlicity wait사용시 추가 클래스
#기대조건이 만족할때까지 기다리기 위한 함수 사용을 위한 클래스
from selenium.webdriver.support.ui import WebDriverWait
#expected_conditions는 Selenium 2.26.0 이후 부터 사용 가능
#기대조건을 설정하기위한 클래스
from selenium.webdriver.support import expected_conditions as EC

import os#표준 라이브러리




try:
    # 1.WebDriver객체 생성
    dirname = os.path.dirname(os.path.realpath(__file__))
    driver_path = f'{os.path.join(dirname, "chromedriver.exe")}'
    service = Service(executable_path=driver_path)
    options = webdriver.ChromeOptions()
    # 자동종료 막기
    options.add_experimental_option("detach", True)
    # 페이스북 로그인 후 권한 허용 확인창이 뜨는 경우
    # 웹드라이버 생성시 options키워드 인수로 추가옵션을 설정해야 한다
    # 크롬 옵션 정의 (1이 허용, 2가 차단)
    options.add_experimental_option("prefs",{"profile.default_content_setting_values.notifications": 1})

    driver = webdriver.Chrome(service=service, options=options)

    # 2.브라우저에 페이스북 로그인페이지 로딩하기
    driver.get('https://ko-kr.facebook.com/')
    # 명시적 대기(Explicit Wait):권장
    # 3.기대조건 설정
    email_ec=EC.presence_of_element_located((By.ID,'email2'))
    # 4.위 기대 조건을 만족할때까지 Wait 즉 id속성이 'email'인 요소를 찾을때까지 대기
    username=WebDriverWait(driver=driver,timeout=3).until(email_ec)
    print(username)#WebElement
    username.send_keys('페북 아이디')

    #위의 'email'이라는 id를 가진 요소가 로드가 되었기때문에
    #당연히 아래 비번입력요소나 로그인 버튼이 로드 된 경우가 99.9999999%다
    #아래처럼 명시적 대기를 하지않고 바로 드라이버의 find_element()찾아도 된다
    '''
    pass_ec = EC.presence_of_element_located((By.ID, 'pass'))
    password = WebDriverWait(driver=driver, timeout=3).until(pass_ec)
    '''
    password =driver.find_element(By.ID,'pass')
    password.send_keys('페북 비번')
    # 엔터키로 로그인
    #password.send_keys(Keys.ENTER)
    # 버튼 클릭으로 로그인
    driver.find_element(By.NAME,'login').click()

except TimeoutException as e:
    print('지정한 요소를 찾을 수 없어요')
finally:
    pass
    #브라우저 닫기
    #driver.quit()

