package com.example.erick.homedashboard.com.ipn.permisos.api;

import com.example.erick.homedashboard.com.ipn.pagos.response.PagoRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PermisosApiService {

    @GET("pokemon")
    Call<PagoRespuesta> obtenerListaPagos(@Query("limit") int limit, @Query("offset") int offset);
}
