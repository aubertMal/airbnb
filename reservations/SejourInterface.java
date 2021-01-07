package aubert.airbnb.reservations;

import aubert.airbnb.outils.MaDate;

import java.util.Date;

public interface SejourInterface {

    /**
     * Cette méthode prend en paramètres une date de séjour et vérifie si c'est après la date actuelle
     * @return true or false
     */

    static boolean verificationDateArrivee(){
        return true;
    }

    static boolean verificationNombreDeNuits(){
        return true;
    }

    static boolean verificationNombreDeVoyageurs(){
        return true;
    }

    static void afficher(){

    }

}