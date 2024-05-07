package tads;

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
    public void setInicio(NodoLista i) {
        this.inicio = i;
    }

    public Lista() {
        this.inicio = null;
        this.fin = null;
        this.cantidad = 0;
        this.tope = -1;
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
    public NodoLista getInicio() {
           return this.inicio;
    }

    @Override
    public void setFin(NodoLista f) {
        this.fin = f;
    }

    @Override
    public NodoLista getFin() {
        return this.fin;
    }

    @Override
    public boolean esVacia() {
        return (this.getInicio()==null);
    }

    @Override
    public void agregarInicio(NodoLista nodo) {
        if (this.getCantidad()>this.getTope() && this.getTope()!=-1){
            return;
        }
        if (this.esVacia()){
            this.setInicio(nodo);
            this.setFin(nodo);
        }
        else {
            nodo.setSig(this.getInicio());
            this.setInicio(nodo);
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
    public void agregarFinal(NodoLista nodo) {
        if (this.getCantidad()>this.getTope() && this.getTope()!=-1){
            return;
        }
        if (this.esVacia()){
            this.agregarInicio(nodo);
        }
        else {
            this.getFin().setSig(nodo);
            this.setFin(nodo);
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
    public void agregarOrd(NodoLista nodo) {
        if (this.getCantidad()>this.getTope() && this.getTope()!=-1){
            return;
        }
        if ( this.esVacia()){
           this.agregarInicio(nodo);
           return;
        }
        if ( this.getInicio().getDato().compareTo(nodo.getDato())>=0){
            this.agregarInicio(nodo);
            return;
        }
        if ( this.getFin().getDato().compareTo(nodo.getDato())<=0){
            this.agregarFinal(nodo);
            return;
        }
        NodoLista aux = this.getInicio();
        while (aux != null && aux.getSig() != null && aux.getSig().getDato().compareTo(nodo.getDato())<0){
            aux = aux.getSig();
        }
        nodo.setSig(aux.getSig());
        aux.setSig(nodo);        
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
        if (nodo==null){
            return;
        }
        System.out.println(nodo.getDato().toString());
        mostrarREC(nodo.getSig());
    }

    @Override
    public void mostrarInversoREC(NodoLista nodo) {
        if (nodo==null){
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
    public boolean pertenece(T x) {
        NodoLista aux = this.getInicio();
        while (aux!=null && !aux.getDato().equals(x)){
                aux = aux.getSig();
        }        
        if (aux==null){
            return false;
        } else {
            return true;
        }
    }


    @Override
    public boolean perteneceR(NodoLista nodo, T x) {
        if (nodo==null){
            return false;
        }
        if ( nodo.getDato().compareTo(x)==0){
            return true;
        }
        return perteneceR(nodo.getSig(),x);
    }
    

    @Override
    public Lista invertir() {
        Lista nueva = new Lista();
        NodoLista aux = this.getInicio();
        while ( aux!=null){
            NodoLista nodoNuevo = new NodoLista(aux.getDato());
            nueva.agregarInicio(nodoNuevo);
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
        if (aux.getSig()!=null){
            return false;
        } else {
            return true;
        }
        
    }   

    public void insertarOrdenado (T elem){  
        NodoLista nuevo = new NodoLista(elem);
        this.agregarOrd(nuevo);
    }
    

    @Override
    public boolean esIgual(Lista p) {
        if ( this.getCantidad()!=p.getCantidad()){
            return false;
        }
        NodoLista aux = this.getInicio();
        NodoLista auxP = p.getInicio();
        while ( aux!=null && auxP!=null && aux.getDato().equals(auxP.getDato())){
            aux = aux.getSig();
            auxP = auxP.getSig();
        }
        if ( aux==null && auxP==null){
            return true;
        } else {
            return false;
        }        
    }
    public boolean esIgualREC(Lista p) {
        if(this.getCantidad()!=p.getCantidad()){
            return false;
        }
        if (this.esVacia() && p.esVacia()){
            return true;
        }
        return esIgualREC(this.getInicio(),p.getInicio());
        
    }
    
    public boolean esIgualREC( NodoLista nodo, NodoLista nodoP){
        if (nodo==null && nodoP==null){
            return true;
        }
        if (!nodo.getDato().equals(nodoP.getDato())){
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
                NodoLista nodonuevo = new NodoLista(auxP.getDato());
                nueva.agregarFinal(nodonuevo);
                auxP = auxP.getSig();
            } else {
                NodoLista nodonuevo = new NodoLista(aux.getDato());
                nueva.agregarFinal(nodonuevo);
                aux = aux.getSig();
                
            }
        }
        while (aux!=null){
                NodoLista nodonuevo = new NodoLista(aux.getDato());
                nueva.agregarFinal(nodonuevo);
                aux = aux.getSig();            
        }
        
        while (auxP!=null){
            NodoLista nodonuevo = new NodoLista(auxP.getDato());
            nueva.agregarFinal(nodonuevo);
            auxP = auxP.getSig();            
        }
        return nueva;
    }
 }