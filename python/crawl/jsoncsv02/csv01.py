#[1.파이썬 객체를 csv파일로 저장하기]
import csv

records=[
    [1,'가길동','KIM','1234','가산동',25],
    [2,'나길동','LEE','1234','나산동',20],
    [3,'다길동','PARK','1234','다산동',30]
]

with open('users_list.csv','w',encoding='utf8',newline='') as f:
    writer=csv.writer(f)
    #writer타입은 writeheader()함수(DictWriter타입에 있는)가 없다
    print(writer,type(writer),sep=' | ')
    # csv파일의 헤더쓰기
    writer.writerow(['번호','이름','아이디','비번','주소','나이'])
    # 방법1. 여러행을 한꺼번에 파일로 출력시
    #writer.writerows(records)
    # 방법2. writerow(Iterable)-한라인씩 쓰기
    for record in records:
        writer.writerow(record)

#2. 파이썬 딕셔너리를 csv파일로 저장하기]
records=[
    {'번호':1,'이름':'가길동','아이디':'KIM','비번':'1234','주소':'가산동','나이':25},
    {'번호':2,'이름':'나길동','아이디':'LEE','비번':'1234','주소':'나산동','나이':35},
    {'번호':3,'이름':'다길동','아이디':'PARK','비번':'1234','주소':'다산동','나이':45}
]
with open('users_dict.csv','w',encoding='utf8',newline='') as f:
    writer=csv.DictWriter(f=f,fieldnames=['번호','이름','아이디','비번','주소','나이'])
    # DictWriter타입에는 CVS파일의 헤더를 쓸수 있는 writeheader()함수가 있다
    writer.writeheader()#CSV파일의 헤더 쓰기
    writer.writerows(records)