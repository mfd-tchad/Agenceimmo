<%-- 
    Document   : pageUnBien
    Created on : 24 mai 2019, 09:56:14
    Author     : Marie-Françoise
--%>

<%@page import="Beans.Bien"%>
<%@page import="Beans.Option"%>
<%@page import="Beans.Img"%>
<%@page import="java.util.HashSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>UnBien</title>
        <%@ include file="head.jsp" %>
    </head>
    <body>
        <%@ include file="menu.jsp" %>
        <h1>Toutes les infos sur un bien</h1>
        <c:if test="${ !empty requestScope.message }">
            <p> ${ requestScope.message } </p>
        </c:if>

        <main class="row container">
            <section class="col-md-8">
                <c:if test="${ !empty requestScope.lebien.listePhotos }">
                    <section class="card-text text-center"> 

                        <c:forEach items = "${ requestScope.lebien.listePhotos }" var ="laphoto" begin = "0" varStatus="status">

                            <img src="./images/${laphoto.cheminFichier}" height="300" width="300" alt="${laphoto.cheminFichier}"/> 
                        </c:forEach>

                    </section>
                </c:if>
            </section>
            <section class="col-md-4 card">

                <div class="card-title text-center bg-secondary">
                    <h4> ${ requestScope.lebien.nom } </h4> 
                </div>

                <div class="card-body">
                    <div class="element text-right"> Mis en vente le ${ requestScope.lebien.dateCreation } </div>
                    <p class="card-text"> ${lebien.typeBien} de ${lebien.surface} m2 de ${lebien.nbPieces} pièces dont ${lebien.nbChambres} chambres</p>
                    <c:if test="${ !empty requestScope.lebien.listeOptions }">
                        <section class="card-text"> 
                            <p> Avec : 
                                <c:forEach items = "${ requestScope.lebien.listeOptions }" var ="loption" begin = "0" varStatus="status">
                                    ${loption.nomOption}
                                    <c:if test=" ${status.count} < ${lebien.listeOptions.size()-1}" > <span>, </span></c:if>

                                </c:forEach>
                            </p>
                        </section>
                    </c:if>
                    <p class="card-text"> Etage : ${lebien.etage} </p>
                    <c:choose>
                        <c:when test="${lebien.etage} == 0 && ${lebien.typeBien != Maison}">
                            <p class="card-text"> Rez de chaussée </p>
                        </c:when>
                        <c:when test="${lebien.etage} == 1" >
                            <p class="card-text"> 1er étage </p>
                        </c:when>
                        <c:when test="${lebien.etage} > 1" >
                            <p class="card-text"> ${lebien.etage}ème étage </p>
                        </c:when>  
                    </c:choose>
                    <p class="card-text"> Chauffage : ${lebien.typeChauffage} </p>
                    <p class="card-text"> situé à : </p>
                    <p class="card-text"> ${lebien.adresse} </p>
                    <p class="card-text"> ${lebien.ville.codePostal} ${lebien.ville.nomVille}</p>
                    <div class="mt-2 text-justify" > Prix : ${lebien.prix} &euro;</div>

                </div>
            </section>
        </main>
    </body>
</html>
