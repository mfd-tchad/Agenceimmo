package ClassesDAO;

import java.sql.Connection;

public class DAOFactory {

    protected static Connection connexion;

    public static DAO getVilleDAO() throws ExceptionDAO {
        connexion = ConnectDAO.getInstance();
        return new VilleDAO(connexion);
    }

    public static DAO getDepartementDAO() {
        connexion = ConnectDAO.getInstance();
        return new DepartementDAO(connexion);
    }

    public static DAO getBienDAO() {
        connexion = ConnectDAO.getInstance();
        return new BienDAO(connexion);
    }

    public static DAO getOptionDAO() {
        connexion = ConnectDAO.getInstance();
        return new OptionDAO(connexion);
    }

    public static DAO getImgDAO() {
        connexion = ConnectDAO.getInstance();
        return new ImgDAO(connexion);
    }
    
    public static DAO getUtilisateurDAO() {
        connexion = ConnectDAO.getInstance();
        return new UtilisateurDAO(connexion);
    }

}
