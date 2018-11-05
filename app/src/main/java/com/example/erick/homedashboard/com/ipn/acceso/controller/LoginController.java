package com.example.erick.homedashboard.com.ipn.acceso.controller;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.acceso.api.LoginApiService;
import com.example.erick.homedashboard.com.ipn.acceso.modelo.Usuario;
import com.example.erick.homedashboard.com.ipn.acceso.response.LoginResponse;
import com.example.erick.homedashboard.com.ipn.home.controller.BibliotecaActivity;
import com.example.erick.homedashboard.com.ipn.home.controller.CajeroActivity;
import com.example.erick.homedashboard.com.ipn.home.controller.CelexActivity;
import com.example.erick.homedashboard.com.ipn.home.controller.ContadorActivity;
import com.example.erick.homedashboard.com.ipn.home.controller.ImpresionesActivity;
import com.example.erick.homedashboard.com.ipn.home.controller.MainActivity;
import com.example.erick.homedashboard.com.ipn.home.controller.SdActivity;
import com.example.erick.homedashboard.com.ipn.home.controller.SubdirectorActivity;
import com.example.erick.homedashboard.com.ipn.pagos.modelo.Pago;
import com.example.erick.homedashboard.com.ipn.util.BaseUrlContants;
import com.example.erick.homedashboard.com.ipn.util.Numbers;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginController extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CONTROL_ACCESO: ";

    private Button loginButton;
    private TextView registrar;
    private EditText correo;
    private EditText password;
    private TextInputLayout mFloatLabelUserId;
    private TextInputLayout mFloatLabelPassword;
    private View mProgressView;
    private View mLoginFormView;

    private LoginApiService service;

    private static Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFloatLabelUserId = findViewById(R.id.float_label_user_id);
        mFloatLabelPassword = findViewById(R.id.float_label_password);
        correo = findViewById(R.id.id_mail);
        password = findViewById(R.id.id_password);
        mProgressView = findViewById(R.id.login_progress);
        mLoginFormView = findViewById(R.id.login_form);
        loginButton = findViewById(R.id.btn_login);
        registrar = findViewById(R.id.registrarse_id);
        loginButton.setOnClickListener(this);
        registrar.setOnClickListener(this);


        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlContants.LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(LoginApiService.class);



    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_login:
                if (isOnline()) {
                    validateCredentials(correo, password);
                } else {
                    showLoginError(getString(R.string.error_network));
                }
                break;
            case R.id.registrarse_id:
                intent = new Intent(this, RegistrarController.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void redireccionarPerfil(Integer perfil) {
        Intent intent;
        switch (perfil){
            case 1:
                intent = new Intent(this, SubdirectorActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, CelexActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, SdActivity.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this, BibliotecaActivity.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(this, ImpresionesActivity.class);
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(this, CajeroActivity.class);
                startActivity(intent);
                break;
            case 7:
                intent = new Intent(this, ContadorActivity.class);
                startActivity(intent);
                break;
            case 8:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case 9:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case 10:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private void validateCredentials(EditText correo, EditText password) {
        mFloatLabelUserId.setError(null);
        mFloatLabelPassword.setError(null);
        String correoText = correo.getText().toString();
        String passwordText = password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(correoText)) {
            mFloatLabelUserId.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelUserId;
            cancel = true;
        } else if (!isUserIdValid(correoText)) {
            mFloatLabelUserId.setError(getString(R.string.error_invalid_user_id));
            focusView = mFloatLabelUserId;
            cancel = true;
        }

        if (TextUtils.isEmpty(passwordText)) {
            mFloatLabelPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelPassword;
            cancel = true;
        } else if (!isPasswordValid(passwordText)) {
            mFloatLabelPassword.setError(getString(R.string.error_invalid_password));
            focusView = mFloatLabelPassword;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            Log.e(TAG, " Trata de hacer login");
            Log.e(TAG, " Datos: " + correoText + " " + passwordText);
            consumeService(service.login(correoText, passwordText));
            redireccionarPerfil(8);
        }
    }

    public void consumeService(Call respuestaCall) {
        Log.e(TAG, "URL" + respuestaCall.request());
        respuestaCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                showProgress(false);
                if(response.isSuccessful()) {
                    Log.e(TAG, " onResponseSuccess: " + new Gson().toJson(response));
                    LoginResponse loginResponse = response.body();
                    Log.e(TAG, " onResponse: " + loginResponse);
                } else {
                    Log.e(TAG, " onResponseError: " + new Gson().toJson(response));
                    Log.e(TAG, " onResponseError: " + response.errorBody());
                    showLoginError(response.body().toString());
                }

                // Guardar afiliado en preferencias
                //SessionPrefs.get(LoginController.this).saveAffiliate(response.body());

                // Ir a la citas médicas
                //  showAppointmentsScreen();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, " onFail: " + call.request());
                Log.e(TAG, " onFail: " +  t.getMessage());
                showProgress(false);
            }
        });
    }

    private boolean isUserIdValid(String userId) {
        return userId.length() < Numbers.CINCUENTA.getValor();
    }

    private boolean isPasswordValid(String password) {
        return password.length() < Numbers.DIEZ.getValor();
    }


    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);

        int visibility = show ? View.GONE : View.VISIBLE;
        mLoginFormView.setVisibility(visibility);
    }

    private void showLoginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
