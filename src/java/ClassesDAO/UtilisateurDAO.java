package ClassesDAO;

import Beans.Bien;
import Beans.Filtre;
import Beans.Utilisateur;
import Beans.Ville;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilisateurDAO extends DAO<Utilisateur> {

    final String ADMINISTRATEUR = "ADMIN";

    public UtilisateurDAO(Connection connect) {
        super(connect);
    }

    public HashSet<Utilisateur> chercher(Filtre filtre) {
        return null;
    }

    private Utilisateur hydrate(ResultSet resultat) throws SQLException, Exception {
        Utilisateur utilisateur = new Utilisateur();
        try {
            utilisateur.setNom(resultat.getString("nom"));
            System.out.println("utilisateur : " + utilisateur.getNom() + ", identifiant : " + resultat.getString("IDENTIFIANTUTIL"));

        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return utilisateur;
    }

    public boolean creer(Utilisateur utilisateur) throws ExceptionDAO {
        String query;
        PreparedStatement prepare = null;
        try {
            System.out.println("Entrée dans creer ------------");
            // On vérifie d'abord si l'utilisateur existe
            if (recupererUn(utilisateur)!= null ) {
                // throw new ExceptionDAO("Création non effectuée, l'utilisateur existe déjà");
                System.out.println("Création non effectuée, l'utilisateur existe déjà");
                return false;
            }
            query = "INSERT IGNORE INTO utilisateur (IDENTIFIANTUTIL, nom, MOTDEPASSE, ADRESSEMAIL, ROLE) values (?,?,?,?,?)";
            prepare = connect.prepareStatement(query);
            if (!utilisateur.getIdentifiant().isEmpty() && !utilisateur.getNom().isEmpty() && !utilisateur.getMotDePasse().isEmpty()
                    && !utilisateur.getAdresseMail().isEmpty() && !utilisateur.getRole().isEmpty()) {
                
                prepare.setString(1, utilisateur.getIdentifiant());
                prepare.setString(2, utilisateur.getNom());
                prepare.setString(3, utilisateur.getMotDePasse());
                prepare.setString(4, utilisateur.getAdresseMail());
                prepare.setString(5, utilisateur.getRole());
                System.out.println("Exécution de la requête de création utilisateur " + utilisateur.getIdentifiant());
                int nbLignes = prepare.executeUpdate();
                if (nbLignes < 1) {
                    System.out.println("Echec de la création de l'utilisateur" + utilisateur.getIdentifiant());
                    throw new ExceptionDAO("Echec à la création de l'utilisateur" );
                } else {
                    System.out.println("Utilisateur " + utilisateur.getIdentifiant() + " créé avec succès");
                    return (true);
                }
            } else {
                throw new ExceptionDAO("Création d'utilisateur impossible");
            }
        } catch (SQLException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            throw new ExceptionDAO("Création d'utilisateur impossible");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de création d'un nouvel utilisateur dans la base");
        } finally {
            if (prepare != null) {
                try {
                    prepare.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
                    throw new ExceptionDAO("Pb de communication avec la base de données");
                }
            }
           
        }

    }

    public boolean modifier(Utilisateur utilisateur) {
        return true;
    }

    public boolean supprimer(Utilisateur utilisateur) throws ExceptionDAO {
        String query;
        PreparedStatement prepare = null;
        try {
            System.out.println("Entrée dans supprimer ------------");
            query = "DELETE FROM utilisateur WHERE IDENTIFIANTUTIL = ?";
            prepare = connect.prepareStatement(query);
            if (!utilisateur.getIdentifiant().isEmpty()) {
                System.out.println("entrée dans préparation de requête xxxxxxxxxxxxxxxxxxxxxxx");
                prepare.setString(1, utilisateur.getIdentifiant());
                System.out.println("Exécution de la requête de suppression utilisateur " + utilisateur.getIdentifiant());
                int nbLignes = prepare.executeUpdate();
                if (nbLignes < 1) {
                    System.out.println("Echec de la suppression de l'utilisateur " + utilisateur.getIdentifiant());
                    // throw new ExceptionDAO("Echec à la suppression de l'utilisateur");
                } else {
                    System.out.println("Utilisateur " + utilisateur.getIdentifiant() + " supprimé avec succés");
                    return (true);
                }
            } else {
                throw new ExceptionDAO("Suppression d'utilisateur impossible");
            }
        } catch (SQLException e) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            throw new ExceptionDAO("Suppression utilisateur impossible");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de suppression d'un utilisateur");
        } finally {
            if (prepare != null) {
                try {
                    prepare.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
                    throw new ExceptionDAO("Pb de communication avec la base de données");
                }
            }
        }
        return false;

    }


    public HashSet<Utilisateur> recupererTout() {
        Utilisateur utilisateur = null;
        HashSet<Utilisateur> setUtilisateur = new HashSet<>();
        try {
            Statement state = connect.createStatement();
            ResultSet resultat = state.executeQuery("SELECT * FROM utilisateur");
            ResultSetMetaData resultatMeta = resultat.getMetaData();

            System.out.println("\n**********************************************************");
            for (int i = 1; i <= resultatMeta.getColumnCount(); i++) {
                System.out.print("  *  " + resultatMeta.getColumnName(i).toUpperCase() + " * ");
            }
            System.out.println("\n**********************************************************");

            while (resultat.next()) {
                utilisateur = hydrate(resultat);
                setUtilisateur.add(utilisateur);
            }
            resultat.close();
            state.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de communication avec la base de données");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de communication avec la base de données");
        }

        return setUtilisateur;
    }


    public Utilisateur recupererUn(int id) {
        return null;
    }

    public Utilisateur recupererUn(Utilisateur filtre) throws ExceptionDAO {
        Utilisateur utilisateur = null;
         PreparedStatement prepare = null;
        try {
            String query = "SELECT * FROM utilisateur WHERE utilisateur.IDENTIFIANTUTIL = ? AND utilisateur.MOTDEPASSE = ?";
            prepare = connect.prepareStatement(query);
            prepare.setString(1,filtre.getIdentifiant());
            prepare.setString(2,filtre.getMotDePasse());
            ResultSet resultat = prepare.executeQuery();
            ResultSetMetaData resultatMeta = resultat.getMetaData();

            System.out.println("\n**********************************************************");
            for (int i = 1; i <= resultatMeta.getColumnCount(); i++) {
                System.out.print("  *  " + resultatMeta.getColumnName(i).toUpperCase() + " * ");
            }
            System.out.println("\n**********************************************************");

            if (resultat.next()) {
                utilisateur = hydrate(resultat);
            }
            resultat.close();
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de communication avec la base de données");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de communication avec la base de données");
        }

        return utilisateur;
    }

    public boolean connecterSiAdmin(Utilisateur user) throws ExceptionDAO {

        Boolean estConnecte = false;
        try {
            String query = "SELECT * FROM utilisateur WHERE utilisateur.IDENTIFIANTUTIL = ? AND utilisateur.MOTDEPASSE = ?";
            PreparedStatement prepare = connect.prepareStatement(query);
            prepare.setString(1, user.getIdentifiant());
            prepare.setString(2, user.getMotDePasse());
            ResultSet resultat = prepare.executeQuery();
            ResultSetMetaData resultatMeta = resultat.getMetaData();
            if (resultat.next()) {
                if (resultat.getString("ROLE").equals(ADMINISTRATEUR)) {
                    estConnecte = true;
                    user.setNom(resultat.getString("nom"));
                    user.setEstConnecte(true);
                    System.out.println("Connexion réussie en tant qu'andministrateur");
                } else {
                    user.setResultatConnect("Utilisateur non autorisé");
                    user.setEstConnecte(false);
                    System.out.println("Utilisateur non autorisé ");
                }
            } else {
                user.setResultatConnect("Identifiant ou mot de passe incorrect");
                System.out.println("Utilisateur ou mot de passe incorrect");
            }
            prepare.close();
            resultat.close();
        } catch (MySQLSyntaxErrorException e) {
            user.setResultatConnect("Problème d'interrogation de la base de données");
            System.out.println("Erreur de syntaxe dans la requete SQL");
            e.printStackTrace();
            throw new ExceptionDAO("Problème de communication avec la base de données");
        } catch (SQLException e) {
            user.setResultatConnect("Problème de connexion à la base de données");
            e.printStackTrace();
            throw new ExceptionDAO("Problème de communication avec la base de données");
        } catch (Exception e) {
            user.setResultatConnect("Problème à la connexion");
            e.printStackTrace();
            throw new ExceptionDAO("Problème de communication avec la base de données");
        }
        return estConnecte;

    }

    boolean deconnecter(String nomUtilisateur) throws ExceptionDAO {
        boolean estConnecte = true;
        try {
            String query = "SELECT idUtilisateur, nom FROM utilisateur WHERE utilisateur.nom = ?";
            PreparedStatement prepare = connect.prepareStatement(query);
            prepare.setString(1, nomUtilisateur);

            ResultSet resultat = prepare.executeQuery();
            if (resultat.next()) {
                estConnecte = false;
            }
        } catch (MySQLSyntaxErrorException e) {
            System.out.println("Erreur de syntaxe dans la requete SQL");
            e.printStackTrace();
            throw new ExceptionDAO("Problème de communication avec la base de données");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de communication avec la base de données");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionDAO("Problème de communication avec la base de données");
        }
        return estConnecte;

    }

}
