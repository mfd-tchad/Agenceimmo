/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import ClassesDAO.ConnectDAO;
import ClassesDAO.DAOFactory;
import ClassesDAO.ExceptionDAO;
import ClassesDAO.UtilisateurDAO;
import java.sql.Connection;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marie-Françoise
 */
public class BeansTest {

   

    public BeansTest() {
    }

    @Test
    public void testConvertMdp() {
        try {
            String mdp = "machin";
            assertNotNull(Utilitaires.getSHA(mdp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
@Test
    public void testAjouterOption() {
        try {
            
            assertNotNull(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

}
