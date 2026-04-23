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
        
        // Simulación de Login para definir el Rol
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        
        String rol = "";
        while (!rol.equals("ADMIN") && !rol.equals("OPERADOR")) {
            System.out.print("Ingrese su rol (ADMIN / OPERADOR): ");
            rol = scanner.nextLine().toUpperCase();
        }

        usuarioActual = new Usuario(nombre, rol);
        System.out.println("\n[BIENVENIDO] " + usuarioActual.toString());
        
        // Mostrar alertas al iniciar sesión
        service.mostrarAlertas();

        mostrarMenuPrincipal();
    }

    private void mostrarMenuPrincipal() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Ver todos los productos");
            System.out.println("2. Registrar movimiento (Entrada/Salida)");
            System.out.println("3. Agregar nuevo producto");
            if (usuarioActual.esAdmin()) {
                System.out.println("4. Desactivar producto");
                System.out.println("5. Revertir último movimiento");
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
                    if (usuarioActual.esAdmin()) desactivarProductoUI();
                    else System.out.println("[ERROR] Opción no válida.");
                    break;
                case 5:
                    if (usuarioActual.esAdmin()) service.revertirUltimoMovimiento(usuarioActual);
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

    // Métodos para ingreso de datos.

    private void agregarProductoUI() {
        System.out.println("\n--- AGREGAR PRODUCTO ---");
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Categoría (HERRAMIENTAS, ELECTRICIDAD, PINTURA, FONTANERIA, CONSTRUCCION): ");
        String categoria = scanner.nextLine().toUpperCase();
        
        System.out.print("Stock inicial: ");
        int stock = leerEnteroSeguro();
        
        System.out.print("Stock mínimo (para alertas): ");
        int minimo = leerEnteroSeguro();

        service.agregarProducto(nombre, categoria, stock, minimo);
    }

    private void registrarMovimientoUI() {
        System.out.println("\n--- REGISTRAR MOVIMIENTO ---");
        System.out.print("Ingrese el ID del producto: ");
        int id = leerEnteroSeguro();
        
        System.out.print("Tipo de movimiento (ENTRADA / SALIDA): ");
        String tipo = scanner.nextLine().toUpperCase();
        
        System.out.print("Cantidad: ");
        int cantidad = leerEnteroSeguro();
        
        System.out.print("Motivo (ej. Venta, Reposición): ");
        String motivo = scanner.nextLine();

        service.registrarMovimiento(id, tipo, cantidad, motivo, usuarioActual);
    }

    private void desactivarProductoUI() {
        System.out.print("\nIngrese el ID del producto a desactivar: ");
        int id = leerEnteroSeguro();
        service.desactivarProducto(id);
    }

    // Validación de ingreso de datos.
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
