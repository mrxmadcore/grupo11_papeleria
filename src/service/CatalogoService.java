package service;

import model.domain.CatalogoNavegable;
import model.domain.Producto;
import model.domain.ProductoOficina;
import model.domain.ProductoEscolar;
import model.structures.NodoDoble;

import java.io.*;

public class CatalogoService {
    private CatalogoNavegable catalogo;
    private static final String RUTA_ARCHIVO = "data/catalogo.txt";

    public CatalogoService() {
        this.catalogo = new CatalogoNavegable("Catálogo General de Papelería");
        cargarCatalogo(); 
    }

    public void registrarProducto(String codigo, String nombre, double precio, int stock, String tipo, String detalle) {
        Producto producto;
        if (tipo.equalsIgnoreCase("oficina")) {
            producto = new ProductoOficina(codigo, nombre, precio, stock, detalle);
        } else {
            producto = new ProductoEscolar(codigo, nombre, precio, stock, detalle);
        }
        catalogo.getProductos().insertarAlFinal(producto);
        guardarCatalogo();
    }

    public String listarProductosEnOrden() {
        return catalogo.getProductos().recorrerAdelante();
    }

    public String listarProductosEnOrdenInverso() {
        return catalogo.getProductos().recorrerAtras();
    }

    public Producto irAlInicio() {
        return catalogo.getProductos().irAlInicio();
    }

    public Producto irAlFinal() {
        return catalogo.getProductos().irAlFinal();
    }

    public Producto verProductoSiguiente() {
        return catalogo.getProductos().siguiente();
    }

    public Producto verProductoAnterior() {
        return catalogo.getProductos().anterior();
    }

    public Producto retirarProducto() {
        Producto retirado = catalogo.getProductos().retirarActual();
        guardarCatalogo();
        return retirado;
    }

    public int contarProductos() {
        return catalogo.getProductos().size();
    }

    public String guardarCatalogo() {
        File directorio = new File("data");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            NodoDoble<Producto> actual = catalogo.getProductos().getHead();
            while (actual != null) {
                Producto p = actual.getDato();
                String tipo = (p instanceof ProductoOficina) ? "oficina" : "escolar";
                String extra = (p instanceof ProductoOficina) ? ((ProductoOficina) p).getCategoria() : ((ProductoEscolar) p).getNivelEscolar();
                
                String linea = p.getCodigo() + ";" + p.getNombre() + ";" + p.getPrecio() + ";" + p.getCantidadStock() + ";" + tipo + ";" + extra;
                writer.write(linea);
                writer.newLine();
                actual = actual.getSiguiente();
            }
            return "Catálogo guardado correctamente en disco.";
        } catch (IOException e) {
            return "Error al guardar el catálogo: " + e.getMessage();
        }
    }

    public void cargarCatalogo() {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            return; 
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] campos = linea.split(";");
                
                if (campos.length < 6) {
                    continue; 
                }

                try {
                    String codigo = campos[0];
                    String nombre = campos[1];
                    double precio = Double.parseDouble(campos[2]);
                    int stock = Integer.parseInt(campos[3]);
                    String tipo = campos[4];
                    String detalle = campos[5];

                    Producto producto;
                    if (tipo.equalsIgnoreCase("oficina")) {
                        producto = new ProductoOficina(codigo, nombre, precio, stock, detalle);
                    } else {
                        producto = new ProductoEscolar(codigo, nombre, precio, stock, detalle);
                    }
                    catalogo.getProductos().insertarAlFinal(producto);
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (IOException e) {
            System.err.println("Aviso: No se pudo leer el archivo de catálogo.");
        }
    }
}
