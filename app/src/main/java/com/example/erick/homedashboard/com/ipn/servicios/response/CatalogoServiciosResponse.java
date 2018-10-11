package com.example.erick.homedashboard.com.ipn.servicios.response;

import com.example.erick.homedashboard.com.ipn.servicios.modelo.CatalogoServicios;

import java.util.ArrayList;

public class CatalogoServiciosResponse {

    private ArrayList<CatalogoServicios> results;

    public ArrayList<CatalogoServicios> getResults() {
        return results;
    }

    public void setResults(ArrayList<CatalogoServicios> results) {
        this.results = results;
    }
}
