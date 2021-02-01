package aubert.airbnb.menu;

import aubert.airbnb.logements.Appartement;
import aubert.airbnb.logements.Maison;

public class GestionLogements {

    static void listerLogements() {

        System.out.println("-------------------------------------");
        System.out.println("Liste des logements ");

        for (int i = 0; i < Menu.listLogements.size(); i++) {
            System.out.print("Numéro " + i + " :");
            Menu.listLogements.get(i).afficher();
        }

        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter un logement");
        System.out.println("2 : Supprimer un logement");
        System.out.println("3 : Retour");

        try {

            switch (Menu.choix(3)) {
                case 1:
                    ajouterLogement();
                    break;
                case 2:
                    supprimerLogement();
                    break;
                case 3:
                    Menu.listerMenu();
                    break;
            }

        } catch (Exception e) {
            System.out.println("Une erreur est survenue");
            Menu.scanner.next();
            listerLogements();
        }
    }

    private static void ajouterLogement() throws Exception {

        System.out.println("-------------------------------------");
        System.out.println("Ajouter un nouveau logement");

        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter une maison");
        System.out.println("2 : Ajouter un appartement");
        System.out.println("3 : Retour");

        switch (Menu.choix(3)) {
            case 1:
                ajouterUneMaison();
                break;
            case 2:
                ajouterUnAppartement();
                break;
            case 3:
                listerLogements();
                break;
        }
    }

    private static void ajouterUneMaison() throws Exception {

        System.out.println("-------------------------------------");
        System.out.println("Ajouter une maison");

        System.out.println("Liste des hôtes : ");

        for (int i = 0; i < Menu.listHotes.size(); i++) {
            System.out.print("Numéro " + i + " :");
            Menu.listHotes.get(i).afficher();
        }
        System.out.print("Numéro de l'hôte : ");
        int numeroHote = Menu.scanner.nextInt();
        System.out.print("Tarif journalier : ");
        int tarifJournalier = Menu.scanner.nextInt();
        System.out.print("Adresse : ");
        String adresse = Menu.scanner.next();
        System.out.print("Superficie : ");
        int supperficie = Menu.scanner.nextInt();
        System.out.print("Nombre de voyageurs max : ");
        int nbVoyageur = Menu.scanner.nextInt();
        System.out.print("Superficie du jardin : ");
        int superficieJardin = Menu.scanner.nextInt();
        System.out.print("Piscine (0 : non, 1 : oui) : ");
        boolean possedePiscine = Menu.scanner.nextInt() == 1;
        System.out.println();

        Maison newMaison = new Maison(Menu.listHotes.get(numeroHote),
                tarifJournalier, adresse, supperficie, nbVoyageur,
                superficieJardin, possedePiscine);
        Menu.listLogements.add(newMaison);

        System.out.println("Votre maison a été ajoutée avec succès");

        listerLogements();
    }

    private static void ajouterUnAppartement() throws Exception {

        System.out.println("-------------------------------------");
        System.out.println("Ajouter un appartement");

        System.out.println("Liste des hôtes : ");

        for (int i = 0; i < Menu.listHotes.size(); i++) {
            System.out.print("Numéro " + i + " :");
            Menu.listHotes.get(i).afficher();
        }
        System.out.print("Numéro de l'hôte : ");
        int numeroHote = Menu.scanner.nextInt();
        System.out.print("Tarif journalier : ");
        int tarifJournalier = Menu.scanner.nextInt();
        System.out.print("Adresse : ");
        String adresse = Menu.scanner.next();
        System.out.print("Superficie : ");
        int supperficie = Menu.scanner.nextInt();
        System.out.print("Nombre de voyageurs max : ");
        int nbVoyageur = Menu.scanner.nextInt();
        System.out.print("Superficie du balcon : ");
        int superficieBalcon = Menu.scanner.nextInt();
        System.out.print("Numéro de l'étage : ");
        int numEtage = Menu.scanner.nextInt();
        System.out.println();

        Appartement newAppartement = new Appartement(
                Menu.listHotes.get(numeroHote), tarifJournalier, adresse,
                supperficie, nbVoyageur, numEtage, superficieBalcon);
        Menu.listLogements.add(newAppartement);

        System.out.println("Votre appartement a été ajouté avec succès");

        listerLogements();
    }

    private static void supprimerLogement() throws Exception {

        System.out.println("-------------------------------------");
        System.out.println("Supprimer un hôte");

        System.out.print("Numéro : ");
        int numero = Menu.scanner.nextInt();
        System.out.println();

        Menu.listLogements.remove(numero);

        System.out.println("Votre logement a été supprimé avec succès");

        listerLogements();
    }

}
