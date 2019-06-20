/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import Beans.Bien;
import ClassesDAO.DAOFactory;
import ClassesDAO.ExceptionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marie-Françoise
 */
@WebServlet(name = "CtrlPageUnBien", urlPatterns = {"/CtrlPageUnBien"})
public class CtrlPageUnBien extends HttpServlet implements ICommand {

     public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ClassesDAO.DAO<Bien> bienDAO = DAOFactory.getBienDAO();
            
            System.out.println("CtrlPageUnBien BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
            
                Bien bien = new Bien();
                //int idBien = (int)request.getAttribute("id");
                int idBien = Integer.parseInt(request.getParameter("idBienSelectionne"));
                System.out.println("id du bien sélectionné : " + idBien);
                bien = bienDAO.recupererUn(idBien);
                System.out.println("\t" + bien.getIdentifiant() + "  |  " + bien.getNom() + "  |  " + bien.getVille().getNomVille() + "  |  " + bien.getSurface() + "  |  " + bien.getNbPieces() + 
                        "  |  " + bien.getNbChambres() + "  |  " + bien.getEtage() + "  |  " + bien.getPrix() + "  |  " + bien.getTypeChauffage());
            
            request.setAttribute("lebien", bien);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            request.setAttribute("message", "Désolé, les informations sur le bien en vente n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("message", "Désolé, les informations sur le bien en vente n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
        }
        catch (ExceptionDAO e) {
            request.setAttribute("message", "Désolé, les informations sur le bien en vente n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
        }
        catch (Exception e) {
            e.printStackTrace(); 
            request.setAttribute("message", "Désolé, les informations sur le bien en vente n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
        }
        return "pageUnBien.jsp";
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
