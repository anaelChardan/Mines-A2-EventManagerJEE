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
            <dd>${CURRENT_USER.company}</dd>
            <dt>Nom :</dt>
            <dd>${CURRENT_USER.lastName}</dd>
            <dt>Prénom :</dt>
            <dd>${CURRENT_USER.firstName}</dd>
            <dt>Email :</dt>
            <dd>${CURRENT_USER.email}</dd>
        </dl>
    </div>
    <div class="row col-md-12">
        <table class="table col-md-8" style="border-radius: 6px">
            <caption>
                <h4 class="text-left">Mes prochains évènements</h4>
            </caption>
            <thead>
            <tr>
                <th class="col-md-2 text-left" style="width: 15%">Titre</th>
                <th class="col-md-5 text-left" style="width: 25%">Description</th>
                <th class="col-md-2 text-left" style="width: 10%">Lieu</th>
                <th class="col-md-2 text-left" style="width: 15%">Date début</th>
                <th class="col-md-2 text-left" style="width: 15%">Date fin</th>
                <th class="col-md-2 text-left" style="width: 5%">Publié</th>
                <th class="col-md-2 text-left" style="width: 5%">Détail</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listFutureEvent}" var="event">
                <add:event-line event="${event}"/>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row col-md-12">
        <table class="table col-md-8" style="border-radius: 6px">
            <caption>
                <h4 class="text-left">Mes évènements passés</h4>
            </caption>
            <thead>
            <tr>
                <th class="col-md-2 text-left" style="width: 15%">Titre</th>
                <th class="col-md-5 text-left" style="width: 25%">Description</th>
                <th class="col-md-2 text-left" style="width: 10%">Lieu</th>
                <th class="col-md-2 text-left" style="width: 15%">Date début</th>
                <th class="col-md-2 text-left" style="width: 15%">Date fin</th>
                <th class="col-md-2 text-left" style="width: 5%">Publié</th>
                <th class="col-md-2 text-left" style="width: 5%">Détail</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listPastEvent}" var="event">
                <add:event-line event="${event}"/>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row col-md-12">
        <table class="table col-md-8" style="border-radius: 6px">
            <caption>
                <h4 class="text-left">Mes participations passées</h4>
            </caption>
            <thead>
            <tr>
                <th class="col-md-2 text-left" style="width: 15%">Titre</th>
                <th class="col-md-5 text-left" style="width: 25%">Description</th>
                <th class="col-md-2 text-left" style="width: 10%">Lieu</th>
                <th class="col-md-2 text-left" style="width: 20%">Date début</th>
                <th class="col-md-2 text-left" style="width: 20%">Date fin</th>
                <th class="col-md-2 text-left" style="width: 10%">Détail</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listPastParticipations}" var="event">
                <add:event-line event="${event}"/>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!--</div>-->
