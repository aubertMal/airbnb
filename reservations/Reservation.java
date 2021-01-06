package aubert.airbnb.reservations;

import aubert.airbnb.utilisateurs.Personne;
import aubert.airbnb.utilisateurs.Voyageur;

import java.util.Date;
import java.util.Random;

public class Reservation {
    private int identifiant;
    private Sejour sejour;
    private Voyageur voyageur;
    private boolean estValidee;
    private Date dateDeReservation;

    public Reservation(Voyageur locataire, boolean validation, Sejour sejourReservation){
        Random rand = new Random();
        identifiant = rand.nextInt();
        sejour = sejourReservation;
        voyageur = locataire;
        estValidee = validation;
    }

    public void afficher(){

        System.out.print(voyageur.toString() + " a fait une réservation chez ");
        sejour.afficher();
    }
}
