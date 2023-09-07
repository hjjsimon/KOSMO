#POST방식으로 요청 보내기
#방법1:requests.post(요청url)
#방법2:requests.request('POST',요청url)

#로그인이 필요 없을때는 requests.post()
#로그인이 필요한 경우 세션이 필요하기 때문에 즉 브라우저로 요청하는게 아니라 프로그램으로 요청함으로
#1.session=requests.Session() 로 세션 객체를 얻고
#2.세션 객체로 session.post() 즉 마치 브라우저의 세션처럼
import requests
#[1. 세션이 필요없는 POST 요청]
url='http://localhost:9090/Annotation/RequestMappingBoth.do'
#res = requests.post(url=url,data={'id':'KIM','pwd':'1234'})#요청헤더 미 설정해도 Forbidden에러 안남
res = requests.request(method='POST',url=url,data={'id':'KIM','pwd':'9999'})
print('요청 방식:',res.request.method)
print('응답바디(HTML소스):',res.text)
#[2. 세션이 필요한 POST 요청]
session = requests.Session()
#세션객체로 post()
url='http://localhost:9090/onememo/auth/LoginProcess.do'
res = session.post(url=url,data={'id':'kosmo','pwd':'1234'})
print('요청 방식:',res.request.method)
print('상태 코드:',res.status_code,sep='')
print('응답헤더:',res.headers,sep='')
print('응답바디(HTML소스):',res.text)




