'''
pip install requests

-설치한 openai 라이브러리는  ChatGPT API 외에도 DALL-E API도 사용가능
 (DALL-E API 사용시 API KEY가 필요없다)
-생성된 이미지는 URL 형태(기본값)로 제공(URL은 1시간 후에 만료)
 https://platform.openai.com/docs/guides/images/usage
 URLs will expire after an hour.
-response_format를 b64_json로 설정시 base64로 인코딩된 문자열을 반환

※한글로 이미지 생성 prompts를 전달하면 원하는 이미지가 생성이 안된다.
그래서 영어로 번역후 번역된 prompts를 DALL-E모델에 전달한다
팁 : 한글을 gpt-3.5-turbo에게 번역 의뢰->번역된 영어로 DALL-E모델에게 전송
'''

import openai
import os
import requests
import json

OPENAI_API_KEY=os.getenv('OPENAI_API_KEY')
'''
#DALL-E REST API로 요청

url='https://api.openai.com/v1/images/generations'
data={
    "prompt": "A cute baby sea otter",
    "n": 1,#이미지 수
    "size": "256x256",#이미지 크기 256,512,1024중 하나(디폴트 1024)
    "response_format":'b64_json'#'url','b64_json'중 하나(디폴트 'url')
  }
headers={"Content-Type": "application/json",'Authorization': f'Bearer {OPENAI_API_KEY}'}
response=requests.post(url=url,data=json.dumps(data),headers=headers)
answer=response.json()
#print(answer)
#print(answer['data'][0]['url'])#"response_format"이 "url"일때
b64_json=answer['data'][0]['b64_json']
#b64_json 인코딩 문자열을 이미지로 저장
import base64
binary_encoded=base64.b64decode(b64_json)#바이너리 데이타로 디코딩
with open('otter.jpg','wb') as f:
    f.write(binary_encoded)
'''
'''
#DALL-E API 사용
try:
    #openai.api_key = os.getenv("OPENAI_API_KEY")#이미지 생성 API사용시(필요없다)
    prompts = input('생성할 이미지를 묘사해 주세요?')#한글 입력시 원하는 결과가 나오지 않는다
    response=openai.Image.create(
      prompt=prompts,#이미지 생성을 위한 프롬프트
      n=1,#이미지 갯수
      response_format='url',#기본값
      size="256x256"#이미지 크기
    )
    # 응답 데이타
    """
    response_format='url'설정시
    {
    "created": 1684918719,
    "data": [
            {
                "url": "https://oaidalleapiprodscus.blob.core.windows.net/private/org-LXnOtXwgsxUVsa7GtQ17utHf/user-6uUbIEQVgaAeJrqr1Hp8G9xA/img-P9IVXFRTB4vOQQ2USL8s1yrE.png?st=2023-05-24T07%3A58%3A39Z&se=2023-05-24T09%3A58%3A39Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-05-24T00%3A06%3A46Z&ske=2023-05-25T00%3A06%3A46Z&sks=b&skv=2021-08-06&sig=UOafbU5PBC40sxKIKaOgtCihmo4DTbSuNHeTw1%2B24m0%3D"
            }
        ]
    }
    response_format='b64_json'설정시
    {
        "created": 1684919047,
        "data": [
            {
                "b64_json": "BASE64로 인코딩된 문자열"
            }
        ]
    }
    """

    image_url = response['data'][0]['url']#이 url은 1시간만 유효하다
    print(image_url)
    #url로 이미지 요청해서 로컬에 이미지 저장
    response=requests.get(image_url )
    with open('cat.jpg','wb') as f:
        f.write(response.content)
#https://platform.openai.com/docs/guides/error-codes/python-library-error-types
except openai.error.APIError as e:
    # Handle API error here, e.g. retry or log
    print(f"OpenAI API returned an API Error: {e}")
except openai.error.APIConnectionError as e:
    # Handle connection error here
    print(f"Failed to connect to OpenAI API: {e}")
except openai.error.InvalidRequestError as e:
    print(f"Invalid Request to OpenAI API: {e}")
except openai.error.RateLimitError as e:
    # Handle rate limit error (we recommend using exponential backoff)
    print(f"OpenAI API request exceeded rate limit: {e}")
'''

#한글을 gpt-3.5-turbo에게 번역 의뢰->번역된 영어로 DALL-E모델에게 전송

#1.한글을 영어로 번역
openai.api_key=os.getenv('OPENAI_API_KEY')
model = 'gpt-3.5-turbo'

def generate_chat(model,messages):
    response = openai.ChatCompletion.create(model=model,messages=messages)
    return response
prompts = input('생성할 이미지를 묘사해 주세요?')
messages = [
    {"role":"system","content":"You are a translation expert who translates Korean into English"},
    {"role":"user","content":prompts}
]
response = generate_chat(model,messages)
english=response.choices[0].message['content']
print(english)
#2.번역된 영어로 이미지 생성(한글 promps은 원하는 이미지가 생성이 안된다)
response=openai.Image.create(
      prompt=english,#이미지 생성을 위한 프롬프트
      n=1,#이미지 갯수
      response_format='url',#기본값
      size="256x256"#이미지 크기
    )
image_url = response['data'][0]['url']#이 url은 1시간만 유효하다
response=requests.get(image_url )
with open('cat.jpg','wb') as f:
    f.write(response.content)


