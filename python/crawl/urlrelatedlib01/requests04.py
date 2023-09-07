#외부 REST API요청(JSONPlaceHolder)
import requests
#requests모듈에는 자바스크립트 JSON형태의 문자열을 파이썬의 리스트로 디코딩해주는 함수가 있다:json()
res= requests.get('https://jsonplaceholder.typicode.com/photos')
print(res.text)#<class 'str'>
print(type(res.json()))#<class 'list'>
photos=res.json()
for photo in photos:
    for key,value in photo.items():
        print(key,value,sep=' : ')
    print('-' * 20)

