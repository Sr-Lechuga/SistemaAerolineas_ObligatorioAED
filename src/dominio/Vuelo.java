package dominio;

import java.util.Objects;

/**
 *
 * @author Jonattan Lima
 */
public class Vuelo implements Comparable<Vuelo>{
  private String codigoVuelo;
  private String nombreAerolinea;
  private String codigoAvion;
  private String paisDestino;
  private int dia;
  private int mes;
  private int año;
  private int cantidadPasajesEconomica;
  private int cantidadPasajesPrimeraClase;

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

  public int getAño() {
    return año;
  }

  public void setAño(int año) {
    this.año = año;
  }

  public int getCantidadPasajesEconomica() {
    return cantidadPasajesEconomica;
  }

  public void setCantidadPasajesEconomica(int cantidadPasajesEconomica) {
    this.cantidadPasajesEconomica = cantidadPasajesEconomica;
  }

  public int getCantidadPasajesPrimeraClase() {
    return cantidadPasajesPrimeraClase;
  }

  public void setCantidadPasajesPrimeraClase(int cantidadPasajesPrimeraClase) {
    this.cantidadPasajesPrimeraClase = cantidadPasajesPrimeraClase;
  }

  public Vuelo(String codigoVuelo, String nombreAerolinea, String codigoAvion, String paisDestino, int dia, int mes, int año, int cantidadPasajesEconomica, int cantidadPasajesPrimeraClase) {
    this.codigoVuelo = codigoVuelo;
    this.nombreAerolinea = nombreAerolinea;
    this.codigoAvion = codigoAvion;
    this.paisDestino = paisDestino;
    this.dia = dia;
    this.mes = mes;
    this.año = año;
    this.cantidadPasajesEconomica = cantidadPasajesEconomica;
    this.cantidadPasajesPrimeraClase = cantidadPasajesPrimeraClase;
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
  public int compareTo(Vuelo o) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
  
  
}
