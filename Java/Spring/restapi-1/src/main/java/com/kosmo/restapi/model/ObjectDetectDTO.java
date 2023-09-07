package com.kosmo.restapi.model;

import java.util.List;

import org.springframework.boot.autoconfigure.cassandra.CassandraProperties.Request;
import org.springframework.boot.context.properties.PropertyMapper.Source;

import lombok.Getter;
import lombok.Setter;

//구글의 객체감지 요청본문(JSON형식)을 자바빈으로 변환(https://www.jsonschema2pojo.org/ 참고)
//pojo에서 https://cloud.google.com/vision/docs/object-localizer?hl=ko#curl_1 요청바디 긁어와서 복붙변환
//근데 클래스 5개로 나눔, 나는 한방에!
//요청본문 똑같은 구조로 DTO 만듦
@Getter
@Setter
public class ObjectDetectDTO {

	private List<Request> requests;
	
	@Getter
	@Setter
	public static class Request{
		private Image image;
		private List<Feature> features;
		private ImageContext imageContext;
	}
	
	@Getter
	@Setter
	public static class Feature{
		private int maxResults;
		private String type;
	}
	
	@Getter
	@Setter
	public static class Image{
		private Source source;
	}
	
	@Getter
	@Setter
	public static class Source{
		private String imageUri;
	}
	
	//구글에게 한글로 응답받기(디폴트 영어)
	@Getter
	@Setter
	public static class ImageContext{
		private List<String> languageHints;
	}
	
	
}
