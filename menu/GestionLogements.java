package aubert.airbnb.menu;

import aubert.airbnb.logements.Appartement;
import aubert.airbnb.logements.Logement;
import aubert.airbnb.logements.Maison;
import aubert.airbnb.utils.Search;

import java.util.ArrayList;

public class GestionLogements {

    static Search.SearchBuilder searchBuilder = new Search.SearchBuilder(3)
            .setPossedeJardin(false)
            .setTarifMinParNuit(100)
            .setPossedeBalcon(true)
            .setTarifMaxParNuit(220);
    static Search search = searchBuilder.build();

    static void listerLogements() {

        System.out.println("-------------------------------------");
        System.out.println("Liste des logements ");

        for (int i = 0; i < Menu.airBnBData.getListLogements().size(); i++) {
            System.out.print("Numéro " + i + " :");
            Menu.airBnBData.getListLogements().get(i).afficher();
        }

        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter un logement");
        System.out.println("2 : Supprimer un logement");
        System.out.println("3 : Rechercher un logement");
        System.out.println("4 : Retour");

        try {

            switch (Menu.choix(4)) {
                case 1:
                    ajouterLogement();
                    break;
                case 2:
                    supprimerLogement();
                    break;
                case 3:
                    System.out.println("1: Rechercher par nom");
                    System.out.println("2: Rechercher par critères");
                    System.out.println("3: Annuler");
                    int choixRecherche = Menu.scanner.nextInt();

                    switch (choixRecherche){
                        case 1:
                            System.out.println("Saisir le nom à rechercher");
                            String nomLogement = Menu.scanner.next();

                            Logement logement = getLogementByName(nomLogement);
                            if (logement == null)
                                System.out.println("Ce logement n'existe pas");
                            else
                                logement.afficher();
                            break;
                        case 2:
                            ArrayList<Logement> listRechercheLogements = rechercheLogementParCritere();
                            if (listRechercheLogements.size()>0){
                                listRechercheLogements.forEach(Logement::afficher);
                            }
                            else
                            {
                                System.out.println("Aucun logement ne répond à cette recherche");
                            }
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 4:
                    Menu.listerMenu();
                    break;
            }

        } catch (Exception e) {
            System.out.println("Une erreur est survenue"+ e.getMessage());
            Menu.scanner.next();
            listerLogements();
        }
    }

    private static ArrayList<Logement> rechercheLogementParCritere() {
        return search.result();
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

        ArrayList<Logement> listLogementsTemps = Menu.airBnBData.getListLogements();

        System.out.println("-------------------------------------");
        System.out.println("Ajouter une maison");

        System.out.println("Liste des hôtes : ");

        for (int i = 0; i < listLogementsTemps.size(); i++) {
            System.out.print("Numéro " + i + " :");
            listLogementsTemps.get(i).afficher();
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
        System.out.print("Nom de la maison : ");
        String nomMaison = Menu.scanner.next();
        System.out.print("Superficie du jardin : ");
        int superficieJardin = Menu.scanner.nextInt();
        System.out.print("Piscine (0 : non, 1 : oui) : ");
        boolean possedePiscine = Menu.scanner.nextInt() == 1;
        System.out.println();

        Maison newMaison = new Maison(Menu.airBnBData.getListHotes().get(numeroHote),
                tarifJournalier, adresse, supperficie, nbVoyageur,
                nomMaison,superficieJardin, possedePiscine);
        listLogementsTemps.add(newMaison);

        System.out.println("Votre maison a été ajoutée avec succès");

        Menu.airBnBData.setListLogements(listLogementsTemps);

        listerLogements();
    }

    private static void ajouterUnAppartement() throws Exception {
        ArrayList<Logement> listLogementsTemp = Menu.airBnBData.getListLogements();

        System.out.println("-------------------------------------");
        System.out.println("Ajouter un appartement");

        System.out.println("Liste des hôtes : ");

        for (int i = 0; i < Menu.airBnBData.getListHotes().size(); i++) {
            System.out.print("Numéro " + i + " :");
            Menu.airBnBData.getListHotes().get(i).afficher();
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
        System.out.print("Nom de l'appartement : ");
        String nomAppart = Menu.scanner.next();
        System.out.print("Superficie du balcon : ");
        int superficieBalcon = Menu.scanner.nextInt();
        System.out.print("Numéro de l'étage : ");
        int numEtage = Menu.scanner.nextInt();
        System.out.println();

        Appartement newAppartement = new Appartement(
                Menu.airBnBData.getListHotes().get(numeroHote), tarifJournalier, adresse,
                supperficie, nbVoyageur, nomAppart,numEtage, superficieBalcon);
        listLogementsTemp.add(newAppartement);

        System.out.println("Votre appartement a été ajouté avec succès");

        Menu.airBnBData.setListLogements(listLogementsTemp);

        listerLogements();
    }

    private static void supprimerLogement() throws Exception {

        ArrayList<Logement> listLogementsTemp = Menu.airBnBData.getListLogements();

        System.out.println("-------------------------------------");
        System.out.println("Supprimer un hôte");

        System.out.print("Numéro : ");
        int numero = Menu.scanner.nextInt();
        System.out.println();

        listLogementsTemp.remove(numero);

        System.out.println("Votre logement a été supprimé avec succès");

        Menu.airBnBData.setListLogements(listLogementsTemp);
        listerLogements();
    }

     static <T extends Logement> T getLogementByName(String nomLogement) {

            for (Logement logement : Menu.airBnBData.getListLogements()) {
                if (logement.getName().equals(nomLogement)) {
                    return (T) logement;
                }
            }
            return null;
    }

}
