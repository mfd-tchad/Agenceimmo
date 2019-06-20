/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import ClassesDAO.BienDAO;
import Beans.Bien;
import ClassesDAO.DAOFactory;
import ClassesDAO.ExceptionDAO;
import java.util.HashSet;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stagiaire
 */
@WebServlet(name = "CtrlPageAccueil", urlPatterns = {"/VoirPageAccueil"})
public class CtrlPageAccueil extends HttpServlet implements ICommand{
 public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            //if (session.getAttribute("touslesbiens") == null)
            ClassesDAO.DAO<Bien> bienDAO = DAOFactory.getBienDAO();
            HashSet<Bien> setBien = ((BienDAO)bienDAO).recupererNDerniers(6);
            
            Iterator<Bien> it = setBien.iterator();
            System.out.println("CtrlAccueil AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            while (it.hasNext()) {
                Bien bien = it.next();            
                System.out.println("\t" + bien.getIdentifiant() + "  |  " + bien.getNom() + "  |  " + bien.getVille().getNomVille() + "  |  " + bien.getSurface() + "  |  " + bien.getNbPieces() + 
                        "  |  " + bien.getNbChambres() + "  |  " + bien.getEtage() + "  |  " + bien.getPrix() + "  |  " + bien.getTypeChauffage());
            }
            request.setAttribute("lesbiens", setBien);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            request.setAttribute("message", "Désolé, les informations sur les biens en vente n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
        }
        catch (ExceptionDAO e) {
            request.setAttribute("message", "Désolé, les informations sur les biens en vente n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
        }
        catch (Exception e) {
            e.printStackTrace(); 
            request.setAttribute("message", "Désolé, les informations sur les biens en vente n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
        }
        return "pageAccueil.jsp";
    }
}