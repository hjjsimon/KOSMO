#urllib.request 모듈 사용하기 1

import urllib.request as request
#urllib.request모듈의 urlretrieve()함수와 urlopen()함수로 HTML소스 스크래핑하기

#urlopen()함수 : HTTPResponse객체 반환
#               HTTPResponse객체의 read()메소드로 html소스 얻을 수 있다
#urlretrieve()함수: html소스를 파일로 바로 다운받는 함수

#urlopen()함수
#request.urlopen('URL 주소') 혹은 request.urlopen(Request객체)
res=request.urlopen('https://www.naver.com')
print(f'value:{res},type:{type(res)}')#<class 'http.client.HTTPResponse'>
print(dir(res))
print('응답 코드:',res.status)
#응답헤더 얻기 1:HTTPResponse객체.headers
print(res.headers)
#응답헤더 얻기 2:HTTPResponse객체.getheaders()
print(res.getheaders())
print('-' * 50)
for name,value in res.getheaders():
    print(f'{name} : {value}')
#응답헤더 얻기 3:HTTPResponse객체.getheader('헤더명')
print(res.getheader('Content-Type'))
#print(type(res.read()))#<class 'bytes'>
source=res.read().decode()#바이너리 문자열을 문자열로 변환
print(source)

with open('naver_urllib01-1.html','w',encoding='utf-8') as f:
    f.write(source)

#urlretrieve()함수:('파일명', <http.client.HTTPMessage at 0x26fe9755df0>)튜플 반환
# 즉 ('파일명','응답헤더(HTTPMessage)')
tuple_=request.urlretrieve('https://www.naver.com',filename='naver_urllib01-2.html')
print(tuple_)
print(tuple_[0])#파일명
print(tuple_[1])#응답헤더


