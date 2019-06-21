/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDAO;

import Beans.Filtre;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Stagiaire
 */
public abstract class DAO<T> {
    
    protected Connection connect = null;

    public DAO(Connection connect) {
        this.connect = connect;
    }
    
    public abstract boolean creer(T obj) throws ExceptionDAO;
    
    /**
     *
     * @param filtre
     * @return
     */
    public abstract HashSet<T> chercher(Filtre filtre) throws ExceptionDAO;
    
  //  public abstract HashSet<T> chercher(T filtre);
    
    public abstract boolean modifier(T obj) throws ExceptionDAO ;
    
    public abstract boolean supprimer(T obj) throws ExceptionDAO;
    
    public abstract HashSet<T> recupererTout() throws ExceptionDAO;
    
    public abstract ArrayList<T> recupererToutTrie() throws ExceptionDAO;
    
    public abstract T recupererUn(T filtre) throws ExceptionDAO;
    
    public abstract T recupererUn(int id) throws ExceptionDAO;
}