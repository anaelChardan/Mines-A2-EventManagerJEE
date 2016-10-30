<%@ taglib prefix="add" uri="application" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>

<!--<div class="container-fluid" style="background-color:#e9e9e9">-->
<div class="row col-md-8 col-md-offset-2" style="border-radius:6px;margin-bottom:30px">

    <div class="row col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3" style="margin-bottom:50px">
        <h3 class="text-center">Mon profil</h3>
    </div>

    <div class="row col-md-10">
        <dl class="dl-horizontal col-md-9">
            <dt>Société :</dt>
            <dd>${user.company}</dd>
            <dt>Nom :</dt>
            <dd>${user.lastName}</dd>
            <dt>Prénom :</dt>
            <dd>${user.firstName}</dd>
            <dt>Email :</dt>
            <dd>${user.email}</dd>
        </dl>
    </div>
    <div class="row col-md-12">
        <table class="table" style="background-color:lightgray;border-radius: 6px">
            <caption>
                <h4 class="text-left">Mes prochains événements</h4>
            </caption>
            <thead>
            <tr>
                <th class="col-md-2 text-left">Titre</th>
                <th class="col-md-5 text-left">Description</th>
                <th class="col-md-2 text-left">Lieu</th>
                <th class="col-md-2 text-left">Date début</th>
                <th class="col-md-2 text-left">Date fin</th>
                <th class="col-md-2 text-left">Détail</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${futureEventAuthored}" var="event">
                <add:event-line event="${event}"/>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row col-md-12">
        <table class="table" style="background-color:lightgray;border-radius: 6px">
            <caption>
                <h4 class="text-left">Mes événements passés</h4>
            </caption>
            <thead>
            <tr>
                <th class="col-md-2 text-left">Titre</th>
                <th class="col-md-5 text-left">Description</th>
                <th class="col-md-2 text-left">Lieu</th>
                <th class="col-md-2 text-left">Date début</th>
                <th class="col-md-2 text-left">Date fin</th>
                <th class="col-md-2 text-left">Détail</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pastEventAuthored}" var="event">
                <add:event-line event="${event}"/>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row col-md-12">
        <table class="table" style="background-color:lightgray;border-radius: 6px">
            <caption>
                <h4 class="text-left">Mes participations passées</h4>
            </caption>
            <thead>
            <tr>
                <th class="col-md-2 text-left">Titre</th>
                <th class="col-md-5 text-left">Description</th>
                <th class="col-md-2 text-left">Lieu</th>
                <th class="col-md-2 text-left">Date début</th>
                <th class="col-md-2 text-left">Date fin</th>
                <th class="col-md-2 text-left">Détail</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pastEventSubscribed}" var="event">
                <part:event-line event="${event}"/>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!--</div>-->
