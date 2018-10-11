package com.example.erick.homedashboard.com.ipn.pagos.response;

import com.example.erick.homedashboard.com.ipn.pagos.modelo.Pago;

import java.util.ArrayList;

public class PagoRespuesta {

    private ArrayList<Pago> results;

    private Integer count;

    public ArrayList<Pago> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pago> results) {
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
