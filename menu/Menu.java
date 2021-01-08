package aubert.airbnb.menu;
import java.util.Scanner;

public class Menu {

    static Scanner scanner;
    public static void main(String[] args) {
        System.out.println("Bienvenue cher AirBnB");

        scanner = new Scanner (System.in);

        listerMenu();

        scanner.close();
    }

    static void listerMenu(){

        int maxChoix =5;

        for (int i = 0; i < 30 ; i++) {
            System.out.print("-");
        }

        System.out.println(" Saisir une Option: ");
        System.out.println(" 1 : Liste des Hôtes");
        System.out.println(" 2 : Liste des logements");
        System.out.println(" 3 : Liste des voyageurs");
        System.out.println(" 4 : Liste des réservations");
        System.out.println(" 5 : Fermer le programme");

        System.out.println("l'utilisateur a choisi : " + choix(maxChoix));

    }

    static int choix(int maxValue){

        int choixUtilisateur;

        try {
            choixUtilisateur = scanner.nextInt();

            while ((choixUtilisateur < 1) || (choixUtilisateur > maxValue)) {
                System.out.println("Re Saisir une Option comprise entre 1 et " + maxValue);
                choixUtilisateur = scanner.nextInt();
            } ;
        } catch (Exception e){
            System.out.println("!!!!!!!Erreur de saisie!!!!!!");
            scanner.nextLine();
            choixUtilisateur = choix(maxValue);
        }
        return choixUtilisateur;
    }
}
