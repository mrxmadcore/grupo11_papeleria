package model.structures;

public class Cola<T> {
    private Nodo<T> front;
    private Nodo<T> fin;
    private int tamano;

    public Cola() {
        this.front = null;
        this.fin = null;
        this.tamano = 0;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return tamano;
    }

    public void enqueue(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException("No se pueden encolar elementos nulos.");
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        if (isEmpty()) {
            front = nuevo;
        } else {
            fin.setSiguiente(nuevo);
        }
        fin = nuevo;
        tamano++;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T dato = front.getDato();
        front = front.getSiguiente();
        if (front == null) {
            fin = null;
        }
        tamano--;
        return dato;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return front.getDato();
    }
}
