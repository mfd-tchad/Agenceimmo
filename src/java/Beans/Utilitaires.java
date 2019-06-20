/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Marie-Françoise
 */
public class Utilitaires {
    public static String getSHA(String chaine) throws ExceptionBeans {
        try {
            // Appel de la méthode statique getInstance avec le SHA 256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // appel de la méthode digest pour faire le calcul sur une chaine et retourner un tableau de byte
            byte[] messageDigest = md.digest(chaine.getBytes());
            
            // Conversion du tableau de bytes en représentation de signes
            BigInteger no = new BigInteger (1, messageDigest);
            
            // Conversion du message digest en valeur héxadécimale
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            System.out.println("mdp codé :" + hashtext);
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Algorithme incorrecte pour le SHA-256" + e);
            throw new ExceptionBeans ("Problème interne de l'application");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionBeans ("Problème interne de l'application");
        }
    }
}
