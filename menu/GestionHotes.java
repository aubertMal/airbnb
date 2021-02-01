package aubert.airbnb.menu;

import aubert.airbnb.utilisateurs.Hote;

import java.util.InputMismatchException;

public class GestionHotes {
    static void listerHotes(){
        for (int i = 0; i < 30 ; i++) {
            System.out.print("-");
        }
        System.out.println("-");

        System.out.println("Liste des Hotes ");
        System.out.println("Saisir une option : ");
        System.out.println("1 : Ajouter un hôte");
        System.out.println("2 : Supprimer un hôte");
        System.out.println("3 : Retour");

        switch (Menu.choix(3)) {
            case 1:
                try {
                    ajouterHote();
                } catch(Exception e){
                    Menu.scanner.next();
                    System.out.println("!!! Erreur de saisie!!!");
                }
                break;
            case 2:
                supprimerHote();
                break;
            case 3:
                Menu.listerMenu();
                break;
    }
}

    private static void supprimerHote() throws IndexOutOfBoundsException, InputMismatchException {

        System.out.println("-------------------------------------");
        System.out.println("Supprimer un hôte");

        System.out.println("Numéro ?");
        int indice = Menu.scanner.nextInt();

        Menu.listHotes.remove(indice);

        listerHotes();
    }


    private static void ajouterHote() throws Exception{

            System.out.println("Saisir le prénom de l'hôte : " );
            String prenom=Menu.scanner.next();

            System.out.println("Saisir le nom de l'hôte: ");
            String nom=Menu.scanner.next();

            System.out.println("Saisir l'âge de l'hôte: ");
            int age = Menu.scanner.nextInt();

            System.out.println("Saisir le délai de réponse: ");
            int delai = Menu.scanner.nextInt();

            Hote nouveauHote = new Hote(prenom, nom, age, delai);
            Menu.listHotes.add(nouveauHote);

            listerHotes();
        }
    }
