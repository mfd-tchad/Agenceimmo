package Beans;

import java.util.Vector;

import Beans.Option;
import java.util.HashSet;

public class Filtre {

  private String type;

  private Integer surfaceMin;

  private Integer surfaceMax;

  private Integer budgetMin;

  private Integer budgetMax;

  private HashSet<Option> listeOptions;

  private HashSet<Ville> listeVilles;

  private HashSet<Departement> listeDepartements;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSurfaceMin() {
        return surfaceMin;
    }

    public void setSurfaceMin(Integer surfaceMin) {
        this.surfaceMin = surfaceMin;
    }

    public Integer getSurfaceMax() {
        return surfaceMax;
    }

    public void setSurfaceMax(Integer surfaceMax) {
        this.surfaceMax = surfaceMax;
    }

    public Integer getBudgetMin() {
        return budgetMin;
    }

    public void setBudgetMin(Integer budgetMin) {
        this.budgetMin = budgetMin;
    }

    public Integer getBudgetMax() {
        return budgetMax;
    }

    public void setBudgetMax(Integer budgetMax) {
        this.budgetMax = budgetMax;
    }

    public HashSet<Option> getListeOptions() {
        return listeOptions;
    }

    public void setListeOptions(HashSet<Option> listeOptions) {
        this.listeOptions = listeOptions;
    }

    public HashSet<Ville> getListeVilles() {
        return listeVilles;
    }

    public void setListeVilles(HashSet<Ville> listeVilles) {
        this.listeVilles = listeVilles;
    }

    public HashSet<Departement> getListeDepartements() {
        return listeDepartements;
    }

    public void setListeDepartements(HashSet<Departement> listeDepartements) {
        this.listeDepartements = listeDepartements;
    }

   

}