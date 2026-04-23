package com.mycompany.proyecto_ferreteria.service;

import com.mycompany.proyecto_ferreteria.model.Movimiento;
import com.mycompany.proyecto_ferreteria.model.Producto;
import com.mycompany.proyecto_ferreteria.model.Usuario;
import com.mycompany.proyecto_ferreteria.persistence.GestorPersistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class InventarioService {
    private List<Producto> productos;
    private Stack<Movimiento> historialMovimientos; 
    private GestorPersistencia gestor;

    public InventarioService() {
        this.gestor = new GestorPersistencia();
        this.productos = gestor.cargarProductos();
        this.historialMovimientos = new Stack<>();
    }

    // Gestión de los Productos: agregar, buscar, desactivar.

    public void agregarProducto(String nombre, String categoria, int stockActual, int stockMinimo) {
        int nuevoId = generarNuevoId();
        Producto p = new Producto(nuevoId, nombre, categoria, stockActual, stockMinimo);
        productos.add(p);
        guardarCambios();
        System.out.println("[ÉXITO] Producto agregado: " + nombre + " (ID: " + nuevoId + ")");
    }

    public Producto buscarProducto(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null; 
    }

    public void desactivarProducto(int id) {
        Producto p = buscarProducto(id);
        if (p != null) {
            p.setActivo(false);
            guardarCambios();
            System.out.println("[ÉXITO] Producto desactivado.");
        } else {
            System.out.println("[ERROR] Producto no encontrado.");
        }
    }

    // Gestión del Inventario y Movimientos.

    public void registrarMovimiento(int idProducto, String tipo, int cantidad, String motivo, Usuario usuario) {
        Producto p = buscarProducto(idProducto);
        
        if (p == null) {
            System.out.println("[ERROR] Producto no existe.");
            return;
        }
        if (!p.isActivo()) {
            System.out.println("[ERROR] No se puede operar sobre un producto inactivo.");
            return;
        }
        if (cantidad <= 0) {
            System.out.println("[ERROR] La cantidad debe ser mayor a 0.");
            return;
        }

        if (tipo.equalsIgnoreCase("SALIDA")) {
            if (p.getStockActual() < cantidad) {
                System.out.println("[ERROR] Stock insuficiente. Stock actual: " + p.getStockActual());
                return;
            }
            p.setStockActual(p.getStockActual() - cantidad);
        } else if (tipo.equalsIgnoreCase("ENTRADA")) {
            p.setStockActual(p.getStockActual() + cantidad);
        } else {
            System.out.println("[ERROR] Tipo de movimiento inválido.");
            return;
        }

        // Registrar en el historial y guardar.
        Movimiento mov = new Movimiento(tipo, idProducto, cantidad, motivo, usuario.getNombre());
        historialMovimientos.push(mov);
        guardarCambios();
        
        System.out.println("[ÉXITO] Movimiento registrado. Nuevo stock: " + p.getStockActual());
        verificarAlertaStock(p);
    }

    public void revertirUltimoMovimiento(Usuario usuario) {
        if (!usuario.esAdmin()) {
            System.out.println("[ERROR] Solo un ADMIN puede revertir movimientos.");
            return;
        }
        if (historialMovimientos.isEmpty()) {
            System.out.println("[INFO] No hay movimientos para revertir.");
            return;
        }

        Movimiento ultimo = historialMovimientos.pop();
        Producto p = buscarProducto(ultimo.getProductoId());

        if (p != null) {
            if (ultimo.getTipo().equalsIgnoreCase("ENTRADA")) {
                p.setStockActual(p.getStockActual() - ultimo.getCantidad());
            } else {
                p.setStockActual(p.getStockActual() + ultimo.getCantidad());
            }
            guardarCambios();
            System.out.println("[ÉXITO] Se revirtió el movimiento: " + ultimo.getTipo() + " de " + ultimo.getCantidad() + " unidades.");
        }
    }

    // Reportes

    public void mostrarTodosLosProductos() {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        System.out.println("| ID   | Nombre               | Categoría       | Stock    | Min      | Estado   |");
        System.out.println("----------------------------------------------------------------------------------");
        for (Producto p : productos) {
            System.out.println(p.toString());
        }
    }

    public void mostrarAlertas() {
        System.out.println("\n--- ALERTAS DE STOCK BAJO ---");
        boolean hayAlertas = false;
        for (Producto p : productos) {
            if (p.isActivo() && p.getStockActual() <= p.getStockMinimo()) {
                System.out.println("⚠️ [ALERTA] " + p.getNombre() + " tiene stock crítico: " + p.getStockActual() + " (Mínimo: " + p.getStockMinimo() + ")");
                hayAlertas = true;
            }
        }
        if (!hayAlertas) System.out.println("Todo el stock está en niveles óptimos.");
    }

    private void verificarAlertaStock(Producto p) {
        if (p.getStockActual() <= p.getStockMinimo()) {
            System.out.println("⚠️ [ALERTA] El producto " + p.getNombre() + " ha alcanzado su nivel de stock mínimo.");
        }
    }

    private int generarNuevoId() {
        int maxId = 0;
        for (Producto p : productos) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        return maxId + 1;
    }

    private void guardarCambios() {
        gestor.guardarProductos(productos);
    }
}
