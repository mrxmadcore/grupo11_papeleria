package model.domain;

import model.structures.ListaDoble;

public class CatalogoNavegable {
    private String nombre;
    private ListaDoble<Producto> productos;

    public CatalogoNavegable(String nombre) {
        this.nombre = nombre;
        this.productos = new ListaDoble<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaDoble<Producto> getProductos() {
        return productos;
    }
}