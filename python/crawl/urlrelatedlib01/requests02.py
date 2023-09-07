#GET방식으로 요청 보내기
#방법1:requests.get(요청url)
#방법2:requests.request('GET',요청url)
import requests

url='https://images.pexels.com/photos/3081487/pexels-photo-3081487.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500'
#res = requests.get(url=url)#요청헤더 미 설정해도 Forbidden에러 안남
res = requests.request(method='get',url=url)
print('요청 방식:',res.request.method)
with open('scrapping.jpg', 'wb') as f:
    f.write(res.content)