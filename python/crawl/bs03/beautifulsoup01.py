import re
from builtins import isinstance, issubclass

from bs4 import BeautifulSoup
import bs4.element

html_doc = """<html><head><title>The Dormouse's story</title></head>
<body>
<p class="title"><b>The Dormouse's story</b></p>

<p class="story">Once upon a time there were three little sisters; and their names were
<a href="http://example.com/elsie" class="sister broder" id="link1">Elsie</a>,
<a href="http://example.com/lacie" class="sister" id="link2">Lacie</a> and
<a href="http://example.com/tillie" class="sister" id="link3">Tillie</a>;
and they lived at the bottom of a well.</p>
<div><p>Hello, <b>Beautiful Soup</b>!</p></div>
<div></div>
<p class="story">...</p>
"""
soup = BeautifulSoup(html_doc,'html.parser')
print(soup,type(soup),sep=' | ')
print(dir(soup))
print(soup.prettify())
#[1. 태그(요소) 찾기]
#1. 제일 먼저 발견되는 태그  요소 하나(<class 'bs4.element.Tag'>) 가져오기, 없으면 None
#1-1 soup.태그명 : 태그명은 반드시 소문자. 대문자는 None
print(soup.a,type(soup.a), sep= ' | ')#<class 'bs4.element.Tag'>
#1-2 soup.find("태그명") 태그명은 반드시 소문자. 대문자는 None
print(soup.find('a'),type(soup.find('a')),sep=' | ')

#※Tag객체의 이름공간은 BeautifulSoup객체(soup)의 이름공간과 거의 같다
tag = soup.a
print(dir(soup))#BeautifulSoup객체
print(dir(tag))#Tag객체
#Tag객체의 주요 메소드 및 속성
#가. 태그명 : name속성
if tag:
    print(tag.name)
#나. 시작태그와 종료태그 사이의 텍스트 : string 혹은 text속성 혹은 get_text()메소드
#string :시작태그와 종료태그 사이의 텍스트가 없는 경우 혹은 하위 요소가 있는 경우 None.
#text :시작태그와 종료태그 사이의 텍스트가 없는 경우 ''.하위 요소에 포함된 모든 텍스트를 반환
#get_text():시작태그와 종료태그 사이의 텍스트가 없는 경우 ''.Beautiful Soup의 현재 버전(4.x 버전)에서 사용되는 메서드(권장)
# getText()(Beautiful Soup(3.x 버전)에서 사용되던 메서드)와 동일

if tag:
    print(f'string:{tag.string},text:{tag.text},get_text():{tag.get_text()}')

print(f'string:{soup.div.string},text:{soup.div.text},get_text():{soup.div.get_text()},getText():{soup.div.getText()}')
#findNextSibling():Beautiful Soup(3.x 버전)에서 사용되던 메서드
#find_next_sibling():Beautiful Soup의 현재 버전(4.x 버전)에서 사용되는 메서드(권장)

print(f'string:{soup.div.find_next_sibling().string},text:{soup.div.findNextSibling().text}')

#2. 태그명에 해당하는 모든 태그 요소 찾기(즉 첫번째 발견되는 태그가 아닌) 찾으면 [Tag객체,Tag객체2,......] 반환
#   없으면 None 빈 ResultSet([])를 반환
#2-1 soup("HTML태그명")
tags = soup('a')
print(tags,type(tags),sep=' | ')
#2-2 soup.find_all("태그명")
tags = soup.find_all('a')
print(tags,type(tags),sep=' | ')
#2-3 원하는 갯수의 태그만 찾기:soup.find_all("태그명",limit=갯수)
tags = soup.find_all('a',limit=2)#요소는 Tag객체
print(tags,type(tags),sep=' | ')

for tag in tags:
    print(tag.text,tag.name,sep=' | ')
#2-4 시작태그와 종료태그 사이의 텍스트에서 특정 문자열 찾기(문자열이 정확히 일치해야함):soup.find_all(string="찾은 문자열")
tags=soup.find_all(string="The Dormouse's story")#요소는 NavigableString객체
print(tags,type(tags[0]),sep=' | ')#["The Dormouse's story", "The Dormouse's story"] | <class 'bs4.element.NavigableString'>
print(dir(tags[0]))#Tag객체의 메소드 및 str객체의 메소드도 갖고 있다

print(isinstance(tags,list))
print(isinstance(tags,bs4.element.ResultSet))
print(issubclass(bs4.element.ResultSet,list))

print(isinstance(tags[0],str))
print(isinstance(tags[0],bs4.element.NavigableString))
print(issubclass(bs4.element.NavigableString,str))

#2-5 텍스트에서 특정 문자열 찾기(정규 표현식으로 찾기):soup.find_all(string=re.compile("정규표현식"))
tags = soup.find_all(string=re.compile(r'.+ie'))
print(tags,type(tags[0]),sep=' | ')
#3. 태그의 속성으로 태그  가져오기
'''
soup.find(태그의 속성명="속성값")#태그의 속성명을 키워드 인수로 지정
soup.find(name=None,{'태그의 속성명':'속성값'}) 모든 태그에서 찾을때는 태그명 None 지정
'''
print(soup.find(id='link1'))#속성명 = 속성값,속성명을 키워드인수로 지정
#태그의 class속성을 그대로 키워드로 사용불가.왜냐하면 파이썬에서 class는 예약어.class대신 class_사용
print(soup.find(class_='sister'))
print(soup.find(name=None,attrs={'id':'link1'}))
print(soup.find(name=None,attrs={'class':'sister'}))

print(soup.find_all(class_='sister'))
print(soup.find_all(name=None,attrs={'class':'sister'}))

#4. CSS셀렉터로 태그 가져오기(XPATH는 지원안함)
'''
select('CSS셀렉터'):find_all()과 같다
select_one('CSS셀렉터'):find()와 같다
'''
print(soup.select('p > a'))
print(soup.select_one('p > a'))

#4. 태그(요소)의 속성명으로 속성값 읽어오기
'''
soup.태그명['속성명']
soup.태그명.get('속성명') #마치 딕션너리의 키값(태그의 속성)으로 읽어오는 것과 같다
soup.태그명.attrs['속성명']
즉 Tag객체['속성명'],혹은 Tag객체.get('속성명') 혹은 Tag객체.attrs['속성명']
왜냐하면 soup.태그명는 Tag객체니까
'''
#1.href 속성의 값 읽어오기:soup.태그명
print(soup.a['href'],soup.a.get('href'),soup.a.attrs['href'] ,sep=' | ')
#Tag객체의 없는 속성으로 가져올때
#soup.a['title']#KeyError: 'title'
#soup.a.get('title')#''
#soup.a.attrs['title']#KeyError: 'title'
tag = soup.a
print(tag['href'],tag.get('href'),tag.attrs['href'] ,sep=' | ')

#모든 a요소를 찾아서 각 a요소의 모든 속성값을 읽어오자
tags = soup.find_all('a')
for tag in tags:
    # 태그의 class속성은 빈 공백으로 구분해서 여러개 지정 가능하기 때문에 list로 반환
    print(f'href:{tag.get("href")},class:{tag.get("class")},id:{tag.get("id")},text:{tag.get_text()}')
    print(f'href:{tag.get("href")},class:{" ".join(tag.get("class"))},id:{tag.get("id")},text:{tag.get_text()}')





