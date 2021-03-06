<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="add" uri="application" %>

<div class="row col-md-10 col-md-offset-1" style="margin-bottom: 8%;margin-top:30px;">

    <div class="row">
        <div class="col-md-8">
            <h4>Mes prochaines participations (et en cours)</h4>
            <table class="table" style="border-radius:6px">
                <thead>
                <th class="col-md-2 text-left" style="width: 15%">Titre</th>
                <th class="col-md-5 text-left" style="width: 25%">Description</th>
                <th class="col-md-2 text-left" style="width: 10%">Lieu</th>
                <th class="col-md-2 text-left" style="width: 20%">Date début</th>
                <th class="col-md-2 text-left" style="width: 20%">Date fin</th>
                <th class="col-md-2 text-left" style="width: 10%">Détail</th>
                </thead>
                <c:forEach items="${eventsInProgressSubscribed}" var="event">
                    <add:event-line event="${event}"/>
                </c:forEach>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-md-8">
            <h4>Les prochains évènements auxquels s'inscrire</h4>
            <table class="table" style="border-radius:6px">
                <thead>
                <th class="col-md-2 text-left" style="width: 15%">Titre</th>
                <th class="col-md-5 text-left" style="width: 25%">Description</th>
                <th class="col-md-2 text-left" style="width: 10%">Lieu</th>
                <th class="col-md-2 text-left" style="width: 20%">Date début</th>
                <th class="col-md-2 text-left" style="width: 20%">Date fin</th>
                <th class="col-md-2 text-left" style="width: 10%">Détail</th>
                </thead>
                <c:forEach items="${eventsInProgressNotSubscribed}" var="event">
                    <add:event-line event="${event}"/>
                </c:forEach>
            </table>
        </div>
    </div>


    <div class="row">
        <div class="col-md-8">
            <h4>Les évènements que vous avez ratés</h4>
            <table class="table" style="border-radius:6px">
                <thead>
                <th class="col-md-2 text-left" style="width: 15%">Titre</th>
                <th class="col-md-5 text-left" style="width: 25%">Description</th>
                <th class="col-md-2 text-left" style="width: 10%">Lieu</th>
                <th class="col-md-2 text-left" style="width: 20%">Date début</th>
                <th class="col-md-2 text-left" style="width: 20%">Date fin</th>
                <th class="col-md-2 text-left" style="width: 10%">Détail</th>
                </thead>
                <c:forEach items="${eventsPassed}" var="event">
                    <add:event-line event="${event}"/>
                </c:forEach>
            </table>
        </div>
    </div>
</div>