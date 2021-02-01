package aubert.airbnb.menu;

import aubert.airbnb.outils.MaDate;
import aubert.airbnb.reservations.Reservation;
import aubert.airbnb.reservations.Sejour;
import aubert.airbnb.reservations.SejourCourt;
import aubert.airbnb.reservations.SejourLong;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public class GestionReservations {
    static void listerReservations() {

        System.out.println("-------------------------------------");
        System.out.println("Liste des réservations ");

        for (int i = 0; i < Menu.listReservations.size(); i++) {
            System.out.print("Numéro " + i + " :");
            Menu.listReservations.get(i).afficher();
        }

        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter une réservation");
        System.out.println("2 : Supprimer une réservation");
        System.out.println("3 : Retour");

        try {
            switch (Menu.choix(3)) {
                case 1:
                    ajouterReservation();
                    break;
                case 2:
                    supprimerReservation();
                    break;
                case 3:
                    Menu.listerMenu();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue (" + e.getMessage() + ")");
            Menu.scanner.next();
            listerReservations();
        }
    }

    private static void ajouterReservation() throws Exception {

        System.out.println("-------------------------------------");
        System.out.println("Ajouter une nouvelle réservation");

        System.out.println("Liste des voyageurs : ");

        for (int i = 0; i < Menu.listVoyageurs.size(); i++) {
            System.out.print("Numéro " + i + " : ");
            Menu.listVoyageurs.get(i).afficher();
            System.out.println();
        }
        System.out.print("Numéro du voyageur : ");
        int numeroVoyageur = Menu.scanner.nextInt();
        System.out.print("Date d'arrivée (DD/MM/YYYY) : ");
        String strDate = Menu.scanner.next();
        System.out.print("Nombre de nuits : ");
        int nombreDeNuits = Menu.scanner.nextInt();
        System.out.println("Liste des logements : ");

        for (int i = 0; i < Menu.listLogements.size(); i++) {
            System.out.print("Numéro " + i + " : ");
            Menu.listLogements.get(i).afficher();
        }
        System.out.print("Numéro du logement : ");
        int numeroLogement = Menu.scanner.nextInt();
        System.out.print("Nombre de personnes : ");
        int nombreDePersonnes = Menu.scanner.nextInt();

        // Création d'un sejour
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        MaDate dateArrivee = (MaDate) simpleDateFormat.parse(strDate);

        Sejour sejour;
        if (nombreDeNuits > 5) {
            sejour = new SejourLong(dateArrivee, nombreDeNuits, Menu.listLogements.get(numeroLogement),
                    nombreDePersonnes);
        } else {
            sejour = new SejourCourt(dateArrivee, nombreDeNuits, Menu.listLogements.get(numeroLogement),
                    nombreDePersonnes);
        }

        Reservation newReservation = new Reservation(Menu.listVoyageurs.get(numeroVoyageur),false,sejour);
        Menu.listReservations.add(newReservation);

        System.out.println("Votre réservation a été ajoutée avec succès");

        try {

            FileWriter fw = new FileWriter("reservation_" + 0 + ".txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("Numéro du voyageur : " + numeroVoyageur);
            pw.println("Numéro du logement : " + numeroLogement);
            pw.println("Date d'arrivée (DD/MM/YYYY) : " + strDate);
            pw.println("Nombre de nuits : " + nombreDeNuits);
            pw.println("Nombre de personnes : " + nombreDePersonnes);

            pw.close();
        } catch (Exception e) {
            System.err.println("error");
        }

        listerReservations();
    }

    private static void supprimerReservation() throws Exception {

        System.out.println("-------------------------------------");
        System.out.println("Supprimer une réservation");

        System.out.print("Numéro : ");
        int numero = Menu.scanner.nextInt();
        System.out.println();

        Menu.listReservations.remove(numero);

        System.out.println("Votre réservation a été supprimée avec succès");

        listerReservations();
    }
}
