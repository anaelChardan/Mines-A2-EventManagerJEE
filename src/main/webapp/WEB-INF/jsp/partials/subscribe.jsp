<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="app" uri="application" %>

<div class="row titre-login col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
    <h3 class="text-center">Inscription</h3>
</div>

<div class="row" style="margin-bottom: 8%">
    <div class="col-md-4 col-md-offset-4">
        <div class="login-form">
            <form method="post" action="<app:PathTag endpoint="/app/subscribe"/>">
                <div class="form-group">
                    <input type="text" name="last-name" class="form-control login-field" value="${user.lastName}"
                           placeholder="Nom"
                           id="last-name"/>
                    <label class="login-field-icon fui-user" for="last-name"></label>
                </div>
                <div class="form-group">
                    <input type="text" name="first-name" class="form-control login-field" value="${user.firstName}"
                           placeholder="Prénom"
                           id="first-name"/>
                    <label class="login-field-icon fui-user" for="first-name"></label>
                </div>
                <div class="form-group">
                    <input type="text" name="company" class="form-control login-field" value="${user.company}"
                           placeholder="Société"
                           id="company"/>
                    <label class="login-field-icon fui-user" for="company"></label>
                </div>
                <div class="form-group">
                    <input type="email" name="email" class="form-control login-field" value="${user.email}"
                           placeholder="Adresse mail"
                           id="login-name"/>
                    <label class="login-field-icon fui-user" for="login-name"></label>
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control login-field" value=""
                           placeholder="Mot de passe"
                           id="login-pass"/>
                    <label class="login-field-icon fui-lock" for="login-pass"></label>
                </div>
                <div class="form-group">
                    <input type="password" name="password2" class="form-control login-field" value=""
                           placeholder="Confirmation du mot de passe"
                           id="login-pass2"/>
                    <label class="login-field-icon fui-lock" for="login-pass2"></label>
                </div>
                <input class="btn btn-info btn-lg btn-block" type="submit" value="Inscription"/>
            </form>
        </div>
    </div>
</div>
<!-- </div> -->