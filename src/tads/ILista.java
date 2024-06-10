package tads;

/**
 *
 * @author Jonattan Lima
 */
public interface ILista <T extends Comparable<T>>{
       
    //Getters
    public NodoLista getInicio();
    public NodoLista getFin();

    //Setters
    public void setFin(NodoLista f);
    public void setInicio(NodoLista i);

    //PRE: Lista vacia o con informacion
    //POS: Retorna true si la lista no tiene nodos
    public boolean esVacia();

    //PRE: Lista vacia o con informacion
    //POS: Agrega un nuevo Nodo al principio de la lista
    public void agregarInicio(T dato);

    //PRE: Lista vacia o con informacion
    //POS: Agrega un nuevo Nodo al final de la lista
    public void agregarFinal(T dato);

    //PRE: Lista vacia o con informacion
    //POS: Borra el primer nodo
    public void borrarInicio();

    //PRE: Lista vacia o con informacion
    //POS: Borra el último nodo
    public void borrarFin();

    //PRE: Lista vacia o con informacion
    //POS: Elimina todos los nodos de una lista dada
    public void vaciar();

    //PRE: Lista vacia o con informacion
    //POS: Recorre y muestra los datos de lista. En caso de ser vacia no muestra nada.
    public String mostrar();

    //PRE: Lista ordenada ascendentemente => mantiena orden
    //POS: Inserta nuevo elemento en orden ascendente
    public void agregarOrd(T dato);
    /* Lista vacía o primer elemento es mayor o igual => agrego al ppio
        último elemento es menor o igual => agrego al final
    */

    //PRE: Lista ordenada ascendentemente
    //POS: Borra la primer ocurrencia de un elemento dado
    public void borrarElemento(T dato);
    
    //PRE: 
    //POS: Retorna la cantidad de nodos que tiene la lista
    public int cantElementos();

    //PRE: Se recibe un dato a buscar en la lista
    /*POS: Retorna un puntero al nodo que contiene el dato. Si el elemento no se encuentra retorna null*/
    public NodoLista obtenerElemento(T dato);

    //PRE: Lista vacia o con datos
    //POS: Muestra los datos de la lista en orden de enlace
    public String mostrarREC(NodoLista nodo);

    //PRE: Lista ordenada o no, con o sin datos
    //POS: Muestra los datos de la lista en orden inverso
    public void mostrarInversoREC(NodoLista nodo);
    
    //PRE: Lista ordenada con o sin datos
    //POS: Retorna true si el elemento pertenece a la lista
    public boolean existeDatoREC(NodoLista nodo, T n);

    //PRE: Lista vacia o con datos
    //POS: Retorna verdadero si el dato pertenece a la lista
    public  boolean pertenece (T dato);
    
    //PRE: Lista vacia o con datos
    //POS: Lista con los datos invertidos en orden de enlace
    public Lista invertir();
    
    //PRE: Lista vacia o con datos
    //POS: Devuelve true si la lista esta ordenada ascendentemente. De lo contrario devuelve false
    public boolean estaOrdenada ();    
    
    //PRE: Lista vacia o con datos
    //POS: Deveulve si un dato pertenece a una lista a partir de un nodo
    public boolean perteneceR(NodoLista nodo,T dato);

    //PRE: Dos listas vacias o con datos
    //POS: Devuelve true si las listas son iguales en todo su contenido y orden
    public boolean esIgual(Lista p);
    
    // PRE: Dos listas ordenadas
    // POS: Devuelve una lista ordenada conteniendo ambas listas 
    public Lista intercalar(Lista p);
}