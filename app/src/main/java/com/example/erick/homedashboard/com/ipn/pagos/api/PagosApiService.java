package com.example.erick.homedashboard.com.ipn.pagos.api;

import com.example.erick.homedashboard.com.ipn.pagos.response.PagoRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PagosApiService {

    @GET("pokemon")
    Call<PagoRespuesta> obtenerListaPagos(@Query("limit") int limit, @Query("offset") int offset);

    @POST("pokemon")
    Call<PagoRespuesta> agreagarPago();
}
