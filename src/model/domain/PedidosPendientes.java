package model.domain;

import model.structures.Cola;

public class PedidosPendientes {
    private Cola<Pedido> colaPedidos;

    public PedidosPendientes() {
        this.colaPedidos = new Cola<>();
    }

    public void registrarPedido(Pedido pedido) {
        colaPedidos.enqueue(pedido);
        System.out.println("Pedido registrado en la cola correctamente.");
    }

    public Pedido procesarSiguientePedido() {
        if (colaPedidos.estaVacia()) {
            System.out.println("No hay pedidos pendientes en la cola.");
            return null;
        }
        Pedido pedidoProcesado = colaPedidos.dequeue();
        System.out.println("Procesando el pedido más antiguo con código/fecha para el proveedor: " 
                           + pedidoProcesado.getProveedor().getNombre());
        return pedidoProcesado;
    }

    public Pedido verSiguientePedido() {
        return colaPedidos.peek();
    }

    public boolean hayPedidosPendientes() {
        return !colaPedidos.estaVacia();
    }

    public int totalPedidosPendientes() {
        return colaPedidos.size();
    }
}