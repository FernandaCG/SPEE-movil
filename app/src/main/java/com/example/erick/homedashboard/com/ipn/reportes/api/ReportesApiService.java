package com.example.erick.homedashboard.com.ipn.reportes.api;

import com.example.erick.homedashboard.com.ipn.pagos.response.PagoRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReportesApiService {

    @GET("pokemon")
    Call<PagoRespuesta> obtenerListaPagos(@Query("limit") int limit, @Query("offset") int offset);
}
