package com.example.erick.homedashboard.com.ipn.servicios.api;

import com.example.erick.homedashboard.com.ipn.servicios.response.CatalogoServiciosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatalogoApiService {

    @GET("pokemon")
    Call<CatalogoServiciosResponse> obtenerCatalogoServicios(@Query("limit") int limit, @Query("offset") int offset);
}
