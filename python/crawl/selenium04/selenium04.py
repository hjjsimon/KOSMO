#스타벅스의 서울 전체 매장 스크래핑해서  CSV 및 JSON파일로 저장(셀레니엄+뷰티풀숩)
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import  By
from selenium.common.exceptions import NoSuchElementException,TimeoutException
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from bs4 import BeautifulSoup
import os,json,csv,re#표준 라이브러리

'''
Headless Browser:
화면이 존재하지 않는 브라우저로 웹 브라우저와 유사한 환경을
가졌지만 커맨드 라인 인터페이스(CLI)를 통해 실행하고 제어 할 수 있는 브라우저를
뜻한다
주로 웹 어플리케이션의 성능 테스트나 스크린 샷등에 사용한다
크롬은 윈도우 기준 크롬 59, 맥/리눅스 기준 크롬 60버전부터 크롬에 
Headless Mode가 정식으로 추가되었다.
PhantomJS역시 Headless Browser지만 크롬이 Headless를 지원하기 시작하면서
2018년부터 개발이 중단되었다
그리고 PhantomJS는 성능상의 문제점과 크롬과 완전히 동일하게 동작하지는 
않는다는 문제점이 있다. 
'''

def starbucks():
    try:
        # 1.WebDriver객체 생성
        dirname = os.path.dirname(os.path.realpath(__file__))
        driver_path = f'{os.path.join(dirname, "chromedriver.exe")}'
        service = Service(executable_path=driver_path)
        options = webdriver.ChromeOptions()
        options.add_experimental_option("detach", True)
        # Headless Browser를 위한 옵션 설정
        options.add_argument('headless')
        #일부 버그용
        options.add_argument('--disable-gpu')
        # 크기에 따른 요소 hidden방지용
        options.add_argument('window-size=1920x1080')
        # 일부 사이트의 크롬 headless 모드 접근 금지 해결을 위한 설정
        # headless chrome 이 아닌 chrome속임수용(User-Agent=Mozilla에서 띄어쓰기 없어야 한다)
        options.add_argument('User-Agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.5735.199 Safari/537.36')


        driver = webdriver.Chrome(service=service, options=options)

        # 2.브라우저에 스타벅스 매장찾기 로딩하기(STORE-매장찾기 클릭 주소)
        driver.get('https://www.starbucks.co.kr/store/store_map.do')
        # 지역->서울->전체 클릭해서 서울의 모든 스타벅스매장 크롤링해서
        # json파일과 csv파일로 저장
        # 3.지역버튼 찾고 클릭 처리하기(정적인 요소다)
        local=driver.find_element(By.XPATH,'//*[@id="container"]/div/form/fieldset/div/section/article[1]/article/header[2]/h3/a')
        local.send_keys(Keys.ENTER)
        # 4.서울 찾고 클릭 처리하기(동적인 요소)
        seoul=WebDriverWait(driver,5).until(EC.presence_of_element_located((By.XPATH,'//*[@id="container"]/div/form/fieldset/div/section/article[1]/article/article[2]/div[1]/div[2]/ul/li[1]/a')))
        seoul.click()
        # 5.전체 버튼 찾고 클릭처리하기(동적인 요소)
        all = WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.XPATH,'//*[@id="mCSB_2_container"]/ul/li[1]/a')))
        all.click()
        # 서울의 각 구의 매장을 표시하는 li를 찾을때까지 명시적 대기
        '''
            <ul class="quickSearchResultBoxSidoGugun">
            </ul>
        '''
        # ※ li를 찾을때까지 대기후 driver.page_source로 HTML소스를 가져와 작업해야한다
        # 방법1 : 암시적 대기 사용
        '''
        driver.implicitly_wait(10)
        driver.find_element(By.CSS_SELECTOR,'#mCSB_3_container > ul > li')#암묵적 대기가 적용됨 즉 #mCSB_3_container > ul > li요소를 찾을때까지 최대 10초 대기
        '''
        # 방법2 : 명시적 대기 사용
        WebDriverWait(driver,10).until(EC.presence_of_element_located((By.CSS_SELECTOR,'#mCSB_3_container > ul > li')))#WebElement

        soup = BeautifulSoup(driver.page_source,'html.parser')
        lis = soup.select('#mCSB_3_container > ul > li')
        #print(len(lis))
        stores=[]#[{},{},{},...]형태로 크롤링한 매장 저장
        # 추출예
        # 역삼아레나빌딩   서울특별시 강남구 언주로 425 (역삼동)1522-3232 리저브 매장 2번
        # 스타필드코엑스몰R   서울특별시 강남구 영동대로 513 (삼성동) 코엑스 A106호1522-3232 리저브 매장 2번
        # SSG마켓도곡R   서울특별시 강남구 언주로30길 57, 타워팰리스Ⅱ F 지하1층 (도곡동)1522-3232 리저브 매장 2번
        # 가양역사거리   서울특별시 강서구 화곡로 416 (등촌동) 가양더스카이벨리5차 지식산업센터 101, 102, 114,~117호1522-3232 리저브 매장 2번
        # 강서우장산   서울특별시 강서구 강서로 231 (화곡동, 우장산역 해링턴 타워), 116/117/118/119/120호1522-3232 리저브 매장 2번
        pattern = re.compile(r'(.+)\s{3}(.+)(\d{4}-\d{4})\s.+')
        for li in lis:
            #print(li.text)
            match=pattern.match(li.get_text())
            if match:
                stores.append({'store':match.group(1).strip(),'loc':match.group(2),'contact':match.group(3)})

        #print(stores)
        # 파이썬 객체(stores)를 JSON파일로 저장
        with open('starbucks.json','w',encoding='utf8') as f:
            f.write(json.dumps(stores,indent=4,ensure_ascii=False))
        # 파이썬 객체(stores)를 CSV파일로 저장
        with open('starbucks.csv','w',encoding='utf8',newline='') as f:
            writer = csv.DictWriter(f,fieldnames=['store','loc','contact'])
            writer.writeheader()
            writer.writerows(stores)
    except TimeoutException as e:
        print('지정한 요소를 찾을 수 없어요')
    finally:
        pass
        #브라우저 닫기
        #driver.quit()

if __name__ == '__main__':
    starbucks()

