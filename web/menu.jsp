<%-- 
    Document   : menu
    Created on : 16 mai 2019, 10:03:54
    Author     : Stagiaire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="/">AgenceImmo</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item {% if current_menu is defined and current_menu == 'accueil' %} active {% endif %}">
                <a class="nav-link" href="aiguillage?cmd=VoirPageAccueil">Accueil</a>
            </li>
            <li class="nav-item {% if current_menu is defined and current_menu == 'consulter'%} active {% endif %}">
                <a class="nav-link" href="aiguillage?cmd=VoirTousLesBiens">Consulter</a>
            </li>
            <li class="nav-item {% if current_menu is defined and current_menu == 'gere'%} active {% endif %}">
                <a class="nav-link" href="aiguillage?cmd=GererLesBiens">Gérer</a>
            </li>
        </ul>
        <c:if test = "${ !empty sessionScope.user}">
           
            
            <ul class="navbar-nav">
                <p class="element_menu"> ${sessionScope.user} </p>
                <li class="nav-item">
                    <a href="aiguillage?cmd=Logout" class="nav-link">Se déconnecter</a>
                </li>
            </ul>
        </c:if>
    </div>
</nav>

