#1. requests모듈 사용:JSON이 내장되어 있어 추가적으로 json모듈 import 불필요
import requests
import json

res = requests.get('https://jsonplaceholder.typicode.com/users')
print(res.text,type(res.text),sep='\n')
print('[json모듈의 loads()함수 사용]')
print(json.loads(res.text),type(json.loads(res.text)),sep='\n')
print('[requests모듈의 Response객체.json()함수 사용-import json 불필요]')
users = res.json()
print(users,type(users),sep='\n')
for user in users:
    print(f'[{user["name"]}의 정보]')
    for key,value in user.items():
        if isinstance(value,dict):
            for k,v in value.items():
                if k=='geo':
                    print('회사 위치',end='-')
                    print(f'위도:{v.get("lat")},경도:{v["lng"]}')
                else:
                    print(f'{k} : {v}')
        else:
            if key != 'name':
                print(f'{key} : {value}')

