package tads;

import java.util.Iterator;


/**
 *
 * @author Jonattan Lima
 */
public class Lista <T extends Comparable<T>> implements ILista<T>{

    private NodoLista inicio;
    private NodoLista fin;
    private int cantidad;
    private int tope; // si Tope = -1 es sin tope;

    @Override
    public void setInicio(NodoLista nodoInicio) {
        this.inicio = nodoInicio;
    }
    
    @Override
    public void setFin(NodoLista nodoFin){
        this.fin = nodoFin;
    }

    @Override
    public NodoLista getInicio() {
        return this.inicio;
    }

    @Override
    public NodoLista getFin() {
        return this.fin;
    }
    
    public Lista() {
        this.inicio = null;
        this.fin = null;
        this.cantidad = 0;
        this.tope = Integer.MAX_VALUE;
    }

    public Lista( int tope) {
        this.inicio = null;
        this.fin = null;
        this.cantidad = 0;
        this.tope = tope;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTope() {
        return tope;
    }

    public void setTope(int tope) {
        this.tope = tope;
    }

    @Override
    public boolean esVacia() {
        return (this.getInicio() == null);
    }

    @Override
    public void agregarInicio(T dato) {
        if (this.getCantidad()>this.getTope() && this.getTope()!=-1){
            return;
        }
        NodoLista nuevoNodo = new NodoLista(dato);
        if (this.esVacia()){
            this.setInicio(nuevoNodo);
            this.setFin(nuevoNodo);
        }
        else {
            nuevoNodo.setSig(this.getInicio());
            this.setInicio(nuevoNodo);
        }
        this.setCantidad(this.getCantidad()+1);
    }

    @Override
    public void mostrar() {
        if (this.esVacia()){
            System.out.println("La lista está vacia");
            return;
        }
        NodoLista aux = this.getInicio();
        while (aux!=null) {
            System.out.println(aux.getDato().toString()+"-");
            aux = aux.getSig();
        }
        System.out.println();
    }

    @Override
    public void agregarFinal(T dato) {
        if (this.getCantidad()>this.getTope() && this.getTope()!=-1){
            return;
        }
        NodoLista nuevoNodo = new NodoLista(dato);
        
        if (this.esVacia()){
            this.agregarInicio(dato);
        }
        else {
            this.getFin().setSig(nuevoNodo);
            this.setFin(nuevoNodo);
            this.setCantidad(this.getCantidad()+1);
        }
    }

    @Override
    public void borrarInicio() {
        if (this.esVacia()){
            return;
        }
        if ( this.getInicio()==this.getFin()){
            this.vaciar();            
        } else {
            this.setInicio(this.getInicio().getSig());
            this.setCantidad(this.getCantidad()-1);
        }
    }

    @Override
    public void borrarFin() {
        if (this.esVacia()){
            return;
        }
        if (this.getInicio()==this.getFin()){
            this.vaciar();
        } else {
            NodoLista aux = this.getInicio();
            while ( aux.getSig()!= this.getFin()){
                aux = aux.getSig();
            }
            aux.setSig(null);
            this.setFin(aux);
            this.setCantidad(this.getCantidad()-1);
        }
    }

    @Override
    public void vaciar() {
        this.setInicio(null);
        this.setFin(null);
        this.setCantidad(0);
    }

    @Override
    @SuppressWarnings("null")
    public void agregarOrd(T dato) {
        if (this.getCantidad()>this.getTope() && this.getTope()!=-1){
            return;
        }
        if ( this.esVacia()){
           this.agregarInicio(dato);
           return;
        }
        if ( this.getInicio().getDato().compareTo(dato) >= 0){
            this.agregarInicio(dato);
            return;
        }
        if ( this.getFin().getDato().compareTo(dato) <= 0){
            this.agregarFinal(dato);
            return;
        }
        
        NodoLista aux = this.getInicio();
        
        while (aux != null && aux.getSig() != null && aux.getSig().getDato().compareTo(dato) < 0){
            aux = aux.getSig();
        }
        
        NodoLista nuevoNodo = new NodoLista(dato);
        
        nuevoNodo.setSig(aux.getSig());
        aux.setSig(nuevoNodo);
        
        this.setCantidad(this.getCantidad()+1);
    }

    @Override
    public void borrarElemento(T dato) {
        if (this.esVacia()){
            return;
        }
        if (this.getInicio().getDato().equals(dato)){
            this.borrarInicio();
            return;
        }
        NodoLista aux = this.getInicio();
        while ( aux.getSig()!=null && !aux.getSig().getDato().equals(dato) ){
            aux = aux.getSig();
        }
        if ( aux.getSig()!=null){
            if ( aux.getSig().getSig() ==null){
                this.setFin(aux);
            }
            aux.setSig(aux.getSig().getSig());
            this.setCantidad(this.getCantidad()-1);
        }
    }

    @Override
    public int cantElementos() {
        return this.getCantidad();
    }

    @Override
    public NodoLista obtenerElemento(T dato) {
        if ( this.esVacia()){
            return null;
        }
        NodoLista aux = this.getInicio();
        while ( aux!=null && !aux.getDato().equals(dato)){
            aux = aux.getSig();
        }
        if ( aux !=null){
            return aux;
        }
        return null;
    }

    @Override
    public void mostrarREC(NodoLista nodo) {
        if (nodo == null){
            return;
        }
        System.out.println(nodo.getDato().toString());
        mostrarREC(nodo.getSig());
    }

    @Override
    public void mostrarInversoREC(NodoLista nodo) {
        if (nodo == null){
            return;
        }
        mostrarREC(nodo.getSig());
        System.out.println(nodo.getDato().toString());
    }

    @Override
    public boolean existeDatoREC(NodoLista nodo, T dato) {
           if ( nodo==null){
               return false;
           }
           if (nodo.getDato().equals(dato)){
               return true;
           }
           return existeDatoREC(nodo.getSig(),dato);
    }

    @Override
    public boolean pertenece(T datoBuscado) {
        NodoLista aux = this.getInicio();
        
        while (aux!=null && !aux.getDato().equals(datoBuscado)){
                aux = aux.getSig();
        }
        
        return aux != null;
    }

    @Override
    public boolean perteneceR(NodoLista nodo, T datoBuscado) {
        if (nodo == null){
            return false;
        }
        if ( nodo.getDato().compareTo(datoBuscado)==0){
            return true;
        }
        return perteneceR(nodo.getSig(),datoBuscado);
    }
    
    @Override
    public Lista invertir() {
        Lista nueva = new Lista();
        NodoLista aux = this.getInicio();
        while ( aux!=null){
            nueva.agregarInicio(aux.getDato());
            aux = aux.getSig();
        }
        return nueva;
    }
    
    @Override
    public boolean estaOrdenada (){
        if (this.esVacia()){
            return true;
        }
        if (this.getInicio()==this.getFin()){
            return true;
        }
        NodoLista aux = this.getInicio();
        while (aux.getSig()!=null && aux.getDato().compareTo(aux.getSig().getDato())<=0){
            aux = aux.getSig();
        }
        
        return aux.getSig() == null;
    }   

    public void insertarOrdenado (T dato){  
        this.agregarOrd(dato);
    }

    @Override
    public boolean esIgual(Lista otraLista) {
        if ( this.getCantidad()!=otraLista.getCantidad()){
            return false;
        }
        NodoLista aux = this.getInicio();
        NodoLista auxP = otraLista.getInicio();
        while ( aux!=null && auxP!=null && aux.getDato().equals(auxP.getDato())){
            aux = aux.getSig();
            auxP = auxP.getSig();
        }
        
        return aux==null && auxP==null;        
    }

    public boolean esIgualREC(Lista otraLista) {
        if(this.getCantidad()!= otraLista.getCantidad()){
            return false;
        }
        if (this.esVacia() && otraLista.esVacia()){
            return true;
        }
        return esIgualREC(this.getInicio(),otraLista.getInicio());
        
    }
    
    @SuppressWarnings("null")
    public boolean esIgualREC( NodoLista nodo, NodoLista nodoP){
        if (nodo==null && nodoP==null){
            return true;
        }
        else if (!nodo.getDato().equals(nodoP.getDato())){
            return false;
        }
        return esIgualREC(nodo.getSig(),nodoP.getSig());
    }
    
    @Override
    public Lista intercalar(Lista p) {
        Lista nueva = new  Lista();
        
        NodoLista aux = this.getInicio();
        NodoLista auxP = p.getInicio();
        
        while (aux!=null && auxP!=null ){
            if ( aux.getDato().compareTo(auxP.getDato())>=0){
                nueva.agregarFinal(auxP.getDato());
                auxP = auxP.getSig();
            } else {
                nueva.agregarFinal(aux.getDato());
                aux = aux.getSig();
                
            }
        }
        while (aux!=null){
                nueva.agregarFinal(aux.getDato());
                aux = aux.getSig();            
        }
        
        while (auxP!=null){
            nueva.agregarFinal(auxP.getDato());
            auxP = auxP.getSig();            
        }
        return nueva;
    }

 }