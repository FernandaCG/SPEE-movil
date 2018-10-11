package com.example.erick.homedashboard.com.ipn.acceso.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.home.controller.MainActivity;

public class LoginController extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private TextView registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.btn_login);
        registrar = findViewById(R.id.registrarse_id);
        loginButton.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.btn_login:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.registrarse_id:
                intent = new Intent(this, RegistrarController.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
