package ClassesDAO;

import Beans.Filtre;
import Beans.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class OptionDAO extends DAO<Option> {

    public OptionDAO(Connection connect) {
        super(connect);
    }

    public Option hydrate(ResultSet resultat) throws ExceptionDAO {
        Option option = new Option();
        try {
            option.setId(resultat.getInt("idOption"));
            option.setNomOption(resultat.getString("nomOption"));
            System.out.println("Option du bien : " + option.getNomOption());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération d'une option");
        }
        return option;
    }

    public HashSet<Option> chercher(Filtre filtre) {
        return null;
    }

    public boolean creer(Option option) {
        return true;
    }

    public boolean modifier(Option option) {
        return true;
    }

    public boolean supprimer(Option option) {
        return true;
    }

    public HashSet<Option> recupererTout() throws ExceptionDAO {
        return recupererOptionsdUnBien(0);
    }

    
    public HashSet<Option> recupererOptionsdUnBien(int idBien) throws ExceptionDAO {
        Option option = new Option();
        HashSet<Option> setOptions = new HashSet<>();
        try {
            OptionDAO optionDAO = new OptionDAO(connect);
            String query = "SELECT * FROM  option";
            if (idBien >0)
                query += " INNER JOIN apouroptions ON apouroptions.idOption = option.idOption WHERE apouroptions.idBien = ?";
            PreparedStatement prepare = connect.prepareStatement(query);
            if (idBien >0) {
                prepare.setInt(1, new Integer(idBien));
            }
            ResultSet resultat = prepare.executeQuery();

            while (resultat.next()) {
                option = hydrate(resultat);
                setOptions.add(option);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération des options du bien");
            
        }    catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération des options du bien");
        }
        return setOptions;
    }

    public Option recupererUn(int id) {
        return null;
    }

    public Option recupererUn(Option no) {
        return null;
    }
}
