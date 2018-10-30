package com.example.erick.homedashboard.com.ipn.pagos.response;

import com.example.erick.homedashboard.com.ipn.pagos.modelo.Pago;

import java.util.ArrayList;

public class PagoRespuesta {

    private ArrayList<Pago> ajaxResult;

    public ArrayList<Pago> getAjaxResult() {
        return ajaxResult;
    }

    public void setAjaxResult(ArrayList<Pago> ajaxResult) {
        this.ajaxResult = ajaxResult;
    }
}
