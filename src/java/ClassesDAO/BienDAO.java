package ClassesDAO;

import Beans.Filtre;
import Beans.Bien;
import Beans.Img;
import Beans.Option;
import Beans.Ville;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;

public class BienDAO extends DAO<Bien> {

    public BienDAO(Connection connect) {
        super(connect);
    }

    public HashSet<Bien> chercher(Filtre filtre) {
        return null;
    }

    /*
  public HashSet<Bien> chercher(Bien filtre) {
      return null;
  }
     */
    public boolean creer(Bien bien) {
        return true;
    }

    public boolean modifier(Bien bien) {
        return true;
    }

    public boolean supprimer(Bien bien) {
        return true;
    }

    public HashSet<Bien> recupererTout() throws ExceptionDAO {
        return recupererNDerniers(0);
    }

    private Bien hydrate(ResultSet resultat) throws ExceptionDAO {
        Bien bien = new Bien();
        Ville ville = new Ville();
        try {
            bien.setIdentifiant(resultat.getInt("idBien"));
            bien.setTypeBien(resultat.getString("TYPEBIEN"));
            bien.setNom(resultat.getString("nom"));
            bien.setSurface(resultat.getInt("SURFACE"));
            bien.setNbPieces(resultat.getInt("NBPIECES"));
            bien.setNbChambres(resultat.getInt("NBCHAMBRES"));
            bien.setEtage(resultat.getInt("ETAGE"));
            bien.setPrix(resultat.getInt("PRIX"));
            bien.setTypeChauffage(resultat.getString("TYPECHAUFFAGE"));
            bien.setAdresse(resultat.getString("ADRESSE"));
            bien.setDateCreation(resultat.getDate("DATECREATION"));
            bien.setVendu(resultat.getBoolean("VENDU"));
            if (bien.getVendu()) {
                bien.setDateVente(resultat.getDate("DATEVENTE"));
            } else {
                bien.setDateVente(null);
            }
            //bien.setVille(resultat.getString(nomVille));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération des données depuis la base");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération des données depuis la base");
        }
        return bien;
    }

    public HashSet<Bien> recupererNDerniers(int nbBiens) throws ExceptionDAO {
        HashSet<Bien> setBiens = new HashSet<>();
       
        Img photo = null;
        Bien bien = null;
        Ville ville = null;
        VilleDAO villeDAO = new VilleDAO(connect);
        String query;
        System.out.println("RecupererNDerniersBiens *********************************************");
        try {
            query = "SELECT * FROM bien INNER JOIN ville ON bien.idVille=ville.idVille ORDER BY DATECREATION DESC";
            if (nbBiens > 0) {
                query += " LIMIT ?";
            }

            PreparedStatement prepare = connect.prepareStatement(query);

            if (nbBiens > 0) {
                prepare.setInt(1, nbBiens);
            }
            ResultSet resultat = prepare.executeQuery();

            System.out.println("\n**********************************************************");

            while (resultat.next()) {
                bien = hydrate(resultat);
                ville = new Ville();
                ville = villeDAO.hydrate(resultat);
                bien.setVille(ville);
                ClassesDAO.DAO<Img> imgDAO = DAOFactory.getImgDAO();
                
                photo = ((ImgDAO) imgDAO).recupererUn(bien.getIdentifiant());
                if (photo != null) {
                    bien.addPhoto(photo);
                }
                setBiens.add(bien);
            }
            resultat.close();
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Pb d'interrogation de la base de données");
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Pb récupération des données dans la base");
        } catch (ExceptionDAO e) {
            if (bien!= null)
                throw new ExceptionDAO(e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération des données depuis la base");
        }
        return setBiens;
    }

    public Bien recupererUn(int identifiant) throws ExceptionDAO {
        Bien bien = null;
        Ville ville;
        VilleDAO villeDAO = new VilleDAO(connect);
        HashSet<Option> setOptions;

        HashSet<Img> setPhotos;
        String query;
        try {
            if (identifiant < 0) {
                throw new ExceptionDAO("bien non identifié");
            } else {

                query = "SELECT * FROM bien INNER JOIN ville ON bien.idVille=ville.idVille AND bien.idBien = ? ";

                PreparedStatement prepare = connect.prepareStatement(query);

                prepare.setInt(1, identifiant);
                ResultSet resultat = prepare.executeQuery();

                System.out.println("\n**********************************************************");

                if (resultat.next()) {
                    bien = hydrate(resultat);
                    ville = new Ville();
                    ville = villeDAO.hydrate(resultat);
                    bien.setVille(ville);
                    // récupérer les options du bien
                    ClassesDAO.DAO<Option> optionDAO = DAOFactory.getOptionDAO();
                    setOptions = ((OptionDAO) optionDAO).recupererOptionsdUnBien(bien.getIdentifiant());
                    bien.setListeOptions(setOptions);
                    ClassesDAO.DAO<Img> imgDAO = DAOFactory.getImgDAO();
                    setPhotos = ((ImgDAO) imgDAO).recupererPhotosdUnBien(bien.getIdentifiant());
                    bien.setListePhotos(setPhotos);
                }
                System.out.println("\n**********************************************************");
                resultat.close();
                prepare.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération des informations du bien");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération des informations du bien");
        }

        return bien;
    }

    public Bien recupererUn(Bien filtre) {
        return null;
    }
}
