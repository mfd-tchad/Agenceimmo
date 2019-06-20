package ClassesDAO;

import Beans.Bien;
import Beans.Departement;
import Beans.Filtre;
import Beans.Ville;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class VilleDAO extends DAO<Ville> {

    public VilleDAO(Connection connect) {
        super(connect);
    }

    public Ville hydrate(ResultSet resultat) throws ExceptionDAO {
        Ville ville = new Ville();
        try {

            ville.setIdVille(resultat.getInt("idVille"));
            ville.setCodePostal(resultat.getInt("codePostal"));
            ville.setNomVille(resultat.getString("nomVille"));
            ville.setDepartement(resultat.getInt("no"));
            System.out.println("Ville : " + ville.getCodePostal() + " " + ville.getNomVille());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération d'une ville");
        }
        return ville;
    }

    public HashSet<Ville> chercher(Filtre filtre) {
        Ville ville = new Ville();
        HashSet<Ville> listeVilles = new HashSet<>();
        try {
            String query = "SELECT * FROM  ville WHERE no = ?";
            PreparedStatement prepare = connect.prepareStatement(query);
            ResultSet resultat = prepare.executeQuery();

            while (resultat.next()) {
                ville = hydrate(resultat);
                listeVilles.add(ville);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération de la liste des villes du département");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération de la liste des villes du département");
        }
        return listeVilles;
    }

    public ArrayList<Ville> recupererVillesDunDepartement(int no) throws ExceptionDAO {
        Ville ville = new Ville();
        ArrayList<Ville> listeVilles = new ArrayList<>();
        try {
            String query = "SELECT * FROM  ville WHERE no = ? ORDER BY idVille";
            PreparedStatement prepare = connect.prepareStatement(query);
            prepare.setInt(1, no);
            ResultSet resultat = prepare.executeQuery();
            while (resultat.next()) {
                ville = hydrate(resultat);
                listeVilles.add(ville);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération de la liste des villes du département");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération de la liste des villes du département");
        }
        return listeVilles;

    }

    public boolean creer(Ville ville) {
        return true;
    }

    public boolean modifier(Ville ville) {
        return true;
    }

    public boolean supprimer(Ville ville) {
        return true;
    }

    public HashSet<Ville> recupererTout() {
        return null;
    }

    public Ville recupererUn(int no) {
        return null;
    }

    public Ville recupererUn(Ville filtre) {
        return null;
    }
}
