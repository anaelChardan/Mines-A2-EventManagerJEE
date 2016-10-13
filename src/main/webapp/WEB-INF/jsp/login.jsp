<%@ page contentType="text/html; UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/assets/css/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/flat-ui.css" rel="stylesheet">
    
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" />
  <title>Authentification</title>
  
</head>
<body class="palette-turquoise container-fluid">
  <div>

    <div class="row">
      <div class="titre-login col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
        <h3 class="text-center">${pageContext.request.contextPath} Bienvenue sur Event Manager</h3>
      </div>
    </div>

    <div class="row">
      <div class="login-screen col-md-6 col-md-offset-3">
        <div class="login-form">
          <div class="form-group">
            <input type="text" class="form-control login-field" value="" placeholder="Nom d'utilisateur" id="login-name" />
            <label class="login-field-icon fui-user" for="login-name"></label>
          </div>

          <div class="form-group">
            <input type="password" class="form-control login-field" value="" placeholder="Mot de passe" id="login-pass" />
            <label class="login-field-icon fui-lock" for="login-pass"></label>
          </div>

          <a class="btn btn-primary btn-lg btn-block" href="#">Connexion</a>
          <a class="btn btn-secondary btn-lg btn-block" href="#">S'inscrire</a>
          <a class="login-link" href="#">Mot de passe perdu ?</a>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
