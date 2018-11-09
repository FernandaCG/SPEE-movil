package com.example.erick.homedashboard.com.ipn.pagos.api;

import java.io.File;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.Query;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

public interface CargarPagoApi {

    @Multipart
    @POST("gestionar-pagos!getImgService")
    Call<ResponseBody> agreagarPago(@Part MultipartBody.Part image,
                                    @Part("name") RequestBody name,
                                    @Query("idServicio") Integer idServicio,
                                    @Query("folio") Integer folio,
                                    @Query("idUser") Integer idUsuario);

    @GET("gestionar-pagos!getImgService")
    Call<ResponseBody> agregarB64(@Query("imgB") RequestBody image);


}
