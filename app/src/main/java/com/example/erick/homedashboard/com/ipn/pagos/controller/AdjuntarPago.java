package com.example.erick.homedashboard.com.ipn.pagos.controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.pagos.api.PagosApiService;
import com.example.erick.homedashboard.com.ipn.pagos.modelo.Pago;
import com.example.erick.homedashboard.com.ipn.pagos.response.PagoRespuesta;
import com.example.erick.homedashboard.com.ipn.util.BaseUrlContants;
import com.example.erick.homedashboard.com.ipn.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdjuntarPago extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ADJUNTAR_PAGO: ";


    private static final int FILE_SELECT_CODE = 0;
    private static final int CAMERA_REQUEST = 1888;
    private CardView pagosCard;
    private CardView notasCard;
    private Bitmap imageBitmap;
    private PagosApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjuntar_pago);

        pagosCard = findViewById(R.id.adjuntar_file_card_id);
        notasCard = findViewById(R.id.aduntar_image_card_id);
        pagosCard.setOnClickListener(this);
        notasCard.setOnClickListener(this);

        service = RetrofitClient
                .getClient(BaseUrlContants.PAGOS_URL)
                .create(PagosApiService.class);

        //consumeService(service.agreagarPago());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pagos_card_id:
                openFileSystem();
                break;
            case R.id.notas_card_id:
                openCamera();
                break;
            default:
                break;
        }
    }

    public void openFileSystem() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            this.startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        } else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    public void consumeService(Call respuestaCall) {
        respuestaCall.enqueue(new Callback<PagoRespuesta>() {
            @Override
            public void onResponse(Call<PagoRespuesta> call, Response<PagoRespuesta> response) {
                if(response.isSuccessful()) {
                    ArrayList<Pago> responseList = response.body().getResults();
                    Log.e(TAG, " onResponse: " + responseList);
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
