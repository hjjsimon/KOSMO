# ※ break, continue는 반복문,스위치문(파이썬에 없음) 안에서만 사용가능
# 반드시 루프 안에 있어야한다~
i = 0
while i < 100000000000000000000000:
    i += 1
    print('[i가 {}일 때]' .format(i))
    print('continue문 이전 출력문')
    if i % 2 == 0:
        continue # ※continue라 여기서 맨 위 반복문으로 되돌아감, 아래 코드 실행안됨
    print('continue문 이후 출력문')
    print('break문 이전 출력문')
    if i == 3: break # ※break 만나면 반복문을 빠져나간다(한줄도 되지만 가독성 안좋음)
    print('break문 이후 출력문')