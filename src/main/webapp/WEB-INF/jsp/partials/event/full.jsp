<%@ taglib prefix="add" uri="application" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" uri="application" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>

<div class="row col-md-8 col-md-offset-2" style="border-radius:6px;margin-bottom:30px">

    <div class="row col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3" style="margin-bottom:50px">
        <h3 class="text-center"><c:out value="${event.name}"/></h3>
    </div>

    <div class="row col-md-12">
        <dl class="dl-horizontal col-md-9">
            <dt>Auteur :</dt>
            <dd>${event.author.firstName} ${event.author.lastName}</dd>
            <dt>Description :</dt>
            <dd>${event.description}</dd>
            <dt>Date de début :</dt>
            <dd><fmt:formatDate pattern="'le' dd/MM/yyyy 'à' H'h'mm" value="${event.startDate}"/></dd>
            <dt>Date de fin :</dt>
            <dd><fmt:formatDate pattern="'le' dd/MM/yyyy 'à' H'h'mm" value="${event.startDate}"/></dd>
            <dt>Lieu :</dt>
            <dd>${event.address.address1}
                ${event.address.address2}
                ${event.address.zipCode} ${event.address.city}
                ${event.address.country}
            </dd>
            <dt>Prix :</dt>
            <dd>${event.price}</dd>
            <dt>Places restantes :</dt>
            <dd>${event.maxTickets - fn:length(event.subscribers)} / ${event.maxTickets}</dd>
        </dl>
    </div>

    <div class="row col-md-12">
        <c:if test="${event.isAuthor(CURRENT_USER)}">
            <table class="table">
                <caption>
                    <h4 class="text-left">Liste des participants <span
                            class="badge">${fn:length(event.subscribers)}</span></h4>
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
    </div>

    <div>
        <c:choose>
            <c:when test="${event.isAuthor(CURRENT_USER)}">
                <c:if test="${not event.published}">
                    <a href="<app:PathTag endpoint="/event/"/>${event.id}/edit" class="pull-right btn btn-block btn-success">Modifier</a>
                    <form method="post" action="<app:PathTag endpoint="/event/"/>${event.id}/publish">
                        <button type="submit" value="publish" class="pull-right btn btn-block btn-success">Publier
                        </button>
                    </form>
                </c:if>
                <c:if test="${event.isRemovable(CURRENT_USER)}">
                    <form method="post" action="<app:PathTag endpoint="/event/"/>${event.id}/delete">
                        <button type="submit" value="delete" class="pull-right btn btn-block btn-danger">Supprimer
                        </button>
                    </form>
                </c:if>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${event.isSubscribable(CURRENT_USER)}">
                        <form method="post" action="<app:PathTag endpoint="/event/"/>${event.id}/subscribe">
                            <button type="submit" value="subscribe" class="pull-right btn-block btn btn-primary">
                                M'inscrire
                            </button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form method="post" action="<app:PathTag endpoint="/event/"/>${event.id}/unsubscribe">
                            <button type="submit" value="unsubscribe" class="pull-right btn btn-block btn-warning">
                                Me désinscrire
                            </button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </div>
</div>

