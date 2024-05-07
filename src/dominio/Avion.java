package dominio;

import java.util.Objects;

/**
 *
 * @author Jonattan Lima
 */
public class Avion implements Comparable<Avion>{
  private String codigo;
  private int capacidadMax;
  private String nomAerolinea;

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public int getCapacidadMax() {
    return capacidadMax;
  }

  public void setCapacidadMax(int capacidadMax) {
    this.capacidadMax = capacidadMax;
  }

  public String getNomAerolinea() {
    return nomAerolinea;
  }

  public void setNomAerolinea(String nomAerolinea) {
    this.nomAerolinea = nomAerolinea;
  }

  public Avion(String codigo, int capacidadMax) {
    this.codigo = codigo;
    this.capacidadMax = capacidadMax;
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
    final Avion other = (Avion) obj;
    return Objects.equals(this.codigo, other.codigo);
  }

  @Override
  public int compareTo(Avion o) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
  
  
}
