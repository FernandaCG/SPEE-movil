package com.example.erick.homedashboard.com.ipn.notas.api;

import com.example.erick.homedashboard.com.ipn.notas.response.NotasResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NotasApiService {
    @GET("pokemon")
    Call<NotasResponse> obtenerListaNotas(@Query("limit") int limit, @Query("offset") int offset);
}
