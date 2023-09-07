'''
인수의 갯수에 따른 분류:
    고정인수-인수의 갯수가 정해진 인수
    가변인수-인수의 갯수가 정해지지 않고 가변적으로 변하는 인수
            함수정의시 매개변수앞에 *나 **를 붙이면 가변인수가 된다
        *는 위치인수를 가변인수로 사용할때
        **는 키워드인수를 가변인수로 사용할때
함수 호출시 인자를 전달할때 매개변수명을 키워드(매개변수명과 일치해야 한다)로 사용하는지 여부에 따른 분류:
  위치인수 - 사용하지 않음
  키워드인수- 사용
'''
print('[고정인수를 받는 함수 정의]')
def fixed(age,tall,weight):#인수를 3개만 받을수 있는(고정인수) 함수 정의]
    print(f'나이:{age},키:{tall},몸무게:{weight}')

#[위치인수로 호출하자-인수의 위치 및 갯수를 꼭 알아야한다]
#fixed(20,180)#TypeError: fixed() missing 1 required positional argument: 'weight'
fixed(180,20,120)##인수의 위치를 잘못 전달한 경우
fixed(20,180,120)
#fixed([20,180,20])#TypeError: fixed() missing 2 required positional arguments: 'tall' and 'weight'
fixed(*[20,180,120])#함수 호출시 인수로 리스트나 튜플를 전달할때 인수에 *를 붙이면 언패킹 된다
#fixed({'weight':120,'age':20,'tall':180})#TypeError: fixed() missing 2 required positional arguments: 'tall' and 'weight'
#fixed(*{'weight':120,'age':20,'tall':180})#*하나를 붙이면 키만 추출되서 위치인수로 작동한다
fixed(**{'weight':120,'age':20,'tall':180})#*를 두개 붙여야 언패킹 된다
#[키워드 인수로 호출하자-위치 및 갯수를 몰라도 된다]
fixed(tall=180,age=20,weight=120)
#fixed(tall=180,age=20)#TypeError: fixed() missing 1 required positional argument: 'weight'
print('[위치 가변인수를 받는 함수 정의  - * 사용]')
#함수 정의시 매개변수(파라미터)앞에 *하나를 붙인다(매개변수는 가변인수이자 위치인수를 받는 변수가 된다)
#이때 매개변수는 튜플이 된다
def positional_variable(*varagrs):
    print(f'value:{varagrs},type:{type(varagrs)}')

positional_variable()
positional_variable(10)
positional_variable(10,20,30,40,50)
positional_variable([10,20,30,40,50])
positional_variable(*[10,20,30,40,50])#variable(10,20,30,40,50)와 같다
positional_variable({'weight':120,'age':20,'tall':180})
positional_variable(*{'weight':120,'age':20,'tall':180})

#variable(**{'weight':120,'age':20,'tall':180})#TypeError: variable() got an unexpected keyword argument 'weight' 키워드 가변인수가 없다
print('[키워드 가변인수를 받는 함수 정의  - ** 사용]')
#함수 정의시 매개변수(파라미터)앞에 **두개를 붙인다(매개변수는 가변인수이자 키워드 인수를 받는 변수가 된다)
#이때 매개변수는 딕셔너리가 된다
def keyword_variable(**kwargs):
    print(f'value:{kwargs},type:{type(kwargs)}')


keyword_variable()
#keyword_variable(10,20,30,40,50)#TypeError: keyword_variable() takes 0 positional arguments but 5 were given 즉 위치인수는 안받고 키워드 인수만 받는 함수이다
keyword_variable(username='코스모')
keyword_variable(username='코스모',password='1234')
#keyword_variable({'weight':120,'tall':180,'age':20})#TypeError: keyword_variable() takes 0 positional arguments but 1 was given
keyword_variable(**{'weight':120,'tall':180,'age':20})

print('[고정인수와 (위치 혹은 키워드)가변인수를 받은 함수 정의')
#함수 정의시 고정인수가 항상 가변인수보다 앞에 와야 한다.(그렇지 않으면 호출시 에러)
def fixed_variable(args,**kwargs):#적어도 인수 하나를 받은 함수
    print(f'고정인수:{args},가변인수:{kwargs}')
#fixed_variable()#TypeError: fixed_variable() missing 1 required positional argument: 'args'
fixed_variable(100)
fixed_variable(100,username='코스모',password='1234')
fixed_variable(100,**{'weight':120,'tall':180,'age':20})

