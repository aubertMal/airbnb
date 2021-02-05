package aubert.airbnb;

import aubert.airbnb.logements.Appartement;
import aubert.airbnb.logements.Maison;
import aubert.airbnb.outils.MaDate;
import aubert.airbnb.reservations.*;
import aubert.airbnb.utilisateurs.Hote;
import aubert.airbnb.utilisateurs.Voyageur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args){
        int dureeSejour = 3;
        Sejour sejour;

        Hote hote = new Hote("Peter","Bardu",28,2);
        Voyageur locataire = new Voyageur("Maxime", "Albert", 23);

        Maison maison = new Maison(hote,50,"81 Rue Colbert, 37000 Tours.",140,4,200, false);

        Appartement appartement = new Appartement(hote,35, "5 résidence des onzes Arpents 37550 Saint-Avertin",55,2, 0,5);

        MaDate arrivee = new MaDate("15/05/2021");

        System.out.println(arrivee);

        sejour = SejourFactory.getSejour(arrivee, maison, dureeSejour,4);

        Reservation reservation = new Reservation(locataire, true, sejour);

        testStream();
    }

    private static void testStream() {
        ArrayList<String> listeNoms = new ArrayList(Arrays.asList("Béatrice", "Deamon","Alex", "Bob",  "Courtney", "Eric","Alain"));
        listeNoms.stream().sorted().forEach(nom -> System.out.println(nom));
    }
}
