
package Formulaires;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Stagiaire
 */
public class FormulaireConnexion {
    private String identifiant;

    private String motDePasse;

    private String resultat;
    
    private boolean estConnecte;

    public FormulaireConnexion() {
        this.estConnecte = false;
        this.motDePasse = null;
        this.identifiant = null;
        this.resultat = "";
    }
    
    
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public boolean isEstConnecte() {
        return estConnecte;
    }

    public void setEstConnecte(boolean estConnecte) {
        this.estConnecte = estConnecte;
    }
    
    public void verifierIdentifiants(HttpServletRequest request){
        String login = request.getParameter("identifiant");
        String motDePasse = request.getParameter("motdepasse");
        
        if (motDePasse.equals(login + "5345")) {
            resultat = "Vous êtes bien connecté !";
            estConnecte = true;
            this.identifiant = login;
        }
        else {
            resultat = "Identifiants incorrects";
            estConnecte = false;
        }
    }
}
