<%-- 
    Document   : index
    Created on : 3 mai 2019, 12:12:22
    Author     : Stagiaire
--%>
<%@page import="Beans.Bien"%>
<%@page import="java.util.HashSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Accueil</title>
        <%@ include file="head.jsp" %>
    </head>
    <body>
        <%--
       <section id="slider" class="carousel slide" data-ride="carousel" data-interval="3000">
           <div class="carousel-inner">
               <div class="carousel-item active">
                   <img class="d-block w-100 h-20 img-fluid" src="./images/2019/02/001.jpg" alt="photo1">
               </div>
               <div class="carousel-item">
                   <img class="d-block w-100 img-fluid" src="./images/2018/02/001.jpg" alt="Second slide">
               </div>
               <div class="carousel-item">
                   <img class="d-block w-100 img-fluid" src="./images/2018/04/001.jpg" alt="CASA TOSCANA">
               </div>
               <div class="carousel-item">
                   <img class="d-block w-100 img-fluid" src="./images/2018/05/001.jpg" alt="CASA TOSCANA">
               </div>
           </div>
       </section>
        --%>

        <section id="slider" class="container">
            <img src="./images/2019/02/001.jpg" class="slider w-100" alt="photo1" height="350px" align="center"/>
            <img src="./images/2018/02/001.jpg" alt="photo2" class="slider w-100" height="350px"/>
            <img src="./images/2018/04/001.jpg" alt="CASA TOSCANA" class="slider w-100" height="350px"/>
            <img src="./images/2018/05/001.jpg" alt="photo4" class="slider w-100" height="350px">
        </section>

        <%@ include file="menu.jsp" %>

        <h1 class="mt-4">Les derniers biens mis en vente</h1>

        <section class="container">
            <c:if test="${ !empty requestScope.message }">
                <div class="text-center"> ${ requestScope.message } </div>
            </c:if>
            <div class="row">
                <c:forEach items = "${ requestScope.lesbiens }" var ="lebien" begin = "0">
                    <div class="col-md-4">
                        <div class="card mb-2 border-info">
                            <div class="card-header"> 
                                <h4> ${lebien.nom} </h4> 
                            </div>
                            <div class="card-body" >
                                <p class="card-text"> ${lebien.surface} m2 à ${lebien.ville.nomVille}</p>
                                <p class="card-text" style="font-size: 1.2rem; font-weight: bold;"> ${lebien.prix} € </p>
                                <p class="card-text" > mis en vente le ${lebien.dateCreation} </p>
                                <div class="card-text">
                                    <%-- <c:if test=" !empty ${lebien.listePhotos}"> --%>
                                    <c:forEach items= "${ lebien.listePhotos }" var ="laphoto" begin = "0" end = "0">

                                        <img src="./images/${laphoto.cheminFichier}" height="150" width="150" alt="${laphoto.cheminFichier}"/> 

                                    </c:forEach>
                                    <%-- </c:if>  --%>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </section>
        <%@ include file="footer.jsp" %>
    </body>

</html>
