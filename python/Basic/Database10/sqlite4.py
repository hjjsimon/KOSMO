#1. sqlite3 패키지를 import
import sqlite3
try:
    # 2. SQLite DB에 연결. 데이타베이스 파일은 현재 디렉토리에서   오픈 혹은 생성
    conn=sqlite3.connect('sample.db')
    # 3.커넥션 객체의 cursor()메소드로 Cursor 객체생성
    cursor=conn.cursor()
    # 4.Cursor객체의 execute() 명령으로 SQL쿼리를 실행(SELECT/INSERT/DELETE/UPDATE등)
    # 데이타 Fetch:쿼리문이 SELECT일때
    # 방법1:fetchall()-리스트 반환  [(),(),()....] 튜플이() 하나의 레코드에 해당
    '''
    cursor.execute("SELECT * FROM member ORDER BY _id DESC")
    rows=cursor.fetchall()
    print(rows)
    for row in rows:
        print(f'{row[0]} {row[1]} {row[2]} {row[3][0:10]}')
    '''
    # 방법2:fetchone()
    '''
    _id = int(input('번호를 입력하세요?'))
    cursor.execute("SELECT * FROM member WHERE _id=?",[_id])
    row = cursor.fetchone()#하나의 레코드를 튜플로 반환:(컬럼1,컬럼2,...)
    print(row)#없는 번호인 경우 None
    if row:
        print(f'{row[0]} {row[1]} {row[2]} {row[3][0:10]}')
    else:
        print(_id,'로 조회한 레코드가 없습니다',sep='번으')
    '''
    # 방법3:fetchmany(가져올 레코드 수) 갯수 미 지정시 디폴트는 1개
    cursor.execute("SELECT * FROM member ORDER BY _id DESC")
    rowcount = int(input('조회할 레코드 수를 입력하세요?'))
    rows = cursor.fetchmany(rowcount)#0일때는 전체
    for row in rows:
        print(f'{row[0]} {row[1]} {row[2]} {row[3][0:10]}')

except Exception as e:
    print(e)
finally:
    # Cusor객체 및 Connection 객체의 close() 메서드로 닫기
    # ※생성한 순서 반대로 닫는다
    if cursor:
        cursor.close()
    if conn:
        conn.close()
