/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import Beans.Bien;
import Beans.Option;
import ClassesDAO.DAOFactory;
import ClassesDAO.ExceptionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marie-Françoise
 */
@WebServlet(name = "CtrlSupprimerUnBien", urlPatterns = {"/CtrlSupprimerUnBien"})
public class CtrlSupprimerUnBien extends HttpServlet implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameterMap().containsKey("modifie")) {
            System.out.println("Premier passage dans CtrlSupprimerUnBien *********************************");

            try {
                ClassesDAO.DAO<Bien> bienDAO = DAOFactory.getBienDAO();

                System.out.println("CtrlPageSupprimerUnBien BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");

                Bien bien = new Bien();
                int idBien = Integer.parseInt(request.getParameter("idBienSelectionne"));
                System.out.println("id du bien sélectionné : " + idBien);
                bien.setIdentifiant(idBien);
                Boolean succes = bienDAO.supprimer(bien);
                if (succes) {
                    System.out.println("\t" + bien.getIdentifiant() + "  supprimé avec succès");
                    request.setAttribute("message", "Le bien a été supprimé avec succès");
                }
                else {
                    System.out.println("Erreur inconnue de suppression du bien" + bien.getIdentifiant() );
                    request.setAttribute("message", "Erreur : Le bien n'a pas pu être supprimé");
                
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                request.setAttribute("message", "Désolé, le bien n'a pas pu être supprimé");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("message", "Désolé, le bien n'a pas pu être supprimé");
            } catch (ExceptionDAO e) {
                request.setAttribute("message", "Désolé, le bien n'a pas pu être supprimé");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Désolé, le bien n'a pas pu être supprimé");
            }

        } else {
            System.out.println("Autre passage dans CtrlSupprimerUnBien ---------------------------------------");
            // mettre à jour le bien

        }
        request.getRequestDispatcher("aiguillage?cmd=AdminBiens").forward(request, response);
        return "aiguillage?cmd=AdminBiens";
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
