package Beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

public class Departement {

    public Integer no;

    public String nomDepartement;

    private HashSet<Ville> listeVilles;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public void addVille(Ville ville) {
    }

    public void removeVille(Ville ville) {
    }

    public HashSet<Ville> getListeVilles() {
        return null;
    }

    public void setListeVilles(HashSet<Ville> listeVilles) {
    }

    public ArrayList<Departement> trierListe(HashSet<Departement> setDepartements) {
        ArrayList<Departement> listeDepartements = new ArrayList<Departement>(setDepartements);
        Collections.sort(listeDepartements, new Comparator<Departement>() {
            @Override
            public int compare(Departement dep1, Departement dep2) {
                return dep1.no.compareTo(dep2.no);
            }
        });
        return listeDepartements;
    }
}
