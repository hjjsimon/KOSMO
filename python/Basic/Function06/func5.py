'''
함수 정의시 매개변수의 순서:
1.고정인수 > 가변인수
2.위치 가변인수(*) >키워드 가변인수(**)
3.고정인수 >매개변수 초기화> 위치 가변인수 > 키워드 가변인수
'''
print('[매개변수 초기화를 사용한 함수 정의]')
def mixed(arg1,arg2='초기값2',arg3='초기값3',*args,**kwargs):
    print(f'''
    arg1:{arg1},
    arg2:{arg2},
    arg3:{arg3},
    args:{args},
    kwargs:{kwargs}
    ''')
#함수호출
#mixed()#TypeError: mixed() missing 1 required positional argument: 'arg1'
mixed(2023)
mixed(2023,'다른값2')
mixed(2023,'다른값2','다른값3')
mixed(2023,'다른값2','다른값3',1,2,3)
mixed(2023,'다른값2','다른값3',*[1,2,3])
mixed(2023,'다른값2','다른값3',username='코스모',loc='가산동')
mixed(2023,'다른값2','다른값3',1,2,3,username='코스모',loc='가산동')
mixed(2023,'다른값2','다른값3',*[1,2,3],**{'username':'코스모','loc':'가산동'})

