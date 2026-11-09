package view;

import service.CatalogoService;
import utils.ConsoleUtils;
import model.domain.Producto;

public class MenuCatalogoView {
    private final CatalogoService catalogoService;

    public MenuCatalogoView(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = ConsoleUtils.leerEntero("Elija una opción: ");
            switch (opcion) {
                case 1 -> registrarProducto();
                case 2 -> listarAdelante();
                case 3 -> listarAtras();
                case 4 -> navegarAdelante();
                case 5 -> navegarAtras();
                case 6 -> retirarActual();
                case 7 -> {
                    String mensaje = catalogoService.guardarCatalogo();
                    System.out.println(mensaje);
                }
                case 0 -> System.out.println("Saliendo del menú de catálogo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("\n--- MENÚ CATÁLOGO NAVEGABLE (LISTA DOBLE) ---");
        System.out.println("1. Registrar nuevo producto (insertar al final)");
        System.out.println("2. Listar productos en orden (Adelante)");
        System.out.println("3. Listar productos en orden inverso (Atrás)");
        System.out.println("4. Ver producto siguiente (Cursor adelante)");
        System.out.println("5. Ver producto anterior (Cursor atrás)");
        System.out.println("6. Retirar producto actual del catálogo");
        System.out.println("7. Guardar cambios en archivo");
        System.out.println("0. Salir");
    }

    private void registrarProducto() {
        String codigo = ConsoleUtils.leerTexto("Código del producto: ");
        String nombre = ConsoleUtils.leerTexto("Nombre del producto: ");
        double precio = ConsoleUtils.leerDecimal("Precio: ");
        int stock = ConsoleUtils.leerEntero("Cantidad en stock: ");
        String tipo = ConsoleUtils.leerTexto("Tipo (oficina / escolar): ");
        String detalle = ConsoleUtils.leerTexto(tipo.equalsIgnoreCase("oficina") ? "Categoría de oficina: " : "Nivel escolar (ej. primaria): ");

        try {
            catalogoService.registrarProducto(codigo, nombre, precio, stock, tipo, detalle);
            System.out.println("¡Producto registrado y guardado con éxito!");
        } catch (Exception e) {
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }

    private void listarAdelante() {
        System.out.println("\n--- CATÁLOGO (ADELANTE) ---");
        String resultado = catalogoService.listarProductosEnOrden();
        if (resultado.isEmpty()) {
            System.out.println("El catálogo está vacío.");
        } else {
            System.out.println(resultado);
        }
    }

    private void listarAtras() {
        System.out.println("\n--- CATÁLOGO (ATRÁS) ---");
        String resultado = catalogoService.listarProductosEnOrdenInverso();
        if (resultado.isEmpty()) {
            System.out.println("El catálogo está vacío.");
        } else {
            System.out.println(resultado);
        }
    }

    private void navegarAdelante() {
        Producto p = catalogoService.verProductoSiguiente();
        if (p != null) {
            System.out.println("Producto actual: " + p.getNombre() + " ($" + p.getPrecio() + ")");
        } else {
            System.out.println("No hay más productos adelante o la lista está vacía.");
        }
    }

    private void navegarAtras() {
        Producto p = catalogoService.verProductoAnterior();
        if (p != null) {
            System.out.println("Producto actual: " + p.getNombre() + " ($" + p.getPrecio() + ")");
        } else {
            System.out.println("No hay productos atrás o se encuentra al inicio.");
        }
    }

    private void retirarActual() {
        Producto retirado = catalogoService.retirarProducto();
        if (retirado != null) {
            System.out.println("Producto retirado del catálogo: " + retirado.getNombre());
        } else {
            System.out.println("No hay ningún producto seleccionado para retirar.");
        }
    }
}