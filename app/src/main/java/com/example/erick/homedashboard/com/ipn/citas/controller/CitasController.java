package com.example.erick.homedashboard.com.ipn.citas.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.adapters.CitasAdapter;
import com.example.erick.homedashboard.com.ipn.citas.api.CitasApiService;
import com.example.erick.homedashboard.com.ipn.citas.modelo.Cita;
import com.example.erick.homedashboard.com.ipn.citas.response.CitaResponse;
import com.example.erick.homedashboard.com.ipn.util.BaseUrlContants;
import com.example.erick.homedashboard.com.ipn.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitasController extends AppCompatActivity {

    private static final String TAG = "CITAS: ";

    private CitasAdapter listaCitasAdapter;
    private RecyclerView recyclerView;
    private CitasApiService service;

    private int offset;
    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);

        service = RetrofitClient
                .getClient(BaseUrlContants.CITAS_URL)
                .create(CitasApiService.class);

        aptoParaCargar = true;
        offset = 0;
        consumeService(service.obtenerListaCitas(20, offset));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_citas_id);
        listaCitasAdapter = new CitasAdapter(this);
        recyclerView.setAdapter(listaCitasAdapter);
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
                            consumeService(service.obtenerListaCitas(20, offset));
                        }
                    }
                }
            }
        });
    }

    public void consumeService(Call respuestaCall) {
        respuestaCall.enqueue(new Callback<CitaResponse>() {
            @Override
            public void onResponse(Call<CitaResponse> call, Response<CitaResponse> response) {
                aptoParaCargar = true;
                if(response.isSuccessful()) {
                    ArrayList<Cita> responseList = response.body().getResults();
                    listaCitasAdapter.agregarListaCitas(responseList);
                    Log.e(TAG, " onResponse: " + responseList);
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CitaResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
