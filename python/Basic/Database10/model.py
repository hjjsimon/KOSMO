from cx_Oracle import connect
from configparser import ConfigParser

def connectdb():#데이타베이스 연결:Connection 객체 반환
    config=ConfigParser()
    config.read('oracle.ini')
    #데이타베이스 연결
    return connect(user=config['ORACLE']['USER'],
            password=config['ORACLE']['PASSWORD'],
            dsn=config['ORACLE']['URL'],
            encoding="UTF-8")
def close(conn):#커넥션객체 닫기
    if conn:
        conn.close()
#list_는 입력 데이타
def insert(conn,list_):#입력처리:성공시 1,실패시 0
    with conn.cursor() as cursor:
        try:
            cursor.execute('INSERT INTO users VALUES(:1,:2,:3,SYSDATE)',list_)
            conn.commit()
            return 1
        except Exception as e:
            #print(e)
            return 0


if __name__ =='__main__':
    conn=connectdb()
    print(insert(conn,['KIM','1234','최길동']),'행 입력')