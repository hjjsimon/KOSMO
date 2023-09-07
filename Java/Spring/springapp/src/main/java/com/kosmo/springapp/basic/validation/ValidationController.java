package com.kosmo.springapp.basic.validation;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ValidationController {

	@PostMapping("/Validation/Validation.do")
	//※매개변수 순서: FormCommand 다음에 Errors 순으로
	public String validate(@Valid FormCommand form, Errors errors, Model model) {

		//체크박스는 여러개 선택하더라도 첫번째 것만 포워드됨
		model.addAttribute("inter", form.getInter());
		
		//FormCommand의 필드 중 어느 하나라도 유효성에 실패시
		//Errors객체의 hasErrors()가 true반환
		if(errors.hasErrors()) {//필드 하나라도 에러를 갖고있으면 true 반환
			for(FieldError value: errors.getFieldErrors()) {
				System.out.println(value.getField()+" : "+value.getDefaultMessage());
			}
			
			//유효성 검증 실패시 뷰정보 반환, 다시 입력폼으로 포워드
			return "validation07/Validation";
		}
		
		
		//유효성 검증 성공시 뷰정보 반환, 출력페이지로 포워드
		return "validation07/ValidationResult";
	}
	
}
