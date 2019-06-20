package Beans;

import javax.servlet.http.HttpServletRequest;


public class Utilisateur {

  private Integer id;
  
  private String identifiant;

  private String nom;

  private String adresseMail;

  private String role;

  public String motDePasse;
  
  private boolean estConnecte;
  
  private String resultatConnect;

  public Utilisateur() {
      this.estConnecte = false;
  }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public boolean estConnecte() {
        return estConnecte;
    }

    public void setEstConnecte(boolean estConnecte) {
        this.estConnecte = estConnecte;
    }

    public String getResultatConnect() {
        return resultatConnect;
    }

    public void setResultatConnect(String resultatConnect) {
        this.resultatConnect = resultatConnect;
    }

}