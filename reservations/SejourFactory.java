package aubert.airbnb.reservations;

import aubert.airbnb.logements.Logement;

import java.util.Date;

public class SejourFactory {
    public static Sejour getSejour(Date dateArrivee, Logement logement, int nbNuits, int nbVoyageurs){
        Sejour nouveauSejour;
        if (nbNuits <= 5) {
            nouveauSejour = new SejourCourt(dateArrivee, nbNuits, logement, nbVoyageurs);
        } else {
            nouveauSejour = new SejourLong(dateArrivee, nbNuits, logement, nbVoyageurs);
        }

        return nouveauSejour;
    }
}
