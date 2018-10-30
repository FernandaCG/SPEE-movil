package com.example.erick.homedashboard.com.ipn.pagos.modelo;

import com.example.erick.homedashboard.com.ipn.servicios.modelo.CatalogoServicios;

import java.util.Date;

public class Pago {

    private String name;

    private Integer id;

    private CatalogoServicios catalogoServicio;

    private String fechaEnvio;

    private Integer idTipoComprobante;

    private Integer idCatalogoServicio;

    private Integer idEstadoPago;

    private Integer idUsuario;

    private Integer idCarpeta;

    private String folioOperacion;

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Integer getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Integer idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public Integer getIdCatalogoServicio() {
        return idCatalogoServicio;
    }

    public void setIdCatalogoServicio(Integer idCatalogoServicio) {
        this.idCatalogoServicio = idCatalogoServicio;
    }

    public Integer getIdEstadoPago() {
        return idEstadoPago;
    }

    public void setIdEstadoPago(Integer idEstadoPago) {
        this.idEstadoPago = idEstadoPago;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdCarpeta() {
        return idCarpeta;
    }

    public void setIdCarpeta(Integer idCarpeta) {
        this.idCarpeta = idCarpeta;
    }

    public String getFolioOperacion() {
        return folioOperacion;
    }

    public void setFolioOperacion(String folioOperacion) {
        this.folioOperacion = folioOperacion;
    }


    public CatalogoServicios getCatalogoServicio() {
        return catalogoServicio;
    }

    public void setCatalogoServicio(CatalogoServicios catalogoServicio) {
        this.catalogoServicio = catalogoServicio;
    }
}
