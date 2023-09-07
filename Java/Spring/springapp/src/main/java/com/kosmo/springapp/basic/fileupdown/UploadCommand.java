package com.kosmo.springapp.basic.fileupdown;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadCommand {

	//FileUpDownController 확인, 이번에는 객체로 받아보기
	//FileUpDown의 파라미터 똑같이 가져와야함, 이름 동일하게 3개
	
	private String writer;
	private String title;
	private List<MultipartFile> files;
}
