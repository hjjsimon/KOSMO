package com.kosmo.retrofit35_2;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/*
Call<T> T는 서버로부터 받은  데이타 타입
맵으로 받을때는 Map<K,V> 반드시 타입 파라미터 K와 V의 타입을 지정해야 한다
예: Call<Map<String,String>> getJson(@QueryMap Map<String,String> map);
[GET요청시]
@GET("/엔드포인트")
추상 메소드의 인자
@Query("파라미터명") String 파라미터명   - 파라미터가 적을때
@QueryMap Map<String,String> map  - 파라미터가 많을때
[POST요청시]
1.key-value 즉 x-www-form-urlencoded 형태로 데이터를 전송시
@FormUrlEncoded
@POST("/엔드포인트")
추상 메소드의 인자
@Field("파라미터명") String 파라미터명  - 파라미터가 적을때
@FieldMap Map<String,String> map - 파라미터가 많을때

2.JSON 형태로 데이터를 전송시
@POST("/엔드포인트")
추상 메소드의 인자
@Body DTO계열클래스 혹은 Map<String,String> 변수


[파일 업로드시]
@Multipart
@POST("/엔드포인트")
추상 메소드의 인자
@Part ArrayList<MultipartBody.Part> 파라미터명1   - type="file"의 파라미터명
@Part("파라미터명") RequestBody 파라미터명2           - type="text"의 파라미터명

요청 보낼때
ArrayList<File> files=new ArrayList<>();
위 컬렉션에 서버로 업로드할 파일들의 File객체를 생성해서 저장

ArrayList<MultipartBody.Part> 변수 = new ArrayList<>();

다중 파일 업로드 코드
for(File file:files){

    RequestBody fileBody = RequestBody.create(file,MediaType.parse("multipart/form-data"));
    변수.add(MultipartBody.Part.createFormData("파라미터명1", file.getName(),fileBody));
}

기타 파라미터
RequestBody 파라미터명2 = RequestBody.create("전달할 값",MediaType.parse("text/plain"));
 */
public interface BBSService {

    @GET("/Ajax/AjaxText.do")
    Call<String> getText(@Query("id") String id,@Query("pwd") String pwd);
    //혹은 Call<String> getText(@QueryMap Map<String,String> map);

    @POST("/Ajax/AjaxJson.do")
    Call<Map<String,String>> getJson(@QueryMap Map<String,String> map);

    @GET("/Ajax/AjaxJsonArray.do")
    Call<List<BBSItem>> getJsonArray();

}
