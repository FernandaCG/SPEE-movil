package com.example.erick.homedashboard.com.ipn.notas.modelo;

import com.example.erick.homedashboard.com.ipn.servicios.modelo.CatalogoServicios;

public class Nota {

    private String fhEmision;

    private Integer idCatalogoServicio;

    private CatalogoServicios catalogoServicio;

    public String getFhEmision() {
        return fhEmision;
    }

    public void setFhEmision(String fhEmision) {
        this.fhEmision = fhEmision;
    }

    public Integer getIdCatalogoServicio() {
        return idCatalogoServicio;
    }

    public void setIdCatalogoServicio(Integer idCatalogoServicio) {
        this.idCatalogoServicio = idCatalogoServicio;
    }

    public CatalogoServicios getCatalogoServicio() {
        return catalogoServicio;
    }

    public void setCatalogoServicio(CatalogoServicios catalogoServicio) {
        this.catalogoServicio = catalogoServicio;
    }
}
