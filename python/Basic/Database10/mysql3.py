#1.pymysql 모듈 import
import pymysql
import datetime
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
    cursor.execute('SELECT * FROM member ORDER BY no DESC')
    # 방법1:fetchall()
    '''
    
    rows = cursor.fetchall()
    print(rows)
    for row in rows:
        for key,value in row.items():
            # joindate키의 값은 datetime타입임으로 str로 변환후 슬라이싱
            print(f'{key}:{str(value)[0:10] if key=="joindate" else value}',end=' ')
        print()
    '''
    # 방법2:fetchone()
    '''
    row=cursor.fetchone()
    print(row)
    print(' '.join(map(lambda x:str(x)[0:10] if isinstance(x,datetime.datetime) else str(x),list(row.values()))))
    '''
    # 방법3:fetchmany(숫자)
    rows = cursor.fetchmany(3)
    print(rows)
    for row in rows:
        for value in row.values():
            print(str(value)[0:10] if isinstance(value,datetime.datetime) else value,end=' ')
        print()


except Exception as e:
    print(e)
finally:
    if cursor:
        cursor.close()
    if conn:
        conn.close()

