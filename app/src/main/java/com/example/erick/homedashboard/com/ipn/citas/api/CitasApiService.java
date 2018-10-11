package com.example.erick.homedashboard.com.ipn.citas.api;

import com.example.erick.homedashboard.com.ipn.citas.response.CitaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CitasApiService {

    @GET("pokemon")
    Call<CitaResponse> obtenerListaCitas(@Query("limit") int limit, @Query("offset") int offset);
}
