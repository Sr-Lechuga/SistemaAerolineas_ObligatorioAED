
package dominio;

import java.util.Objects;

/**
 *
 * @author jlima
 */
public class Pasaje implements Comparable<Pasaje>{

    
    public enum Categoria {
        ECONOMICA,
        PRIMERA;
        
        public static Categoria fromInt(int nCategoria){
            switch (nCategoria) {
                case 1:
                    return ECONOMICA;
                case 2:
                    return PRIMERA;
                default:
                    return null;
            }
        }
    };
    
    public enum Estado {
        PENDIENTE,
        APROBADO,
        DEVUELTO;
        
        public static Estado fromInt(int nCategoria){
            switch (nCategoria) {
                case 1:
                    return PENDIENTE;
                case 2:
                    return APROBADO;
                case 3:
                    return DEVUELTO;
                default:
                    return null;
            }
        }
    };
    
    private String pasaporteCliente;
    private String codigoVuelo;
    private Categoria categoria;
    private Estado estado;
    
    // Getters y Setters

    public String getPasaporteCliente() {
        return pasaporteCliente;
    }

    public void setPasaporteCliente(String pasaporteCliente) {
        this.pasaporteCliente = pasaporteCliente;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
    //Constructores
    public Pasaje(String cliente, String codigoVuelo, Categoria categoria) {
        this.pasaporteCliente = cliente;
        this.codigoVuelo = codigoVuelo;
        this.categoria = categoria;
        this.estado = Estado.PENDIENTE;
    }

    //-------------------------------------------------------------------------- Overrided methods
    @Override
    public int compareTo(Pasaje otro) {
        return this.getCodigoVuelo().compareTo(otro.getCodigoVuelo());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.pasaporteCliente);
        hash = 23 * hash + Objects.hashCode(this.codigoVuelo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pasaje other = (Pasaje) obj;
        if (!Objects.equals(this.pasaporteCliente, other.pasaporteCliente)) {
            return false;
        }
        return Objects.equals(this.codigoVuelo, other.codigoVuelo);
    }
    
    
}
