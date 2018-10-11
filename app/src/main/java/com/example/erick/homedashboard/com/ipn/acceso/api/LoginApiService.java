package com.example.erick.homedashboard.com.ipn.acceso.api;

import com.example.erick.homedashboard.com.ipn.acceso.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LoginApiService {

    @GET("pokemon")
    Call<LoginResponse> obtenerAcceso();
}
