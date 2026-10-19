package view;

import model.domain.Pedido;
import service.PedidoService;
import utils.ConsoleUtils;

public class MenuColaView {
    private final PedidoService pedidoService;

    public MenuColaView(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = ConsoleUtils.leerEntero("Elija una opción: ");
            switch (opcion) {
                case 1 -> registrarLlegada();
                case 2 -> atender();
                case 3 -> consultarSiguiente();
                case 4 -> verSiHayEnEspera();
                case 5 -> contar();
                case 6 -> listar();
                case 0 -> System.out.println("Saliendo del menú de colas...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("\n--- MENÚ COLA DE PEDIDOS PENDIENTES ---");
        System.out.println("1. Registrar llegada (enqueue)");
        System.out.println("2. Atender al siguiente (dequeue)");
        System.out.println("3. Ver quién sigue (front)");
        System.out.println("4. ¿Hay pedidos en espera? (isEmpty)");
        System.out.println("5. Cantidad en espera (size)");
        System.out.println("6. Listar la fila (Parte 7)");
        System.out.println("0. Salir");
    }

    private void registrarLlegada() {
        String codigo = ConsoleUtils.leerTexto("Código del pedido: ");

        try {
            pedidoService.registrarLlegadaPedido(codigo);
            System.out.println("¡Pedido registrado en la cola con éxito!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void atender() {
        if (pedidoService.sinPedidosEnEspera()) {
            System.out.println("No hay pedidos en espera");
            return;
        }
        Pedido pedidoAtendido = pedidoService.atenderSiguiente();
        System.out.println("Pedido atendido: " + pedidoAtendido);
    }

    private void consultarSiguiente() {
        if (pedidoService.sinPedidosEnEspera()) {
            System.out.println("No hay pedidos en espera");
            return;
        }
        Pedido siguiente = pedidoService.consultarSiguiente();
        System.out.println("Siguiente pedido en la fila: " + siguiente);
    }

    private void verSiHayEnEspera() {
        if (pedidoService.sinPedidosEnEspera()) {
            System.out.println("No hay pedidos en espera");
        } else {
            System.out.println("Hay pedidos en espera");
        }
    }

    private void contar() {
        int total = pedidoService.contarEnEspera();
        System.out.println("Cantidad de pedidos en espera: " + total);
    }

    private void listar() {
        System.out.println("Función de listar la fila dispuesta para la Parte 7.");
    }
}