#writelines(반복가능한객체) 함수 사용-반복가능한 객체의 각 요소(str타입)를
# 라인단위로 출력하는 함수
#Iterable[AnyStr]:반복 가능한 객체의 모든 요소는 str이어야 한다라는 의미
#리스트의 요소들을 아래처럼 파일에 쓰기:writelines(list타입)
#※list의 요소는 모두 str이어야한다
#아래내용을 파일에 써보자
'''
한국 소프트웨어
인재 개발원입니다
파이썬 수업입니다
자바보다 쉬워요
화이팅!!!
2023
'''
lines=['한국 소프트웨어','인재 개발원입니다','파이썬 수업입니다','자바보다 쉬워요','화이팅!!!',2023]
f=open('list.txt','w',encoding='utf8')
#f.write(lines)#TypeError: write() argument must be str, not list
#방법1 : 리스트의 각 요소에 str객체의 join함수 사용 '\n'으로 각 요소 연결
#f.write('\n'.join(map(str,lines)))
#f.writelines(lines)#TypeError: write() argument must be str, not int
#방법2 : 리스트의 각 요소에 '\n'을 추가,단 그전에 리스트의 각 요소를 str로 변환
#       for문 사용
'''
list_ =[]
for index,ele in enumerate(lines):
    #lines[index]=str(ele)+'\n'#원본이 변경됨
    list_.append(str(ele)+'\n')
print(lines)
print(list_)
f.writelines(list_)
'''
#방법3 : 리스트의 각 요소에 '\n'을 추가,단 그전에 리스트의 각 요소를 str로 변환
#        for문 미 사용
f.writelines(list(map(lambda s:s+'\n',map(str,lines))))
f.close()
#방법4: with절 사용
with open('listWith.txt','w',encoding='utf8') as f:
    f.writelines(list(map(lambda s: s + '\n', map(str, lines))))