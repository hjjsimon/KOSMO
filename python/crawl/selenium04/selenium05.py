#페이스북에 자동으로 로그인하기
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import  By
from selenium.common.exceptions import NoSuchElementException,TimeoutException
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import os,time#표준 라이브러리

try:
    # 1.WebDriver객체 생성
    dirname = os.path.dirname(os.path.realpath(__file__))
    driver_path = f'{os.path.join(dirname, "chromedriver.exe")}'
    service = Service(executable_path=driver_path)
    options = webdriver.ChromeOptions()
    # 자동종료 막기
    options.add_experimental_option("detach", True)
    driver = webdriver.Chrome(service=service, options=options)

    # 2.브라우저에 네이버 로그인페이지 로딩하기
    #driver.get('https://nid.naver.com/nidlogin.login')
    '''
    #아래는 네이버에서 자동입력 방지 문자 조치해서 이제 더 이상 자동로그인이 안됨
    #방법1]각 요소별로 대기시간 지정
    #id = WebDriverWait(driver,10).until(EC.presence_of_element_located((By.ID,'id')))
    #pw = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.ID, 'pw')))
    #방법2]
    id=WebDriverWait(driver,10).until(EC.presence_of_element_located((By.ID,'id')))
    pw=driver.find_element(By.ID,'pw')    
    id.send_keys('아이디')
    pw.send_keys('비번')
    pw.send_keys(Keys.ENTER)
    '''

    '''
    # 입력 요소가 로드될때까지 기다리기
    WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.ID, 'id')))
    # 자바스크립트로 로그인 처리
    driver.execute_script("document.querySelector('#id').value='아이디';")
    driver.execute_script("document.querySelector('#pw').value='비번';")
    print('로그인 버튼 클릭전:',driver.current_url)

    driver.find_element(By.ID,'log.login').click()

    # 등록안함 버튼 처리
    print('로그인 버튼 클릭후:',driver.current_url)
    driver.find_element(By.ID,'new.dontsave').click()
    '''
    # 추가적인 execute_script()함수 사용예제 코드
    #첫번째 탭에 네이버가 열린다
    driver.get('https://www.naver.com')
    driver.implicitly_wait(5)
    #새로운 탭에 네이버 부동산이 열린다
    real_estate=driver.find_element(By.XPATH,'//*[@id="shortcutArea"]/ul/li[7]/a')
    real_estate.click()
    #역시 새로운 탭에 구글이 열린다
    driver.execute_script("window.open('https://www.google.com')")
    #2초후 첫번째 탭으로 이동
    time.sleep(2)
    print(driver.window_handles,type(driver.window_handles),sep='\n')#['A7F130B2F46B457641F5D471EA98B52A', '6CB936E7CE16F17DE50C30034977571F', 'C0B47494442B23DA54DDD6983AE107AD']
    driver.switch_to.window(driver.window_handles[0])#첫번째 탭으로 이동
    print(driver.current_url)
    driver.execute_script('document.title="네이버"')
    #1초후에 첫번째 탭을 제외한 모든 탭 닫기
    time.sleep(1)
    for index,tab in enumerate(driver.window_handles):
        if index:
            driver.switch_to.window(tab)
            driver.close()

except (TimeoutException,NoSuchElementException) as e:
    print('지정한 요소를 찾을 수 없어요')
finally:
    pass
    #브라우저 닫기
    #driver.quit()

