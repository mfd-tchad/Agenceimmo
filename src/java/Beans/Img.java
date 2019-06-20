/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Stagiaire
 */
public class Img {
    private int idImg;
    private String cheminFichier;

    public Img(String cheminFichier) {
        setCheminFichier(cheminFichier);
    }

    public Img() {
    }

    public int getIdImg() {
        return idImg;
    }

    public void setIdImg(int idImg) {
        this.idImg = idImg;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        if (cheminFichier.isEmpty())
            throw new ExceptionBeans("Chemin de fichier photo vide");
        this.cheminFichier = cheminFichier;
    }
    
    
}
