package exception21;

public class Club {

	void entrance(String clothes,int age) throws NotGoodAppearanceException {
		if("남루".equals(clothes)) //인자로 받은 clothes가 남루하면 예외를 던짐
			throw new NotGoodAppearanceException();//내가 만든 예외, 기본생성자로 만든거 던짐, 위에도 throws 해야함
		else if("정장".equals(clothes)&&age<20)//정장인데 20살 미만
			throw new NotGoodAppearanceException("나이가 너무 어려요"); //인자생성자 던짐
		else if("정장".equals(clothes)&&age>40)
			throw new NotGoodAppearanceException("나이가 너무 많아요");
		System.out.println("입장하세요...즐..."); //여기까지오면 조건 다 통과한것
	}
}
