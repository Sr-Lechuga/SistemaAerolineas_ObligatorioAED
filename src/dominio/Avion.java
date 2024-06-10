package dominio;

import java.util.Objects;
import sistemaAutogestion.Retorno;
import tads.Lista;

/**
 *
 * @author Jonattan Lima
 */
public class Avion implements Comparable<Avion>, IValidable {

    private String codigo;
    private int capacidadMax;
    private Lista<String> listaCodigosVuelos;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public Lista<String> getListaCodigosVuelos() {
        return listaCodigosVuelos;
    }

    public void setListaCodigosVuelos(Lista<String> listaVuelos) {
        this.listaCodigosVuelos = listaVuelos;
    }

    public Avion(String codigo, int capacidadMax) {
        this.codigo = codigo;
        this.capacidadMax = capacidadMax;
        this.listaCodigosVuelos = new Lista<String>();
    }

    // ---------------------------------------------------------------------------------------- Methods
    private Retorno validarCapacidadMaxima() {

        if (this.getCapacidadMax() < 9 || this.getCapacidadMax() % 3 != 0) {
            return Retorno.error2();
        } else {
            return Retorno.ok();
        }
    }

    public Retorno agregarCodigoVuelo(String codigoVuelo) {
        if (perteneceCodigoVuelo(codigoVuelo)) {
            return Retorno.error1();
        }
        listaCodigosVuelos.agregarOrd(codigoVuelo);
        return Retorno.ok();
    }

    public Retorno eliminarCodigoVuelo(String codigoVuelo) {
        if (!perteneceCodigoVuelo(codigoVuelo)) {
            return Retorno.error1();
        }
        listaCodigosVuelos.borrarElemento(codigoVuelo);
        return Retorno.ok();
    }

    public boolean perteneceCodigoVuelo(String codigoVuelo) {
        return listaCodigosVuelos.pertenece(codigoVuelo);
    }
    
    // ---------------------------------------------------------------------------------------- Overrided
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
        final Avion other = (Avion) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

    @Override
    public int compareTo(Avion otroAvion) {
        //Compara un avion con otro segun su codigo
        return this.getCodigo().compareTo(otroAvion.getCodigo());
    }

    @Override
    public Retorno validar() {
        return validarCapacidadMaxima();
    }

    @Override
    public String toString() {
        return codigo + "-" + capacidadMax;
    }
}
