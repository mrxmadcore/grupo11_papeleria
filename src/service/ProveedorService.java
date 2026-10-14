package service;

import model.domain.Proveedor;
import model.structures.ListaSimple;
import model.structures.Nodo;

public class ProveedorService {
    private ListaSimple<Proveedor> proveedores = new ListaSimple<>();

    public Proveedor crearProveedor(String nombre, String telefono, String categoria) {
        Proveedor proveedor = new Proveedor(nombre, telefono, categoria);
        proveedor.insertarFinal(proveedor);
        return proveedor;
    }

    public Proveedor buscarPorNombre(String nombre) {
        Nodo<Proveedor> proveedorActual = proveedores.getHead();
        while (proveedorActual != null) {
            if (proveedorActual.getDato().getNombre().equals(nombre)) {
                return proveedorActual.getDato();
            }
            proveedorActual = proveedorActual.getSiguiente();
        }
        System.out.println("Proveedor no encontrado");
        return null;
    }

    public Proveedor buscarPorIndice(int indice) {
        Proveedor proveedorEncontrado = proveedores.buscarPorIndice(indice);
        if (proveedorEncontrado != null) {
            return proveedorEncontrado;
        }
        return null;
    }

    public void recorrerLista() {
        Nodo<Proveedor> actual = proveedores.getHead();
        for (int i = 0; i < proveedores.getTamano(); i++) {
            Proveedor proveedorActual = actual.getDato();
            System.out.println("Indice: " + i + " Proveedor: " +
                    proveedorActual.getNombre());
            actual = actual.getSiguiente();
        }
    }

    public void actualizarNombreProveedor(Proveedor proveedor, String nuevoNombre) {
        if (proveedor != null) {
            proveedor.setNombre(nuevoNombre);
            System.out.println("Nombre del Proveedor actualizado exitosamente.");
        } else {
            System.out.println("Proveedor no encontrado.");
        }
    }

    public void actualizarTelefonoProveedor(Proveedor proveedor, String nuevoTelefono) {
        if (proveedor != null) {
            proveedor.setTelefono(nuevoTelefono);
            System.out.println("Teléfono del Proveedor actualizado exitosamente.");
        } else {
            System.out.println("Proveedor no encontrado.");
        }
    }

    public void actualizarCategoriaProveedor(Proveedor proveedor, String nuevaCategoria) {
        if (proveedor != null) {
            proveedor.setCategoriaProductos(nuevaCategoria);
            System.out.println("Categoría actualizada exitosamente.");
        } else {
            System.out.println("Categoría no existente.");
        }
    }

    public boolean eliminarProveedor(Proveedor proveedor) {
        if (proveedor != null) {
            boolean eliminado = proveedores.eliminar(proveedor);
            if (eliminado) {
                System.out.println("Proveedor eliminado exitosamente.");
                return true;
            } else {
                System.out.println("Error al eliminar el Proveedor.");
                return false;
            }
        } else {
            System.out.println("Proveedor no encontrado.");
            return false;
        }
    }

}
