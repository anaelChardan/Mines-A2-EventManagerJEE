<%@ taglib prefix="add" uri="application" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="app" uri="application" %>

<div class="container-fluid">
    <nav class="navbar-inverse navbar-embossed" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Event Manager</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="#">Les événements</a></li>
                <li><a href="<add:PathTag endpoint="/user/profile"/>">Mon profil</a></li>
                <li><a href="<add:PathTag endpoint="/event/eventForm"/>">Nouvel événement</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Se connecter <span class="fui-exit"/></a></li>
            </ul>
        </div>
    </nav>
</div>

