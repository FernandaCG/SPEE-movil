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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotaController extends AppCompatActivity {

    private static final String TAG = "NOTAS: ";

    private NotasAdapter listaNotasAdapter;
    private RecyclerView recyclerView;
    private NotasApiService service;

    private int offset;
    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        service = RetrofitClient
                .getClient(BaseUrlContants.NOTAS_URL)
                .create(NotasApiService.class);

        aptoParaCargar = true;
        offset = 0;
        consumeService(service.obtenerListaNotas(20, offset));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_notas_id);
        listaNotasAdapter = new NotasAdapter(this);
        recyclerView.setAdapter(listaNotasAdapter);
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
                            consumeService(service.obtenerListaNotas(20, offset));
                        }
                    }
                }
            }
        });
    }

    public void consumeService(Call respuestaCall) {
        respuestaCall.enqueue(new Callback<NotasResponse>() {
            @Override
            public void onResponse(Call<NotasResponse> call, Response<NotasResponse> response) {
                aptoParaCargar = true;
                if(response.isSuccessful()) {
                    ArrayList<Nota> responseList = response.body().getResults();
                    listaNotasAdapter.agregarListaNotas(responseList);
                    Log.e(TAG, " onResponse: " + responseList);
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
