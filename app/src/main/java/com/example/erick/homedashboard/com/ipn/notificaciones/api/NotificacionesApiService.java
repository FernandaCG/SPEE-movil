package com.example.erick.homedashboard.com.ipn.notificaciones.api;

import com.example.erick.homedashboard.com.ipn.notificaciones.response.NotificacionesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NotificacionesApiService {

    @GET("pokemon")
    Call<NotificacionesResponse> obtenerListaNotificaciones(@Query("limit") int limit, @Query("offset") int offset);
}
