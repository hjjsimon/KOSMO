'''
1. root로 로그인
   C:\Program Files\MySQL\MySQL Server 8.0\bin>mysql -u root -p
2. 데이타베이스 생성
   mysql> create database kosmodb;
3. 사용자 생성
   mysql> create user 'kosmo'@'localhost' identified by '1234';
4. 권한부여 및 적용
   mysql> grant all privileges on kosmodb.* to 'kosmo'@'localhost';
   mysql> flush privileges;
5. 접속끊기
   mysql> exit;
'''
#1.pymysql 모듈 import
import pymysql
print(dir(pymysql))
try:
    #2.pymysql.connect() 메소드를 사용하여 MySql에 연결
    conn = pymysql.connect(
        host='localhost',
        user='kosmo',
        password='1234',
        db='kosmodb',
        charset='utf8',#반드시 UTF8(utf8), UTF-8는 에러
        # ※cursorclass=pymysql.cursors.DictCursor는 딕셔너리 형태로 출력되고
        # 이 부분을 생략하면 결과가 튜플 형태로 출력된다.
        cursorclass=pymysql.cursors.DictCursor
    )
    # 3.커넥션 객체의 cursor()메소드로 Cursor 객체생성(쿼리 실행용)
    cursor=conn.cursor()
    # 4. Cursor 객체의 execute() 명령으로 SQL 쿼리를 실행
    cursor.execute("DROP TABLE IF EXISTS member")
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS member(
            no INT PRIMARY KEY AUTO_INCREMENT,
            name VARCHAR(10) NOT NULL,
            age TINYINT UNSIGNED DEFAULT 1,
            joindate DATETIME DEFAULT NOW()
        ) 
    
    ''')
except Exception as e:
    print(e)
finally:
    if cursor:
        cursor.close()
    if conn:
        conn.close()

