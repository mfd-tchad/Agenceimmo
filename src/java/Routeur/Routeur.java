/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Routeur;

import Controleurs.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stagiaire
 */
@WebServlet(name = "Routeur", urlPatterns = {"/aiguillage"})
public class Routeur extends HttpServlet {

    private Map commandes = new HashMap();

    @Override
    public void init() {
        System.out.println("Initialisation du Routeur.......*************************************");
        commandes.put("VoirPageAccueil", new CtrlPageAccueil());
        commandes.put("VoirTousLesBiens", new CtrlPageBiens());
        commandes.put("VoirUnBien", new CtrlPageUnBien());
        commandes.put("GererLesBiens", new CtrlAdmin());
        commandes.put("AdminBiens", new CtrlAdminBiens());
        commandes.put("EditerUnBien", new CtrlEditerUnBien());
        commandes.put("SupprimerUnBien", new CtrlSupprimerUnBien());
        commandes.put("CreerUnBien", new CtrlCreerUnBien());
        commandes.put("AdminOptions", new CtrlAdminOptions());
        commandes.put("Logout", new CtrlLogout());
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

        try {
            String cmdDemandee = request.getParameter("cmd");
            if (cmdDemandee == null) {
                cmdDemandee = "VoirPageAccueil";
            }
            System.out.println("Page :" + cmdDemandee + " appelée");
            ICommand commande = (ICommand) commandes.get(cmdDemandee);
            System.out.println("Commande :" + cmdDemandee + "------------------------------------------");
            String urlSuite = commande.execute(request, response);
            System.out.println("222222222222222222222222222222222222222222222222222222222222222222222");
            request.getRequestDispatcher(urlSuite).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur de routage");
        }
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
