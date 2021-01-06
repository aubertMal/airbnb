package aubert.airbnb.reservations;

import aubert.airbnb.logements.Logement;
import aubert.airbnb.outils.MaDate;

public class Sejour {
    private MaDate dateArrivee;
    private int nbNuits;
    private Logement logement;
    private int nbVoyageurs;

    public Sejour(MaDate date,int nuitees, Logement logementSejour, int nombreVoyageurs){
        dateArrivee = date;
        nbNuits = nuitees;
        logement = logementSejour;
        nbVoyageurs = nombreVoyageurs;
    }

    public void afficher(){
        logement.afficher();
        System.out.println("La date d'arrivée est le " + dateArrivee.toString(dateArrivee));
        System.out.println("Le prix de ce séjour est de " + logement.getTarifParNuit() + '€');
    }
}
