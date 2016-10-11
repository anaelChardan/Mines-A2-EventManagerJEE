<!DOCTYPE html>
<html>
<head>
    <link href="css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/flat-ui.css" rel="stylesheet">
    
    <link href="css/style.css" rel="stylesheet"></style>
  <title>Authentification</title>
  
</head>
<body class="palette-turquoise container-fluid">
  <div>

    <div class="row">
      <div class="titre-login col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
        <h3 class="text-center">Bienvenue sur Event Manager</h3>
      </div class="col-md-6 col-md-offset-3">
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
