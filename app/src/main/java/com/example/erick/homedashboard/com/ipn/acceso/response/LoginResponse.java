package com.example.erick.homedashboard.com.ipn.acceso.response;

import com.example.erick.homedashboard.com.ipn.acceso.modelo.Usuario;

public class LoginResponse {

    private Usuario ajaxResult;

    public Usuario getAjaxResult() {
        return ajaxResult;
    }

    public void setAjaxResult(Usuario ajaxResult) {
        this.ajaxResult = ajaxResult;
    }
}
