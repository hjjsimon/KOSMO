package model;

public class MemberDTO {
	
	private String id;
	private String pwd;
	private String name;
	private String age;
	//생성자
	public MemberDTO() {}
	public MemberDTO(String id, String pwd, String name, String age) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.age = age;
	}
	//게터 세터 만들기
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {//주소나오니까 귀찮, 오버라이딩 해줌 값 나오게
		
		return String.format("이름:%s,아이디:%s,비번:%s,나이:%s",name,id,pwd,age);
	}
	
}
