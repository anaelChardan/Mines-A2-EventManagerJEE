 <div class="container-fluid" style="background-color:#e9e9e9">
        <div class="row col-md-8 col-md-offset-2" style="border-radius:6px;background-color:#c4c4c4;margin-bottom:30px">

            <div class="row col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3" style="margin-bottom:50px">
                <h3 class="text-center">Mon profil</h3>
            </div>

            <div class="row col-md-10">
                <dl class="dl-horizontal col-md-9">
                    <dt>Nom d'utilisateur : </dt>
                    <dd>${pageContext.session.getAttribute("id")}</dd>
                    <dt>Nom : </dt>
                    <dd>Dujardin</dd>
                    <dt>Prénom : </dt>
                    <dd>Jean-Paul</dd>
                    <dt>Email : </dt>
                    <dd>jp-dujardin@gmail.com</dd>
                </dl>
            </div>
            <div class="row col-md-12">
                <table class="table">
                    <caption>
                        <h4 class="text-left">Mes participations à venir</h4>
                    </caption>
                    <thead>
                    <tr>
                        <th class="col-md-2 text-left">Titre</th>
                        <th class="col-md-6 text-left">Description</th>
                        <th class="col-md-2 text-left">Date début</th>
                        <th class="col-md-2 text-left">Date fin</th>
                        <th class="col-md-2 text-left">Détail</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Evenement 1</td>
                        <td>Un évènement exemple à venir</td>
                        <td>21/11/2016 18:00</td>
                        <td>22/11/2016 18:00</td>
                        <td><a href="#">Link</a></td>
                    </tr>
                    <tr>
                        <td>Evenement 2</td>
                        <td>Un évènement exemple à venir</td>
                        <td>01/12/2016 18:00</td>
                        <td>01/12/2016 20:00</td>
                        <td><a href="#">Link</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="row col-md-12">
                <table class="table">
                    <caption>
                        <h4 class="text-left">Mes participations passée</h4>
                    </caption>
                    <thead>
                    <tr>
                        <th class="col-md-2 text-left">Titre</th>
                        <th class="col-md-6 text-left">Description</th>
                        <th class="col-md-2 text-left">Date début</th>
                        <th class="col-md-2 text-left">Date fin</th>
                        <th class="col-md-2 text-left">Détail</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Evenement 1</td>
                        <td>Un évènement exemple passé</td>
                        <td>01/10/2016 18:00</td>
                        <td>01/10/2016 22:00</td>
                        <td><a href="#">Link</a></td>
                    </tr>
                    <tr>
                        <td>Evenement 2</td>
                        <td>Un évènement exemple passé</td>
                        <td>20/10/2016 18:00</td>
                        <td>21/10/2016 20:00</td>
                        <td><a href="#">Link</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
