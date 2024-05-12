package dominio;

import java.util.Objects;
import sistemaAutogestion.Retorno;

/**
 *
 * @author Jonattan Lima
 */
public class Avion implements Comparable<Avion>,IValidable{
  private String codigo;
  private int capacidadMax;

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
  public int compareTo(Avion otroAvion) {
      //Compara un avion con otro segun su codigo
      return this.getCodigo().compareTo(otroAvion.getCodigo());
  }

    @Override
    public Retorno Validar() {
        return ValidarCapacidadMaxima();
    }

    private Retorno ValidarCapacidadMaxima() {
        
        if(this.getCapacidadMax() < 9 || this.getCapacidadMax() % 3 != 0){
            return Retorno.error2();
        }else{
            return Retorno.ok();
        }
    }
}
