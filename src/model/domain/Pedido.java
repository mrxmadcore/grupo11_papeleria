package model.domain;

import java.time.LocalDate;

public class Pedido {
    private String codigo;
    private LocalDate fecha;
    private String estado;
    private Proveedor proveedor;

    public Pedido(LocalDate fecha, String estado, Proveedor proveedor) {
        this("PED-" + System.currentTimeMillis(), fecha, estado, proveedor);
    }

    public Pedido(String codigo, LocalDate fecha, String estado, Proveedor proveedor) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede estar vacío");
        }
        if (proveedor == null) {
            throw new IllegalArgumentException("El proveedor no puede ser nulo");
        }

        this.codigo = codigo;
        this.fecha = fecha;
        this.estado = estado;
        this.proveedor = proveedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}