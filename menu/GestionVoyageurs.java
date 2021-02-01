package aubert.airbnb.menu;

import aubert.airbnb.utilisateurs.Voyageur;

public class GestionVoyageurs {
    static void listerVoyageurs() {

        System.out.println("-------------------------------------");
        System.out.println("Liste des voyageurs ");

        for (int i = 0; i < Menu.listVoyageurs.size(); i++) {
            System.out.print("Numéro " + i + " :");
            Menu.listVoyageurs.get(i).afficher();
            System.out.println();
        }

        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter un voyageur");
        System.out.println("2 : Supprimer un voyageur");
        System.out.println("3 : Retour");

        try {
            switch (Menu.choix(3)) {
                case 1:
                    ajouterVoyageur();
                    break;
                case 2:
                    supprimerVoyageur();
                    break;
                case 3:
                    Menu.listerMenu();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue");
            Menu.scanner.next();
            listerVoyageurs();
        }
    }

    private static void ajouterVoyageur() throws Exception {

        System.out.println("-------------------------------------");
        System.out.println("Ajouter un nouvel hôte");

        System.out.print("Prénom : ");
        String prenom = Menu.scanner.next();
        System.out.print("Nom : ");
        String nom = Menu.scanner.next();
        System.out.print("Age : ");
        int age = Menu.scanner.nextInt();
        System.out.println();

        Voyageur newVoyageur = new Voyageur(prenom, nom, age);
        Menu.listVoyageurs.add(newVoyageur);

        System.out.println("Votre voyageur a été ajouté avec succès");

        listerVoyageurs();
    }

    private static void supprimerVoyageur() throws Exception {

        System.out.println("-------------------------------------");
        System.out.println("Supprimer un voyageur");

        System.out.print("Numéro : ");
        int numero = Menu.scanner.nextInt();
        System.out.println();

        Menu.listVoyageurs.remove(numero);

        System.out.println("Votre voyageur a été supprimé avec succès");

        listerVoyageurs();
    }
}
