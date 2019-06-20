<%-- 
    Document   : admin
    Created on : 15 mai 2019, 16:05:48
    Author     : Stagiaire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <c:if test="${ !empty sessionScope.nom && !empty sessionScope.prenom}">
            <p> Bienvenue ${ sessionScope.prenom } ${ sessionScope.nom } ! </p>
        </c:if>
        <form method="post">
            <p>
                <label for="nom">Nom : </label>
                <input type="text" name="nom" id="nom" />
            </p>
            <p>
                <label for="prenom">Prénom : </label>
                <input type="text" name="prenom" id="prenom" />
            </p>
            <input type="submit" />
        </form>
    </body>
</html>
