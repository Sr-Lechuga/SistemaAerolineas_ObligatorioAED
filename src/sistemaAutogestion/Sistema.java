package sistemaAutogestion;

import dominio.Aerolinea;
import dominio.Cliente;
import dominio.Vuelo;
import tads.Lista;

public class Sistema implements IObligatorio {

    private Lista<Aerolinea> listaAerolineas;
    private Lista<Cliente> listaClientes;
    private Lista<Vuelo> listaVuelos;

    @Override
    public Retorno crearSistemaDeGestion(){
        listaAerolineas = new Lista<>();
        listaClientes = new Lista<>();
        listaVuelos = new Lista<>();
        
        return Retorno.ok("Se inicializo el sistema correctamente.");
    }

    @Override
    public Retorno crearAerolinea(String nombre, String pais, int cantMaxAviones) {

        if(existeAerolinea(nombre)){
            return Retorno.error1();
        }else if(cantMaxAviones <= 0){
          return Retorno.error2();
        }
        
        Aerolinea nuevaAerolinea = new Aerolinea(nombre, pais, cantMaxAviones);
        
        listaAerolineas.agregarInicio(nuevaAerolinea);
        
        return Retorno.ok("Se creo la aerolinea" + nombre + " con exito.");
    }

    @Override
    public Retorno eliminarAerolinea(String nombre) {
        
        Aerolinea aerolineaParaBorrar = new Aerolinea(nombre, "", 0);
        aerolineaParaBorrar = (Aerolinea)listaAerolineas.obtenerElemento(aerolineaParaBorrar).getDato();
        
        if(isNull(aerolineaParaBorrar)){
            return Retorno.error1();
        }else if(aerolineaParaBorrar.cantidadAvionesRegistrados() > 0){
            return Retorno.error2();
        }
        
        listaAerolineas.borrarElemento(aerolineaParaBorrar);
        return Retorno.ok("Se elimino la aerolinea " + nombre + " con exito.");
    }

    @Override
    public Retorno registrarAvion(String codigo, int capacidadMax, String nomAerolinea) {
        
        Aerolinea aerolineaBuscada = obtenerAerolinea(nomAerolinea);
        
        if(isNull(aerolineaBuscada)){
            return Retorno.error3();
        }
        
        Retorno retorno = aerolineaBuscada.registrarAvion(codigo, capacidadMax);
        
        return retorno;
    }

    @Override
    public Retorno eliminarAvion(String nomAerolinea, String codAvion) {
        
        Aerolinea aerolineaBuscada = obtenerAerolinea(nomAerolinea);
        
        if(isNull(aerolineaBuscada)){
            return Retorno.error1();
        }else if(!aerolineaBuscada.existeAvion(codAvion)){
            return Retorno.error2();
        }
        
        

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

    // ----------------------------------------------- Auxiliar Methods
    private boolean isNull(Object dato){
        return dato == null;
    }
    
    private boolean existeAerolinea(String nombreAerolinea){
        //Dummy object para busqueda
        Aerolinea aerolineaBuscada = new Aerolinea(nombreAerolinea, "", 0);
        return listaAerolineas.pertenece(aerolineaBuscada);
    }

    private Aerolinea obtenerAerolinea(String nomAerolinea) {
        //Dummy object to search
        Aerolinea aerolineaBuscada = new Aerolinea(nomAerolinea, "", 0);
        return (Aerolinea)listaAerolineas.obtenerElemento(aerolineaBuscada).getDato();
    }
}
