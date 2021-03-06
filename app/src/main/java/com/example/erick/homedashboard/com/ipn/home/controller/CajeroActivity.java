package com.example.erick.homedashboard.com.ipn.home.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.erick.homedashboard.R;
import com.example.erick.homedashboard.com.ipn.citas.controller.CitasController;
import com.example.erick.homedashboard.com.ipn.cuenta.controller.MiCuentaController;
import com.example.erick.homedashboard.com.ipn.notas.controller.NotaController;
import com.example.erick.homedashboard.com.ipn.notificaciones.controller.NotificacionController;
import com.example.erick.homedashboard.com.ipn.pagos.controller.HistorialPagoController;
import com.example.erick.homedashboard.com.ipn.pagos.controller.PagoController;
import com.example.erick.homedashboard.com.ipn.pagos.service.PagoService;
import com.example.erick.homedashboard.com.ipn.reportes.controller.ReportesController;
import com.example.erick.homedashboard.com.ipn.servicios.controller.ServiciosController;

public class CajeroActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cajero);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_cajero);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_cajero);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.nav_caj_pagos) {
            intent = new Intent(this, PagoController.class);
            startActivity(intent);
        } else if (id == R.id.nav_caj_notificaciones) {
            intent = new Intent(this, NotificacionController.class);
            startActivity(intent);
        }  else if (id == R.id.nav_caj_historial) {
            intent = new Intent(this, HistorialPagoController.class);
            startActivity(intent);
        }else if (id == R.id.nav_mi_cuenta) {
            intent = new Intent(this, MiCuentaController.class);
            startActivity(intent);
        } else if (id == R.id.nav_acerca) {
            intent = new Intent(this, AboutController.class);
            startActivity(intent);
        } else if (id == R.id.nav_cerrar_sesion) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_cajero);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}