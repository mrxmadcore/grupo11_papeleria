package model.domain;

import model.structures.Cola;

public class PedidosPendientes {
    private String descripcion;
    private Cola<Pedido> fila;

    public PedidosPendientes(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede ser nula ni vacía.");
        }
        this.descripcion = descripcion;
        this.fila = new Cola<>();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cola<Pedido> getFila() {
        return fila;
    }
}