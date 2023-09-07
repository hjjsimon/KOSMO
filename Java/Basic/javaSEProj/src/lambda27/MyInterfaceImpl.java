package lambda27;

public class MyInterfaceImpl implements MyInterface{
	
	//+기능 하나만 정의가능
	@Override
	public int calc(int a,int b) {
		
		return a+b; //2개 숫자를 받아서 할 수 있는게 +,-,*,/ 중 return으로 하나만 반환가능
	}
	
	
}
