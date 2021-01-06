package aubert.airbnb;

import aubert.airbnb.logements.Appartement;
import aubert.airbnb.logements.Maison;
import aubert.airbnb.outils.MaDate;
import aubert.airbnb.outils.Utile;
import aubert.airbnb.reservations.Reservation;
import aubert.airbnb.reservations.Sejour;
import aubert.airbnb.utilisateurs.Hote;
import aubert.airbnb.utilisateurs.Voyageur;
import java.util.Date;

public class Main {

    public static void main(String[] args){
        Hote hote = new Hote("Peter","Bardu",28,2);
        Voyageur locataire = new Voyageur("Maxime", "Albert", 23);

        Maison maison = new Maison(hote,50,"81 Rue Colbert, 37000 Tours.",140,4, 200, false);

        Appartement appartement = new Appartement(hote,35, "5 résidence des onzes Arpents 37550 Saint-Avertin",55,2,0,5);

        //Date arrivee = Utile.setDateFromString("5/01/2016");

        MaDate arrivee = new MaDate("15/05/2021");

        System.out.println(arrivee);

        System.out.println("****Première réservation****");
        Sejour sejour = new Sejour(arrivee,4, maison,4);
        Reservation reservation = new Reservation(locataire, true, sejour);
        reservation.afficher();


        System.out.println("****Deuxième réservation****");
        Sejour sejour2 = new Sejour(arrivee,4, appartement,4);
        Reservation reservation2 = new Reservation(locataire, true, sejour2);
        reservation2.afficher();

    }
}