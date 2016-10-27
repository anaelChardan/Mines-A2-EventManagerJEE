<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>

<div class="row col-md-10 col-md-offset-1" style="margin-bottom: 8%;margin-top:30px;">

    <div class="row">
        <div class="col-md-8">
            <h4>Mes prochains événements</h4>
            <table class="table col-md-8 palette-silver" style="border-radius:6px">
                <thead>
                <th>Titre</th>
                <th>Description</th>
                <th>Ville</th>
                <th>Date de début</th>
                <th>Date de fin</th>
                <th>Détail</th>
                </thead>
                <c:forEach items="${eventsSubscribed}" var="event">
                    <tr>
                        <td>${event.name}</td>
                        <td>${event.description}</td>
                        <td>${event.address.city}</td>
                        <td>${event.startDate}</td>
                        <td>${event.endDate}</td>
                        <td>
                            <a href="#" class="btn btn-primary btn-block btn-large">Détail</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-md-8">
            <h4>Les événements à venir</h4>
            <table class="table col-md-8 palette-silver" style="border-radius:6px">
                <thead>
                <th>Titre</th>
                <th>Description</th>
                <th>Ville</th>
                <th>Date de début</th>
                <th>Date de fin</th>
                <th>Détail</th>
                </thead>
                <c:forEach items="${eventsNotPassed}" var="event">
                    <tr>
                        <td>${event.name}</td>
                        <td>${event.description}</td>
                        <td>${event.address.city}</td>
                        <td>${event.startDate}</td>
                        <td>${event.endDate}</td>
                        <td>
                            <a href="#" class="btn btn-primary btn-block btn-large">Détail</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>


    <div class="row">
        <div class="col-md-8">
            <h4>Les événements passés</h4>
            <table class="table col-md-8 palette-silver" style="border-radius:6px">
                <thead>
                <th>Titre</th>
                <th>Description</th>
                <th>Ville</th>
                <th>Date de début</th>
                <th>Date de fin</th>
                <th>Détail</th>
                </thead>
                <c:forEach items="${eventsPassed}" var="event">
                <tr>
                    <td>${event.name}</td>
                    <td>${event.description}</td>
                    <td>${event.address.city}</td>
                    <td>${event.startDate}</td>
                    <td>${event.endDate}</td>
                    <td>
                        <a href="#" class="btn btn-primary btn-block btn-large">Détail</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>