#※urllib3는 requests모듈 설치시 함께 설치된다. 고로 파일명을 urllib3.py로 하지 말아라

import urllib.request as request
#urllib.parse 모듈 사용하기
import urllib.parse as parse

#1.quote() 및 quote_plus()함수 : url인코딩하는 함수
query=input('검색어를 입력하세요?')
#검색어로 한글 입력시 UnicodeEncodeError발생
#codeEncodeError: 'ascii' codec can't encode characters in position 14-16
#해결책
#URL에 한글이 포함되면  URL인코딩해서 요청해야 한다.parse모듈로 URL을 인코딩
#파이참이 %ED%8C%8C%EC%9D%B4%EC%B0%B8 URL인코딩
encoded_qoute = parse.quote(query)#빈 공백이 %20
encoded_qoute_plus = parse.quote_plus(query)#빈 공백이 +로 인코딩(권장)
print(encoded_qoute,encoded_qoute_plus,sep='\n')
url=f'https://www.google.com/search?q={encoded_qoute_plus}'
req=request.Request(url=url,headers={'user-agent':'Mozilla/5.0'})
print(dir(req))
print(f'요청방식:{req.get_method()},요청 서버:{req.host}')
print('요청 헤더:{}'.format(req.headers))
print('요청 url:%s' % req.full_url)
res = request.urlopen(req)
source=res.read().decode()
with open('google.html','w',encoding='utf8') as f:
    f.write(source)

#2. urlsplit(주소):URL을 각 부분으로 나눠 SplitResult객체 반환
urls = parse.urlsplit(url)
print(urls)
for url in urls:
    print(url)

print(f'protocol:{urls.scheme},domain:{urls.netloc},path:{urls.path},query:{urls.query}')
#3. urlunsplit():나눈 URL을 합치는 함수
print(parse.urlunsplit(urls))

#4. urlencode(딕셔너리):딕셔너리를 쿼리스트링 형태로 인코딩하는 함수
#                   {키1:값1,키2:값2}를 키1=값1&키2=값2
#즉 쿼리스트링으로 문자열을 만들고 URL인코딩도한다(인코딩방식은 quote_plus()함수와 같다)
print(parse.urlencode({'q':'파이참','oq':'파이참'}))
#oq파라미터 포함해서 검색
url =f"https://www.google.com/search?{parse.urlencode({'q':query,'oq':query})}"
req=request.Request(url=url,headers={'user-agent':'Mozilla/5.0'})
res = request.urlopen(req)
source=res.read().decode()
with open('google_.html','w',encoding='utf8') as f:
    f.write(source)


