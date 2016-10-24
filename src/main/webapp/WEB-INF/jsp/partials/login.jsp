<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="app" uri="application" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<!-- <div class="container-fluid palette-turquoise"> -->
    <div class="row titre-login col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
        <h3 class="text-center">Authentification</h3>
    </div>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">

            <div class="login-form">

                <form method="post" action="<app:PathTag endpoint="/app/loginpost"/>">
                    <div class="form-group">
                        <input type="text" name="email" class="form-control login-field" value=""
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
                        <label class="checkbox" for="checkbox1">
                            <input name="remember" type="checkbox" value="" id="checkbox1" data-toggle="checkbox">
                            Se souvenir de moi
                        </label>
                    </div>
                    <input class="btn btn-info btn-lg btn-block" type="submit" value="Connexion"/>
                </form>


                <div class="row">
                    <div class="col-md-6">
                        <a class="login-link" href="#">S'inscrire</a>
                    </div>
                    <div class="col-md-6">
                        <a class="login-link" href="#">Mot de passe perdu ?</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
<!-- </div> -->