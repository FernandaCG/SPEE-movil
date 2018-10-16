package com.example.erick.homedashboard.com.ipn.acceso.api;

import com.example.erick.homedashboard.com.ipn.acceso.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginApiService {

    @POST("login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("correo") String title, @Field("password") String body);


    @GET("pokemon")
    Call<LoginResponse> logout();
}
