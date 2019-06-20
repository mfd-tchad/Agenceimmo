/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import Beans.Bien;
import ClassesDAO.DAOFactory;
import ClassesDAO.ExceptionDAO;
import java.util.HashSet;
import java.util.Iterator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marie-Françoise
 */
public class ChercherBiens {

    public static void chercherLesBiens(HttpServletRequest request, HttpServletResponse response) {

        try {
            ClassesDAO.DAO<Bien> bienDAO = DAOFactory.getBienDAO();
            HashSet<Bien> setBien = bienDAO.recupererTout();
            Iterator<Bien> it = setBien.iterator();

            System.out.println("CtrlAdminBiens AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            while (it.hasNext()) {
                Bien bien = it.next();
                System.out.println("\t" + bien.getIdentifiant() + "  |  " + bien.getNom() + "  |  " + bien.getVille().getNomVille() + "  |  " + bien.getSurface() + "  |  " + bien.getNbPieces()
                        + "  |  " + bien.getNbChambres() + "  |  " + bien.getEtage() + "  |  " + bien.getPrix() + "  |  " + bien.getTypeChauffage());
                /*for (Option option : bien.getListeOptions())
                 {
                    System.out.print(option.getNomOption() + "  ,  ");
                }
                System.out.println();
                 */
            }
            request.setAttribute("lesbiens", setBien);
        } catch (NullPointerException e) {
            e.printStackTrace();
            request.setAttribute("message", "Désolé, les informations sur les biens en vente n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
        } catch (ExceptionDAO e) {
            request.setAttribute("message", "Désolé, les informations sur les biens en vente n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Désolé, les informations sur les biens en vente n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
        }
    }
}
