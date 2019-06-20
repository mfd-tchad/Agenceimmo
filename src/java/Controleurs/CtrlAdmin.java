/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import Beans.Bien;
import Beans.Utilisateur;
import Beans.Utilitaires;
import ClassesDAO.DAOFactory;
import ClassesDAO.UtilisateurDAO;
import Formulaires.FormulaireConnexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Stagiaire
 */
@WebServlet(name = "CtrlAdmin", urlPatterns = {"/CtrlAdmin"})
public class CtrlAdmin extends HttpServlet implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if ((session.getAttribute("user") != null) && (session.getAttribute("statutConnexion") != null)) {
            System.out.println("Nième passage dans CtrlAdmin ---------------------------------------");
            Boolean statutConnexion = (Boolean) session.getAttribute("statutConnexion");
            if (statutConnexion) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setNom((String) (session.getAttribute("user")));
                System.out.println("Utilisateur déjà enregistré :" + utilisateur.getNom());

                request.getRequestDispatcher("aiguillage?cmd=AdminBiens").forward(request, response);
            }
        } else if (!request.getParameterMap().containsKey("identifiant")) {
            System.out.println("Premier passage dans CtrlAdmin *********************************");
            return "login.jsp";
        } else {

            System.out.println("Autre passage dans CtrlAdmin ---------------------------------------");
            if (request.getParameterMap().containsKey("reset")) {
                request.getRequestDispatcher("aiguillage?cmd=VoirPageAccueil").forward(request, response);
            } else {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setIdentifiant(request.getParameter("identifiant"));
                String mdp = Utilitaires.getSHA(request.getParameter("motdepasse"));
                utilisateur.setMotDePasse(mdp);
                ClassesDAO.DAO<Utilisateur> utilisateurDAO = DAOFactory.getUtilisateurDAO();
                Boolean estConnecte = ((UtilisateurDAO) utilisateurDAO).connecterSiAdmin(utilisateur);
                if (estConnecte) {
                    // HttpSession session = request.getSession();
                    session.setAttribute("user", utilisateur.getNom());
                    session.setAttribute("statutConnexion", estConnecte);
                    System.out.println("Utilisateur connecté :" + utilisateur.getNom() + " enregistré dans request");
                    request.getRequestDispatcher("aiguillage?cmd=AdminBiens").forward(request, response);
                } else {
                    request.setAttribute("resultatConnect", utilisateur.getResultatConnect());
                    return "login.jsp";
                }
            }
        }
        return "login.jsp";
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
        execute(request, response);
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
