package com.example.erick.homedashboard.com.ipn.pagos.api;

import com.example.erick.homedashboard.com.ipn.pagos.response.PagoRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import okhttp3.ResponseBody;
import retrofit2.http.Query;

public interface PagosApiService {

    @GET("gestionar-pagos!getPaymentsByUserId")
    Call<PagoRespuesta> obtenerListaPagos();

    @Multipart
    @POST("cargar-pago/new")
    Call<ResponseBody> agreagarPago(@Part MultipartBody.Part image, @Part("name") RequestBody name, @Query("idServicio") Integer idServicio);
}
