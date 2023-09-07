def showMenu():
    print('=' * 31)
    print('1.입력 2.수정 3.삭제 4.조회 9.종료')
    print('=' * 31)
    return int(input('메뉴번호를 입력하세요?'))

def inputData(*args):#사용자로부터 가변적인 항목을 입력받기 위한 UI
    list_=[]
    for arg in args:
        list_.append(input(f'{arg}을(를) 입력하세요?'))
    return list_#입력받은 값을 리스트로 반환

if __name__ =='__main__':
    showMenu()