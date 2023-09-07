#configparser모듈을 사용한 중요 정보 관리
#섹션명([])은 대소문자 구분.
#섹션안의 키=값은 구분하지 않는다
#user = maven 혹은 user=maven 는 같다 즉 자동으로 앞뒤 공백 제거
#https://docs.python.org/ko/3/library/configparser.html
import configparser

#1. ConfigParser객체 생성
config=configparser.ConfigParser()
print(f'value:{config},type:{type(config)}')
#2.ini파일 읽기
print(config.read('oracle.ini'))#['oracle.ini']
#3. 모든 섹션명 얻기
sections=config.sections()
print(sections)#['section', 'ORACLE', 'DATABASE']
#4.items() : ItemsView[str, SectionProxy] 반환 즉 ('섹션명',SectionProxy)를 요소로 갖는 ItemsView객체 반환
items=config.items()
print(items)#ItemsView(<configparser.ConfigParser object at 0x000002BE79AA6210>)
print(list(items))#[('DEFAULT', <Section: DEFAULT>), ('section', <Section: section>), ('ORACLE', <Section: ORACLE>), ('DATABASE', <Section: DATABASE>)]
print(type(list(items)[0][1]))#<class 'configparser.SectionProxy'> 즉 <Section: DEFAULT>는 SectionProxy타입
print('SectionProxy타입의 이름공간:',dir(list(items)[0][1]))
for 섹션명,섹션프락시 in items:
    print(섹션명)
    if 섹션명 == 'ORACLE':
        user = 섹션프락시.get('user')
        password = 섹션프락시.get('password')
        url = 섹션프락시.get('url')
        print(f'아이디:{user},비번:{password},주소:{url}')
#ConfigParser객체['섹션명']['키값'] : 섹션명은 대소문자 구분.키값은 대소문자 구분 필요없다
user = config['ORACLE']['USER']
password = config['ORACLE']['PASSWORD']
url = config['ORACLE']['URL']

print(f'아이디:{user},비번:{password},주소:{url}')
#1.모듈 import
import cx_Oracle
#2.데이타베이스 연결
with cx_Oracle.connect(user=user, password=password,dsn=url,encoding="UTF-8") as conn:
    print(f'value:{conn},type:{type(conn)}')
    #3.쿼리 실행을 위한 커서객체 얻기
    cursor=conn.cursor()
    #4.쿼리 실행
    #방법1:numbered placeholder -  :번호(번호는 1부터 시작)-데이타는 tuple 혹은 list로
    #cursor.execute('INSERT INTO users VALUES(:1,:2,:3,SYSDATE)',['KIM','1234','김길동'])
    # 방법2:named placeholder -(데이타는 tuple/list/딕셔너리로:키값은 컬럼명으로)
    #cursor.execute('INSERT INTO users VALUES(:username,:password,:name,SYSDATE)', ['LEE', '1234', '이길동'])
    #cursor.execute('INSERT INTO users VALUES(:username,:password,:name,SYSDATE)', username='PARK',password='1234',name='박길동')
    cursor.execute('INSERT INTO users VALUES(:username,:password,:name,SYSDATE)', {'username':'KOSMO','password':'1234','name':'박길동'})
    #5.commit
    conn.commit()
    #6.연결해제
    cursor.close()
    #conn.close()






