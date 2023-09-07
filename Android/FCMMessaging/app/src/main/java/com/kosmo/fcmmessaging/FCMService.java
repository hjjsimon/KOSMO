package com.kosmo.fcmmessaging;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FCMService
{
    @FormUrlEncoded
    @POST("/restapi/token")
    Call<Map<String,String>> newToken(@Field("token") String token);
}
