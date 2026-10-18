package model.structures;

public class Pila<T> {
    private Nodo<T> cima;
    private int tamano;

    public Pila() {
        this.cima = null;
        this.tamano = 0;
    }

    public void push(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException("No se puede apilar un elemento nulo.");
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        tamano++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía, no se puede hacer pop.");
        }
        T dato = cima.getDato();
        cima = cima.getSiguiente();
        tamano--;
        return dato;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía, no se puede hacer peek.");
        }
        return cima.getDato();
    }

    public boolean isEmpty() {
        return cima == null;
    }

    public int size() {
        return tamano;
    }
}