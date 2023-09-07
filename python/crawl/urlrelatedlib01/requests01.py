#한글 파라미터가 있는 경우 url인코딩 해야한다 하지만 requests모듈은 한글 인코딩 불필요.
#표준 라이브러리가 아님으로 설치 필요
import requests
print(dir(requests))
res=requests.get('https://www.naver.com',headers={'user-agent':'Mozilla/5.0'})#requests.request(method='get',url='https://www.naver.com')와 같다
print(f'value:{res},type:{type(res)}')#<class 'requests.models.Response'>
print('응답코드:',res.status_code,sep='')
print('응답헤더:',res.headers,sep='')
print('응답바디(바이트 문자열):',res.content,'자료형:',type(res.content))#<class 'bytes'>
print('응답바디(문자열):',res.text,'자료형:',type(res.text))#<class 'str'>
print('네이버 인코딩 방식:',res.encoding)#UTF-8
with open('naver_requests.html','w',encoding='utf8') as f:
    f.write(res.text)
'''
str -> bytes: encode()(str의 메소드)
bytes -> str: decode()(bytes의 메소드)
'''
#응답과 관련된 요청객체 얻기
req=res.request
print(f'value:{req},type:{type(req)}')
print('요청방식:',req.method,sep='')
print('요청URL:',req.url,sep='')
print('요청헤더:',req.headers,sep='')#{'User-Agent': 'python-requests/2.29.0', 'Accept-Encoding': 'gzip, deflate, br', 'Accept': '*/*', 'Connection': 'keep-alive'}
