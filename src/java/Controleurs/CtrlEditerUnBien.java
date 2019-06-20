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
@WebServlet(name = "CtrlEditerUnBien", urlPatterns = {"/CtrlEditerUnBien"})
public class CtrlEditerUnBien extends HttpServlet implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameterMap().containsKey("nom")) {
            System.out.println("Premier passage dans CtrlEditerUnBien *********************************");

            try {
                ClassesDAO.DAO<Bien> bienDAO = DAOFactory.getBienDAO();

                System.out.println("CtrlPageEditerUnBien BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");

                Bien bien = new Bien();
                int idBien = Integer.parseInt(request.getParameter("idBienSelectionne"));
                System.out.println("id du bien sélectionné : " + idBien);
                bien = bienDAO.recupererUn(idBien);
                System.out.println("\t" + bien.getIdentifiant() + "  |  " + bien.getNom() + "  |  " + bien.getVille().getNomVille() + "  |  " + bien.getSurface() + "  |  " + bien.getNbPieces()
                        + "  |  " + bien.getNbChambres() + "  |  " + bien.getEtage() + "  |  " + bien.getPrix() + "  |  " + bien.getTypeChauffage());

                request.setAttribute("lebien", bien);

                // récupérer la liste complète des options
                ClassesDAO.DAO<Option> optionDAO = DAOFactory.getOptionDAO();

                System.out.println("Récupération de la liste des options BBBBBB");

                HashSet<Option> setOptions = new HashSet<>();
                setOptions = optionDAO.recupererTout();
                request.setAttribute("lesoptions", setOptions);
            } catch (NullPointerException e) {
                e.printStackTrace();
                request.setAttribute("message", "Désolé, les informations sur le bien en vente n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("message", "Désolé, les informations sur le bien en vente n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
            } catch (ExceptionDAO e) {
                request.setAttribute("message", "Désolé, les informations sur le bien en vente ou les options n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Désolé, les informations sur le bien en vente n'ont pas pu être récupérées\nVeuillez réessayer plus tard");
            }
            return "pageEditerUnBien.jsp";
        } else {
            System.out.println("Autre passage dans CtrlAdminUnBien ---------------------------------------");
            // mettre à jour le bien
            request.getRequestDispatcher("aiguillage?cmd=AdminBiens").forward(request, response);
        }
        return "pageEditerUnBien.jsp";
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
