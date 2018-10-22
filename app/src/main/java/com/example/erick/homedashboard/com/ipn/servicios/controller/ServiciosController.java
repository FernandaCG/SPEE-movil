package com.example.erick.homedashboard.com.ipn.servicios.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.example.erick.homedashboard.AdjuntarPago;
import android.content.Intent;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.adapters.CatalogoServiciosAdapter;
import com.example.erick.homedashboard.com.ipn.adapters.OnItemClickListener;
import com.example.erick.homedashboard.com.ipn.servicios.api.CatalogoApiService;
import com.example.erick.homedashboard.com.ipn.servicios.modelo.CatalogoServicios;
import com.example.erick.homedashboard.com.ipn.servicios.response.CatalogoServiciosResponse;
import com.example.erick.homedashboard.com.ipn.util.BaseUrlContants;
import com.example.erick.homedashboard.com.ipn.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ServiciosController extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "SERVICIOS: ";

    private CatalogoServiciosAdapter listaServiciosAdapter;
    private RecyclerView recyclerView;
    private CatalogoApiService service;

    private int offset;
    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        service = RetrofitClient
                .getClient(BaseUrlContants.CATALOGO_SERVICIOS_URL)
                .create(CatalogoApiService.class);

        aptoParaCargar = true;
        offset = 0;
        consumeService(service.obtenerCatalogoServicios(20, offset));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_servicios_id);
        listaServiciosAdapter = new CatalogoServiciosAdapter(this);
        listaServiciosAdapter.setClickListener(this);
        recyclerView.setAdapter(listaServiciosAdapter);
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
                            consumeService(service.obtenerCatalogoServicios(20, offset));
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        Log.i(TAG, " Llegamos al click.");
        Intent intent;
        intent = new Intent(this, AdjuntarPago.class);
        startActivity(intent);
    }

    public void consumeService(Call respuestaCall) {
        respuestaCall.enqueue(new Callback<CatalogoServiciosResponse>() {
            @Override
            public void onResponse(Call<CatalogoServiciosResponse> call, Response<CatalogoServiciosResponse> response) {
                aptoParaCargar = true;
                if(response.isSuccessful()) {
                    ArrayList<CatalogoServicios> responseList = response.body().getResults();
                    listaServiciosAdapter.agregarListaServicios(responseList);
                    Log.e(TAG, " onResponseSuccess: " + responseList.get(1).getName());
                } else {
                    Log.e(TAG, " onResponseError: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CatalogoServiciosResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
