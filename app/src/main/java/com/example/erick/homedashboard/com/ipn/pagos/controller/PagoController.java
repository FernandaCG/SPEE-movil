package com.example.erick.homedashboard.com.ipn.pagos.controller;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.adapters.OnItemClickListener;
import com.example.erick.homedashboard.com.ipn.adapters.PagosAdapter;
import com.example.erick.homedashboard.com.ipn.pagos.api.PagosApiService;
import com.example.erick.homedashboard.com.ipn.pagos.modelo.Pago;
import com.example.erick.homedashboard.com.ipn.pagos.response.PagoRespuesta;
import com.example.erick.homedashboard.com.ipn.util.BaseUrlContants;
import com.example.erick.homedashboard.com.ipn.util.RetrofitClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoController extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "PAGO: ";

    private PagosAdapter listaPagosAdapter;
    private RecyclerView recyclerView;
    private PagosApiService service;
    private Dialog customDialog;

    private int offset;
    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);

       service = RetrofitClient
                .getClient(BaseUrlContants.PAGOS_URL)
                .create(PagosApiService.class);

        aptoParaCargar = true;
        offset = 0;
        consumeService(service.obtenerListaPagos(14));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        listaPagosAdapter = new PagosAdapter(this);
        listaPagosAdapter.setClickListener(this);
        recyclerView.setAdapter(listaPagosAdapter);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View view, int position) {
        customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.custom_pop_pagos);
        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.show();
    }

    public void consumeService(Call respuestaCall) {
        System.out.println(respuestaCall.request());
        respuestaCall.enqueue(new Callback<PagoRespuesta>() {
            @Override
            public void onResponse(Call<PagoRespuesta> call, Response<PagoRespuesta> response) {
                aptoParaCargar = true;
                if(response.isSuccessful()) {
                    Log.e(TAG, " onResponseSuccess: " + new Gson().toJson(response));
                    ArrayList<Pago> responseList = response.body().getAjaxResult();
                    listaPagosAdapter.agregarListaPagos(responseList);
                } else {
                    Log.e(TAG, " onResponseError: " + new Gson().toJson(response));
                    Log.e(TAG, " onErrorResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PagoRespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + new Gson().toJson(call.request()));
                t.printStackTrace();
            }
        });
    }

}

