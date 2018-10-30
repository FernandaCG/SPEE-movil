package com.example.erick.homedashboard.com.ipn.servicios.response;

import com.example.erick.homedashboard.com.ipn.pagos.modelo.Pago;
import com.example.erick.homedashboard.com.ipn.servicios.modelo.CatalogoServicios;

import java.util.ArrayList;
import java.util.List;

public class CatalogoServiciosResponse {

    private ArrayList<CatalogoServicios> ajaxResult;

    public ArrayList<CatalogoServicios> getAjaxResult() {
        return ajaxResult;
    }

    public void setAjaxResult(ArrayList<CatalogoServicios> ajaxResult) {
        this.ajaxResult = ajaxResult;
    }
}
