package com.mycompany.proyecto_ferreteria.model;

public class Producto {
    private int id;
    private String nombre;
    private Categoria categoria; 
    private Almacen ubicacion;  
    private int stockActual;
    private int stockMinimo;
    private boolean activo;

    public Producto(int id, String nombre, Categoria categoria, Almacen ubicacion, int stockActual, int stockMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.activo = true;
    }

    // Getters y Setters 
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public Almacen getUbicacion() { return ubicacion; }
    public void setUbicacion(Almacen ubicacion) { this.ubicacion = ubicacion; }
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }
    public int getStockMinimo() { return stockMinimo; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-15s | %-12s | %-15s | %-6d | %-6s |", 
                id, nombre, categoria.getNombre(), ubicacion.getUbicacionCompleta(), stockActual, (activo ? "SI" : "NO"));
    }
}
