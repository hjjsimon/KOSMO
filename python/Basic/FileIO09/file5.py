#바이너리모드로 읽고 쓰기
#파이썬 객체(list,tuple등)를 "파일"로 읽고 쓰기(pickle이라는 라이브러리 사용)
import pickle
print(dir(pickle))
#바이너리 모드로 파이썬 객체를 파일로 저장하기
name='가길동'
age=20
isMember=True
list_=list('가나다')
dict_ = dict(zip(['국어','영어','수학'],[100,99,100]))
#바이너리 모드일때는 encoding의미 없음
with open('object.txt','wb') as f:
    pickle.dump(name,f)
    pickle.dump(age, f)
    pickle.dump(isMember, f)
    pickle.dump(list_, f)
    pickle.dump(dict_, f)
#읽을때는 저장한 객체 수만큼 load()호출
with open('object.txt','rb') as f:
    print(pickle.load(f))
    print(pickle.load(f))
    print(pickle.load(f))
    print(pickle.load(f))
    print(pickle.load(f))
    #print(pickle.load(f))#EOFError: Ran out of input