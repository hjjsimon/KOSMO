#1. sqlite3 패키지를 import
import sqlite3_
import time
try:
    # 2. SQLite DB에 연결. 데이타베이스 파일은 현재 디렉토리에서   오픈 혹은 생성
    conn=sqlite3.connect('sample.db')
    # 3.커넥션 객체의 cursor()메소드로 Cursor 객체생성
    cursor=conn.cursor()
    # 4.Cursor객체의 execute() 명령으로 SQL쿼리를 실행(SELECT/INSERT/DELETE/UPDATE등)
    # 방법1:Parameter Placeholder방식
    #cursor.execute('INSERT INTO member(name,tel,joindate) VALUES(?,?,datetime("now","localtime"))',['이길동','010-1111-2222'])
    # 방법2:Named Placeholder  ?대신 ":컬럼명"으로
    joindate = time.strftime('%Y-%m-%d %H:%M:%S',time.localtime())
    cursor.execute('INSERT INTO member(name,tel,joindate) VALUES(:name,:tel,:jondate)',
                   ('라길동', '010-222-333',joindate))
    # 5. commit:실제 테이블에 반영(쿼리문이 Insert/Delete/Update일때)
    conn.commit()
    print(f'{cursor.rowcount}행이 입력되었어요')

except Exception as e:
    conn.rollback()
    print(e)
finally:
    # Cusor객체 및 Connection 객체의 close() 메서드로 닫기
    # ※생성한 순서 반대로 닫는다
    if cursor:
        cursor.close()
    if conn:
        conn.close()
