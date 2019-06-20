package ClassesDAO;

import Beans.Filtre;
import Beans.Img;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class ImgDAO extends DAO<Img> {

    public ImgDAO(Connection connect) {
        super(connect);
    }

    public Img hydrate(ResultSet resultat) throws ExceptionDAO {
        Img photo = new Img();
        try {
            photo.setIdImg(resultat.getInt("idPhoto"));
            photo.setCheminFichier(resultat.getString("nomPhoto"));
            System.out.println("Photo du bien : " + photo.getCheminFichier());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération d'une photo");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération d'une photo");
        }
        return photo;
    }

    public HashSet<Img> chercher(Filtre filtre) {
        return null;
    }

    public boolean creer(Img photo) throws ExceptionDAO {
        try {
        ImgDAO imgDAO = new ImgDAO(connect);
        String requete = "INSERT INTO photo (nomPhoto) VALUES (?)";
            PreparedStatement prepare = connect.prepareStatement(requete);
            if (photo.getCheminFichier().isEmpty()) {
                throw new ExceptionDAO("Aucune photo trouvée, enregistrement impossible");
            }
            prepare.setString(1, photo.getCheminFichier());
            int nbLignes = prepare.executeUpdate();
            if (nbLignes == 1) {
                return true;
            }
         } catch (MySQLSyntaxErrorException e ) {
            e.printStackTrace();
            throw new ExceptionDAO("Impossible d'enregistrer la photo du bien");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Impossible d'enregistrer la photo du bien");
            
        }    catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Impossible d'enregistrer la photo du bien");
        }
        return true;
    }

    public boolean modifier(Img photo) {
        return true;
    }

    public boolean supprimer(Img photo) {
        return true;
    }

    public HashSet<Img> recupererTout() {
        return null;
    }

    public HashSet<Img> recupererPhotosdUnBien(int idBien) throws ExceptionDAO {
        Img photo = new Img();
        HashSet<Img> setPhotos = new HashSet<>();
        System.out.println("Récupération des photos du bien no " + idBien);
        try {
            ImgDAO imgDAO = new ImgDAO(connect);
            String query = "SELECT * FROM apourphotos INNER JOIN photo ON apourphotos.idPhoto = photo.idPhoto AND apourphotos.idBien = ?";
            PreparedStatement prepare = connect.prepareStatement(query);
            prepare.setInt(1, new Integer(idBien));
            ResultSet resultat = prepare.executeQuery();

            while (resultat.next()) {
                photo = hydrate(resultat);
                setPhotos.add(photo);
            }
        } catch (MySQLSyntaxErrorException e ) {
            e.printStackTrace();
            throw new ExceptionDAO("Impossible de récupérer les photos du bien");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Impossible de récupérer les photos du bien");
            
        }    catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de récupération des photos du bien");
        }
        return setPhotos;
    }

    public Img recupererUn(int idBien) {
        Img photo = null;
        HashSet<Img> setPhotos = new HashSet<>();
         System.out.println("Récupération d'une photo du bien no " + idBien);
        try {
            ImgDAO imgDAO = new ImgDAO(connect);
            String query = "SELECT * FROM apourphotos INNER JOIN photo ON apourphotos.idPhoto = photo.idPhoto AND apourphotos.idBien = ?";
            PreparedStatement prepare = connect.prepareStatement(query);
            prepare.setInt(1, new Integer(idBien));
            ResultSet resultat = prepare.executeQuery();

            if (resultat.next()) {
                photo = hydrate(resultat);
                //setPhotos.add(photo);
            }
            else 
                System.out.println("Pas de photo pour le bien");
        } catch (MySQLSyntaxErrorException e ) {
            e.printStackTrace();
             System.out.println("Problème dans la récupération d'une photo du bien");
            throw new ExceptionDAO("Problème dans la récupération d'une photo du bien");
           
        } catch (SQLException e) {
            e.printStackTrace();
             System.out.println("Problème dans la récupération d'une photo du bien");
            throw new ExceptionDAO("Problème dans la récupération d'une photo du bien");
            
        }    catch (Exception e) {
            e.printStackTrace();
             System.out.println("Problème dans la récupération d'une photo du bien");
            throw new ExceptionDAO("Problème dans la récupération d'une photo du bien");
        }   finally {
            return photo;
        }
    }

    public Img recupererUn(Img photo) {
        return null;
    }
}

