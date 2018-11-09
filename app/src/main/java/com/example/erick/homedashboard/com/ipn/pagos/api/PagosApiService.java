package com.example.erick.homedashboard.com.ipn.pagos.api;

import com.example.erick.homedashboard.com.ipn.pagos.response.PagoRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;


public interface PagosApiService {

    @GET("gestionar-pagos!getPaymentsByUserId")
    Call<PagoRespuesta> obtenerListaPagos();

}
