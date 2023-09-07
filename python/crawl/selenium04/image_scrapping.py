#구글 혹은 네이버 포탈등에서 이미지 스크래핑하기
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import  By
from selenium.common.exceptions import NoSuchElementException,TimeoutException
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import os,time#표준 라이브러리

#base64인코딩 문자열을 이미지로 저장하기위한 import
import base64
import requests


def base64_image_save(base64_src,query,startindex,dirpath,ext):

    for src in base64_src:
        #base64인코딩 문자열(/9i~->이진 데이타(b'x67x97~')
        image_data = base64.b64decode(src.split(',')[1])
        filename = os.path.join(dirpath, query + str(startindex) + "." + ext)
        with open(filename,'wb') as f:
            f.write(image_data)
        startindex+=1
def url_image_save(https_src,query,startindex,dirpath,ext):
    for src in https_src:
        filename = os.path.join(dirpath, query + str(startindex) + "." + ext)
        res=requests.get(src)
        with open(filename,'wb') as f:
            f.write(res.content)
        startindex+=1
def images_list(dirpath,selectors,driver):
    images=[]
    if dirpath == 'naver':
        images.extend(driver.find_elements(By.CSS_SELECTOR, selectors)) # [WebElement,WebElement....]
    else:
        for selector in selectors:
            images.extend(driver.find_elements(By.CSS_SELECTOR, selector))
    return images
def scrappingImages(ext,dirpath,url,selector,**kwargs):
    '''
    이미지 스크래핑하는 함수
    :param ext:스크래핑 이미지 확장자 .[ex] : 'png' 혹은 'jpg'
    :param dirpath:스크래핑한 이미지를 저장할 디렉토리명. [ex] : 'naver' 혹은 'google'
    :param url:스크래핑할 url.[ex] : 'https://www.google.com/search?q={}&sxsrf=ALeKk02aqZ_Du1tRnfKxD80NOvDFxZTtzg:1610336833601&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjz0L2X_JLuAhUTy4sBHXRgAK4Q_AUoAXoECBAQAw&biw=1455&bih=636'
    :param selector:스크래핑할 모든 이미지 요소(img)의 CSS Selector
    :param kwargs:query키워드로 전달한 검색어
    :return:스크래핑한 이미지 갯수 반환
    '''
    try:
        #1. 스크래핑한 이미지를 저장할 디렉토리 생성
        if not os.path.isdir(dirpath):
            os.mkdir(dirpath)

        # 2.WebDriver객체 생성
        dirname = os.path.dirname(os.path.realpath(__file__))
        driver_path = f'{os.path.join(dirname, "chromedriver.exe")}'
        service = Service(executable_path=driver_path)
        options = webdriver.ChromeOptions()
        options.add_experimental_option("detach", True)
        driver = webdriver.Chrome(service=service, options=options)
        # 3.브라우저에 url로드하기
        driver.get(url)
        # 4.모든 데이타를 로딩하도록 자바스크립트로 스크롤이 안될때까지 스크롤링한다
        # 스크롤 전 높이 구하기
        scrollHeight = driver.execute_script('return document.body.scrollHeight')
        print('scrollHeight(스크롤 전):',scrollHeight)
        while True:
            # 자바스크립트(자동으로)로 아래로 스크롤하기:window.scrollTo(x좌표,y좌표)
            driver.execute_script(f'window.scrollTo(0,{scrollHeight})')
            # 컨텐츠가 로드될때까지 다음 코드 진행을 멈춘다
            time.sleep(3)
            # 스크롤후 높이 다시 구하기
            scrollHeight_new = driver.execute_script('return document.body.scrollHeight')

            if scrollHeight == scrollHeight_new:
                break
            scrollHeight=scrollHeight_new
            print('scrollHeight(스크롤 후):', scrollHeight)

        # 5.스크롤링이 끝난후 이미지 스크래핑하기(모든 요소가 로드가 되었으니까)

        images=images_list(dirpath,selector,driver)

        # 6. 스크래핑한 img 요소의 src 속성에 따라 분리 즉 data:image~ 계열 및 https~계열
        base64_src=[]
        https_src=[]

        # 이미지명 네이밍 방식:고양이1.jpg 고양이2.jpg.....
        for image in images:
            # img태그의 src속성 가져오기(이미지의 url 혹은 base64인코딩 문자열)
            src = image.get_attribute('src') if image.get_attribute('src') else image.get_attribute('data-src')
            if src.startswith('http'):
                https_src.append(src)
            else:
                base64_src.append(src)

        base64_image_save(base64_src,query,1,dirpath,ext)
        url_image_save(https_src, query,len(base64_src)+1, dirpath, ext)


    except Exception as e:
        print('에러 발생:',e)
    finally:
        return len(images)

if __name__ =='__main__':
    category = int(input('이미지 스크래피할 사이트는?(1:네이버,2:구글)'))
    query=input('스크래핑할 이미지를 입력하세요?')
    dir = 'naver' if category==1 else 'google'
    '''
    구글용 셀렉터:
        #islrg > div.islrc > div > a.wXeWr.islib.nfEiy > div.bRMDJf.islir > img
        와
        #islrg > div.islrc > div > div > a.wXeWr.islib.nfEiy > div.bRMDJf.islir > img
    네이버용 셀렉터:
        #main_pack > section.sc_new.sp_nimage._prs_img._imageSearchPC > div > div.photo_group._listGrid > div.photo_tile._grid > div > div > div.thumb > a > img
    '''
    selector={
        1:'#main_pack > section.sc_new.sp_nimage._prs_img._imageSearchPC > div > div.photo_group._listGrid > div.photo_tile._grid > div > div > div.thumb > a > img',
        2:['#islrg > div.islrc > div > a.wXeWr.islib.nfEiy > div.bRMDJf.islir > img','#islrg > div.islrc > div > div > a.wXeWr.islib.nfEiy > div.bRMDJf.islir > img']
    }.get(category)
    google_url=f'https://www.google.com/search?sxsrf=AB5stBiSDXnoC_AVWRnpFv5NQY7uBY9S4Q:1689298560953&q={query}&tbm=isch&sa=X&ved=2ahUKEwizqZTFh42AAxW6cfUHHShVAgcQ0pQJegQIDRAB&biw=1280&bih=580&dpr=1.5'
    naver_url = f'https://search.naver.com/search.naver?where=image&sm=tab_jum&query={query}'
    url = naver_url if category==1 else google_url
    print(scrappingImages('JPG', dir, url, selector, query=query),
          '개의 이미지가 스크래핑 되었습니다', sep='')
