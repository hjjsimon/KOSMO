#지마켓 쇼핑 상품 목록 정보 가져오기(함수버전-제너레이터 사용)
#페이징 끝번호 입력받아서 끝번호까지의 모든 상품목록 가져오기
from bs4 import BeautifulSoup
import requests


def start_requests(pages):
    '''
    인자로 받은 페이지 번호까지 요청 URL을 생성하는 함수
    :param pages:끝 페이지 번호
    :yield: url에 따른 스크래핑 결과를 반환(함수로 작성)
    '''
    query = input('검색어를 입력하세요?')
    for page in range(1,pages+1):
        url =f'https://browse.gmarket.co.kr/search?keyword={query}&k=44&p={page}'
        yield parse(url)

def parse(url):
    #1. HTML소스 가져오기
    res=requests.get(url)
    source = res.text
    #2. HTML소스에서 BeautifulSoup로 원하는 데이타(상품명하고 가격) 가져오기
    soup = BeautifulSoup(source,'html.parser')
    #상품명
    names=soup.select('#section__inner-content-body-container > div> div > div.box__item-container > div.box__information > div.box__information-major > div.box__item-title > span > a > span.text__item')

    #가격
    prices = soup.select('#section__inner-content-body-container > div > div > div > div.box__information > div.box__information-major > div.box__item-price > div.box__price-seller > strong')

    return zip(names,prices)

if __name__=='__main__':
    pages = int(input('끝 페이지 번호를 입력하세요?'))
    products=start_requests(pages)#<generator object start_requests at 0x000001BAB5F202E0>
    #print(products)
    for index,product in enumerate(products):
        print(f'[{index+1}페이지 상품 목록 입니다]')
        for name,price in list(product):
            print(f'상품명:{name.get("title")},가격{price.text}원')
