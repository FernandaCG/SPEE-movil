package com.example.erick.homedashboard.com.ipn.notas.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.adapters.NotasAdapter;
import com.example.erick.homedashboard.com.ipn.notas.api.NotasApiService;
import com.example.erick.homedashboard.com.ipn.notas.modelo.Nota;
import com.example.erick.homedashboard.com.ipn.notas.response.NotasResponse;
import com.example.erick.homedashboard.com.ipn.util.BaseUrlContants;
import com.example.erick.homedashboard.com.ipn.util.RetrofitClient;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotaController extends AppCompatActivity {

    private static final String TAG = "NOTAS: ";

    private NotasAdapter listaNotasAdapter;
    private RecyclerView recyclerView;
    private NotasApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        service = RetrofitClient
                .getClient(BaseUrlContants.NOTAS_URL)
                .create(NotasApiService.class);

        consumeService(service.obtenerListaNotas(14));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_notas_id);
        listaNotasAdapter = new NotasAdapter(this);
        recyclerView.setAdapter(listaNotasAdapter);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void consumeService(Call respuestaCall) {
        Log.e(TAG, " onResponse: " + respuestaCall.request());
        respuestaCall.enqueue(new Callback<NotasResponse>() {
            @Override
            public void onResponse(Call<NotasResponse> call, Response<NotasResponse> response) {
                if(response.isSuccessful()) {
                    Log.e(TAG, " onResponseSuccess: " + new Gson().toJson(response));
                    ArrayList<Nota> responseList = response.body().getAjaxResult();
                    Log.e(TAG, " List: " + response.body().getAjaxResult());
                    Log.e(TAG, " onMessage: " + response.message());
                    listaNotasAdapter.agregarListaNotas(responseList);
                    Log.e(TAG, " onResponse: success");
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<NotasResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
