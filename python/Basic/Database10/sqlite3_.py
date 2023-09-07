#v파일명은 반드시 sqlite3_.py
#1. sqlite3 패키지를 import
import sqlite3
try:
    # 2. SQLite DB에 연결. 데이타베이스 파일은 현재 디렉토리에서   오픈 혹은 생성
    conn=sqlite3.connect('sample.db')
    print('conn:',conn)
    # 3.커넥션 객체의 cursor()메소드로 Cursor 객체생성
    cursor=conn.cursor()
    print('cursor:',cursor)
    # 4.Cursor객체의 execute() 명령으로 SQL쿼리를 실행(SELECT/INSERT/DELETE/UPDATE등)
    #여러행 한번에 입력하기
    records=[('코스모1','010-1111-1111'),('코스모2','010-1111-1112'),('코스모3','010-1111-1113')]
    '''
    for record in records:
        cursor.execute("INSERT INTO member(name,tel,joindate) VALUES(?,?,strftime('%Y-%m-%d %H:%M:%S','now','localtime'))",record)
    '''
    # 여러개의 레코드 입력시
    cursor.executemany("INSERT INTO member(name,tel,joindate) VALUES(?,?,strftime('%Y-%m-%d %H:%M:%S','now','localtime'))",records)

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
