package model.structures;

public class ListaDoble<T> {
    private NodoDoble<T> head;
    private NodoDoble<T> tail;
    private NodoDoble<T> actual;
    private int tamano;

    public ListaDoble() {
        this.head = null;
        this.tail = null;
        this.actual = null;
        this.tamano = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return tamano;
    }

    public NodoDoble<T> getHead() {
        return head;
    }

    public void insertarAlInicio(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se pueden insertar elementos nulos.");
        }
        NodoDoble<T> nuevo = new NodoDoble<>(elemento);
        if (isEmpty()) {
            head = tail = actual = nuevo;
        } else {
            nuevo.setSiguiente(head);
            head.setAnterior(nuevo);
            head = nuevo;
        }
        tamano++;
    }

    public void insertarAlFinal(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se pueden insertar elementos nulos.");
        }
        NodoDoble<T> nuevo = new NodoDoble<>(elemento);
        if (isEmpty()) {
            head = tail = actual = nuevo;
        } else {
            tail.setSiguiente(nuevo);
            nuevo.setAnterior(tail);
            tail = nuevo;
        }
        tamano++;
    }

    public NodoDoble<T> buscar(T dato) {
        NodoDoble<T> temp = head;
        while (temp != null) {
            if (temp.getDato().equals(dato)) {
                return temp;
            }
            temp = temp.getSiguiente();
        }
        return null;
    }

    public T eliminar(NodoDoble<T> nodo) {
        if (nodo == null || isEmpty()) return null;

        T datoEliminado = nodo.getDato();

        if (head == tail) { 
            head = tail = actual = null;
        } else if (nodo == head) { 
            head = head.getSiguiente();
            head.setAnterior(null);
            if (actual == nodo) actual = head;
        } else if (nodo == tail) { 
            tail = tail.getAnterior();
            tail.setSiguiente(null);
            if (actual == nodo) actual = tail;
        } else { 
            NodoDoble<T> ant = nodo.getAnterior();
            NodoDoble<T> sig = nodo.getSiguiente();
            ant.setSiguiente(sig);
            sig.setAnterior(ant);
            if (actual == nodo) actual = sig;
        }

        tamano--;
        return datoEliminado;
    }

    public T irAlInicio() {
        if (isEmpty()) return null;
        actual = head;
        return actual.getDato();
    }

    public T irAlFinal() {
        if (isEmpty()) return null;
        actual = tail;
        return actual.getDato();
    }

    public T siguiente() {
        if (actual != null && actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
            return actual.getDato();
        }
        return null; 
    }

    public T anterior() {
        if (actual != null && actual.getAnterior() != null) {
            actual = actual.getAnterior();
            return actual.getDato();
        }
        return null;
    }

    public T retirarActual() {
        if (actual == null) return null;
        NodoDoble<T> aRetirar = actual;
        if (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        } else {
            actual = actual.getAnterior();
        }
        return eliminar(aRetirar);
    }

    public String recorrerAdelante() {
        StringBuilder sb = new StringBuilder();
        NodoDoble<T> temp = head;
        while (temp != null) {
            sb.append(temp.getDato().toString()).append("\n");
            temp = temp.getSiguiente();
        }
        return sb.toString().trim();
    }

    public String recorrerAtras() {
        StringBuilder sb = new StringBuilder();
        NodoDoble<T> temp = tail;
        while (temp != null) {
            sb.append(temp.getDato().toString()).append("\n");
            temp = temp.getAnterior();
        }
        return sb.toString().trim();
    }
}
