package com.example.erick.homedashboard.com.ipn.acceso.api;

import com.example.erick.homedashboard.com.ipn.acceso.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApiService {

    @GET("login!getLoginService")
    Call<LoginResponse> login(@Query("login") String login, @Query("password") String password);


    @GET("pokemon")
    Call<LoginResponse> logout();
}
