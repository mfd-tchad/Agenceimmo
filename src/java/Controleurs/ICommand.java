/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stagiaire
 */
public interface ICommand extends java.io.Serializable, java.lang.Cloneable {
    
    String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
