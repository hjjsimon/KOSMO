#in (not in)  연산자 : 딕션너리에 적용시 딕션너리에 [키의 존재여부]를 파악할 수 있다
print('[딕셔너리에 in/not in연산자 적용]')
a=dict(zip(('name','age','addr','tel'),['홍길동',20,'가산동','010']))
print(a)
print('tall' in a)
print('tall' not in a)
print('name' in a)
print(True if 'age' in a else False)
print('[for문에 딕셔너리 객체 적용]')
for key in a:#a.keys()와 같다 즉 무조건 키를 추출
    print(key)
    value = a.get(key)
    print(key,value,sep=':')

for key,value in a.items():
    print(key, value, sep=':')
# for문으로 값을 찾아서 딕셔너리 요소 삭제하면 에러가 발생
# 즉 iteration하는동안 요소를 삭제하면 크기가 변하기때문에..
'''
for key,value in a.items():
    if value== '홍길동':
        #del a[key]#RuntimeError: dictionary changed size during iteration
        a.pop(key)#RuntimeError: dictionary changed size during iteration
'''
#딕셔너리에서 값으로 찾아서 요소 삭제
#요소 하나 삭제시
'''
key_ = None
for key,value in a.items():
    if value== '홍길동':
        key_= key
#iteration 끝난 후 삭제
del a[key_]
print(a)
'''
#여러 요소 삭제시
#문]키가 'tel'인 요소만 제외하고 모든 요소를 삭제하시오

keys=[]
for key,value in a.items():
    if key != 'tel':
        keys.append(key)
for key in keys:
    del a[key]
print(a)



