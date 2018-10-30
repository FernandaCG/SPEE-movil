package com.example.erick.homedashboard.com.ipn.reportes.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.adapters.PagosAdapter;
import com.example.erick.homedashboard.com.ipn.adapters.ReportesAdapter;
import com.example.erick.homedashboard.com.ipn.pagos.api.PagosApiService;
import com.example.erick.homedashboard.com.ipn.pagos.modelo.Pago;
import com.example.erick.homedashboard.com.ipn.pagos.response.PagoRespuesta;
import com.example.erick.homedashboard.com.ipn.reportes.api.ReportesApiService;
import com.example.erick.homedashboard.com.ipn.util.BaseUrlContants;
import com.example.erick.homedashboard.com.ipn.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportesController extends AppCompatActivity {

    private static final String TAG = "REPORTES: ";

    private ReportesAdapter listaReportesAdapter;
    private RecyclerView recyclerView;
    private ReportesApiService service;

    private int offset;
    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        service = RetrofitClient
                .getClient(BaseUrlContants.PAGOS_URL)
                .create(ReportesApiService.class);

        aptoParaCargar = true;
        offset = 0;
        consumeService(service.obtenerListaPagos(20, offset));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_reportes_id);
        listaReportesAdapter = new ReportesAdapter(this);
        recyclerView.setAdapter(listaReportesAdapter);
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
                            consumeService(service.obtenerListaPagos(20, offset));
                        }
                    }
                }
            }
        });
    }

    public void consumeService(Call respuestaCall) {
        respuestaCall.enqueue(new Callback<PagoRespuesta>() {
            @Override
            public void onResponse(Call<PagoRespuesta> call, Response<PagoRespuesta> response) {
                aptoParaCargar = true;
                if(response.isSuccessful()) {
                 //   ArrayList<Pago> responseList = response.body().getResults();
                  //  Log.e(TAG, " onResponse: " + responseList);
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PagoRespuesta> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
