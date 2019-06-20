package ClassesDAO;

import Beans.Departement;
import Beans.Filtre;
import Beans.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class DepartementDAO extends DAO<Departement> {

    public DepartementDAO(Connection connect) {
        super(connect);
    }

    public Departement hydrate(ResultSet resultat) throws ExceptionDAO {
        Departement departement = new Departement();
        try {
            departement.setNo(resultat.getInt("no"));
            departement.setNomDepartement(resultat.getString("nomDepartement"));
            System.out.println("Departement : " + departement.getNomDepartement());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération d'un département");
        }
        return departement;
    }

    public boolean creer(Departement departement) {
        return true;
    }

    public boolean modifier(Departement departement) {
        return true;
    }

    public boolean supprimer(Departement departement) {
        return true;
    }

    public HashSet<Departement> chercher(Filtre filtre) {
        return null;
    }
    
    
    public ArrayList<Departement> recupererToutTrie() {
        Departement departement = new Departement();
        ArrayList<Departement> listeDepartements = new ArrayList<>();
        try {
            String query = "SELECT * FROM  departement ORDER BY no";
            PreparedStatement prepare = connect.prepareStatement(query);
            ResultSet resultat = prepare.executeQuery();

            while (resultat.next()) {
                departement = hydrate(resultat);
                listeDepartements.add(departement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération de la liste des départements");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération de la liste des départements");
        }
        return listeDepartements;
    }

    public HashSet<Departement> recupererTout() {
        Departement departement = new Departement();
        HashSet<Departement> setDepartements = new HashSet<>();
        try {
            //DepartementDAO departementDAO = new DepartementDAO(connect);
            String query = "SELECT * FROM  departement ORDER BY no";
            PreparedStatement prepare = connect.prepareStatement(query);
            ResultSet resultat = prepare.executeQuery();

            while (resultat.next()) {
                departement = hydrate(resultat);
                setDepartements.add(departement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération de la liste des départements");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération de la liste des départements");
        }
        return setDepartements;
    }

    public Departement recupererUn(int no) {
        return null;
    }

    public Departement recupererUn(Departement filtre) {
        return null;
    }
}
