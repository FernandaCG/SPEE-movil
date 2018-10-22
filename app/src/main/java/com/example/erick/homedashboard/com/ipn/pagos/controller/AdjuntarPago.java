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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.home.controller.SubdirectorActivity;
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
    private static final int FILE_SIZE_LIMIT = 2000;

    private CardView notasCard;
    private Bitmap imageBitmap;
    private PagosApiService service;
    private ImageView imagenPago;
    private Button pagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjuntar_pago);

        imagenPago = findViewById(R.id.imagen_pago_id);
        notasCard = findViewById(R.id.aduntar_image_card_id);
        pagar = findViewById(R.id.btn_enviar_pago);
        pagar.setVisibility(View.INVISIBLE);
        notasCard.setOnClickListener(this);
        pagar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aduntar_image_card_id:
                openCamera();
                break;
            case R.id.btn_enviar_pago:
                validatePayment();
                break;
            default:
                break;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imagenPago.setImageBitmap(imageBitmap);
            if(imagenPago != null) {
                pagar.setVisibility(View.VISIBLE);
            }
        }
    }

    public void validatePayment() {
        if(imagenPago != null){
            service = RetrofitClient
                    .getClient(BaseUrlContants.PAGOS_URL)
                    .create(PagosApiService.class);
            consumeService(service.agreagarPago());
            Toast.makeText(this, "Envio Exitoso", Toast.LENGTH_LONG).show();
            Log.e(TAG, "Envio Exitoso: ");
            Intent intent = new Intent(this, PagoController.class);
            startActivity(intent);
        } else {
            Log.e(TAG, "lel Imagen Nula: ");
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
