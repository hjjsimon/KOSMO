#내가 만든 REST API서버에 OCR서비스 요청
'''
    POST http://localhost:5000/ocr
    파라미터명 base64
'''
import requests
import base64
import json
def image_to_base64(image_path):
    with open(image_path, "rb") as f:
        base64_bytes = base64.b64encode(f.read())
        print(base64_bytes)#b'iVBORw0KGg~' <class 'bytes'>
        base64_str=base64_bytes.decode("utf-8")
        print(base64_str)
        return base64_str

base64=image_to_base64('ocr.png')
res=requests.post('http://localhost:5000/ocr',
                  headers={'Content-Type':'application/json'},
                  data=json.dumps({'base64':base64}))
print(res.text)
