import configparser

#1. ConfigParser객체 생성
config=configparser.ConfigParser()
#2.ini파일 읽기
config.read('oracle.ini',encoding='utf8')#한글이 포함된 경우(encoding='utf8'추가)
#1.모듈 import
import cx_Oracle
#2.데이타베이스 연결
with cx_Oracle.connect(user=config['ORACLE']['USER'],
                       password=config['ORACLE']['PASSWORD'],
                       dsn=config['ORACLE']['URL'],
                       encoding="UTF-8") as conn:

    #3.쿼리 실행을 위한 커서객체 얻기
    cursor=conn.cursor()
    #4.쿼리 실행
    cursor.execute('SELECT * FROM users ORDER BY joindate DESC')
    # 5.패치
    rows = cursor.fetchall()
    print(rows)
    for user,password,name,joindate in rows:
        print(f'아이디:{user},비번:{password},이름:{name},가입일:{str(joindate)[0:10]}')

    #6.연결해제
    cursor.close()







