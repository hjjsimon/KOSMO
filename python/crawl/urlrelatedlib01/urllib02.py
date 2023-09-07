#urllib.request 모듈 사용하기 2

import urllib.request as request

#urllib.request모듈의 urlretrieve()함수와 urlopen()함수로 이미지 스크래핑하기

#아래는 urllib.error.HTTPError: HTTP Error 403: Forbidden에러
#프로그램으로 요청한게 아니고 브라우저로 요청한거로 인식 되도록 요청헤더를 추가해야 한다
#request.urlopen('https://images.pexels.com/photos/3081487/pexels-photo-3081487.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500')
#urlopen(url:str)함수 호출시 403에러 해결
#urlopen(Request객체) 이때 Request객체 생성시 요청헤더를 추가한다
req=request.Request('https://images.pexels.com/photos/3081487/pexels-photo-3081487.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',headers={'user-agent':'Mozilla/5.0'})
res=request.urlopen(req)
binary=res.read()
with open('landscape1.jpg','wb') as f:
    f.write(binary)
#urlretrieve()함수 호출시 403에러 해결
opener=request.build_opener()
opener.addheaders=[('user-agent','Mozilla/5.0')]#인자는 [(str,str)]
request.install_opener(opener)

request.urlretrieve('https://images.pexels.com/photos/3081487/pexels-photo-3081487.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500','landscape2.jpg')
