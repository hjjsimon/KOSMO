#POST방식으로 요청하기

#urlopen('요청URL') 혹은  urlopen(Request(요청url)) : GET방식
#urlopen(Request(요청url,data=params,headers={})): POST방식


import urllib.request as request
import urllib.parse as parse

#GET방식 요청-데이타 전송(쿼리스트링으로)
params = parse.urlencode({'id':'kosmo','pwd':'1234','name':'한소인'})
headers={'user-agent':'Mozilla/5.0'}
url = 'https://nid.naver.com/nidlogin.login'
req = request.Request(f'{url}?{params}',headers=headers)
res=request.urlopen(req)
print(req.get_method())
print(res.status)
print(res.read().decode())

#POST방식 요청
'''
TypeError: POST data should be bytes, an iterable of bytes, or a file object. It cannot be of type str
req = request.Request(url,headers=headers,data=params)
res=request.urlopen(req)
'''
req = request.Request(url,headers=headers,data=params.encode())
res=request.urlopen(req)
print(req.get_method())
print(res.status)
print(res.read().decode())

#스프링 서버로 POST방식요청(로그인)
url='http://127.0.0.1:9090/onememo/auth/LoginProcess.do'
params = parse.urlencode({'id':'kim','pwd':'1234'})
req = request.Request(url,headers=headers,data=params.encode())
res=request.urlopen(req)
with open('spring_login.html','w',encoding='utf8') as f:
    f.write(res.read().decode())

