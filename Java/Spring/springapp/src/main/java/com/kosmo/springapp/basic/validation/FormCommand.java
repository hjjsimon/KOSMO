package com.kosmo.springapp.basic.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class FormCommand {

	//폼(태그)의 파라미터명과 같게 속성(멤버변수) 정의
	@NotBlank(message = "이름을 입력하세요")//Null 및 빈 문자열 그리고 빈 공백(" ") 불가(textbox에 좋음)
	private String name;
	
	@NotBlank(message = "나이를 입력하세요")//입력안했을때 에러메세지
	@Pattern(regexp = "[0-9]{1,3}", message = "나이는 숫자만...")//입력했는데 숫자 아니면 에러메시지
	private String years;
	
	@NotNull(message = "성별을 선택하세요")
	private String gender;
	
	@NotNull(message = "관심사항을 선택하세요")//선택시 null 안넘어옴, 근데 추가로 최소 선택개수 설정가능
	@Size(min = 2,message = "최소 2개 이상 선택하세요")
	private String[] inter;
	
	@NotBlank(message = "학력을 선택하세요")
	private String grade;

	//게터,세터
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String[] getInter() {
		return inter;
	}

	public void setInter(String[] inter) {
		this.inter = inter;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	
	
}
