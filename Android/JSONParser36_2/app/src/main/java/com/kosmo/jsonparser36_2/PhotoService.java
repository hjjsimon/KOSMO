package com.kosmo.jsonparser36_2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoService {
    @GET("/photos")
    Call<List<PhotoItem>> photos();
}
