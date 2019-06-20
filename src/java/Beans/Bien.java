package Beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Vector;

public class Bien {

    private Integer identifiant;

    private String typeBien;

    private String nom;

    private Integer surface;

    private Integer nbPieces;

    private Integer nbChambres;

    private Integer etage;

    private Integer prix;

    private String typeChauffage;

    private Ville ville;

    private String adresse;

    private Integer codePostal;

    private HashSet<Option> listeOptions;

    private HashSet<Img> listePhotos;

    private Date dateCreation;

    private Boolean vendu;

    private Date dateVente;

    public Bien() {
        this.listeOptions = new HashSet<>();
        this.listePhotos = new HashSet<>();
    }

    
    public Integer getIdentifiant() {
        return identifiant;
    }

    public String getNom() {
        return nom;
    }

    public Integer getSurface() {
        return surface;
    }

    public Integer getNbPieces() {
        return nbPieces;
    }

    public Integer getNbChambres() {
        return nbChambres;
    }

    public Integer getEtage() {
        return etage;
    }

    public Integer getPrix() {
        return prix;
    }

    public String getTypeChauffage() {
        return typeChauffage;
    }

    public Ville getVille() {
        return ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Boolean getVendu() {
        return vendu;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public String getTypeBien() {
        return typeBien;
    }

    public void setTypeBien(String typeBien) {
        this.typeBien = typeBien;
    }

    public void setIdentifiant(Integer identifiant) {
        if (identifiant != 0) {
            this.identifiant = identifiant;
        } else {
            System.out.println("Erreur : identifiant = 0");
        }
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
    }

    public void setNbPieces(Integer nbPieces) {
        this.nbPieces = nbPieces;
    }

    public void setNbChambres(Integer nbChambres) {
        this.nbChambres = nbChambres;
    }

    public void setEtage(Integer etage) {
        this.etage = etage;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public void setTypeChauffage(String typeChauffage) {
        this.typeChauffage = typeChauffage;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public void setPhotos(HashSet<Img> photos) {
        this.listePhotos = photos;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setVendu(Boolean vendu) {
        if (vendu == null) {
            this.vendu = false;
        } else {
            this.vendu = vendu;
        }
    }

    public void setDateVente(Date dateVente) {
        if (dateVente == null) {
            this.dateVente = null;
        } else {
            this.dateVente = dateVente;
        }
    }

    public HashSet<Option> getListeOptions() {
        return this.listeOptions;
    }

    public boolean setListeOptions(HashSet<Option> listeOptions) {
        Boolean reussi = false;
        System.out.println("setListeOptions -------------------------------");
        if (listeOptions == null) {
            
            System.out.println("Pas d'options pour le bien");
        }
        else {
            this.listeOptions = listeOptions;
            reussi = true;
            for (Option option : listeOptions)
                System.out.println(option.getNomOption() + " , ");
        }
        return reussi;
    }

    public boolean addOption(Option option) {
        Boolean reussi = false;
        if (option.getNomOption().isEmpty()) {
            throw new ExceptionBeans("Option non reconnue");
        } else {
            this.listeOptions.add(option);
            reussi = true;
        }
        return reussi;
    }

    public boolean removeOption(Option option) {
         Boolean reussi = false;
        if (option.getNomOption().isEmpty()) {
            throw new ExceptionBeans("L'option à supprimer n'est pas identifiée");
        } else if (!this.listeOptions.contains(option)){
            throw new ExceptionBeans("L'option à supprimer n'a pas été trouvée dans la liste");
        } else {
            this.listeOptions.remove(option);
            reussi = true;
        }
        return reussi;
    }

    public HashSet<Img> getListePhotos() {
        return this.listePhotos;
    }

    public boolean setListePhotos(HashSet<Img> listePhotos) {
        Boolean reussi = false;
        System.out.println("setListePhotos -------------------------------");
        if (listePhotos == null) {
            // this.photos = null;
            System.out.println("Pas de photo pour le bien");
        }
        else {
            this.listePhotos = listePhotos;
            reussi = true;
            for (Img photo : listePhotos)
                System.out.println(photo.getCheminFichier() + " , ");
        }
        return reussi;
    }

    public boolean addPhoto(Img photo) throws ExceptionBeans {
        Boolean reussi = false;
        if (photo == null) {
            System.out.println("Pas de photo à ajouter");
            throw new ExceptionBeans("Pas de photo à ajouter");
        }
        else if (photo.getCheminFichier().isEmpty()) {
            System.out.println("Chemiin de fichier de photo à ajouter vide");
            throw new ExceptionBeans("Chemin de fichier photo vide");
        } else {
            try {
                this.listePhotos.add(photo);
            } catch (NullPointerException e) {
                throw new ExceptionBeans("Pas de photo à ajouter");
            }
            reussi = true;
        }
        return reussi;
    }
  
    public boolean removePhoto(Img photo) throws ExceptionBeans {
        Boolean reussi = false;
        if (photo.getCheminFichier().isEmpty()) {
            throw new ExceptionBeans("Le fichier photo à supprimer n'est pas identifié");
        } else if (!this.listePhotos.contains(photo)){
            throw new ExceptionBeans("Le fichier photo à supprimer n'a pas été trouvé dans la liste");
        } else {
            this.listePhotos.remove(photo);
            reussi = true;
        }
        return reussi;
    }

}
