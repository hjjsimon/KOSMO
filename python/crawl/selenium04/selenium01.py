#Selenium으로 브라우저 제어 테스트 하기

import selenium #정상 설치확인 혹은 버전 확인용
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.keys import Keys
import time

print(help(selenium))#4.10.0

#driver = webdriver.Chrome(executable_path='chromedriver.exe')#DeprecationWarning: executable_path has been deprecated, please pass in a Service object
#1.웹드라이버를 위한 Service객체 생성
service = Service(executable_path='chromedriver.exe')

#2.웹드라이버 객체 생성-브라우저 제어용
#웹 드라이버 옵션 설정
'''
옵션정리
add_experimental_option("detach", True) : 브라우저 자동종료 방지
add_argument('headless') # headless 모드 설정 
add_argument('window-size=1920x1080') #브라우저 사이즈 설정(요소 숨김 방지용-크기작을때).headless일때만 동작
add_argument('--start-fullscreen') #풀 사이즈 설정(headless일때 미 적용됨. 위처럼 크기 직접 지정)
add_argument("--disable-gpu" ) #gpu(그래픽 카드 가속) 사용 안하도록 설정(윈도우 환경일때).The --disable-gpu flag is a temporary work around for a few bugs
add_argument("lang=ko_KR") #한국어로 실행
add_argument('User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36')#headless chrome이 아닌 chrom임을 위한 설정(일부 사이트에서 headless금지)
add_argument("--no-sandbox")# 리눅스 환경일때.또한 add_argument('window-size=1920x1080')도 함께 설정

※Headless Chrome으로 실행시
selenium.common.exceptions.WebDriverException: Message: unknown error: Chrome failed to start: was killed.
 에러 발생시
 add_argument('headless') 만 설정한다
'''
#브라우저 자동종료 막기를 위한 드라이버 옵션추가
options=webdriver.ChromeOptions()
options.add_experimental_option("detach",True)
driver = webdriver.Chrome(service=service,options=options)
print(driver)#<selenium.webdriver.chrome.webdriver.WebDriver (session="3ccdae566b82cf6f2bafd5db9987efa1")>
#3.사이트 요청 즉 브라우저로 사이트 로딩
'''
-WebDriver객체(driver)의 주요 속성 및 메소드
page_source : html 소스
current_url : 드라이버에 의해 요청된 현재 URL
get('요청주소') : 브라우저에 요청주소를 로딩
implicitly_wait(초단위) : 요소를 찾을때까지 지정한 최대 초만큼 지연.찾으면 바로 실행
find_element : WebElement반환  ,못 찾으면 에러발생
find_elements : [WebElement,....]반환,못찾으면 빈 [] 반환
quit() : 브라우저 close
close() : 해당 탭만 close
execute_script('자바스크립트 코드')
'''
driver.get('https://www.naver.com')
#print(driver.page_source)
with open('naver.html','w',encoding='utf8') as f:
    f.write(driver.page_source)
print(driver.current_url)
#4.요소(태그) 찾기:WebDriver의 find_element계열 메소드의 반환타입은 WebElement
#time.sleep(5)#요소를 찾든 못찾든 무조간 5초 지연 즉 아래 코드가 5초동안 실행 안됨
driver.implicitly_wait(5)#즉 최대 5초동안 요소를 찾고 못찾으면 NoSuchElementException예외 발생
print('5초 블락킹 된후 출력')
from selenium.webdriver.common.by import  By
'''
WebElement의 속성 및 메소드
    text : 시작태그와 종료태그 사이의 텍스트
    get_attribute(\"속성명\") : 속성명에 따른 속성값 반환
    click() : 찾은 요소의 클릭 이벤트를 발생시키는 메소드
    send_keys('텍스트' 혹은 Keys계열 필드) : 찾은 요소(주로 input계열 요소)에 텍스트 나 키보드 키 입력 메소드
'''
element=driver.find_element(by=By.CSS_SELECTOR,value='#shortcutArea > ul > li:nth-child(4) > a')
print(element)#<selenium.webdriver.remote.webelement.WebElement (session="9e78ec42db79610a07baaa4f20ba76ad", element="1D3833ADA23ED613808784981B84B610_element_57")>
print(element.text)
print(element.get_attribute('href'))
shopping_url=element.get_attribute('href')
#쇼핑 클릭하기-원래 새로운 탭에 쇼핑 사이트가 뜬다
element.click()


#아래 실행시 무조건 요소를 찾을 수 없다는 에러 발생
#왜냐하면 현재 드라이버는 https://www.naver.com주소의 html소스에서 찾기때문에
#따라서 드라이버를 새로운 탭(쇼핑사이트)으로 변경해야 한다
driver.switch_to.window(driver.window_handles[1])
element = driver.find_element(By.CLASS_NAME,'_searchInput_search_text_3CUDs')
#검색어 자동 입력
#driver.execute_script('document.querySelector("._searchInput_search_text_3CUDs").value="컴퓨터";')
element.send_keys("컴퓨터")
element.send_keys(Keys.ENTER)
#브라우저 화면 캡처하기
#driver.get_screenshot_as_file('naver.png')#save_screenshot('naver.png')와 같다
driver.save_screenshot('naver.png')

time.sleep(2)#2초 지연후 닫기
#현재 드라이버에 보이는 탭 닫기
#driver.close()
#현재 브라우저 다기
driver.quit()

'''
#https://www.selenium.dev/documentation/webdriver/browser/frames/
1.iframe태그요소를 찾는다
iframe = driver.find_element(By.CSS_SELECTOR, "#modal > iframe")

2.찾은 iframe으로 변경한다
driver.switch_to.frame(iframe)

3.이제부터는 find계열로 ifame문서에서 요소를 찾는다
driver.find_element(By.TAG_NAME, 'button').click()
4.driver.get()으로 요청한 문서로 빠져 나올때 즉 iframe소스에서 부모 소스로 전환할때
driver.switch_to.default_content()
'''




