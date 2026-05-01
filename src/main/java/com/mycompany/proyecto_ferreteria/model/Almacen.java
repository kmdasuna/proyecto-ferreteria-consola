package com.mycompany.proyecto_ferreteria.model;

public class Almacen {
    private int idAlm;
    private String nombre; // Ej: Almacén Principal, Zona Exhibición
    private String seccion; // Ej: Pasillo A, Estantería 4

    public Almacen(int idAlm, String nombre, String seccion) {
        this.idAlm = idAlm;
        this.nombre = nombre;
        this.seccion = seccion;
    }

    public int getidAlm(){ return idAlm; }
    public String getUbicacionCompleta() {
        return nombre + " - " + seccion;
    }
}
