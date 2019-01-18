package com.example.erick.homedashboard.com.ipn.pagos.controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.home.controller.MainActivity;
import com.example.erick.homedashboard.com.ipn.pagos.api.CargarPagoApi;
import com.example.erick.homedashboard.com.ipn.util.BaseUrlContants;
import com.example.erick.homedashboard.com.ipn.util.RetrofitClient;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdjuntarPago extends AppCompatActivity {

    private static final String TAG = "ADJUNTAR_PAGO: ";
    private static final int FILE_SELECT_CODE = 0;

    private CardView notasCard;
    private CargarPagoApi service;
    private ImageView imagenPago;
    private TextView folio;
    private TextInputLayout mFloatLabelFolio;
    private Button pagar;
    private String mediaPath;
    private String[] mediaColumns = { MediaStore.Video.Media._ID };
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjuntar_pago);

        imagenPago = findViewById(R.id.imagen_pago_id);
        notasCard = findViewById(R.id.aduntar_image_card_id);
        notasCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, FILE_SELECT_CODE);
            }
        });
        folio = findViewById(R.id.id_folio);
        folio.setVisibility(View.INVISIBLE);
        pagar = findViewById(R.id.btn_enviar_pago);
        pagar.setVisibility(View.INVISIBLE);
        mFloatLabelFolio = findViewById(R.id.float_label_folio);
        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cancel = false;
                View focusView = null;
                mFloatLabelFolio.setError(null);
                String folioText = folio.getText().toString();
                if (TextUtils.isEmpty(folioText)) {
                    mFloatLabelFolio.setError(getString(R.string.error_field_required));
                    focusView = mFloatLabelFolio;
                    cancel = true;
                } else {
                    cancel = false;
                }

                if (cancel) {
                    focusView.requestFocus();
                } else {
                    uploadFile( 1, Integer.parseInt(folioText), 14);
                    redireccionar();
                }
            }
        });
    }

    public void redireccionar() {
        Toast.makeText(this, "Evio de Pago exitoso", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, PagoController.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if ( result == PackageManager.PERMISSION_GRANTED) {

            try {
                if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
                    Toast.makeText(this, "Ingrese Folio", Toast.LENGTH_LONG).show();
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);

                    Bitmap bm = BitmapFactory.decodeFile(mediaPath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                    imagenPago.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    cursor.close();

                    validatePago(imagenPago, folio);

                } else {
                    Toast.makeText(this, "No se ha capturado un archivo", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Ha sucedido un error ", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean validatePago(ImageView imagenPago,TextView  folio) {
        if(imagenPago != null) {
            folio.setVisibility(View.VISIBLE);
            pagar.setVisibility(View.VISIBLE);
            return Boolean.TRUE;
        } else {
            Toast.makeText(this, "Ha sucedido un error ", Toast.LENGTH_LONG).show();
            return Boolean.FALSE;
        }
    }

    private void uploadFile(Integer idServicio, Integer folio, Integer idUsuario) {
        File file = new File(mediaPath);

        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        service = RetrofitClient
                .getClient(BaseUrlContants.CARGAR_PAGOS_URL)
                .create(CargarPagoApi.class);
        Call<ResponseBody> call = service.agreagarPago(fileToUpload, filename,idServicio, folio, idUsuario);
        System.out.println( "==========>>>" + call);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody serverResponse = response.body();
                Log.e(TAG, " onResponseSuccess: " + new Gson().toJson(serverResponse));
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
}
