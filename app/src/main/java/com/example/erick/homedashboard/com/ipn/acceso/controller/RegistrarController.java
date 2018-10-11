package com.example.erick.homedashboard.com.ipn.acceso.controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erick.homedashboard.R;

public class RegistrarController extends AppCompatActivity implements View.OnClickListener  {

    private TextView regresarLogin;
    private Button registrar;
    private CardView identificacionCard;
    private CardView comprobanteCard;
    private Bitmap imageBitmap;

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imagenIdentificacion;
    private ImageView imagenComprobante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        imagenIdentificacion = findViewById(R.id.imagen_identificacion_id);
        imagenComprobante = findViewById(R.id.imagen_comprobante_id);

        regresarLogin = findViewById(R.id.regreso_login);
        registrar = findViewById(R.id.btn_crear_cuenta);
        identificacionCard = findViewById(R.id.identificacion_card_id);
        comprobanteCard = findViewById(R.id.comprobante_card_id);

        regresarLogin.setOnClickListener(this);
        identificacionCard.setOnClickListener(this);
        comprobanteCard.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.regreso_login:
                intent = new Intent(this, LoginController.class);
                startActivity(intent);
                break;
            case R.id.identificacion_card_id:
                openCamera();
                imagenIdentificacion.setImageBitmap(imageBitmap);
                break;
            case R.id.comprobante_card_id:
                openCamera();
                imagenComprobante.setImageBitmap(imageBitmap);
                break;
            case R.id.btn_crear_cuenta:
                //enviar servicio de correo
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
        }
    }
}
