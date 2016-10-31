<%@ taglib prefix="app" uri="application" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="row col-md-8 col-md-offset-2" style="margin-bottom:30px">

    <div class="row col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3" style="margin-bottom: 30px;margin-top:30px;">
        <h3 class="text-center">Modification d'un évènement</h3>
    </div>
    
    <div class="row">
        <form class="form-horizontal col-md-8 col-md-offset-3" method="post" action="<app:PathTag endpoint="/event/"/>${event.id}/edit">
            <app:event-form event="${event}"/>
            <div class="row col-md-offset-1">
                <div class="col-xs-3">
                    <a class="btn btn-danger btn-block btn-sm" href="<app:PathTag endpoint="/event/"/>${event.id}"
                       style="margin-right:5px">Annuler</a>
                </div>
                <div class="col-xs-3">
                    <button type="submit" value="validate" name="action" class="btn btn-info btn-block btn-sm"
                            style="margin-right:5px">Valider
                    </button>
                </div>
                <div class="col-xs-3">
                    <button type="submit" value="edit-and-publish" name="action" class="btn btn-info btn-block btn-sm"
                            style="margin-right:5px">Valider et Publier
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>