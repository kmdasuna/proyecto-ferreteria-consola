package com.mycompany.proyecto_ferreteria.model;

public class Proveedor {
    private int idProv;
    private String nombreEmpresa;
    private String ruc;
    private String telefono;

    public Proveedor(int idProv, String nombreEmpresa, String ruc, String telefono) {
        this.idProv = idProv;
        this.nombreEmpresa = nombreEmpresa;
        this.ruc = ruc;
        this.telefono = telefono;
    }

    public int getidProv(){ return idProv; }
    public String getNombreEmpresa() { return nombreEmpresa; }
    public String getRuc() { return ruc; }
    
    @Override
    public String toString() {
        return String.format("ID: %d | %s (RUC: %s)", idProv, nombreEmpresa, ruc);
    }
}