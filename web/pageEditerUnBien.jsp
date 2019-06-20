<%-- 
    Document   : PageEditerUnBien
    Created on : 29 mai 2019, 07:14:41
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
        <title>Modification</title>
        <%@ include file="head.jsp" %>
    </head>
    <body>
        <%@ include file="menu.jsp" %>
        <h1>Modification des infos sur un bien</h1>
        <c:if test="${ !empty requestScope.message }">
            <p> ${ requestScope.message } </p>
        </c:if>

        <main>
            <div class="card">
                <form method="post">
                    <div class="card-header">
                        <label for="nom">Nom : </label> 
                        <input class="form-control" type="text" value="${lebien.nom} " />
                    </div>


                    <div class="card-body">

                        <section class="row">
                            <div class="form-group col-md-4">
                                <label for="type">Type : </label>
                                <select class="form-control" name="type">
                                    <option value="${lebien.typeBien }" >${lebien.typeBien }</option>
                                    <option value="Maison" >Maison</option>
                                    <option value="Appartement">Appartement</option>
                                    <option value="Garage">Garage</option>
                                    <option value="Autre">Autre</option>
                                </select>
                            </div>

                            <div class="form-group col-md-4">
                                <label for="surface">Surface : </label> 
                                <input class="form-control" type="text" value="${lebien.surface} " />
                            </div>	
                            <div class="form-group col-md-4">
                                <label for="nbPieces">Nombre de pièces : </label>
                                <input class="form-control" type="text" name="nbPieces" value="${lebien.nbPieces} " />
                            </div>
                            <div class="form-group col-md-4">
                                <label for="nbChambres">Nombre de chambres : </label>
                                <input class="form-control" type="text" name="nbChambres" value="${lebien.nbChambres} " />
                            </div>
                            <div class="form-group col-md-4">
                                <label for="etage">Etage : </label>
                                <input class="form-control" type="text" name="etage" value="${lebien.etage} " />
                            </div>
                            <section class="form-group form-check col-md-4">
                                <label for="chauffage">Chauffage : </label>
                                <select class="form-control" name="chauffage">
                                    <option value="${lebien.typeChauffage }" >${lebien.typeChauffage }</option>
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
                                            <c:set var="trouve" scope="page" value="false"/>
                                            <input type="checkbox" class="custom-control-input" id="option${status.count}" value="${loption.id}" 
                                                   <c:forEach items="${lebien.listeOptions}" var="loptionutil" >
                                                       <c:if test="${loptionutil.nomOption} eq (${loption.nomOption})"> <c:set var="trouve" scope="page" value="true"/>  </c:if> 
                                                   </c:forEach> />
                                            <c:if test="${trouve}"> checked </c:if> 
                                            <label class="custom-control-label" for="option${status.count}"> ${loption.nomOption}</label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </section>

                            <div class="form-group col-md-6">
                                <label for="adresse">Adresse : </label>
                                <input class="form-control" type="text" name="adresse" value="${lebien.adresse} " />
                            </div>
                            <div class="form-group col-md-4">
                                <label for="codePostal">Code postal : </label>
                                <input class="form-control" type="text" name="codePostal" value="${lebien.ville.codePostal} " />
                            </div>

                            <div class="form-group col-md-4">
                                <label for="ville">Ville : </label>
                                <select class="form-control" name="ville">
                                    <option value="${lebien.ville.idVille}" selected>${lebien.ville.nomVille}</option>
                                </select>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="departement">Département : </label>
                                <select class="form-control" name="departement">
                                    <option value="${lebien.ville.departement}" selected>${lebien.ville.departement}</option>
                                </select>
                            </div>
                        </section>

                    </div>

                    <div class="card-footer">
                        <input class="btn btn-success w-25 p-2" type="submit" value="Validez" />

                        <input class="btn btn-secondary p-2 w-25 float-right" type="reset" value="Annulez" />
                    </div>

                </form>

            </div>
        </main>
    </body>
</html>
