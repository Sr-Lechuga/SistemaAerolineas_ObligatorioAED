package dominio;

import java.util.Objects;
import sistemaAutogestion.Retorno;
import tads.Lista;

/**
 *
 * @author Jonattan Lima
 */
public class Aerolinea implements Comparable<Aerolinea>{
  private String nombre;
  private String pais;
  private int cantidadMaximaAviones;
  private Lista<Avion> listaAviones;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public int getCantMaxAviones() {
    return cantidadMaximaAviones;
  }

  public void setCantMaxAviones(int cantMaxAviones) {
    this.cantidadMaximaAviones = cantMaxAviones;
  }

    public Lista<Avion> getListaAviones() {
        return listaAviones;
    }

  public Aerolinea(String nombre, String pais, int cantMaxAviones) {
    this.nombre = nombre;
    this.pais = pais;
    this.cantidadMaximaAviones = cantMaxAviones;
    listaAviones = new Lista<>();
  }

  @Override
  public boolean equals(Object otraAerolinea) {
    if (this == otraAerolinea) {
      return true;
    }
    if (otraAerolinea == null) {
      return false;
    }
    if (getClass() != otraAerolinea.getClass()) {
      return false;
    }
    final Aerolinea other = (Aerolinea) otraAerolinea;
    return Objects.equals(this.nombre, other.nombre);
  }

  @Override
  public int compareTo(Aerolinea otraAerolinea) {
    //Ordena las aerolineas, alfabeticamente ascendente
    return this.getNombre().compareTo(otraAerolinea.getNombre());
  }
  
  public int cantidadAvionesRegistrados(){
      return listaAviones.getCantidad();
  }
  
  public boolean existeAvion(String codigoAvion){
      Avion avionBuscado = new Avion(codigoAvion,0);
      return listaAviones.pertenece(avionBuscado);
  }
  
  public Avion obtenerAvion(String codigoAvion){
      Avion avionBuscado = new Avion(codigoAvion,0);
      avionBuscado = (Avion)listaAviones.obtenerElemento(avionBuscado).getDato();
      return avionBuscado;
  }
  
  public Retorno registrarAvion(String codigoAvion, int CapacidadMaxima){
      
      Avion nuevoAvion = new Avion(codigoAvion, CapacidadMaxima);
      
      if(cantidadAvionesRegistrados() >= cantidadMaximaAviones){
          return Retorno.error4();
      }else if(nuevoAvion.Validar().getResultado() == Retorno.Resultado.ERROR_2){
          return Retorno.error2();
      }else if(listaAviones.pertenece(nuevoAvion)){
          return Retorno.error1();
      }

      listaAviones.agregarFinal(nuevoAvion);
      return Retorno.ok();
  }

    @Override
    public String toString() {
        return nombre + "-" + pais + "-" + cantidadMaximaAviones;
    }
  
  
}
