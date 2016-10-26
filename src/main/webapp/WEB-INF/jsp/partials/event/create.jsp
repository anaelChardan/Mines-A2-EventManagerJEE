<%@ taglib prefix="app" uri="application" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="row col-md-8 col-md-offset-2" style="margin-bottom:30px">

    <div class="row col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3" style="margin-bottom: 30px;margin-top:30px;">
        <h3 class="text-center">Création d'un évènement</h3>
    </div>

    <div class="row">
        <form class="form-horizontal col-md-8 col-md-offset-3" method="post" action="<app:PathTag endpoint="/event/new"/>">
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
                        <input type="datetime-local" class="form-control" id="dateDebut" name="start_date">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="dateFin" class="col-md-3 control-label">Date de fin : </label>
                    <div class="col-md-6">
                        <input type="datetime-local" class="form-control" id="dateFin" name="end_date">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="address1" class="col-md-3 control-label">Addresse principale : </label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" id="address1" name="address1">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="address2" class="col-md-3 control-label">Complément d'adresse : </label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" id="address2" name="address2">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="zip_code" class="col-md-3 control-label">Code postal : </label>
                    <div class="col-md-6">
                        <input type="number" minlength="2" class="form-control" id="zip_code" name="zip_code">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="city" class="col-md-3 control-label">Ville : </label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" id="city" name="city">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="country" class="col-md-3 control-label">Pays : </label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" id="country" name="country">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="prix" class="col-md-3 control-label">Prix : </label>
                    <div class="col-md-6">
                        <input type="number" min="0" class="form-control" placeholder="15.00" id="prix" name="price"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="nbPlaces" class="col-md-3 control-label">Nombre de places : </label>
                    <div class="col-md-6">
                        <input type="number" min="0" class="form-control" id="nbPlaces" name="max_tickets">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="description" class="col-md-3 control-label">Description : </label>
                    <div class="row">
                        <div class="col-md-6">
                            <textarea type="textarea" maxlength="200" class="form-control" id="description" name="description"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group form-inline">
                <button class="pull-right btn btn-info" style="margin-right:5px">Annuler</button>
                <button type="submit" value="create-and-publish" name="action" class="pull-right btn btn-info" style="margin-right:5px">Créer et publier</button>
                <button type="submit" value="create" name="action" class="pull-right btn btn-info" style="margin-right:5px">Créer</button>
            </div>
        </form>
    </div>
</div>