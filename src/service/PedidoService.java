package service;

import model.domain.Proveedor;
import model.domain.Pedido;
import model.domain.PedidosPendientes;
import model.structures.Cola;
import model.structures.ListaSimple;
import model.structures.Nodo;

public class PedidoService {

    private final PedidosPendientes pedidosPendientes = new PedidosPendientes("Pedidos Pendientes");
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
        if (pedidos.isEmpty()) {
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

    // MÉTODOS DE LA COLA

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

        pedidosPendientes.getFila().enqueue(pedidoEncontrado);
    }

    public boolean sinPedidosEnEspera() {
        return pedidosPendientes.getFila().isEmpty();
    }

    public Pedido atenderSiguiente() {
        return pedidosPendientes.getFila().dequeue();
    }

    public Pedido consultarSiguiente() {
        return pedidosPendientes.getFila().front();
    }

    public int contarEnEspera() {
        return pedidosPendientes.getFila().size();
    }

    public String listarEnEspera() {
        Cola<Pedido> fila = pedidosPendientes.getFila();
        if (fila.isEmpty()) {
            return "No hay pedidos en espera";
        }
        StringBuilder sb = new StringBuilder();
        int total = fila.size();
        for (int i = 1; i <= total; i++) {
            Pedido p = fila.dequeue();
            sb.append(i).append(". Código: ").append(p.getCodigo())
                    .append(" - Estado: ").append(p.getEstado()).append("\n");
            fila.enqueue(p);
        }
        return sb.toString().trim();
    }
}