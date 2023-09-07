package innerclass22;

import java.util.Arrays;
import java.util.Comparator;

//내부정적 클래스: 빌더패턴으로 객체생성에 사용
//빌더패턴: 객체생성과 관련된 디자인패턴, 인자가 많은 객체를 생성할때 유리(클래스 설계 디자인패턴의 하나)-> 아래1,2..]규칙만 따르면 빌더패턴
class Member{
	//1]멤버변수는 private으로 -> 세터로 값 설정
	//필수항목
	private String id;
	private String name;
	//선택항목
	private String tel;
	private String addr;
	//2]내부정적클래스
	static class Builder{
		//3]외부클래스와 똑같은 멤버변수(필드)를 갖는다
		private String id;
		private String name;
		private String tel;
		private String addr;
		//4]내부클래스 인자생성자(필수항목만 받는 생성자)]
		public Builder(String id, String name) {
			this.id = id;
			this.name = name;
		}
		//5]멤버변수(선택항목)를 초기화하는 세터(보통 세터는 반환x라 반환타입은 void, 이번에는 Builder), 반환은 this
		public Builder setTel(String tel){//세터니까 인자로 세팅할 값 받음
			this.tel=tel;//this가 자기자신
			return this;
		}
		public Builder setAddr(String addr){
			this.addr=addr;
			return this;
		}	
		//7]외부클래스타입(Member타입)을 반환하는 메소드(얘를 호출하면 멤버가 만들어짐)
		public Member build() {
			return new Member(this);//Builder타입 넣어주는 인자생성자 만듦, this가 Builder니까 맞음
		}
	}
	//6]내부 정적클래스타입(Builder)을 인자로 받는 생성자 정의
	public Member(Builder builder){//빌더를 인자로 받음, 내부는 위에 초기화했고, 외부는 초기화안함, 이제 함
		this.id=builder.id;
		this.name=builder.name;
		this.tel=builder.tel;
		this.addr=builder.addr;
	}
	@Override//7다음에 함
	public String toString() {
		return String.format("[아이디:%s,이름:%s,연락처:%s,주소:%s]",id,name,tel,addr); 
	}
	
	
}
public class innerStaticBuilder {

	public static void main(String[] args) {
		//필수항목만 갖고 객체 생성]
		//Member member1=new Member("아이디","이름",null,null);//Member객체 생성, 지금 생성자가 Builder뿐, 이전에는 null처리로 했는데 빌더는 그렇게 할 필요 없음
		Member member1=new Member.Builder("KIM", "김길동").build();//정적내부클래스도 멤버니까 뜸, 필수인 것만 뜨게할 수 있음/ Builder타입이니까 Member에 담을 수 없음(상속도 없어서 형변환불가, 내부클래스가 자식은 아님)		
		//위에 멤버타입을 반환하는 .build()를 추가해주면 됨, builder.tell, builder.addr으로 this.까지null됨, builder.id랑 builder.name은 초기화 완료한것->this.id, this.name에 들어감
		System.out.println(member1);
		//원하는 항목만 세팅하는법
		Member member2=new Member.Builder("LEE", "이길동")
				.setTel("010-1234-5678") //Builder가 Builder반환, set은 또 Builder반환, build는 Member반환, 이런식으로 원하는 것만 딱 세팅 가능
				.build();
		System.out.println(member2);
		Member member3=new Member.Builder("PARK", "박길동")
				.setTel("010-4321-8765") //Builder가 Builder반환, set은 또 Builder반환, build는 Member반환, 이런식으로 원하는 것만 딱 세팅 가능
				.setAddr("가산동")
				.build();
		System.out.println(member3);
		
		//Comparator 인터페이스인데 new?불가, 근데 이걸 이름을 빌리면 new가능, 클래스 이름이 없으면 함, 콜렉션엔어레이솔트해 있음 
		//메인메소드 안에 정의했던 이름없는 익명클래스, {} 중괄호 안은 그냥 클래스임!, 부모인 Comparator의 이름을 빌린 클래스 ex. 허재준x 허진의 아들, 이런셈
/*		Arrays.sort(names,new Comparator<String>() {//원본배열이 재배치 된다(in-place방식) 동일
			@Override
			public int compare(String src, String target) {
				//숫자는 -, 문자열은 compareTo
				return target.compareTo(src); //src가 앞이면 오름차순
			}//Comparable 모르겠다 싶으면 그냥 항상 구현하면 됨 T[]에는 배열 넣어주면 됨
		});	*/
		
		
	}

}
