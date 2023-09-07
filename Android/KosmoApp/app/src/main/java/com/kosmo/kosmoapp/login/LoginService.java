package com.kosmo.kosmoapp.login;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {
    //로그인용
    @FormUrlEncoded
    @POST("/users/auth")
    Call<Map<String,Boolean>>  isLogin(@Field("username") String username,
                                       @Field("password") String password);
}
