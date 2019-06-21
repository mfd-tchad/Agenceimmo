/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import Beans.Bien;
import Beans.Departement;
import Beans.Option;
import Beans.Ville;
import ClassesDAO.DAOFactory;
import ClassesDAO.DepartementDAO;
import ClassesDAO.ExceptionDAO;
import ClassesDAO.VilleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marie-Françoise
 */
@WebServlet(name = "CtrlCreerUnBien", urlPatterns = {"/CtrlCreerUnBien"})
public class CtrlCreerUnBien extends HttpServlet implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Bien bien = new Bien();
        if (!request.getParameterMap().containsKey("submit")) {
            System.out.println("Premier passage dans CtrlCreerUnBien *********************************");

            try {

                System.out.println("CtrlCreerUnBien BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");

                // récupérer la liste complète des options
                ClassesDAO.DAO<Option> optionDAO = DAOFactory.getOptionDAO();

                System.out.println("Récupération de la liste des options BBBBBB");

                HashSet<Option> setOptions = new HashSet<>();
                setOptions = optionDAO.recupererTout();
                request.setAttribute("lesoptions", setOptions);
            } catch (NullPointerException e) {
                e.printStackTrace();
                request.setAttribute("message", "Désolé, la liste des options possibles n'a pas pu être récupérée\nVeuillez réessayer plus tard");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("message", "Désolé, la liste des options possibles n'a pas pu être récupérée\nVeuillez réessayer plus tard");
            } catch (ExceptionDAO e) {
                request.setAttribute("message", "Désolé, la liste des options possibles n'a pas pu être récupérée\nVeuillez réessayer plus tard");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Désolé, la liste des options possibles n'a pas pu être récupérée\nVeuillez réessayer plus tard");
            }

            // Récupérer la liste des départements
            ClassesDAO.DAO<Departement> departementDAO = DAOFactory.getDepartementDAO();

            System.out.println("Récupération de la liste des départements DDDDDDDDDDDDD");
            ArrayList<Departement> listeDepartements = new ArrayList<Departement>();
            // A la première demande de la liste des départements, celle-ci est récupérée dans la base de données et
            // stockée dans une variable de session de manière à ne pas avoir à la rechercher en BD à chaque fois
            if (session.getAttribute("lesdepartements") != null) {
                System.out.println("Liste des départements existante dans variable de session -------------------------");
                listeDepartements = (ArrayList<Departement>) session.getAttribute("lesdepartements");
            } else {
                listeDepartements = ((DepartementDAO) departementDAO).recupererToutTrie();
                session.setAttribute("lesdepartements", listeDepartements);
            }
            request.setAttribute("lesdepartements", listeDepartements);

            ClassesDAO.DAO<Ville> villeDAO = DAOFactory.getVilleDAO();
            ArrayList<Ville> listeVilles = new ArrayList<Ville>();
            listeVilles = ((VilleDAO) villeDAO).recupererVillesDunDepartement(1);
            request.setAttribute("lesvilles", listeVilles);
            request.setAttribute("lebien", bien);
            return "pageCreerUnBien.jsp";
        } else {
            System.out.println("Autre passage dans CtrlCreerUnBien ---------------------------------------");
            if (request.getParameterMap().containsKey("reset")) {
                request.getRequestDispatcher("aiguillage?cmd=AdminBiens").forward(request, response);
            } else {
                // Créer le bien
                try {
                    bien = (Bien) request.getAttribute("lebien");
                    System.out.println("\t" + bien.getIdentifiant() + "  |  " + bien.getNom() + "  |  " + bien.getVille().getNomVille() + "  |  " + bien.getSurface() + "  |  " + bien.getNbPieces()
                            + "  |  " + bien.getNbChambres() + "  |  " + bien.getEtage() + "  |  " + bien.getPrix() + "  |  " + bien.getTypeChauffage());

                    ClassesDAO.DAO<Bien> bienDAO = DAOFactory.getBienDAO();

                    Boolean succes = bienDAO.creer(bien);
                    if (!succes) {
                        request.setAttribute("message", "Désolé, le bien n'a pas pu être créé\nVeuillez réessayer plus tard");
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                    request.setAttribute("message", "Désolé, le bien n'a pas pu être créé\nVeuillez réessayer plus tard");
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    request.setAttribute("message", "Désolé, le bien n'a pas pu être créé\nVeuillez réessayer plus tard");
                } catch (ExceptionDAO e) {
                    request.setAttribute("message", "Désolé, le bien n'a pas pu être créé\nVeuillez réessayer plus tard");
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("message", "Désolé, le bien n'a pas pu être créé\nVeuillez réessayer plus tard");
                }

                request.getRequestDispatcher("aiguillage?cmd=AdminBiens").forward(request, response);
            }
        }
        return "pageCreerUnBien.jsp";
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
