#스타벅스의 서울 전체 매장 스크래핑해서  CSV 및 JSON파일로 저장(셀레니엄+뷰티풀숩)
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import  By
from selenium.common.exceptions import NoSuchElementException,TimeoutException
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from bs4 import BeautifulSoup
import os,json,re#표준 라이브러리


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
        # headless chrome 이 아닌 chrome속임수용
        #options.add_argument('User-Agent = Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.5735.199 Safari/537.36')


        driver = webdriver.Chrome(service=service, options=options)

        # 2.브라우저에 스타벅스 매장찾기 로딩하기(STORE-매장찾기 클릭 주소)
        driver.get('https://www.starbucks.co.kr/store/store_map.do')
        # 3.지역버튼 찾고 클릭 처리하기(정적인 요소다)
        local=driver.find_element(By.XPATH,'//*[@id="container"]/div/form/fieldset/div/section/article[1]/article/header[2]/h3/a')
        local.send_keys(Keys.ENTER)
        # 4.서울 찾고 클릭 처리하기(동적인 요소)
        seoul=WebDriverWait(driver,5).until(EC.presence_of_element_located((By.XPATH,'//*[@id="container"]/div/form/fieldset/div/section/article[1]/article/article[2]/div[1]/div[2]/ul/li[1]/a')))
        seoul.click()
        # 5.전체 버튼 찾고 클릭처리하기(동적인 요소)
        all = WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.XPATH,'//*[@id="mCSB_2_container"]/ul/li[1]/a')))
        all.click()

        WebDriverWait(driver,10).until(EC.presence_of_element_located((By.CSS_SELECTOR,'#mCSB_3_container > ul > li')))#WebElement

        soup = BeautifulSoup(driver.page_source,'html.parser')
        lis = soup.select('#mCSB_3_container > ul > li')

        stores=[]#[{},{},{},...]형태로 크롤링한 매장 저장

        pattern = re.compile(r'(.+)\s{3}(.+)(\d{4}-\d{4})\s.+')
        for li in lis:
            match=pattern.match(li.get_text())
            if match:
                stores.append({'store':match.group(1).strip(),'loc':match.group(2),'contact':match.group(3)})

        print(stores)
    except TimeoutException as e:
        print('지정한 요소를 찾을 수 없어요')
    finally:

        return stores
        #브라우저 닫기
        #driver.quit()

if __name__ == '__main__':
    starbucks()

