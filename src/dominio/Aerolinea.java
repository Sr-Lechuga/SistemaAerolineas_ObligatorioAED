package dominio;

import java.util.Objects;

/**
 *
 * @author Jonattan Lima
 */
public class Aerolinea implements Comparable<Aerolinea>{
  private String nombre;
  private String pais;
  private int cantMaxAviones;

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
    return cantMaxAviones;
  }

  public void setCantMaxAviones(int cantMaxAviones) {
    this.cantMaxAviones = cantMaxAviones;
  }

  public Aerolinea(String nombre, String pais, int cantMaxAviones) {
    this.nombre = nombre;
    this.pais = pais;
    this.cantMaxAviones = cantMaxAviones;
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
    final Aerolinea other = (Aerolinea) obj;
    return Objects.equals(this.nombre, other.nombre);
  }

  @Override
  public int compareTo(Aerolinea o) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
}
