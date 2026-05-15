package com.mycompany.proyecto_ferreteria.service;

import com.mycompany.proyecto_ferreteria.model.*;
import com.mycompany.proyecto_ferreteria.persistence.GestorPersistencia;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InventarioService {
    private List<Producto> productos;
    private List<Categoria> categorias; 
    private List<Almacen> almacenes;
    private List<Proveedor> proveedores;
    private Stack<Movimiento> historialMovimientos;
    private GestorPersistencia gestor;

    public InventarioService() {
        this.gestor = new GestorPersistencia();
        this.productos = gestor.cargarProductos();
        this.historialMovimientos = new Stack<>();
        this.categorias = new ArrayList<>();
        this.almacenes = new ArrayList<>();
        this.proveedores = new ArrayList<>();
        cargarDatosMaestros(); 
    }

    private void cargarDatosMaestros() {
        // Categorías
        categorias.add(new Categoria(1, "HERRAMIENTAS", "Herramientas manuales y eléctricas"));
        categorias.add(new Categoria(2, "ELECTRICIDAD", "Cables, focos y tableros"));
        categorias.add(new Categoria(3, "PINTURA", "Látex, esmaltes y brochas"));
        
        // Almacenes
        almacenes.add(new Almacen(1, "Principal", "Pasillo A"));
        almacenes.add(new Almacen(2, "Exhibición", "Estante 4"));
        
        // Proveedores
        proveedores.add(new Proveedor(1, "TROPER.", "20123456789", "987654321"));
    }

    
    public Producto buscarProducto(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public Producto buscarProducto(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) return p;
        }
        return null;
    }

    public void agregarProducto(String nombre, int idCat, int idAlm, int stock, int min) {
        Categoria cat = buscarCategoria(idCat);
        Almacen alm = buscarAlmacen(idAlm);
        
        if (cat != null && alm != null) {
            int nuevoId = productos.isEmpty() ? 1 : productos.get(productos.size() - 1).getId() + 1;
            Producto p = new Producto(nuevoId, nombre, cat, alm, stock, min);
            productos.add(p);
            gestor.guardarProductos(productos);
            System.out.println("[ÉXITO] Producto registrado en: " + alm.getUbicacionCompleta());
        } else {
            System.out.println("[ERROR] Categoría o Almacén no válidos.");
        }
    }

    public void registrarEntrada(int idProd, int cant, String mot, Usuario user, int idProv) {
        Producto p = buscarProducto(idProd);
        Proveedor prov = buscarProveedor(idProv);
        
        if (p != null && prov != null && p.isActivo()) {
            p.setStockActual(p.getStockActual() + cant);
            Movimiento mov = new Movimiento("ENTRADA", idProd, cant, mot + " (Prov: " + prov.getNombreEmpresa() + ")", user.getNombre());
            historialMovimientos.push(mov);
            gestor.guardarProductos(productos);
            System.out.println("[ÉXITO] Stock actualizado. Proveedor: " + prov.getNombreEmpresa());
        }
    }

    public void registrarSalida(int idProd, int cant, String mot, Usuario user) {
        Producto p = buscarProducto(idProd);
        if (p != null && p.isActivo() && p.getStockActual() >= cant) {
            p.setStockActual(p.getStockActual() - cant);
            Movimiento mov = new Movimiento("SALIDA", idProd, cant, mot, user.getNombre());
            historialMovimientos.push(mov);
            gestor.guardarProductos(productos);
            System.out.println("[ÉXITO] Salida registrada.");
        } else {
            System.out.println("[ERROR] No se pudo realizar la salida (Stock insuficiente o inactivo).");
        }
    }

    public Categoria buscarCategoria(int id) {
        return categorias.stream().filter(c -> c.getIdCat() == id).findFirst().orElse(null);
    }

    public Almacen buscarAlmacen(int id) {
        return almacenes.stream().filter(a -> a.getidAlm() == id).findFirst().orElse(null);
    }

    public Proveedor buscarProveedor(int id) {
        return proveedores.stream().filter(pr -> pr.getidProv() == id).findFirst().orElse(null);
    }

    public void mostrarCategorias() {
        categorias.forEach(c -> System.out.println(c.getIdCat() + ". " + c.getNombre()));
    }

    public void mostrarAlmacenes() {
        almacenes.forEach(a -> System.out.println(a.getidAlm() + ". " + a.getUbicacionCompleta()));
    }

    public void mostrarProveedores() {
        proveedores.forEach(p -> System.out.println(p.toString()));
    }

  public void mostrarTodosLosProductos() {
    System.out.println("\n| ID   | NOMBRE          | CATEGORIA    | UBICACIÓN            | STOCK  | ACTIVO | AVISO");
    productos.forEach(p -> {
        String aviso = (p.getStockActual() <= p.getStockMinimo()) ? "[ STOCK BAJO ]" : "";
        System.out.println(p.toString() + " " + aviso);
    });
}
    
    public java.util.List<Producto> getProductos() {
        return this.productos;
    }
}