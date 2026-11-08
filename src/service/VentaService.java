package service;

import model.domain.ItemVenta;
import model.domain.Venta;
import model.structures.ListaSimple;
import model.structures.Pila;

public class VentaService {

    private Pila<Venta> historialVentas = new Pila<>();

    public void registrarVenta(Venta nuevaVenta) {
        if (nuevaVenta != null) {
            historialVentas.push(nuevaVenta);
        }
    }

    public Venta anularUltimaVenta() {
        Venta ventaAnulada = historialVentas.pop();
        restaurarStock(ventaAnulada);
        return ventaAnulada;
    }

    private void restaurarStock(Venta venta) {
        if (venta == null || venta.getItems() == null) {
            return;
        }

        ListaSimple<ItemVenta> items = venta.getItems();

        for (int i = 0; i < items.size(); i++) {
            ItemVenta item = items.buscarPorIndice(i);
            if (item != null && item.getProducto() != null) {
                var producto = item.getProducto();
                int cantidadVendida = item.getCantidad();

                producto.setCantidadStock(producto.getCantidadStock() + cantidadVendida);
            }
        }
    }

    public Venta consultarUltimaVenta() {
        if (historialVentas.isEmpty()) {
            throw new IllegalStateException("No hay ventas registradas.");
        }
        return historialVentas.peek();
    }

    public boolean noHayVentas() {
        return historialVentas.isEmpty();
    }

    public int totalVentasRealizadas() {
        return historialVentas.size();
    }

    public String listarOperaciones() {
        if (historialVentas.isEmpty()) {
            return "No hay ventas registradas para listar.";
        }

        Pila<Venta> auxiliar = new Pila<>();
        StringBuilder sb = new StringBuilder();
        int contador = 1;

        // Retirar de la original, agregar al texto y pasar a la auxiliar (queda en
        // orden inverso)
        while (!historialVentas.isEmpty()) {
            Venta venta = historialVentas.pop();
            sb.append(contador++).append(". ").append(venta.toString()).append("\n");
            auxiliar.push(venta);
        }

        // Devolver los elementos de la auxiliar a la original para restaurarla intacta
        while (!auxiliar.isEmpty()) {
            historialVentas.push(auxiliar.pop());
        }

        return sb.toString().trim();
    }
}