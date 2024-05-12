package sistemaAutogestion;

import dominio.Aerolinea;
import dominio.Avion;
import dominio.Cliente;
import dominio.Vuelo;
import tads.Lista;

public class Sistema implements IObligatorio {

    private Lista<Aerolinea> aerolineas;
    private Lista<Avion> aviones;
    private Lista<Cliente> clientes;
    private Lista<Vuelo> vuelos;

    @Override
    public Retorno crearSistemaDeGestion(){
        aerolineas = new Lista<>();
        aviones = new Lista<>();
        clientes = new Lista<>();
        vuelos = new Lista<>();
        
        return Retorno.ok();
    }

    @Override
    public Retorno crearAerolinea(String nombre, String pais, int cantMaxAviones) {
      
        Aerolinea nuevaAerolinea = new Aerolinea(nombre, pais, cantMaxAviones);
        
        if(aerolineas.pertenece(nuevaAerolinea)){
            return Retorno.error1();
        }else if(nuevaAerolinea.getCantMaxAviones() <= 0){
          return Retorno.error2();
        }
        
        aerolineas.agregarInicio(nuevaAerolinea);
        
        return Retorno.ok();
    }

    @Override
    public Retorno eliminarAerolinea(String nombre) {
        
        Aerolinea aerolineaParaBorrar = new Aerolinea(nombre, "", 0);
        aerolineaParaBorrar = (Aerolinea)aerolineas.obtenerElemento(aerolineaParaBorrar).getDato();
        
        if(isNull(aerolineaParaBorrar)){
            return Retorno.error1();
        }
        
        if(aerolineaParaBorrar.)
        
    }

    @Override
    public Retorno registrarAvion(String codigo, int capacidadMax, String nomAerolinea) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eliminarAvion(String nomAerolinea, String codAvion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCliente(String pasaporte, String nombre, int edad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno crearVuelo(String codigoVuelo, String aerolinea, String codAvion, String paisDestino, int dia, int mes, int año, int cantPasajesEcon, int cantPasajesPClase) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprarPasaje(String pasaporteCliente, String codigoVuelo, int categoríaPasaje) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno devolverPasaje(String pasaporteCliente, String codigoVuelo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarAerolineas() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarAvionesDeAerolinea(String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarClientes() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarVuelos() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno vuelosDeCliente(String pasaporte) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno pasajesDevueltos(String nombreAerolinea) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno vistaDeVuelo(String codigoVuelo) {
        return Retorno.noImplementada();
    }

    private boolean isNull(Object dato){
        return dato == null;
    }
}
