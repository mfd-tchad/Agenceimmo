<%-- 
    Document   : pageBiens
    Created on : 3 mai 2019, 12:12:22
    Author     : Stagiaire
--%>

<%@page import="Beans.Bien"%>
<%@page import="Beans.Img"%>
<%@page import="java.util.HashSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Biens</title>
        <%@ include file="head.jsp" %>
    </head>
    <body>
        <%@ include file="menu.jsp" %>

        <h1>Tous les biens immobiliers en vente</h1>
        <c:if test="${ !empty requestScope.message }">
            <p style="align:center"> ${ requestScope.message } </p>
        </c:if>
        <section class="container">
            <div class="row">
                <c:forEach items = "${ requestScope.lesbiens }" var ="lebien" begin = "0">

                    <div class="card mb-4 border-info col-md-4">
                        <div>
                            <h5 class="card-header text-center">

                                <a href = "aiguillage?cmd=VoirUnBien&idBienSelectionne=${lebien.identifiant}"> ${lebien.nom} </a>
                            </h5> 

                            <div class="card-body" >
                                <p class="card-text"> ${lebien.surface} m2 à ${lebien.ville.nomVille}</p>
                                <p class="card-text" style="font-size: 1.2rem; font-weight: bold;"> ${lebien.prix} € </p>
                                <p class="card-text" > mis en vente le ${lebien.dateCreation} </p>
                            </div>
                        </div>
                        <div class="card-text">
                            <%-- <c:if test=" !empty ${lebien.listePhotos}"> --%>
                            <c:forEach items= "${ lebien.listePhotos }" var ="laphoto" begin = "0" end = "0">

                                <img src="./images/${laphoto.cheminFichier}" height="150" width="150" alt="${laphoto.cheminFichier}"/> 

                            </c:forEach>
                            <%-- </c:if>  --%>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>

    </body>
</html>
