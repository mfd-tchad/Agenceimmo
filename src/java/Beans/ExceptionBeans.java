/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import ClassesDAO.ConnectDAO;
import java.sql.Connection;

/**
 *
 * @author Marie-Françoise
 */
public class ExceptionBeans extends RuntimeException {
    
     public ExceptionBeans (String message) {
        super(message);
    }
     
}
