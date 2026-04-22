package com.mycompany.proyecto_ferreteria.model;

public class Producto {
    private int id;
    private String nombre;
    private String categoria; 
    private int stockActual;
    private int stockMinimo;
    private boolean activo;

    public Producto(int id, String nombre, String categoria, int stockActual, int stockMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.activo = true; // Por defecto siempre estará activo
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }
    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    // Formato tabular para el reporte en consola
    @Override
    public String toString() {
        return String.format("| %-4d | %-20s | %-15s | %-8d | %-8d | %-8s |", 
                id, nombre, categoria, stockActual, stockMinimo, (activo ? "ACTIVO" : "INACTIVO"));
    }
}
