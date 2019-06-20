<%-- 
    Document   : ville
    Created on : 20 juin 2019, 08:14:23
    Author     : Marie-Françoise
--%>

<%@page import="ClassesDAO.VilleDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ClassesDAO.DAOFactory"%>
<%@page import="Beans.Ville"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--Cette page représente le 'petit bout' de page HTML (JSP) que l'on va rafraîchir suite à maj du département -->
<%
// récupère la nouvelle liste des villes
    ClassesDAO.DAO<Ville> villeDAO = DAOFactory.getVilleDAO();
    ArrayList<Ville> listeVilles = new ArrayList<Ville>();
    listeVilles = ((VilleDAO) villeDAO).recupererVillesDunDepartement(Integer.parseInt(request.getParameter("departement")));
    request.setAttribute("lesvilles", listeVilles);
%>
<label for="ville">Ville : </label>
<select class="form-control" name="ville">
    <c:forEach items="${lesvilles}" var="laville" begin="0">
        <option value="${lebien.ville.idVille}">${laville.codePostal} - ${laville.nomVille}</option>
    </c:forEach>
</select>