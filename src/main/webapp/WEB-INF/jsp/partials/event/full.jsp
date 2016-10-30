<%@ taglib prefix="add" uri="application" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" uri="application" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- <div class="container-fluid" style="background-color:#e9e9e9"> -->
<div class="row col-md-8 col-md-offset-2" style="border-radius:6px;margin-bottom:30px">

    <div class="row col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3" style="margin-bottom:50px">
        <h3 class="text-center">${event.name}</h3>
            <c:choose>
                <c:when test="${event.isASubscriber(CURRENT_USER)}">
                    <form method="post" action="<app:PathTag endpoint="/event/"/>${event.id}/unsubscribe">
                        <button type="submit" value="unsubscribe" class="pull-right btn-warning">Me désinscrire</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <form method="post" action="<app:PathTag endpoint="/event/"/>${event.id}/subscribe">
                        <button type="submit" value="subscribe" class="pull-right btn-primary">M'inscrire</button>
                    </form>
                </c:otherwise>
            </c:choose>
            <c:if test="${event.author == CURRENT_USER}">
                <form method="post" action="<app:PathTag endpoint="/event/"/>${event.id}/delete">
                    <button type="submit" value="delete" class="pull-right btn-danger">Supprimer l'événement</button>
                </form>
            </c:if>
            <c:if test="${event.author == CURRENT_USER}">
                <form method="post" action="<app:PathTag endpoint="/event/"/>${event.id}/delete">
                    <button type="submit" value="delete" class="pull-right btn-danger">Supprimer l'événement</button>
                </form>
            </c:if>
    </div>

    <div class="row col-md-12">
        <dl class="dl-horizontal col-md-9">
            <dt>Auteur :</dt>
            <dd>${event.author.firstName} ${event.author.lastName}</dd>
            <dt>Description :</dt>
            <dd>${event.description}</dd>
            <dt>Date de début :</dt>
            <dd>${event.startDate}</dd>
            <dt>Date de fin :</dt>
            <dd>${event.endDate}</dd>
            <dt>Lieu :</dt>
            <dd>${event.address.address1}
                ${event.address.address2}
                ${event.address.zipCode} ${event.address.city}
                ${event.address.country}
            </dd>
            <dt>Prix :</dt>
            <dd>${event.price}</dd>
            <dt>Nombre de places restantes :</dt>
            <dd>${event.maxTickets - fn:length(event.subscribers)} / ${event.maxTickets}</dd>
        </dl>
    </div>

    <div class="row col-md-12">
        <c:if test="${event.author == CURRENT_USER}">
            <table class="table">
                <caption>
                    <h4 class="text-left">Liste des participants <span class="badge">${fn:length(event.subscribers)}</span></h4>
                </caption>
                <thead>
                <tr>
                    <th class="col-md-2 text-left">Nom</th>
                    <th class="col-md-2 text-left">Prénom</th>
                    <th class="col-md-2 text-left">Société</th>
                    <th class="col-md-6 text-left">Mail</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${event.subscribers}" var="user">
                    <tr>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.company}</td>
                        <td>${user.email}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <form class="col-md-12" method="post" action="<app:PathTag endpoint="/event/action"/>">
            <div class="form-group form-inline">
                <c:if test="${event.author == CURRENT_USER}">
                    <button type="submit" value="cancel" name="action" class="pull-right btn btn-danger"
                            style="margin-right:20px">Annuler l'évènement
                    </button>
                </c:if>
                <c:if test="${(not event.published) && (event.author == CURRENT_USER)}">
                    <button type="submit" value="publish" name="action" class="pull-right btn btn-info"
                            style="margin-right:20px">Publier
                    </button>
                    <button type="submit" value="modify" name="action" class="pull-right btn btn-info"
                            style="margin-right:20px">Modifier
                    </button>
                </c:if>
                <c:if test="${(event.published)}">
                    <button type="submit" value="subscribe" class="pull-right btn btn-info" style="margin-right:20px">
                        S'inscrire
                    </button>
                </c:if>
            </div>
        </form>
    </div>

</div>

