<%-- 
    Document   : pageEditerUnBien
    Created on : 20 mai 2019, 10:27:08
    Author     : Stagiaire
--%>

<%@page import="Beans.Bien"%>
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
        <h1>Gérez les biens en vente</h1>
        <c:if test="${ !empty requestScope.message }">
            <p style="align:center"> ${ requestScope.message } </p>
        </c:if>
        <div class="container mt-4">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th> Nom </th>
                        <th> Surface </th>
                        <th> Ville </th>
                        <th> Prix </th>
                        <th> date création </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items = "${ requestScope.lesbiens }" var ="lebien" begin = "0">
                        <tr>
                            <td>  ${lebien.nom}  </td>
                            <td>  ${lebien.surface}  </td>
                            <td>  ${lebien.ville.nomVille}  </td>
                            <td>  ${lebien.prix} €</td>
                            <td> ${lebien.dateCreation} </td>
                            <td> 
                                <a href="aiguillage?cmd=EditerUnBien&idBienSelectionne=${lebien.identifiant}" class="btn btn-secondary"> Editer </a>
                                <form  action="aiguillage?cmd=SupprimerUnBien&idBienSelectionne=${lebien.identifiant}" style="display:inline-block" method="POST" onsubmit="return confirm('Etes vous vraiment sûr ?')"> 
                                    <input type="hidden" name="_method" value="DELETE">

                                    <button class="btn btn-danger"> Supprimer </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="text-right">
                <a href="aiguillage?cmd=CreerUnBien" class="btn btn-primary">Mettre un nouveau bien en vente</a>
            </div>
        </div>

    </body>
</html>

