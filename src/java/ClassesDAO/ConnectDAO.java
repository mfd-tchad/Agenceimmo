package ClassesDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectDAO {

    private static Connection connect;

    private ConnectDAO() throws ExceptionDAO {
        try {

            final Properties prop = new Properties();
            File fichier = new File("E:\\AgenceImmo\\src\\java\\Config\\properties.properties");
            if (!fichier.exists()) {
                throw new ExceptionDAO("le fichier de configuration est absent");
            } else {
                FileInputStream fis = new FileInputStream(fichier);
                prop.load(fis);
                Class.forName(prop.getProperty("db.driver"));

                connect = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.passwd", ""));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Pb driver base de données");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Pb lecture du fichier de configuration");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Pb connexion base de données");
         } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Pb connexion base de données");
         }
    }

    public static Connection getInstance() throws ExceptionDAO {
        if (connect == null) {
            new ConnectDAO();
        }
        return connect;
    }

}
