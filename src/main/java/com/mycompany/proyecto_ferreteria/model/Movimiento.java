package com.mycompany.proyecto_ferreteria.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimiento {
    private String fechaHora; // Se guardará como String para facilitar el archivo JSON
    private String tipo;      // Aquí solo existen dos tipos "ENTRADA" o "SALIDA"
    private int productoId;
    private int cantidad;
    private String motivo;
    private String responsable;

    public Movimiento(String tipo, int productoId, int cantidad, String motivo, String responsable) {
        this.fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        this.tipo = tipo.toUpperCase();
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.responsable = responsable;
    }

    // Getters
    public String getTipo() { return tipo; }
    public int getProductoId() { return productoId; }
    public int getCantidad() { return cantidad; }
    public String getFechaHora() { return fechaHora; }

    @Override
    public String toString() {
        return String.format("[%s] %-8s | Prod ID: %-3d | Cant: %-4d | Motivo: %-15s | Por: %s",
                fechaHora, tipo, productoId, cantidad, motivo, responsable);
    }
}
