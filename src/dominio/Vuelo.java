package dominio;

import dominio.Pasaje.Categoria;
import java.util.Objects;
import sistemaAutogestion.Retorno;
import tads.Cola;
import tads.Lista;
import tads.NodoLista;

/**
 *
 * @author Jonattan Lima
 */
public class Vuelo implements Comparable<Vuelo> {

    private String codigoVuelo;
    private String nombreAerolinea;
    private String codigoAvion;
    private String paisDestino;
    private int dia;
    private int mes;
    private int anio;
    private int cantidadMaxPasajesEconomica;
    private int cantidadMaxPasajesPrimeraClase;
    private Lista<Pasaje> listaPasajesEconomica;
    private Lista<Pasaje> listaPasajesPrimera;
    private Cola<Pasaje> listaEsperaEconomica;
    private Cola<Pasaje> listaEsperaPrimera;
    private Lista<Pasaje> listaPasajesDevueltos;

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public String getNombreAerolinea() {
        return nombreAerolinea;
    }

    public void setNombreAerolinea(String nombreAerolinea) {
        this.nombreAerolinea = nombreAerolinea;
    }

    public String getCodigoAvion() {
        return codigoAvion;
    }

    public void setCodigoAvion(String codigoAvion) {
        this.codigoAvion = codigoAvion;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int año) {
        this.anio = año;
    }

    public int getCantidadMaxPasajesEconomica() {
        return cantidadMaxPasajesEconomica;
    }

    public void setCantidadMaxPasajesEconomica(int cantidadPasajesEconomica) {
        this.cantidadMaxPasajesEconomica = cantidadPasajesEconomica;
    }

    public int getCantidadMaxPasajesPrimeraClase() {
        return cantidadMaxPasajesPrimeraClase;
    }

    public void setCantidadMaxPasajesPrimeraClase(int cantidadPasajesPrimeraClase) {
        this.cantidadMaxPasajesPrimeraClase = cantidadPasajesPrimeraClase;
    }

    public Lista<Pasaje> getListaPasajesDevueltos() {
        return listaPasajesDevueltos;
    }

    public void setListaPasajesDevueltos(Lista<Pasaje> listaPasajesDevueltos) {
        this.listaPasajesDevueltos = listaPasajesDevueltos;
    }
    
    

    public Vuelo(String codigoVuelo, String nombreAerolinea, String codigoAvion, String paisDestino, int dia, int mes, int año, int cantidadPasajesEconomica, int cantidadPasajesPrimeraClase) {
        this.codigoVuelo = codigoVuelo;
        this.nombreAerolinea = nombreAerolinea;
        this.codigoAvion = codigoAvion;
        this.paisDestino = paisDestino;
        this.dia = dia;
        this.mes = mes;
        this.anio = año;
        this.cantidadMaxPasajesEconomica = cantidadPasajesEconomica;
        this.cantidadMaxPasajesPrimeraClase = cantidadPasajesPrimeraClase;
        this.listaPasajesEconomica = new Lista<>();
        this.listaPasajesPrimera = new Lista<>();
        this.listaEsperaEconomica = new Cola<>();
        this.listaEsperaPrimera = new Cola<>();
        this.listaPasajesDevueltos = new Lista<>();
    }

    // ---------------------------------------------------------------------------------------- Methods
    public boolean coincideAgenda(int dia, int mes, int anio) {
        return this.getDia() == dia && this.getMes() == mes && this.getAnio() == anio;
    }

    public Retorno agregarPasajeParaVuelo(Pasaje nuevoPasaje) {
        Retorno retorno;
        if (null == nuevoPasaje.getCategoria()) {
            retorno = new Retorno(Retorno.Resultado.ERROR_3);
        } else {
            switch (nuevoPasaje.getCategoria()) {
                case ECONOMICA:
                    if (listaPasajesEconomica.getCantidad() >= getCantidadMaxPasajesEconomica()) {
                        listaEsperaEconomica.encolar(nuevoPasaje);
                        retorno = new Retorno(Retorno.Resultado.OK, "Se agrego el pasaje de clase economica a la lista de espera exitosamente");
                    } else {
                        nuevoPasaje.setEstado(Pasaje.Estado.APROBADO);
                        listaPasajesEconomica.agregarFinal(nuevoPasaje);
                        retorno = new Retorno(Retorno.Resultado.OK, "Se compro el pasaje de clase economica exitosamente");
                    }
                    break;
                case PRIMERA:
                    if (listaPasajesPrimera.getCantidad() >= getCantidadMaxPasajesPrimeraClase()) {
                        listaEsperaPrimera.encolar(nuevoPasaje);
                        retorno = new Retorno(Retorno.Resultado.OK, "Se agrego el pasaje de primera clase a la lista de espera exitosamente");
                    } else {
                        nuevoPasaje.setEstado(Pasaje.Estado.APROBADO);
                        listaPasajesPrimera.agregarFinal(nuevoPasaje);
                        retorno = new Retorno(Retorno.Resultado.OK, "Se compro el pasaje de primera clase exitosamente");
                    }
                    break;
                default:
                    retorno = new Retorno(Retorno.Resultado.ERROR_3);
                    break;
            }
        }

        return retorno;
    }
    
    public boolean clienteEnVuelo(String pasaporte){
        return obtenerPasajeClienteEnVuelo(pasaporte) != null;
    }
    
    public Pasaje obtenerPasajeClienteEnVuelo(String pasaporte) {
        Pasaje pasajeEnEconomicaBuscado = obtenerPasajeDeClienteEnVueloCategoriaEconomica(pasaporte);
        Pasaje pasajeEnPrimeraBuscado = obtenerPasajeDeClienteEnVueloCategoriaPrimera(pasaporte);

        if (pasajeEnEconomicaBuscado != null) {
            return pasajeEnEconomicaBuscado;
        } else if (pasajeEnPrimeraBuscado != null) {
            return pasajeEnPrimeraBuscado;
        } else {
            return null;
        }
    }

    private Pasaje obtenerPasajeDeClienteEnVueloCategoriaEconomica(String pasaporte) {
        if (listaPasajesEconomica.esVacia()) {
            return null;
        }
        String pasaporteEnPasajeEncontrado;
        Pasaje pasajeEncontrado;
        NodoLista posicion = listaPasajesEconomica.getInicio();

        while (posicion != listaPasajesEconomica.getFin()) {
            pasajeEncontrado = (Pasaje) posicion.getDato();
            pasaporteEnPasajeEncontrado = pasajeEncontrado.getPasaporteCliente();

            if (pasaporteEnPasajeEncontrado.equals(pasaporte)) {
                return pasajeEncontrado;
            }

            posicion = posicion.getSig();
        }

        pasajeEncontrado = (Pasaje) listaPasajesEconomica.getFin().getDato();
        pasaporteEnPasajeEncontrado = pasajeEncontrado.getPasaporteCliente();

        if (pasaporteEnPasajeEncontrado.equals(pasaporte)) {
            return pasajeEncontrado;
        } else {
            return null;
        }
    }

    private Pasaje obtenerPasajeDeClienteEnVueloCategoriaPrimera(String pasaporte) {
        if (listaPasajesPrimera.esVacia()) {
            return null;
        }
        String pasaporteEnPasajeEncontrado;
        Pasaje pasajeEncontrado;
        NodoLista posicion = listaPasajesPrimera.getInicio();

        while (posicion != listaPasajesPrimera.getFin()) {
            pasajeEncontrado = (Pasaje) posicion.getDato();
            pasaporteEnPasajeEncontrado = pasajeEncontrado.getPasaporteCliente();

            if (pasaporteEnPasajeEncontrado.equals(pasaporte)) {
                return pasajeEncontrado;
            }

            posicion = posicion.getSig();
        }

        pasajeEncontrado = (Pasaje) listaPasajesPrimera.getFin().getDato();
        pasaporteEnPasajeEncontrado = pasajeEncontrado.getPasaporteCliente();

        if (pasaporteEnPasajeEncontrado.equals(pasaporte)) {
            return pasajeEncontrado;
        } else {
            return null;
        }
    }
    
    public Pasaje obtenerPasajeDeClienteEnDevueltos(String pasaporte) {
        if (listaPasajesDevueltos.esVacia()) {
            return null;
        }
        String pasaporteEnPasajeEncontrado;
        Pasaje pasajeEncontrado;
        NodoLista posicion = listaPasajesDevueltos.getInicio();

        while (posicion != listaPasajesDevueltos.getFin()) {
            pasajeEncontrado = (Pasaje) posicion.getDato();
            pasaporteEnPasajeEncontrado = pasajeEncontrado.getPasaporteCliente();

            if (pasaporteEnPasajeEncontrado.equals(pasaporte)) {
                return pasajeEncontrado;
            }

            posicion = posicion.getSig();
        }

        //Ultimo
        pasajeEncontrado = (Pasaje)posicion.getDato();
        pasaporteEnPasajeEncontrado = pasajeEncontrado.getPasaporteCliente();

        if (pasaporteEnPasajeEncontrado.equals(pasaporte)) {
            return pasajeEncontrado;
        } else {
            return null;
        }
    }

    public void devolucionPasaje(Pasaje pasaje) {
        pasaje.setEstado(Pasaje.Estado.DEVUELTO);

        if (pasaje.getCategoria() == Categoria.ECONOMICA) {
            listaPasajesEconomica.borrarElemento(pasaje);
            listaPasajesDevueltos.agregarFinal(pasaje);
            if(listaEsperaEconomica.getCantidad() > 0){
                Pasaje primerPasajeEnEspera = listaEsperaEconomica.datoFrente();
                primerPasajeEnEspera.setEstado(Pasaje.Estado.APROBADO);
                listaPasajesEconomica.agregarFinal(primerPasajeEnEspera);
                listaEsperaEconomica.desencolar();
            }
        } else if (pasaje.getCategoria() == Categoria.PRIMERA) {
            listaPasajesPrimera.borrarElemento(pasaje);
            listaPasajesDevueltos.agregarFinal(pasaje);
            if(listaEsperaPrimera.getCantidad() > 0){
                Pasaje primerPasajeEnEspera = listaEsperaPrimera.datoFrente();
                primerPasajeEnEspera.setEstado(Pasaje.Estado.APROBADO);
                listaPasajesPrimera.agregarFinal(primerPasajeEnEspera);
                listaEsperaPrimera.desencolar();
            }
        }
    }

    private int getCantidadPasajesDisponibles() {
        int cantidadPasajesDisponibles = cantidadMaxPasajesEconomica - listaPasajesEconomica.getCantidad();
        cantidadPasajesDisponibles = cantidadPasajesDisponibles + (cantidadMaxPasajesPrimeraClase - listaPasajesPrimera.getCantidad());
        return cantidadPasajesDisponibles;
    }

    // ---------------------------------------------------------------------------------------- Overrided
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.codigoVuelo);
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
        final Vuelo other = (Vuelo) obj;
        return Objects.equals(this.codigoVuelo, other.codigoVuelo);
    }

    @Override
    public int compareTo(Vuelo otro) {
        return this.getCodigoVuelo().compareTo(otro.getCodigoVuelo());
    }

    @Override
    public String toString() {
        return codigoVuelo + "-" + nombreAerolinea + "-" + codigoAvion + "-" + listaPasajesEconomica.getCantidad() + "-" + listaPasajesPrimera.getCantidad() + "-" + getCantidadPasajesDisponibles();
    }

}
