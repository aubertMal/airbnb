package aubert.airbnb.menu;

import aubert.airbnb.utilisateurs.Voyageur;

import java.util.ArrayList;

public class GestionVoyageurs {
    static void listerVoyageurs() {

        System.out.println("-------------------------------------");
        System.out.println("Liste des voyageurs ");

        for (int i = 0; i < Menu.airBnBData.getListVoyageurs().size(); i++) {
            System.out.print("Numéro " + i + " :");
            Menu.airBnBData.getListVoyageurs().get(i).afficher();
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

        ArrayList<Voyageur> listVoyageurTemp = Menu.airBnBData.getListVoyageurs();

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
        listVoyageurTemp.add(newVoyageur);

        System.out.println("Votre voyageur a été ajouté avec succès");

        Menu.airBnBData.setListVoyageurs(listVoyageurTemp);

        listerVoyageurs();
    }

    private static void supprimerVoyageur() throws Exception {

        ArrayList<Voyageur> listVoyageurTemp= Menu.airBnBData.getListVoyageurs();

        System.out.println("-------------------------------------");
        System.out.println("Supprimer un voyageur");

        System.out.print("Numéro : ");
        int numero = Menu.scanner.nextInt();
        System.out.println();

        listVoyageurTemp.remove(numero);

        System.out.println("Votre voyageur a été supprimé avec succès");

        Menu.airBnBData.setListVoyageurs(listVoyageurTemp);
        listerVoyageurs();
    }
}
