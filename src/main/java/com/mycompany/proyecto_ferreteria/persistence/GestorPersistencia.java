package com.mycompany.proyecto_ferreteria.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mycompany.proyecto_ferreteria.model.Producto;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestorPersistencia {
    private static final String ARCHIVO_PRODUCTOS = "productos.json";
    private final Gson gson;

    public GestorPersistencia() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // En este metodo se guarda la lista de productos en el archivo JSON
    public void guardarProductos(List<Producto> productos) {
        try (Writer writer = new FileWriter(ARCHIVO_PRODUCTOS)) {
            gson.toJson(productos, writer);
            System.out.println("[SISTEMA]: Datos guardados en " + ARCHIVO_PRODUCTOS);
        } catch (IOException e) {
            System.err.println("Error al guardar productos: " + e.getMessage());
        }
    }

    // En este metodo se carga la lista de productos desde el archivo JSON
    public List<Producto> cargarProductos() {
        File archivo = new File(ARCHIVO_PRODUCTOS);
        if (!archivo.exists()) {
            return new ArrayList<>(); // En caso de no exitir, devolvemos una lista vacía
        }

        try (Reader reader = new FileReader(ARCHIVO_PRODUCTOS)) {
            Type listType = new TypeToken<ArrayList<Producto>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.err.println("Error al cargar productos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
