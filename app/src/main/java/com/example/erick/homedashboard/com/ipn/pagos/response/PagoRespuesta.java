package com.example.erick.homedashboard.com.ipn.pagos.response;

import com.example.erick.homedashboard.com.ipn.pagos.modelo.Pago;

import java.util.ArrayList;

public class PagoRespuesta {

    private ArrayList<Pago> jsonResult;

    public ArrayList<Pago> getAjaxResult() {
        return jsonResult;
    }

    public void setAjaxResult(ArrayList<Pago> ajaxResult) {
        this.jsonResult = ajaxResult;
    }
}
