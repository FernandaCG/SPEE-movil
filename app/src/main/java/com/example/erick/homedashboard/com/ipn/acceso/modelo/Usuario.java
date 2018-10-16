package com.example.erick.homedashboard.com.ipn.acceso.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("correo")
    @Expose
    private String login;

    @SerializedName("password")
    @Expose
    private String password;

    public String getLogin() {
        return login;
    }

    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
