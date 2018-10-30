package com.example.erick.homedashboard.com.ipn.notificaciones.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.adapters.NotificacionesAdapter;
import com.example.erick.homedashboard.com.ipn.notificaciones.api.NotificacionesApiService;
import com.example.erick.homedashboard.com.ipn.notificaciones.modelo.Notificacion;
import com.example.erick.homedashboard.com.ipn.notificaciones.response.NotificacionesResponse;
import com.example.erick.homedashboard.com.ipn.util.BaseUrlContants;
import com.example.erick.homedashboard.com.ipn.util.RetrofitClient;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificacionController extends AppCompatActivity {

    private static final String TAG = "NOTIFICACIONES: ";

    private NotificacionesAdapter listaNotificacionesAdapter;
    private RecyclerView recyclerView;
    private NotificacionesApiService service;

    private int offset;
    private boolean aptoParaCargar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);

        service = RetrofitClient
                .getClient(BaseUrlContants.NOTIFICACIONES_URL)
                .create(NotificacionesApiService.class);

        aptoParaCargar = true;
        offset = 0;
        consumeService(service.obtenerListaNotificaciones(20, offset));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_notificaciones_id);
        listaNotificacionesAdapter = new NotificacionesAdapter(this);
        recyclerView.setAdapter(listaNotificacionesAdapter);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Llegamos al final.");

                            aptoParaCargar = false;
                            offset += 20;
                            consumeService(service.obtenerListaNotificaciones(20, offset));
                        }
                    }
                }
            }
        });
    }

    public void consumeService(Call respuestaCall) {
        Log.e(TAG, " Request: " + respuestaCall.request());
        respuestaCall.enqueue(new Callback<NotificacionesResponse>() {
            @Override
            public void onResponse(Call<NotificacionesResponse> call, Response<NotificacionesResponse> response) {
                aptoParaCargar = true;
                if(response.isSuccessful()) {
                    Log.e(TAG, " onResponseSuccess: " + new Gson().toJson(response));
                    ArrayList<Notificacion> responseList = response.body().getAjaxResult();
                    listaNotificacionesAdapter.agregarListaNotificaciones(responseList);
                    Log.e(TAG, " onResponse: " + responseList);
                } else {
                    Log.e(TAG, " onError: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<NotificacionesResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
