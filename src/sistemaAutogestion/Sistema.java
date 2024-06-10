package sistemaAutogestion;

import dominio.Aerolinea;
import dominio.Avion;
import dominio.Cliente;
import dominio.Pasaje;
import dominio.Vuelo;
import tads.Lista;
import tads.NodoLista;

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
         if(edad < 0){
             return Retorno.error1();
         }
         if(pasaporte.length() != 7){
             return Retorno.error2();
         }
         if(existeCliente(pasaporte)){
             return Retorno.error3();
         }
         
         Cliente clienteNuevo = new Cliente(pasaporte, nombre, edad);
         
         listaClientes.agregarInicio(clienteNuevo);
         
         return Retorno.ok();
    }

    @Override
    public Retorno crearVuelo(String codigoVuelo, String aerolinea, String codAvion, String paisDestino, int dia, int mes, int año, int cantPasajesEcon, int cantPasajesPClase) {
        Aerolinea aerolineaBuscada;
        Avion avionBuscado;
        
        if(existeVuelo(codigoVuelo)){
            return Retorno.error1();
        }
        if(!existeAerolinea(aerolinea)){
            return Retorno.error2();
        }
        if(!existeAerolinea(aerolinea)){
            return Retorno.error3();
        }else{
            aerolineaBuscada = obtenerAerolinea(aerolinea);
            if(!aerolineaBuscada.ExisteAvion(codAvion)){
                return Retorno.error3();
            }
            avionBuscado = aerolineaBuscada.obtenerAvion(codAvion);
        }
        if(tieneAgendadoVuelo(avionBuscado.getListaCodigosVuelos(), dia, mes, año)){
            return Retorno.error4();
        }
        if(cantPasajesEcon % 3 != 0 || cantPasajesPClase % 3 != 0){
            return Retorno.error5();
        }
        if(cantPasajesEcon + cantPasajesPClase > avionBuscado.getCapacidadMax()){
            return Retorno.error6();
        }
        
        Vuelo nuevoVuelo = new Vuelo(codigoVuelo, aerolinea, codAvion, paisDestino, dia, mes, año, cantPasajesEcon, cantPasajesPClase);
        completarCapacidadVuelo(nuevoVuelo, avionBuscado.getCapacidadMax());
        
        if(cantPasajesEcon + cantPasajesPClase > avionBuscado.getCapacidadMax()){
            return Retorno.error6();
        }
        
        listaVuelos.agregarFinal(nuevoVuelo);
        avionBuscado.agregarCodigoVuelo(codigoVuelo);
        
        return Retorno.ok();
    }
    
    private void completarCapacidadVuelo(Vuelo vuelo, int pasajesDisponibles){
        
        int totalPasajesDefinidos = vuelo.getCantidadMaxPasajesEconomica() + vuelo.getCantidadMaxPasajesPrimeraClase();
        vuelo.setCantidadMaxPasajesEconomica(vuelo.getCantidadMaxPasajesEconomica()+ (pasajesDisponibles -totalPasajesDefinidos));
    }

    @Override
    public Retorno comprarPasaje(String pasaporteCliente, String codigoVuelo, int categoríaPasaje) {
        if(!existeCliente(pasaporteCliente)){
            return Retorno.error1();
        }
        if(!existeVuelo(codigoVuelo)){
            return Retorno.error2();
        }
        
        Pasaje nuevoPasaje = new Pasaje(pasaporteCliente, codigoVuelo, Pasaje.Categoria.fromInt(categoríaPasaje));
        
        Vuelo vueloBuscado = obtenerVuelo(codigoVuelo);
        
        return vueloBuscado.agregarPasajeParaVuelo(nuevoPasaje);
    }

    @Override
    public Retorno devolverPasaje(String pasaporteCliente, String codigoVuelo) {
        if(!existeCliente(pasaporteCliente)){
            return Retorno.error1();
        }
        if(!existeVuelo(codigoVuelo)){
            return Retorno.error2();
        }
        Vuelo vueloBuscado = obtenerVuelo(codigoVuelo);
        
        Pasaje pasajeBuscado = vueloBuscado.obtenerPasajeClienteEnVuelo(pasaporteCliente);
        if(isNull(pasajeBuscado)){
            return Retorno.error3();
        }
        
        vueloBuscado.devolucionPasaje(pasajeBuscado);
        
        return Retorno.ok();
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
        Retorno retorno = Retorno.ok(listaClientes.mostrarREC(listaClientes.getInicio()));
        return retorno;
    }

    @Override
    public Retorno listarVuelos() {
        Retorno retrono = Retorno.ok(listaVuelos.mostrarREC(listaVuelos.getInicio()));
        return retrono;
    }

    @Override
    public Retorno vuelosDeCliente(String pasaporte) {
        if(!existeCliente(pasaporte)){
            return Retorno.error1();
        }
        
        Lista<String> vuelosCliente = new Lista<>();
        
        Pasaje pasajeEnVuelo;
        Pasaje pasajeDevueltoEnVuelo;
        NodoLista posicion = listaVuelos.getInicio();
        
        while(posicion != listaVuelos.getFin()){
            pasajeEnVuelo = ((Vuelo) posicion.getDato()).obtenerPasajeClienteEnVuelo(pasaporte);
            if (pasajeEnVuelo != null) {
                vuelosCliente.agregarInicio(pasajeEnVuelo.getCodigoVuelo() + "-CPR");
            }
            
            pasajeDevueltoEnVuelo = ((Vuelo)posicion.getDato()).obtenerPasajeDeClienteEnDevueltos(pasaporte);
            if(pasajeDevueltoEnVuelo != null){
                vuelosCliente.agregarInicio(pasajeDevueltoEnVuelo.getCodigoVuelo() + "-CPR");
                vuelosCliente.agregarFinal(pasajeDevueltoEnVuelo.getCodigoVuelo() + "-DEV");
            }
            posicion = posicion.getSig();
        }
        //Ultimo
        pasajeEnVuelo = ((Vuelo) posicion.getDato()).obtenerPasajeClienteEnVuelo(pasaporte);
        if (pasajeEnVuelo != null) {
            vuelosCliente.agregarInicio(pasajeEnVuelo.getCodigoVuelo() + "-CPR");
        }
        pasajeDevueltoEnVuelo = ((Vuelo)posicion.getDato()).obtenerPasajeDeClienteEnDevueltos(pasaporte);
        if(pasajeDevueltoEnVuelo != null){
            vuelosCliente.agregarInicio(pasajeDevueltoEnVuelo.getCodigoVuelo() + "-CPR");
            vuelosCliente.agregarFinal(pasajeDevueltoEnVuelo.getCodigoVuelo() + "-DEV");
        }
        
        Retorno retorno = Retorno.ok(vuelosCliente.mostrarREC(vuelosCliente.getInicio()));
        return retorno;
    }

    @Override
    public Retorno pasajesDevueltos(String nombreAerolinea) {
        if(!existeAerolinea(nombreAerolinea)){
            return Retorno.error1();
        }
        String listaPasajesDevueltos = "";
        
        NodoLista posicion = listaVuelos.getInicio();
        
        while(posicion != listaVuelos.getFin()){
            Vuelo vueloBuscado = (Vuelo)posicion.getDato();
            
            if(vueloBuscado.getNombreAerolinea().equals(nombreAerolinea)){
                Pasaje pasajeDevuelto;
                NodoLista posicionPasaje = vueloBuscado.getListaPasajesDevueltos().getInicio();
                
                while(posicionPasaje !=  vueloBuscado.getListaPasajesDevueltos().getFin()){
                    pasajeDevuelto = (Pasaje)posicionPasaje.getDato();
                    listaPasajesDevueltos = listaPasajesDevueltos + pasajeDevuelto.getPasaporteCliente() + "-" + pasajeDevuelto.getCodigoVuelo() + "|\n";
                    
                    posicionPasaje = posicionPasaje.getSig();
                }
                //Ultimo
                pasajeDevuelto = (Pasaje)vueloBuscado.getListaPasajesDevueltos().getFin().getDato();
                listaPasajesDevueltos = listaPasajesDevueltos + pasajeDevuelto.getPasaporteCliente() + "-" + pasajeDevuelto.getCodigoVuelo() + "|\n";
            }
            
            posicion = posicion.getSig();
        }
        
        Vuelo ultimoVueloBuscado = (Vuelo)listaVuelos.getFin().getDato();
        if(ultimoVueloBuscado.getNombreAerolinea().equals(nombreAerolinea)){
                Pasaje pasajeDevuelto;
                NodoLista posicionPasaje = ultimoVueloBuscado.getListaPasajesDevueltos().getInicio();
                
                while(posicionPasaje !=  ultimoVueloBuscado.getListaPasajesDevueltos().getFin()){
                    pasajeDevuelto = (Pasaje)posicionPasaje.getDato();
                    listaPasajesDevueltos = listaPasajesDevueltos + pasajeDevuelto.getPasaporteCliente() + "-" + pasajeDevuelto.getCodigoVuelo() + "|\n";
                    
                    posicionPasaje = posicionPasaje.getSig();
                }
                //Ultimo
                pasajeDevuelto = (Pasaje)ultimoVueloBuscado.getListaPasajesDevueltos().getFin().getDato();
                listaPasajesDevueltos = listaPasajesDevueltos + pasajeDevuelto.getPasaporteCliente() + "-" + pasajeDevuelto.getCodigoVuelo()+ "|";
            }
        
        return Retorno.ok(listaPasajesDevueltos);
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
    
    private boolean existeCliente(String pasaporte){
        //Dummy object para busqueda
        Cliente clienteBuscado = new Cliente(pasaporte, "", 0);
        return listaClientes.pertenece(clienteBuscado);
    }
    
    private boolean existeVuelo(String codigoVuelo){
        //Dummy object para busqueda
        Vuelo vueloBuscado = new Vuelo(codigoVuelo, "", "", "", 0, 0, 0, 0, 0);
        return listaVuelos.pertenece(vueloBuscado);
    }
    
    private Vuelo obtenerVuelo(String codigoVuelo){
        //Dummy object para busqueda
        Vuelo vueloBuscado = new Vuelo(codigoVuelo, "", "", "", 0, 0, 0, 0, 0);
        return (Vuelo)listaVuelos.obtenerElemento(vueloBuscado).getDato();
    }
    
    private boolean tieneAgendadoVuelo(Lista<String> codigosVueloAsociados, int dia, int mes, int anio){
        if(codigosVueloAsociados.esVacia()){
            return false;
        }
        
        boolean tieneAgendadoVuelo = false;
        NodoLista posicion = codigosVueloAsociados.getInicio();
        
        while(posicion != codigosVueloAsociados.getFin() && !tieneAgendadoVuelo){
            Vuelo vueloBuscado = obtenerVuelo((String)posicion.getDato());
            
            if(vueloBuscado.coincideAgenda(dia, mes, anio)){
                tieneAgendadoVuelo = true;
            }
            
            posicion = posicion.getSig();
        }
        
        Vuelo ultimoVueloBuscado = obtenerVuelo((String)codigosVueloAsociados.getFin().getDato());
        if(ultimoVueloBuscado.coincideAgenda(dia, mes, anio)){
            tieneAgendadoVuelo = true;
        }
        
        return tieneAgendadoVuelo;
    }
}
