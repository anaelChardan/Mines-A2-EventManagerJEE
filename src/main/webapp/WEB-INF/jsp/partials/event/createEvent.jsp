<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="row col-md-8 col-md-offset-2" style="margin-bottom:30px">

    <div class="row col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3" style="margin-bottom: 30px;margin-top:30px;">
        <h3 class="text-center">Création d'un évènement</h3>
    </div>

    <div class="row">
        <form class="form-horizontal col-md-8 col-md-offset-3" method="post" action="/event/newEvent">
            <div class="row">
                <div class="form-group">
                    <label for="titre" class="col-md-3 control-label">Titre de l'évènement : </label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" id="titre" name="titre">
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
                    <label for="lieu" class="col-md-3 control-label">Lieu : </label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" id="lieu" name="address">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="prix" class="col-md-3 control-label">Prix : </label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" placeholder="15.00" id="prix" name="price"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="nbPlaces" class="col-md-3 control-label">Nombre de places : </label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" id="nbPlaces" name="max_tickets">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="description" class="col-md-3 control-label">Description : </label>
                    <div class="row">
                        <div class="col-md-6">
                            <textarea type="textarea" class="form-control" id="description" name="description"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group form-inline">
                <button class="pull-right btn btn-info" style="margin-right:5px">Annuler</button>
                <button type="submit" class="pull-right btn btn-info" style="margin-right:5px">Créer et publier</button>
                <button type="submit" class="pull-right btn btn-info" style="margin-right:5px">Créer</button>
            </div>
        </form>
    </div>
</div>