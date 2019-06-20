/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.Bien;
import ClassesDAO.DAOFactory;
import Controleurs.CtrlPageBiens;
import Controleurs.ICommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stagiaire
 */
// public class ServletTest extends HttpServlet implements ICommand{
public class ServletTest {
    
    // public String execute(HttpServletRequest request, HttpServletResponse response) {
    public String execute() { 
        String retour = "OK";
        try {
            System.out.println("Entrée dans execute .......................................");
            ClassesDAO.DAO<Bien> bienDAO = DAOFactory.getBienDAO();
            assertNotNull(bienDAO);
        }
        catch (Exception e) {
            retour = "KO";  
        }
        return retour;
    }

    @Test
    public void testControleur() {
        String retour ;
        System.out.println("Entrée dans testControleur 11111111111111111111111111111111111");
        retour = execute();
        assertEquals("OK", retour);
    }
    
}
   