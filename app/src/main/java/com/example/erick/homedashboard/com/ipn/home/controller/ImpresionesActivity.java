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
import com.example.erick.homedashboard.com.ipn.acceso.controller.LoginController;
import com.example.erick.homedashboard.com.ipn.citas.controller.CitasController;
import com.example.erick.homedashboard.com.ipn.cuenta.controller.MiCuentaController;
import com.example.erick.homedashboard.com.ipn.notas.controller.NotaController;
import com.example.erick.homedashboard.com.ipn.notificaciones.controller.NotificacionController;
import com.example.erick.homedashboard.com.ipn.pagos.service.PagoService;
import com.example.erick.homedashboard.com.ipn.servicios.controller.ServiciosController;

public class ImpresionesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_impresiones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_impresiones);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_impresiones);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

        if (id == R.id.nav_impr_pagos) {
            intent = new Intent(this, PagoService.class);
            startActivity(intent);
        } else if (id == R.id.nav_impr_nota) {
            intent = new Intent(this, NotaController.class);
            startActivity(intent);
        } else if (id == R.id.nav_cerrar_sesion) {
            intent = new Intent(this, LoginController.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_impresiones);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
