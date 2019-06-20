<%-- 
    Document   : pageCreerUnBien
    Created on : 17 juin 2019, 06:15:01
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creation</title>
        <%@ include file="head.jsp" %>
    </head>
    <body>
        <%@ include file="menu.jsp" %>
        <h1>Création d'un bien</h1>
        <c:if test="${ !empty requestScope.message }">
            <div class="text-center"> ${ requestScope.message } </div>
        </c:if>

        <main class="card">
            <form method="post" onsubmit="return confirm('Etes vous vraiment sûr ?')">
                <section class="card-header">
                    <label for="nom">Nom : </label> 
                    <input class="form-control" name="nom" type="text" value="${lebien.nom}" required/>
                </section>

                <div class="card-body">

                    <section class="row">
                        <div class="form-group col-md-4">
                            <label for="type">Type : </label>
                            <select class="form-control" name="type" required>

                                <option value="Maison" >Maison</option>
                                <option value="Appartement">Appartement</option>
                                <option value="Garage">Garage</option>
                                <option value="Autre">Autre</option>
                            </select>
                        </div>

                        <div class="form-group col-md-4">
                            <label for="surface">Surface : </label> 
                            <input class="form-control" type="text" name="surface" value="${lebien.surface} " required/>
                        </div>	
                        <div class="form-group col-md-4">
                            <label for="nbPieces">Nombre de pièces : </label>
                            <input class="form-control" type="text" name="nbPieces" value="${lebien.nbPieces} " required/>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="nbChambres">Nombre de chambres : </label>
                            <input class="form-control" type="text" name="nbChambres" value="${lebien.nbChambres} " required/>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="etage">Etage : </label>
                            <input class="form-control" type="text" name="etage" value="${lebien.etage} " required/>
                        </div>
                        <section class="form-group form-check col-md-4">
                            <label for="chauffage">Chauffage : </label>
                            <select class="form-control" name="chauffage" required>

                                <option value="Fuel" >Fuel</option>
                                <option value="Electricité">Electricité</option>
                                <option value="Gaz">Gaz</option>
                                <option value="Bois">Bois</option>
                                <option value="Autre">Autre</option>
                            </select>
                        </section>
                        <section class="form-group form-check col-md-6">
                            <label class="form-check-label" for="option1">Options</label>
                            <div>
                                <c:forEach items="${lesoptions}" var="loption" begin="0" varStatus="status">
                                    <div class="custom-control custom-checkbox" style="display: inline-block">

                                        <input type="checkbox" class="custom-control-input" id="option${status.count}" value="${loption.id}" 

                                               <label class="custom-control-label" for="option${status.count}"> ${loption.nomOption}</label>
                                    </div>
                                </c:forEach>
                            </div>
                        </section>

                        <div class="form-group col-md-6">
                            <label for="adresse">Adresse : </label>
                            <input class="form-control" type="text" name="adresse" value="${lebien.adresse} " required/>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="departement">Département : </label>
                            <select id="departement" class="form-control" name="departement"  onchange="rafraichirVillesAjax();" language="javascript" required>
                                <c:forEach items="${lesdepartements}" var="ledepartement" begin="0">
                                    <option value="${lebien.ville.departement}">${ledepartement.no} - ${ledepartement.nomDepartement}</option>

                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group col-md-4" id="ville">
                            <label for="ville">Ville : </label>
                                <select class="form-control" name="ville">
                                    <c:forEach items="${lesvilles}" var="laville" begin="0">
                                        <option value="${lebien.ville.idVille}">${laville.codePostal} - ${laville.nomVille}</option>
                                    </c:forEach>
                                </select>
                        </div>
                        <%--                        
                                                <div class="form-group col-md-4">
                                                    <label for="codePostal">Code postal : </label>
                                                    <input class="form-control" type="text" name="codePostal" value="${lebien.ville.codePostal} " />
                                                </div>
                        --%>
                    </section>

                </div>

                <div class="card-footer">
                    <input class="btn btn-success w-25 p-2" type="submit" name="submit" value="Valider" />
                    <input class="btn btn-secondary p-2 w-25 float-center" type="reset" name="reset" value="Effacer" />
                    <a href="aiguillage?cmd=AdminBiens" class="btn btn-primary p-2 w-25 float-right">Annuler la création</a>
                </div>



            </form>

        </main>
    </body>
</html>

