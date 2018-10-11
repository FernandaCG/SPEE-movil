package com.example.erick.homedashboard.com.ipn.notas.response;

import com.example.erick.homedashboard.com.ipn.notas.modelo.Nota;

import java.util.ArrayList;

public class NotasResponse {

    private ArrayList<Nota> results;

    public ArrayList<Nota> getResults() {
        return results;
    }

    public void setResults(ArrayList<Nota> results) {
        this.results = results;
    }
}
