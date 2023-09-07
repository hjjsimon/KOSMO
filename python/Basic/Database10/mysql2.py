#1.pymysql 모듈 import
import pymysql

try:
    #2.pymysql.connect() 메소드를 사용하여 MySql에 연결
    conn = pymysql.connect(
        host='localhost',
        user='kosmo',
        password='1234',
        db='kosmodb',
        charset='utf8',
        cursorclass=pymysql.cursors.DictCursor
    )
    # 3.커넥션 객체의 cursor()메소드로 Cursor 객체생성(쿼리 실행용)
    cursor=conn.cursor()
    # 4. Cursor 객체의 execute() 명령으로 SQL 쿼리를 실행
    # ※Parameter Placeholder방식:%s사용 (sqlite3는 ?,pymysql는 %s)
    # executemany()로 여러 레코드 입력

    users=[]
    persons = int(input('인원수를 입력하세요?'))
    for _ in range(persons):
        name = input('이름 입력?')
        age  = input('나이 입력?')
        users.append((name,age))
    cursor.executemany('INSERT INTO member(name,age) VALUES(%s,%s)',users)
    conn.commit()
    print(cursor.rowcount,'행이 입력되었습니다',sep='')
except Exception as e:
    conn.rollback()
    print(e)
finally:
    if cursor:
        cursor.close()
    if conn:
        conn.close()

