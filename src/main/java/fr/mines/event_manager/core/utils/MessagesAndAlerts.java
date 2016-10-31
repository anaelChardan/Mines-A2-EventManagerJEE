package fr.mines.event_manager.core.utils;

import fr.mines.event_manager.framework.utils.Alert;

public class MessagesAndAlerts {
    /******
     * UserServlet
     ******/
    public static final String loginImpossible = "L'adresse mail et/ou le mot de passe ne sont pas valides";
    public static final String deconnected = "Vous avez bien été déconnecté";
    public static final Alert  differentPasswords = Alert.danger("Les mots de passes sont différents");
    public static final Alert  wellSubscribed = Alert.success("Votre utilisateur a bien été enregistré");
}
