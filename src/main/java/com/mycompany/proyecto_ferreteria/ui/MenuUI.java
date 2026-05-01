package com.mycompany.proyecto_ferreteria.ui;

import com.mycompany.proyecto_ferreteria.model.Usuario;
import com.mycompany.proyecto_ferreteria.service.InventarioService;
import java.util.Scanner;

public class MenuUI {
    private InventarioService service;
    private Scanner scanner;
    private Usuario usuarioActual;

    public MenuUI() {
        this.service = new InventarioService();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("==========================================");
        System.out.println("   SISTEMA DE INVENTARIO - FERRETERÍA");
        System.out.println("==========================================");
        
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        
        String rol = "";
        while (!rol.equals("ADMIN") && !rol.equals("OPERADOR")) {
            System.out.print("Ingrese su rol (ADMIN / OPERADOR): ");
            rol = scanner.nextLine().toUpperCase();
        }

        usuarioActual = new Usuario(nombre, rol);
        System.out.println("\n[BIENVENIDO] " + usuarioActual.getNombre() + " (" + usuarioActual.getRol() + ")");
        
        mostrarMenuPrincipal();
    }
    // Menu principal, interración con el usuario después de escoger su rol/ingresar su nombre.
    private void mostrarMenuPrincipal() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Ver todos los productos");
            System.out.println("2. Registrar movimiento (Entrada/Salida)");
            System.out.println("3. Agregar nuevo producto");
            if (usuarioActual.esAdmin()) {
                System.out.println("4. Buscar producto (Por ID o Nombre)");
            }
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEnteroSeguro();

            switch (opcion) {
                case 1:
                    service.mostrarTodosLosProductos();
                    break;
                case 2:
                    registrarMovimientoUI();
                    break;
                case 3:
                    agregarProductoUI();
                    break;
                case 4:
                    if (usuarioActual.esAdmin()) buscarProductoUI();
                    else System.out.println("[ERROR] Opción no válida.");
                    break;
                case 0:
                    salir = true;
                    System.out.println("Saliendo del sistema... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("[ERROR] Opción incorrecta. Intente de nuevo.");
            }
        }
    }

    // Métodos para la interfaz interactiva

    private void agregarProductoUI() {
        System.out.println("\n--- AGREGAR PRODUCTO ---");
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        
        System.out.println("\nCategorías disponibles:");
        service.mostrarCategorias();
        System.out.print("Seleccione el ID de la categoría: ");
        int idCat = leerEnteroSeguro();
        
        System.out.println("\nAlmacenes disponibles:");
        service.mostrarAlmacenes();
        System.out.print("Seleccione el ID del almacén: ");
        int idAlm = leerEnteroSeguro();
        
        System.out.print("\nStock inicial: ");
        int stock = leerEnteroSeguro();
        
        System.out.print("Stock mínimo (para alertas): ");
        int minimo = leerEnteroSeguro();

        service.agregarProducto(nombre, idCat, idAlm, stock, minimo);
    }

    private void registrarMovimientoUI() {
        System.out.println("\n--- REGISTRAR MOVIMIENTO ---");
        System.out.print("Ingrese el ID del producto: ");
        int idProd = leerEnteroSeguro();
        
        System.out.print("Tipo de movimiento (ENTRADA / SALIDA): ");
        String tipo = scanner.nextLine().toUpperCase();
        
        System.out.print("Cantidad: ");
        int cantidad = leerEnteroSeguro();
        
        System.out.print("Motivo (ej. Venta, Reposición): ");
        String motivo = scanner.nextLine();

        if (tipo.equals("ENTRADA")) {
            System.out.println("\nProveedores disponibles:");
            service.mostrarProveedores();
            System.out.print("Seleccione el ID del proveedor: ");
            int idProv = leerEnteroSeguro();
            service.registrarEntrada(idProd, cantidad, motivo, usuarioActual, idProv);
        } else if (tipo.equals("SALIDA")) {
            service.registrarSalida(idProd, cantidad, motivo, usuarioActual);
        } else {
            System.out.println("[ERROR] Tipo de movimiento no reconocido.");
        }
    }

    private void buscarProductoUI() {
        System.out.println("\n--- BUSCAR PRODUCTO ---");
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por Nombre");
        System.out.print("Seleccione opción: ");
        int opc = leerEnteroSeguro();

        if (opc == 1) {
            System.out.print("Ingrese ID: ");
            int id = leerEnteroSeguro();
            var p = service.buscarProducto(id);
            if(p != null) System.out.println(p.toString());
            else System.out.println("Producto no encontrado.");
        } else if (opc == 2) {
            System.out.print("Ingrese Nombre Exacto: ");
            String nombre = scanner.nextLine();
            var p = service.buscarProducto(nombre);
            if(p != null) System.out.println(p.toString());
            else System.out.println("Producto no encontrado.");
        } else {
            System.out.println("Opción inválida.");
        }
    }

    private int leerEnteroSeguro() {
        int numero = -1;
        while (true) {
            try {
                numero = Integer.parseInt(scanner.nextLine());
                break; 
            } catch (NumberFormatException e) {
                System.out.print("[ERROR] Por favor ingrese un número válido: ");
            }
        }
        return numero;
    }
}