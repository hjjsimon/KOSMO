# 파이썬에는 switch문이 없다
# 딕셔너리와 사용자 정의함수를 이용해서 대체한다
'''
맨위에는 모듈에 대한 설명
'''
kor,eng,math = map(int,input('세 개의 숫자 입력(공백구분)?').split())
avg = (kor+eng+math)//3 //10 # //로 소수점 버림, 10으로 또 나눔
"""
def switch(key): # 함수 정의는 def, 인자로 key 받음
    '''
    학점을 반환하는 함수(함수 아래에 ' ' ' 쓰면 함수설명, 출력도 됨)
    key: 학점
    return: 학점을 나타내는 문자열
    '''
    return {10: 'A학점', 9: 'A학점', 8: 'B학점', 7: 'C학점', 6: 'D학점'}.get(key, 'F학점')
    #10, 9.. 가 키, 키가 없으면 F학점 -> 디폴트랑 동일
print('평균:%.2f, 학점:%s' %((kor+eng+math)/3,switch(avg)))
# print(switch.__doc__)
"""

def switch(key):
    return {
        10:lambda : 'A학점',#인자 없으면 lambda 뒤 바로 콜론:, 10이 키, 람다식이 밸류 됨
                #[키] -> 하면 밸류 가져옴
                # b = lambda x,y : x+y -> b에는 함수의 주소가 저장됨, 값2개 전달시 계산되어 나옴
                # 값 1개 반환시 return 안써도 됨
        9: lambda: 'A학점',
        8: lambda: 'B학점',
        7: lambda: 'C학점',
        6: lambda: 'D학점',
        5: lambda: 'F학점',
        4: lambda: 'F학점',
        3: lambda: 'F학점', 
        2: lambda: 'F학점',
        1: lambda: 'F학점',
        0: lambda: 'F학점'
        # ※ print('A학점')을 쓰면 값을 반환하는건 아님 그래서 에러날 수 있음
    }[key]
# 반환하는 값이 dictionary, [키] 하면 밸류 가져옴

f = switch(avg)
print('value:{}, type:{}' .format(f,type(f)))
#value:<function switch.<locals>.<lambda> at 0x000002529F19A2A0>, type:<class 'function'>
print('평균:%.2f, 학점:%s' % ((kor+eng+math)/3,f()))
# f()는 함수호출, 위에 switch에서 print 시 값자체를 반환하는게 아니라 %s에 해당하는 값 없어 에러남
# ex. def bb() print(200) -> print(bb()) 하면 None 나옴

#f = switch(avg)
#print('평균:%.2f,학점:%s' % ((kor+eng+math)/3,f()))

#위 두줄을 한줄로!
print('평균:%.2f,학점:%s' % ((kor+eng+math)/3,switch(avg)()))
#print('value:{}, type:{}' .format(f,type(f)))

















