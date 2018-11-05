package com.example.erick.homedashboard.com.ipn.pagos.controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
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
import com.example.erick.homedashboard.com.ipn.pagos.api.PagosApiService;
import com.example.erick.homedashboard.com.ipn.pagos.modelo.Pago;
import com.example.erick.homedashboard.com.ipn.pagos.response.PagoRespuesta;
import com.example.erick.homedashboard.com.ipn.util.BaseUrlContants;
import com.example.erick.homedashboard.com.ipn.util.RetrofitClient;
import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
    Button btnUpload, btnPickImage;
    private ImageView imagenPago;
    private Button pagar;
    ImageView imgView;

    String mediaPath;
    String[] mediaColumns = { MediaStore.Video.Media._ID };



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
        btnPickImage = (Button) findViewById(R.id.pick_img);
        imgView = (ImageView) findViewById(R.id.preview);

        service = RetrofitClient
                .getClient(BaseUrlContants.PAGOS_URL)
                .create(PagosApiService.class);

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });
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
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK &&  data != null) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                // Set the Image in ImageView for Previewing the Media
                imgView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();
                uploadFile();

            } else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }

    // Uploading Image/Video
    private void uploadFile() {

        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(mediaPath);

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

            PagosApiService getResponse = RetrofitClient.getClient(BaseUrlContants.CARGAR_PAGOS_URL).create(PagosApiService.class);
        Call<ResponseBody> call = getResponse.agreagarPago(fileToUpload, filename, 1);
        Log.e(TAG, "URL" + call.request());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody serverResponse = response.body();
                if(response.isSuccessful()) {
                    Log.e(TAG, " onResponseSuccess: " + new Gson().toJson(response));
                    ResponseBody responses = response.body();
                    Log.e(TAG, " onResponse: " + responses);
                } else {
                    Log.e(TAG, " onError: " + new Gson().toJson(response));
                    Log.e(TAG, " onErrorResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void validatePayment() {
        if(imagenPago != null){
            service = RetrofitClient
                    .getClient(BaseUrlContants.PAGOS_URL)
                    .create(PagosApiService.class);
           // consumeService(service.agreagarPago());
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
                    ArrayList<Pago> responseList = response.body().getAjaxResult();
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
