package dominio;

import java.util.Objects;

/**
 *
 * @author Jonattan Lima
 */
public class Cliente implements Comparable<Cliente>{
  private String pasaporte;
  private String nombre;
  private int edad;

  public String getPasaporte() {
    return pasaporte;
  }

  public void setPasaporte(String pasaporte) {
    this.pasaporte = pasaporte;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public Cliente(String pasaporte, String nombre, int edad) {
    this.pasaporte = pasaporte;
    this.nombre = nombre;
    this.edad = edad;
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
    final Cliente other = (Cliente) obj;
    return Objects.equals(this.pasaporte, other.pasaporte);
  }

  @Override
  public int compareTo(Cliente o) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
}
