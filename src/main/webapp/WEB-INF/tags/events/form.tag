<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ tag pageEncoding="UTF-8"%>

<%@ attribute name="event" type="fr.mines.event_manager.event.entity.Event" required="true" description="The event to display" rtexprvalue="true" %>

<div class="row">
    <div class="form-group">
        <label for="titre" class="col-md-3 control-label">Titre de l'évènement : </label>
        <div class="col-md-6">
            <input type="text" value="${event.name}" class="form-control" id="titre" name="titre">
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group">
        <label for="dateDebut" class="col-md-3 control-label">Date de début : </label>
        <div class="col-md-6">
            <input type="datetime-local" value="<fmt:formatDate pattern="yyyy-MM-dd'T'HH':'mm" value="${event.startDate}"/>" class="form-control" id="dateDebut" name="start_date">
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group">
        <label for="dateFin" class="col-md-3 control-label">Date de fin : </label>
        <div class="col-md-6">
            <input type="datetime-local" value="<fmt:formatDate pattern="yyyy-MM-dd'T'HH':'mm" value="${event.endDate}"/>" class="form-control" id="dateFin" name="end_date">
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group">
        <label for="address1" class="col-md-3 control-label">Addresse principale : </label>
        <div class="col-md-6">
            <input type="text" value="${event.address.address1}" class="form-control" id="address1" name="address1">
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group">
        <label for="address2" class="col-md-3 control-label">Complément d'adresse : </label>
        <div class="col-md-6">
            <input type="text" value="${event.address.address2}" class="form-control" id="address2" name="address2">
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group">
        <label for="zip_code" class="col-md-3 control-label">Code postal : </label>
        <div class="col-md-6">
            <input type="number" value="${event.address.zipCode}" minlength="2" class="form-control" id="zip_code" name="zip_code">
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group">
        <label for="city" class="col-md-3 control-label">Ville : </label>
        <div class="col-md-6">
            <input type="text" value="${event.address.city}" class="form-control" id="city" name="city">
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group">
        <label for="country" class="col-md-3 control-label">Pays : </label>
        <div class="col-md-6">
            <input type="text" value="${event.address.country}" class="form-control" id="country" name="country">
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group">
        <label for="prix" class="col-md-3 control-label">Prix : </label>
        <div class="col-md-6">
            <input type="number" value="${event.price}" min="0" class="form-control" placeholder="15.00" id="prix" name="price"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group">
        <label for="nbPlaces" class="col-md-3 control-label">Nombre de places : </label>
        <div class="col-md-6">
            <input type="number" value="${event.maxTickets}" min="0" class="form-control" id="nbPlaces" name="max_tickets">
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group">
        <label for="description" class="col-md-3 control-label">Description : </label>
        <div class="row">
            <div class="col-md-6">
                <textarea type="textarea" maxlength="200" class="form-control" id="description" name="description">${event.description}</textarea>
            </div>
        </div>
    </div>
</div>