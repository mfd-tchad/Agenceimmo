<%-- 
    Document   : login
    Created on : 16 mai 2019, 11:42:20
    Author     : Stagiaire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <%@ include file="head.jsp" %>
    </head>
    <body>
        <h3 class="mt-4">Veuillez vous identifier pour accéder à la gestion des biens</h3>
        <c:if test="${ !empty resultatConnect }">
            <p> ${ resultatConnect } </p>
        </c:if>
        <div class="row">
            <div class="col-md-4"></div>
            <main class="card mt-4 col-md-4">
                <form method="post">
                    <div class="card-body">

                        <section class="row">

                            <div class="form-group col-md-12">

                                <label for="identifiant">Identifiant : </label>
                                <input class="form-check" type="text" name="identifiant" id="identifiant" required/>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="motdepasse">Mot de passe : </label>
                                <input class="form-check" type="password" name="motdepasse" id="motdepasse" required />
                            </div>
                        </section>          
                    </div>
                    <div class="card-footer">
                        <input class="btn btn-success w-25 p-2" type="submit" name="submit" value="Valider" />
                        <a href="aiguillage?cmd=VoirPageAccueil" class="btn btn-secondary p-2 w-25 float-right">Annuler</a>
                    </div>
                </form>
            </main>
            
        </div>
    </body>
</body>
</html>
