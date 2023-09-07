#지마켓 쇼핑 상품 목록 정보 가져오기
from bs4 import BeautifulSoup
import requests

query,qty = input('검색어와 스크래핑 갯수를 입력하세요(공백)?').split()
url =f'https://browse.gmarket.co.kr/search?keyword={query}'
#1. HTML소스 가져오기
res=requests.get(url)
source = res.text
#2. HTML소스에서 BeautifulSoup로 원하는 데이타(상품명하고 가격) 가져오기
soup = BeautifulSoup(source,'html.parser')
#상품명
names=soup.select('#section__inner-content-body-container > div> div > div.box__item-container > div.box__information > div.box__information-major > div.box__item-title > span > a > span.text__item',limit=int(qty))
#print(names)
print(names[0].get('title'))
print(names[0].get_text().strip())
#가격
prices = soup.select('#section__inner-content-body-container > div > div > div > div.box__information > div.box__information-major > div.box__item-price > div.box__price-seller > strong',limit=int(qty))
print(prices[0].get_text())
#print(list(zip(names,prices)))
for name,price in zip(names,prices):
    print(f'{name.get("title")} : {price.get_text()}원')