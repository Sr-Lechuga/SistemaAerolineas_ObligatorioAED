package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PruebasEntrega2 {

    private Sistema miSistema;

    @Before
    public void setUp() {
        miSistema = new Sistema();
        miSistema.crearSistemaDeGestion();
    }

    @Test
    public void testCrearClienteOK() {
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("AM11111", "Alfonso Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("CB34555", "Camila Barcos", 54);
        assertEquals(Retorno.ok().resultado, r.resultado);
    }
    
    @Test
    public void testCrearClienteERROR1() {
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", -1);
        assertEquals(Retorno.error1().resultado, r.resultado);
//        r = miSistema.registrarCliente("AM11111", "Alfonso Miguez", 0);
//        assertEquals(Retorno.error1().resultado, r.resultado);
    }
    
    @Test
    public void testCrearClienteERROR2() {
        Retorno r = miSistema.registrarCliente("MF341112", "Martina Fernandez", 34);
        assertEquals(Retorno.error2().resultado, r.resultado);
        r = miSistema.registrarCliente("AM1111", "Alfonso Miguez", 0);
        assertEquals(Retorno.error2().resultado, r.resultado);
    }
    
    @Test
    public void testCrearClienteERROR3() {
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("MF34111", "Camila Barcos", 54);
        assertEquals(Retorno.error3().resultado, r.resultado);
    }
    
    @Test
    public void testListarClienteOK() {

        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("AM11111", "Alfonso Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("CB34555", "Camila Barcos", 54);
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.listarClientes();
        assertEquals("CB34555-Camila Barcos-54|\nAM11111-Alfonso Miguez-34|\nMF34111-Martina Fernandez-1|", r.valorString);
    }

    @Test
    public void testCrearVueloOK() {
        //Creación de aerolineas
        Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarAvion("FLY300", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 12, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA2341", "Aerolineas Argentinas", "FLY300", "Brasil", 1, 11, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
    }
    
    @Test
    public void testCrearVueloERROR1() {
        //Creación de aerolineas
        Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarAvion("FLY300", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 12, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY300", "Brasil", 1, 11, 2024, 6, 3);
        assertEquals(Retorno.error1().resultado, r.resultado);
    }
    
    @Test
    public void testCrearVueloERROR2() {
        //Creación de aerolineas
        Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarAvion("FLY300", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 12, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA1112", "Aerolineas Uruguayas", "FLY300", "Brasil", 1, 11, 2024, 6, 3);
        assertEquals(Retorno.error2().resultado, r.resultado);
    }
    
    @Test
    public void testCrearVueloERROR3() {
        //Creación de aerolineas
        Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarAvion("FLY300", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 12, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA1112", "Aerolineas Argentinas", "FLY777", "Brasil", 1, 11, 2024, 6, 3);
        assertEquals(Retorno.error3().resultado, r.resultado);
    }
    
    @Test
    public void testCrearVueloERROR4() {
        //Creación de aerolineas
        Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarAvion("FLY300", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 12, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA1112", "Aerolineas Argentinas", "FLY221", "Uruguay", 11, 11, 2024, 12, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA1113", "Aerolineas Argentinas", "FLY221", "Uruguay", 11, 12, 2024, 12, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA1114", "Aerolineas Argentinas", "FLY221", "Brasil", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.error4().resultado, r.resultado);
    }
    
    @Test
    public void testCrearVueloERROR5() {
        //Creación de aerolineas
        Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 11, 3);
        assertEquals(Retorno.error5().resultado, r.resultado);
        r = miSistema.crearVuelo("AA1112", "Aerolineas Argentinas", "FLY221", "Brasil", 10, 12, 2024, 6, 4);
        assertEquals(Retorno.error5().resultado, r.resultado);
        r = miSistema.crearVuelo("AA1113", "Aerolineas Argentinas", "FLY221", "Brasil", 10, 12, 2024, 7, 5);
        assertEquals(Retorno.error5().resultado, r.resultado);
    }
    
    @Test
    public void testCrearVueloERROR6() {
        //Creación de aerolineas
        Retorno r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 21, 15);
        assertEquals(Retorno.error6().resultado, r.resultado);
    }
    
    @Test
    public void testListarVuelosOK() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("VM32132", "Veronida Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 15, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 12, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA2222", "Aerolineas Argentinas", "FLY221", "Uruguay", 2, 10, 2024, 6, 9);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA3333", "Aerolineas Argentinas", "FLY221", "Uruguay", 5, 9, 2024, 3, 12);
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.listarVuelos();
        assertEquals("AA1111-Aerolineas Argentinas-FLY221-0-0-15|\nAA2222-Aerolineas Argentinas-FLY221-0-0-15|\nAA3333-Aerolineas Argentinas-FLY221-0-0-15|", r.valorString);

        //Compra de pasajes
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("VM32132", "AA2222", 2);
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.listarVuelos();
        assertEquals("AA1111-Aerolineas Argentinas-FLY221-1-0-14|\nAA2222-Aerolineas Argentinas-FLY221-0-1-14|\nAA3333-Aerolineas Argentinas-FLY221-0-0-15|", r.valorString);
    }

    @Test
    public void testComprarPasajeOK() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("VM32132", "Veronida Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("CB34555", "Camila Barcos", 54);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("GV99882", "Gerardo Vercias", 19);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        assertEquals("Se compro el pasaje de clase economica exitosamente", r.valorString);
        r = miSistema.comprarPasaje("VM32132", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        assertEquals("Se compro el pasaje de clase economica exitosamente", r.valorString);
        r = miSistema.comprarPasaje("CB34555", "AA1111", 2);
        assertEquals(Retorno.ok().resultado, r.resultado);
        assertEquals("Se compro el pasaje de primera clase exitosamente", r.valorString);
    }
    
    @Test
    public void testComprarPasajeError1() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("VM32132", "AA1111", 1);
        assertEquals(Retorno.error1().resultado, r.resultado);
    }
    
    @Test
    public void testComprarPasajeError2() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1112", 1);
        assertEquals(Retorno.error2().resultado, r.resultado);
    }
    
    @Test
    public void testComprarPasajeError3() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 3);
        assertEquals(Retorno.error3().resultado, r.resultado);
    }
    
    @Test
    public void testDevolverPasajeOK() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("VM32132", "Veronida Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("CB34555", "Camila Barcos", 54);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("GV99882", "Gerardo Vercias", 19);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("VM32132", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("CB34555", "AA1111", 2);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Devolución de pasaje 
        r = miSistema.devolverPasaje("MF34111", "AA1111");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverPasaje("CB34555", "AA1111");
        assertEquals(Retorno.ok().resultado, r.resultado);
    }
    
    @Test
    public void testDevolverPasajeERROR1() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Devolución de pasaje 
        r = miSistema.devolverPasaje("VM32132", "AA1111");
        assertEquals(Retorno.error1().resultado, r.resultado);
    }
    
    @Test
    public void testDevolverPasajeERROR2() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Devolución de pasaje 
        r = miSistema.devolverPasaje("MF34111", "AA1112");
        assertEquals(Retorno.error2().resultado, r.resultado);
    }

    @Test
    public void testDevolverPasajeERROR3() {
        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("VM32132", "Veronida Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Devolución de pasaje 
        r = miSistema.devolverPasaje("VM32132", "AA1111");
        assertEquals(Retorno.error3().resultado, r.resultado);
    }
    
    @Test
    public void testListarVuelosDeClientesOK() {

        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA2222", "Aerolineas Argentinas", "FLY221", "Uruguay", 11, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA3333", "Aerolineas Argentinas", "FLY221", "Uruguay", 14, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);

        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("MF34111", "AA2222", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("MF34111", "AA3333", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Devolución de pasaje (se deberían otorgar a los dos clientes que están esperando)
        r = miSistema.devolverPasaje("MF34111", "AA2222");
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.vuelosDeCliente("MF34111");
        assertEquals("AA3333-CPR|\nAA2222-CPR|\nAA1111-CPR|\nAA2222-DEV|", r.valorString);
        

    }
    
    @Test
    public void testListarVuelosDeClientesERROR1() {

        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA2222", "Aerolineas Argentinas", "FLY221", "Uruguay", 11, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA3333", "Aerolineas Argentinas", "FLY221", "Uruguay", 14, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);

        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("MF34111", "AA2222", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("MF34111", "AA3333", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Devolución de pasaje (se deberían otorgar a los dos clientes que están esperando)
        r = miSistema.devolverPasaje("MF34111", "AA2222");
        assertEquals(Retorno.ok().resultado, r.resultado);

       r = miSistema.vuelosDeCliente("VM32132");
        assertEquals(Retorno.error1().resultado, r.resultado);
    }

    @Test
    public void testPasajesDevueltosOK() {

        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("VM32132", "Veronida Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("CB34555", "Camila Barcos", 54);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("GV99882", "Gerardo Vercias", 19);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA9999", "Aerolineas Argentinas", "FLY221", "Uruguay", 14, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("VM32132", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("CB34555", "AA1111", 2);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("MF34111", "AA9999", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);

        //Devolución de pasaje
        r = miSistema.devolverPasaje("VM32132", "AA1111");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverPasaje("CB34555", "AA1111");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverPasaje("MF34111", "AA9999");
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.pasajesDevueltos("Aerolineas Argentinas");
        assertEquals("MF34111-AA9999|\nVM32132-AA1111|\nCB34555-AA1111|", r.valorString);
    }

    @Test
    public void testPasajesDevueltosError1() {

        //Creación de clientes
        Retorno r = miSistema.registrarCliente("MF34111", "Martina Fernandez", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("VM32132", "Veronida Miguez", 34);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("CB34555", "Camila Barcos", 54);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.registrarCliente("GV99882", "Gerardo Vercias", 19);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aerolineas
        r = miSistema.crearAerolinea("Aerolineas Argentinas", "Argentina", 10);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de aviones
        r = miSistema.registrarAvion("FLY221", 9, "Aerolineas Argentinas");
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Creación de vuelos
        r = miSistema.crearVuelo("AA9999", "Aerolineas Argentinas", "FLY221", "Uruguay", 14, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.crearVuelo("AA1111", "Aerolineas Argentinas", "FLY221", "Uruguay", 10, 12, 2024, 6, 3);
        assertEquals(Retorno.ok().resultado, r.resultado);
        //Compra de pasajes (hay disponible)
        r = miSistema.comprarPasaje("MF34111", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("VM32132", "AA1111", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("CB34555", "AA1111", 2);
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.comprarPasaje("MF34111", "AA9999", 1);
        assertEquals(Retorno.ok().resultado, r.resultado);

        //Devolución de pasaje
        r = miSistema.devolverPasaje("MF34111", "AA9999");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverPasaje("VM32132", "AA1111");
        assertEquals(Retorno.ok().resultado, r.resultado);
        r = miSistema.devolverPasaje("CB34555", "AA1111");
        assertEquals(Retorno.ok().resultado, r.resultado);

        r = miSistema.pasajesDevueltos("Aerolineas Uruguayas");
        assertEquals(Retorno.error1().resultado, r.resultado);
    }
    
    @Test
    public void testVistaDeVueloOK() {

        /* Esta prueba no es imprescindible validarla mediante pruebas unitarias, pudiendo hacer una impresión de pantalla con la vista
        del avión, tal como se muestra en la letra del obligatorio */
    }

}