/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Bien;
import Beans.Img;
import Beans.Option;
import Beans.Utilisateur;
import Beans.Utilitaires;
import ClassesDAO.DAOFactory;
import java.util.HashSet;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import ClassesDAO.*;
import Controleurs.ICommand;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stagiaire
 */
public class DAOTest {
    
     final public String ADMINISTRATEUR = "ADMIN";
     
    public DAOTest() {
        //testRecupererTout();
     
    }
    
    @Test
    public void testConnectDAO() {
        try {
                Connection connexion = ConnectDAO.getInstance();
                assertNotNull(connexion);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
   
     @Test
    public void testCreerUtilisateur() {
        try {
            System.out.println("Entrée dans testCreerUtilisateur ***********************************************");
            String nom = "tonton";
            String mdp = Utilitaires.getSHA("machin");
            assertNotNull(mdp);
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setIdentifiant("tonton");
            utilisateur.setNom("Aimé Césaire");
            utilisateur.setMotDePasse(mdp);
            utilisateur.setAdresseMail("tonton@gmail.com");
            utilisateur.setRole(ADMINISTRATEUR);
            ClassesDAO.DAO<Utilisateur> utilisateurDAO = DAOFactory.getUtilisateurDAO();
            assertNotNull(utilisateurDAO);
            Boolean estEnregistre = utilisateurDAO.creer(utilisateur);
            assertEquals(estEnregistre, false);
            
            // 2e utilisateur
            mdp = Utilitaires.getSHA("machin");
            assertNotNull(mdp);
           
            utilisateur.setIdentifiant("blaise");
            utilisateur.setNom("Blaise Pascal");
            utilisateur.setMotDePasse(mdp);
            utilisateur.setAdresseMail("blaisep@gmail.com");
            utilisateur.setRole(ADMINISTRATEUR);
            estEnregistre = utilisateurDAO.creer(utilisateur);
            assertEquals(estEnregistre, true);
        } catch (ExceptionDAO e) {
            System.out.println(e.getMessage());
            assertEquals(true, false);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals(true, false);
        }
    }

    @Test
    public void testSupprimerUtilisateur() {
        try {
            System.out.println("Entrée dans testSupprimerUtilisateur ***********************************************");
            
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setIdentifiant("admin");
            ClassesDAO.DAO<Utilisateur> utilisateurDAO = DAOFactory.getUtilisateurDAO();
            assertNotNull(utilisateurDAO);
            Boolean estSupprime = utilisateurDAO.supprimer(utilisateur);
            // assertEquals(estSupprime, false);  
        } catch (ExceptionDAO e) {
            System.out.println(e.getMessage());
            assertEquals(true, false);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals(true, false);
        }
    }

    @Test
    public void testRecupererUtilisateurs() {
        System.out.println("Entrée dans testRecupererUtilisateurs");
        try {
            ClassesDAO.DAO<Utilisateur> utilisateurDAO = DAOFactory.getUtilisateurDAO();
            HashSet<Utilisateur> setUtilisateurs = utilisateurDAO.recupererTout();
            assertNotNull(setUtilisateurs);
            for (Utilisateur utilisateur : setUtilisateurs) {
                System.out.println(utilisateur.getNom());
            }
        } catch (ExceptionDAO e) {
            System.out.println(e.getMessage());
            assertEquals(true,false);
        }
    
    }
    @Test
    public void testRecupererOptionsdUnBien() {
        System.out.println("Entrée dans testRecupererOptionsdUnBien");
        try {
            ClassesDAO.DAO<Option> optionDAO = DAOFactory.getOptionDAO();
            HashSet<Option> setOptions = ((OptionDAO)optionDAO).recupererOptionsdUnBien(1);
            assertNotNull(setOptions);
            for (Option option : setOptions) {
                System.out.println(option.getNomOption());
            }
        } catch (ExceptionDAO e) {
            System.out.println(e.getMessage());
            
        }
    }
    
    @Test
    public void testRecupererPhotosdUnBien() {
        System.out.println("Entrée dans testRecupererPhotosdUnBien");
        try {
            ClassesDAO.DAO<Img> imgDAO = DAOFactory.getImgDAO();
            HashSet<Img> setPhotos = ((ImgDAO)imgDAO).recupererPhotosdUnBien(1);
            assertNotNull(setPhotos);
            for (Img img : setPhotos) {
                System.out.println(img.getCheminFichier());
            }
        } catch (ExceptionDAO e) {
            System.out.println(e.getMessage());
            
        }
    }
    @Test
    public void testRecupererTout() {
        try {
            System.out.println("Entrée dans testRecupererTout AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            ClassesDAO.DAO<Bien> bienDAO = DAOFactory.getBienDAO();
            
            HashSet<Bien> setBien = bienDAO.recupererTout();
            assertNotNull(setBien);
            Iterator<Bien> it = setBien.iterator();
            while (it.hasNext()) {
                Bien bien = it.next();
                assertNotNull(bien.getIdentifiant());
                assertNotEquals((Integer)0,bien.getIdentifiant());
                assertNotNull(bien.getNom());
                assertNotEquals("",bien.getNom());
                assertNotNull(bien.getVille());
                assertNotEquals((Integer)0,bien.getVille());
                assertNotNull(bien.getSurface());
                assertNotEquals((Integer)0,bien.getSurface());
                assertNotNull(bien.getNbPieces());
                assertNotEquals((Integer)0,bien.getNbPieces());
                assertNotNull(bien.getNbChambres());
                assertNotNull(bien.getEtage());
                assertNotNull(bien.getPrix());
                assertNotEquals((Integer)0,bien.getPrix());
                assertNotNull(bien.getTypeChauffage());
                assertNotEquals("",bien.getTypeChauffage());
                System.out.println("\t" + bien.getIdentifiant() + "  |  " + bien.getNom() + "  |  " + bien.getVille().getNomVille() + "  |  " + bien.getSurface() + "  |  " + bien.getNbPieces() + 
                        "  |  " + bien.getNbChambres() + "  |  " + bien.getEtage() + "  |  " + bien.getPrix() + "  |  " + bien.getTypeChauffage() + "  |  " + bien.getAdresse() +
                        "  |  " + bien.getCodePostal());
            }
        }
        catch (Exception e) {
            e.printStackTrace();  
        }
    }
    
   
  /*  public void testRecupererUn() {
        try {
            ClassesDAO.DAO<Bien> bienDAO = DAOFactory.getBienDAO();
            Bien bien = bienDAO.recupererUn(1);
            assertNotNull(bien);
           
                System.out.println(bien.getNom());
                // request.setAttribute("lebien", bien);
            }
        }
        catch (Exception e) {
            e.printStackTrace();  
        }
    }
    */
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
