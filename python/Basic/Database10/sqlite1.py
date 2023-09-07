#1. sqlite3 패키지를 import
import sqlite3_
print(sqlite3.__file__)
print(dir(sqlite3))
print(sqlite3.sqlite_version)
try:
    # 2. SQLite DB에 연결. 데이타베이스 파일은 현재 디렉토리에서   오픈 혹은 생성
    conn=sqlite3.connect('sample.db')
    print(f'value:{conn},type:{type(conn)}')#<class 'sqlite3.Connection'>
    # 방법1:커넥션객체.cursor()로 Cursor객체 생성후 쿼리 실행
    # 3.커넥션 객체의 cursor()메소드로 Cursor 객체생성
    #cursor=conn.cursor()
    #print(f'value:{cursor},type:{type(cursor)}')  # <class 'sqlite3.Cursor'>
    # 4.Cursor객체의 execute() 명령으로 SQL쿼리를 실행(SELECT/INSERT/DELETE/UPDATE등)
    #cursor.execute('SELECT * FROM member ORDER BY _id DESC')

    #방법2:커서객체=커넥션객체.execute()로 바로 쿼리 실행
    cursor=conn.execute('SELECT * FROM member ORDER BY _id DESC')

    # 5.데이타 Fetch(SQL문이 SELECT일때)
    # 테이블의 쿼리결과는 [(),(),(),..] 형태로 반환(리스트)된다.  즉 하나의 레코드는 튜플로 만들어진다
    rows=cursor.fetchall()
    print(f'value:{rows},type:{type(rows)}')
    for _id,name,tel,joindate in rows:
        print(f'번호:{_id},이름:{name},연락처:{tel},가입일:{joindate[0:10]}')
except Exception as e:
    print(e)
finally:
    # Cusor객체 및 Connection 객체의 close() 메서드로 닫기
    # ※생성한 순서 반대로 닫는다
    if cursor:
        cursor.close()
    if conn:
        conn.close()
