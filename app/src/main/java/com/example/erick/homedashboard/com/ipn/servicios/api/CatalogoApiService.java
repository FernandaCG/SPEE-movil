package com.example.erick.homedashboard.com.ipn.servicios.api;

import com.example.erick.homedashboard.com.ipn.servicios.response.CatalogoServiciosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatalogoApiService {

    @GET("gestionar-servicios!getAllServices")
    Call<CatalogoServiciosResponse> obtenerCatalogoServicios();
}
