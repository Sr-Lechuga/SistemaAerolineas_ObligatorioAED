package tads;

/**
 *
 * @author jlima
 * @param <T> Recibe un dato generico
 */
public class Cola<T extends Comparable<T>> implements ICola<T>{
    
    private NodoLista<T> frente;

    public NodoLista<T> getFrente() {
        return frente;
    }
    public void setFrente(NodoLista<T> frente) {
        this.frente = frente;
    }
    
    private NodoLista<T> fondo;

    public NodoLista<T> getFondo() {
        return fondo;
    }
    public void setFondo(NodoLista<T> fondo) {
        this.fondo = fondo;
    }
    
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    private int cantidadMaxima;

    public int getCantidadMaxima() {
        return cantidadMaxima;
    }
    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }
    
    
    public Cola() {
        this.fondo = null;
        this.frente = null;
        this.cantidad = 0;
        this.cantidadMaxima = 100;
    }

    public Cola(int cantidadMaxima) {
        this.fondo = null;
        this.frente = null;
        this.cantidad = 0;
        this.cantidadMaxima = cantidadMaxima;
    }
    
    
    @Override
    public void encolar(T dato) {
        if (this.esllena()) {
            return;
        }

        NodoLista<T> nuevo = new NodoLista<T>(dato);
        if (!this.esVacia()) {
            nuevo.setSig(getFondo());
        }
        
        fondo = nuevo;

        this.cantidad++;
    }

    @Override
    public void desencolar() {
        if (this.esVacia()) {
            return;
        }

        if(this.getFrente() == this.getFondo()){
            setFondo(null);
            setFrente(null);
        }else{
            NodoLista<T> pos = getFondo();
            
            while(pos.getSig() != null){
                pos = pos.getSig();
            }
                        
            pos.setSig(null);
            setFrente(pos);
        }
        
        this.cantidad--;
    }

    @Override
    public boolean esVacia() {
        return (this.cantidad == 0 && this.frente == null && this.fondo == null);
    }

    @Override
    public boolean esllena() {
        return this.cantidad == this.cantidadMaxima;
    }

    @Override
    public int cantElementos() {
        return this.cantidad;
    }

    @Override
    public NodoLista frente() {
        return getFrente();
    }
    
    @Override
    public T datoFrente() {
        return getFrente().getDato();
    }
    
    @Override
    public String mostrar() {
        String retorno = "";
        
        NodoLista<T> pos = getFondo();
        
        while(pos.getSig() != null){
            retorno = retorno.concat(pos.getDato().toString()).concat("\n");
            pos = pos.getSig();
        }
        
        retorno = retorno.concat(pos.getDato().toString());
        
        return retorno;
    }
}
