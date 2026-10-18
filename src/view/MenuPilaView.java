package view;

import model.domain.Venta;
import service.VentaService;
import utils.ConsoleUtils;

public class MenuPilaView {
    private final VentaService ventaService;

    public MenuPilaView(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = ConsoleUtils.leerEntero("Elija una opción: ");
            switch (opcion) {
                case 1 -> registrar();
                case 2 -> deshacer();
                case 3 -> consultarUltima();
                case 4 -> verSiHayVentas();
                case 5 -> contar();
                case 6 -> listar();
                case 0 -> System.out.println("Saliendo del menú de ventas...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("\n--- MENÚ GESTIÓN DE VENTAS (HISTORIAL) ---");
        System.out.println("1. Registrar nueva venta (push)");
        System.out.println("2. Anular última venta - Restaurar stock (pop)");
        System.out.println("3. Ver última venta realizada (peek)");
        System.out.println("4. ¿Hay ventas registradas? (isEmpty)");
        System.out.println("5. Cantidad de ventas en el historial (size)");
        System.out.println("6. Listar ventas");
        System.out.println("0. Salir");
    }

    private void registrar() {
        System.out.println("Registrando nueva venta...");
        Venta nuevaVenta = new Venta();

        ventaService.registrarVenta(nuevaVenta);
        System.out.println("¡Venta registrada y apilada con éxito!");
    }

    private void deshacer() {
        if (ventaService.noHayVentas()) {
            System.out.println("No hay ventas registradas para anular.");
            return;
        }

        Venta ventaAnulada = ventaService.anularUltimaVenta();
        System.out.println("Se anuló la venta: " + ventaAnulada + " y se restauró el stock.");
    }

    private void consultarUltima() {
        if (ventaService.noHayVentas()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        Venta ultima = ventaService.consultarUltimaVenta();
        System.out.println("Última venta consultada: " + ultima);
    }

    private void verSiHayVentas() {
        if (ventaService.noHayVentas()) {
            System.out.println("No hay ventas registradas.");
        } else {
            System.out.println("Sí hay ventas en el historial.");
        }
    }

    private void contar() {
        int total = ventaService.totalVentasRealizadas();
        System.out.println("Cantidad total de ventas en el historial: " + total);
    }

    private void listar() {
        System.out.println("Función de listar dispuesta para más adelante en el proyecto.");
    }
}