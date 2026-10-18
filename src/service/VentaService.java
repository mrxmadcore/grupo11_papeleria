package service;

import model.domain.ItemVenta;
import model.domain.Venta;
import model.structures.ListaSimple;
import model.structures.Pila;

public class VentaService {

    private Pila<Venta> historialVentas = new Pila<>();

    public void registrarVenta(Venta nuevaVenta) {
        historialVentas.push(nuevaVenta);
    }

    public Venta anularUltimaVenta() {
        if (historialVentas.isEmpty()) {
            System.out.println("No hay ventas registradas para anular.");
            return null;
        }

        Venta ventaAnulada = historialVentas.pop();

        restaurarStock(ventaAnulada);

        System.out.println("Se ha anulado la última venta y se ha restaurado el stock exitosamente.");
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
            return null;
        }
        return historialVentas.peek();
    }

    public boolean noHayVentas() {
        return historialVentas.isEmpty();
    }

    public int totalVentasRealizadas() {
        return historialVentas.size();
    }
}