package service;

import model.domain.Proveedor;
import model.domain.Pedido;
import model.domain.PedidosPendientes;
import model.structures.ListaSimple;
import model.structures.Nodo;

public class PedidoService {

    private final PedidosPendientes pedidosPendientes = new PedidosPendientes();
    private final ListaSimple<Proveedor> listaProveedores = new ListaSimple<>();

    public Pedido crearPedidoProveedor(Proveedor proveedor, Pedido nuevoPedido) {
        if (!contieneProveedor(proveedor)) {
            listaProveedores.insertarFinal(proveedor);
        }
        proveedor.getPedidos().insertarFinal(nuevoPedido);
        return nuevoPedido;
    }

    private boolean contieneProveedor(Proveedor proveedorBuscado) {
        Nodo<Proveedor> actual = listaProveedores.getHead();
        while (actual != null) {
            if (actual.getDato() == proveedorBuscado) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public Pedido buscarPedidoPorCodigo(Proveedor proveedor, String codigoPedido) {
        Nodo<Pedido> actual = proveedor.getPedidos().getHead();
        while (actual != null) {
            if (actual.getDato().getCodigo().equals(codigoPedido)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
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

    // --- MÉTODOS DE LA COLA ---

    public void registrarLlegadaPedido(String codigo) {
        Pedido pedidoEncontrado = null;

        Nodo<Proveedor> nodoProv = listaProveedores.getHead();
        while (nodoProv != null) {
            Proveedor prov = nodoProv.getDato();
            pedidoEncontrado = buscarPedidoPorCodigo(prov, codigo);
            if (pedidoEncontrado != null) {
                break;
            }
            nodoProv = nodoProv.getSiguiente();
        }

        if (pedidoEncontrado == null) {
            throw new IllegalArgumentException("No se encontró ningún pedido con el código: " + codigo);
        }

        pedidosPendientes.registrarPedido(pedidoEncontrado);
    }

    public boolean sinPedidosEnEspera() {
        return !pedidosPendientes.hayPedidosPendientes();
    }

    public Pedido atenderSiguiente() {
        return pedidosPendientes.procesarSiguientePedido();
    }

    public Pedido consultarSiguiente() {
        return pedidosPendientes.verSiguientePedido();
    }

    public int contarEnEspera() {
        return pedidosPendientes.totalPedidosPendientes();
    }
}