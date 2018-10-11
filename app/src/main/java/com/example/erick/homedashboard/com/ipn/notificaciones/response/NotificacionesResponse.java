package com.example.erick.homedashboard.com.ipn.notificaciones.response;

import com.example.erick.homedashboard.com.ipn.notificaciones.modelo.Notificacion;

import java.util.ArrayList;

public class NotificacionesResponse {

    private ArrayList<Notificacion> results;

    public ArrayList<Notificacion> getResults() {
        return results;
    }

    public void setResults(ArrayList<Notificacion> results) {
        this.results = results;
    }
}
