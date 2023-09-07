#위키피디아에서 윤동주 작품 아래처럼 수집하여라
from bs4 import BeautifulSoup
import requests
import re
"""
《새 명동》
《서시(序詩)》
《또 다른 고향》
《별 헤는 밤》
《하늘과 바람과 별과 시》
《사진판 윤동주 자필 시고전집》
《별을 사랑하는 아이들아》
"""

res = requests.get('https://ko.wikipedia.org/wiki/윤동주')
soup = BeautifulSoup(res.text,'html.parser')


books = soup.find(name=None,attrs= {'id':'mw-content-text'})#Tag 혹은 NavigableString 혹은 None
books=books.find_all(name='div',attrs={'class':'mw-parser-output'})#ResultSet
#print(list(books[0].find_all(name='ul')[0].children))
books = list(books[0].find_all(name='ul')[0].children)
print(books)#'\n' 제거 전
books = list(filter(lambda x : x != '\n',books))
print(books)#'\n' 제거 후

#방법1:정규 표현식 미 사용:li요소들 가져온다
'''
for book in books:
    a=book.find('a')
    if a :
        print(f'《{a.get_text()}》')
    else:
        print(book.get_text())
'''
#방법2:정규표현식-패턴객체 사용:li요소들 가져온다
for book in books:
    pattern = re.compile('《.+》')
    #print(pattern.match(book.text))
    result = pattern.match(book.text)
    print(result.group())





# Copy - Copy selector 시 "#mw-content-text > div.mw-parser-output > ul:nth-child(56) > li"
# 로 추출하고자는 li요소들을 가져올 수 있으나
# 2023/07/13현재 동적으로 다른 요소가 추가되어서 res.text와 구조가 다르다
# 따라서 selenium을 사용해야함 CSS Selector로 요소를 가져올 수 있다

#방법1:정규 표현식 미 사용:li요소들 가져온다

#for book in books:
#    print(book)

