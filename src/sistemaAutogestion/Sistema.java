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
        
        return Retorno.ok();
    }

    @Override
    public Retorno crearAerolinea(String nombre, String pais, int cantMaxAviones) {

        if(existeAerolinea(nombre)){
            return Retorno.error1();
        }else if(cantMaxAviones <= 0){
          return Retorno.error2();
        }
        
        Aerolinea nuevaAerolinea = new Aerolinea(nombre, pais, cantMaxAviones);
        
        listaAerolineas.agregarOrd(nuevaAerolinea);
        
        return Retorno.ok();
    }

    @Override
    public Retorno eliminarAerolinea(String nombre) {
        
        if(!existeAerolinea(nombre)){
            return Retorno.error1();
        }
        Aerolinea aerolineaParaBorrar = new Aerolinea(nombre, "", 0);
        aerolineaParaBorrar = (Aerolinea)listaAerolineas.obtenerElemento(aerolineaParaBorrar).getDato();
        
        if(aerolineaParaBorrar.cantidadAvionesRegistrados() > 0){
            return Retorno.error2();
        }
        
        listaAerolineas.borrarElemento(aerolineaParaBorrar);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarAvion(String codigo, int capacidadMax, String nomAerolinea) {
        
        if(!existeAerolinea(nomAerolinea)){
            return Retorno.error3();
        }
        
        Aerolinea aerolineaBuscada = obtenerAerolinea(nomAerolinea);
        
        Retorno retorno = aerolineaBuscada.registrarAvion(codigo, capacidadMax);
        
        return retorno;
    }

    @Override
    public Retorno eliminarAvion(String nomAerolinea, String codAvion) {
        
        if(!existeAerolinea(nomAerolinea)){
            return Retorno.error1(); 
        }
        Aerolinea aerolineaBuscada = obtenerAerolinea(nomAerolinea);

        Retorno retorno = aerolineaBuscada.EliminarAvion(codAvion);

        return retorno;
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
        Retorno retorno = Retorno.ok(listaAerolineas.mostrar());
        return retorno;
    }

    @Override
    public Retorno listarAvionesDeAerolinea(String nombre) {
        Aerolinea aerolineaBuscada = new Aerolinea(nombre, "", 0);
        if(!existeAerolinea(nombre)){
            return Retorno.error1();
        }
        
        aerolineaBuscada = (Aerolinea)listaAerolineas.obtenerElemento(aerolineaBuscada).getDato();
        Retorno retorno = Retorno.ok(aerolineaBuscada.getListaAviones().mostrar());
        
        return retorno;
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
