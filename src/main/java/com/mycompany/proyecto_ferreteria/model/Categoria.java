package com.mycompany.proyecto_ferreteria.model;

public class Categoria {
    private int IdCat;
    private String nombre;
    private String descripcion;

    public Categoria(int IdCat, String nombre, String descripcion) {
        this.IdCat = IdCat;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public int getIdCat(){ return IdCat; }
    public String getNombre() { return nombre; }
}
