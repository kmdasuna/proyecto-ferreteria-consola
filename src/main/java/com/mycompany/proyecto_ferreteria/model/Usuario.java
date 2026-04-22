package com.mycompany.proyecto_ferreteria.model;

public class Usuario {
    private String nombre;
    private String rol; // AQUÍ SOLO EXISTEN DOS TIPOS DE USUARIOS "ADMIN" o "OPERADOR"

    public Usuario(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol.toUpperCase();
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getRol() { return rol; }

    // Métodos de utilidad para lógica de permisos
    public boolean esAdmin() {
        return "ADMIN".equals(this.rol);
    }

    @Override
    public String toString() {
        return "Usuario: " + nombre + " [" + rol + "]";
    }
}
