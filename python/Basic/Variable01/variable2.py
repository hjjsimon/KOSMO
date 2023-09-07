import sys
print(sys.getrefcount(2023))#3
x=2023
print(sys.getrefcount(2023))#4, x가 2023참조해서 증가
y=2023
print(sys.getrefcount(2023))#5, y가 2023참조해서 또 증가
print(sys.getrefcount(1))#1000000046, 파이썬이 여기저기서 모듈로 1을 많이 쓰고있는 것
#파이썬은 변수 외에 값 자체도 객체, 그래서 변수에 값 그대로 할당하지않고 객체를 가리키도록함
#자바로치면 x라는 변수는 2023객체의 주소를 가리키는 주소를 갖고있는 방인셈
#sys.getrefcount() 참조 횟수를 가져옴, 1이나 2는 내부적으로 파이썬이 쓰므로 다른 숫자보다 훨씬 큼

#is연산자: 객체가 같은 타입인지 판단하는 연산자
#위의 x,y는 같은 2023 객체를 가리키므로 타입도 같음
print(x is y)#True

