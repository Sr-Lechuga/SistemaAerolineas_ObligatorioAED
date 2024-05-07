package tads;

/**
 *
 * @author Jonattan Lima
 */
public class NodoLista<T extends Comparable<T>>  {
    
    private T dato;
    private NodoLista<T> sig;

    public NodoLista(T dato) {
        this.dato = dato;
        this.sig = null;
    }

    
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoLista<T> getSig() {
        return sig;
    }

    public void setSig(NodoLista<T> sig) {
        this.sig = sig;
    }   
}