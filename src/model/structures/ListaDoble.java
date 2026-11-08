package model.structures;


public class ListaDoble<T> {
    private NodoDoble<T> head;
    private NodoDoble<T> tail;
    private NodoDoble<T> actual;
    private int tamano;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return tamano;
    }

    public void add(T dato) {
        NodoDoble<T> nuevoNodo = new NodoDoble<>(dato);
        if (isEmpty()) {
            head = nuevoNodo;
            tail = nuevoNodo;
            actual = nuevoNodo;
        } else {
            tail.setSiguiente(nuevoNodo);
            nuevoNodo.setAnterior(tail);
            tail = nuevoNodo;
        }
        tamano++;
    }

}
