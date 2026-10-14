package model.structures;

public class Pila<T> {
    private Nodo<T> cima;
    private int tamano;

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public Pila() {
        this.cima = null;
        this.tamano = 0;
    }

    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
        tamano++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T dato = cima.dato;
        cima = cima.siguiente;
        tamano--;
        return dato;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return cima.dato;
    }

    public boolean isEmpty() {
        return cima == null;
    }

    public int size() {
        return tamano;
    }
}