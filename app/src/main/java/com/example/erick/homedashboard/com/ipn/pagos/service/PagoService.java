package com.example.erick.homedashboard.com.ipn.pagos.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.notas.controller.NotaController;
import com.example.erick.homedashboard.com.ipn.pagos.controller.PagoController;

public class PagoService extends AppCompatActivity implements View.OnClickListener{

    private CardView pagosCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pagosCard = findViewById(R.id.add_queja_id);
        pagosCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.add_queja_id:
                intent = new Intent(this, PagoController.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
