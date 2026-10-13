package service;

import model.domain.Proveedor;
import model.domain.Pedido;
import model.structures.ListaSimple;
import model.structures.Nodo;

public class PedidoService {

    public Pedido crearPedidoProveedor(Proveedor proveedor, Pedido nuevoPedido) {
        proveedor.getPedidos().insertarFinal(nuevoPedido);
        return nuevoPedido;
    }

    public Pedido buscarPedidoPorCodigo(Proveedor proveedor, String codigoPedido) {
        Nodo<Pedido> actual = proveedor.getPedidos().getHead();
        while (actual != null) {
            if (actual.getDato().getCodigo().equals(codigoPedido)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        System.out.println("Pedido no encontrado");
        return null;
    }

    public void listarPedidosProveedor(Proveedor proveedor) {
        ListaSimple<Pedido> pedidos = (ListaSimple<Pedido>) proveedor.getPedidos();
        if (pedidos.estaVacia()) {
            System.out.println("El proveedor no tiene pedidos registrados.");
            return;
        }
        
        Nodo<Pedido> actual = pedidos.getHead();
        while (actual != null) {
            Pedido pedidoActual = actual.getDato();
            System.out.println("Pedido: " + pedidoActual.getCodigo() + 
                               " | Estado: " + pedidoActual.getEstado());
            actual = actual.getSiguiente();
        }
    }

    public int contarPedidos(Proveedor proveedor) {
        return proveedor.getPedidos().size();
    }
}