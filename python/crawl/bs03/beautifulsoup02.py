from bs4 import BeautifulSoup
import requests
#1.requests로 요청 해서 html소스 가져오기
url = 'https://finance.naver.com/marketindex/'
res = requests.get(url)
source = res.text
#print(source)
#2. BeautifulSoup로 html소스에서 필요한 데이타 스크래핑
soup = BeautifulSoup(source,'html.parser')
dollars=soup.select_one('#exchangeList > li.on > a.head.usd > div > span.value')
#print(dollars)
print(f'1달러 환율:{dollars.text}원')

yen= soup.select_one('#exchangeList > li > a.head.jpy > div > span.value')
print(f'100엔 환율:{yen.text}원')