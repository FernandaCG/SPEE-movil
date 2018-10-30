package com.example.erick.homedashboard.com.ipn.notificaciones.response;

import com.example.erick.homedashboard.com.ipn.notificaciones.modelo.Notificacion;
import com.example.erick.homedashboard.com.ipn.pagos.modelo.Pago;

import java.util.ArrayList;

public class NotificacionesResponse {

    private ArrayList<Notificacion> ajaxResult;

    public ArrayList<Notificacion> getAjaxResult() {
        return ajaxResult;
    }

    public void setAjaxResult(ArrayList<Notificacion> ajaxResult) {
        this.ajaxResult = ajaxResult;
    }
}
