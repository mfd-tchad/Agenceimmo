package Beans;

import java.util.Vector;

public class Ville {

  private Integer idVille;

  private String nomVille;
  
  private int codePostal;

  public int departement;

    public Integer getIdVille() {
        return idVille;
    }

    public void setIdVille(Integer id) {
        this.idVille = id;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public int getDepartement() {
        return departement;
    }

    public void setDepartement(int departement) {
        this.departement = departement;
    }

 

}