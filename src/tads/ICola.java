package tads;

/**
 *
 * @author jlima
 */
public interface ICola<T extends Comparable<T>> {

    //Inserta el elemento al final de la cola 
    public void encolar(T dato);
    
    //Elimina el primer elemento (frente) de la cola 
    public void desencolar();

    //Indica si la cola esta vacía 
    public boolean esVacia();
    
    //Indica si la cola esta llena 
    public boolean esllena();
    
    //Retorna la cantidad de elementos 
    public int cantElementos();
    
    //Retorna el primer elemento de la cola (al frente) 
    public NodoLista<T> frente();
    
    //Retorna el dato del primer elemento de la cola (al frente) 
    public T datoFrente();
    
    
    //Muestra los elementos presentes en la cola en forma de string
    public String mostrar();
}
