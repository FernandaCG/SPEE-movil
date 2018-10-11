package com.example.erick.homedashboard.com.ipn.citas.response;

import com.example.erick.homedashboard.com.ipn.citas.modelo.Cita;

import java.util.ArrayList;

public class CitaResponse {

    private ArrayList<Cita> results;

    public ArrayList<Cita> getResults() {
        return results;
    }

    public void setResults(ArrayList<Cita> results) {
        this.results = results;
    }
}
