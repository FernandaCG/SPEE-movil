package com.example.erick.homedashboard.com.ipn.notas.response;

import com.example.erick.homedashboard.com.ipn.notas.modelo.Nota;

import java.util.ArrayList;

public class NotasResponse {

    private ArrayList<Nota> ajaxResult;

    public ArrayList<Nota> getAjaxResult() {
        return ajaxResult;
    }

    public void setAjaxResult(ArrayList<Nota> ajaxResult) {
        this.ajaxResult = ajaxResult;
    }
}
