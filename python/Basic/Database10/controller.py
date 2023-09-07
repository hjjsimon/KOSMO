from view import *
from model import *
#데이타베이스 연결
conn=connectdb()
while True:
    # '1.입력 2. 수정 3. 삭제 4. 조회 9. 종료'
    menu=showMenu()
    print(menu)
    if menu == 9:
        print('프로그램을 종료합니다')
        close(conn)#데이타베이스 닫기
        break
    elif menu == 1:
        #뷰(화면) 호출
        list_ = inputData(*['아이디','비번','이름'])
        #print(list_)
        # 모델 호출
        affected=insert(conn,list_)
        print(f'{str(affected)+"행이 입력되었습니다" if affected==1 else "입력시 오류발생"}')