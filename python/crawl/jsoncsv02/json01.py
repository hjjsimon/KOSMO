import json #파이썬 2.6부터 표준 라이브러리로 제공됨
#[1.파이썬 객체를 JSON형태의 문자열로 변환하기-JSON인코딩]
dic = dict(zip(('id','pwd','name','age'),('kim','1234','김길동',20)))
print(dic)
#dump()->None,dumps()->str
#ensure_ascii=False :한글을 유니코드값으로 인코딩 안하기 위함
#indent=4:인덴트 적용된 JSON형태의 문자열 반환
dic_=json.dumps(obj=dic,ensure_ascii=False,indent=4)
print(dic_,type(dic_),sep='\n')
#dic_.get('name')#AttributeError: 'str' object has no attribute 'get'
#dic_['name']#TypeError: string indices must be integers
#[2.JSON형태의 문자열(dic_)을 str함수만으로 파싱해서 딕션너리로 만들기]
split_ = dic_.split(',')
#print(split_)#['{\n    "id": "kim"', '\n    "pwd": "1234"', '\n    "name": "가길동"', '\n    "age": 20\n}']
dic_new = {}
for index,element in enumerate(split_):
    if index==0:#첫번째 요소
        key = element.split(':')[0][1:].strip().replace('"','')
        value = element.split(':')[1].strip().replace('"','')
    elif index == len(split_)-1:# 마지막 요소인 경우
        key = element.split(':')[0].strip().replace('"', '')
        value = element.split(':')[1][:-1].strip().replace('"', '')
    else:# 첫번째 및 마지막요소를 제외한 요소들
        key = element.split(':')[0].strip().replace('"', '')
        value = element.split(':')[1].strip().replace('"', '')

    #print(key,value)
    dic_new[key]= value

print(dic_new)
print(dic_new.get('name'))
print(dic_new['name'])
#[3. JSON형태의 문자열(dic_)을 파이썬 객체(딕션너리)로 변환하기-JSON디코딩]
dic_new=json.loads(dic_)
print(dic_new)
print(dic_new.get('name'))
print(dic_new['name'])
