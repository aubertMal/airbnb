package aubert.airbnb.menu;
import aubert.airbnb.logements.Appartement;
import aubert.airbnb.logements.Logement;
import aubert.airbnb.logements.Maison;
import aubert.airbnb.reservations.Reservation;
import aubert.airbnb.utilisateurs.Hote;
import aubert.airbnb.utilisateurs.Voyageur;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    static Scanner scanner;
    static File xmlFile = new File("C:/Workspace/logements.xml");

    static ArrayList<Hote> listHotes;
    static ArrayList<Logement> listLogements;
    static ArrayList<Voyageur> listVoyageurs;
    static ArrayList<Reservation> listReservations;



    public static void main(String[] args) {
        System.out.println("Bienvenue cher AirBnB");

        scanner = new Scanner (System.in);

        System.out.println("Voulez vous charger des listes d'un fichier?");
        System.out.println(" 1 : oui");
        System.out.println(" 2 : non");
        System.out.println(" 3: Annuler");
        int fromXml = scanner.nextInt();
        switch (fromXml){
            case 1:
                initFromXml(xmlFile);
                break;
            case 2:
                init();
                break;
            case 3:
                break;
        }

        //listerMenu();

        scanner.close();
    }

    private static void init() {
        listHotes = new ArrayList<>();
        listLogements = new ArrayList<>();
        listVoyageurs = new ArrayList<>();
        listReservations = new ArrayList<>();

        // Création des Hotes
        Hote hote1 = new Hote("Peter", "Bardu", 28, 12);
        Hote hote2 = new Hote("Patrick", "Martin", 32, 12);
        Hote hote3 = new Hote("Jeanne", "Voisin", 26, 24);
        Hote hote4 = new Hote("Maurice", "Meunier", 46, 12);

        listHotes.add(hote1);
        listHotes.add(hote2);
        listHotes.add(hote3);
        listHotes.add(hote4);

        // Création de Logement
        Maison maison1 = new Maison(hote1, 40, "18 Bis rue Romain Rolland, 37230 Fondettes", 140, 2, 500, true);
        Maison maison2 = new Maison(hote1, 35, "146 Rue George Sand, 59553 Cuincy", 120, 2, 200, false);
        Maison maison3 = new Maison(hote1, 60, "13 Rue de la Liberté, 62800 Liévin", 90, 4, 2000, true);
        Appartement appartement1 = new Appartement(hote1, 35, "46 Rue des Canonniers, 59800 Lille", 72, 2, 3, 20);
        Appartement appartement2 = new Appartement(hote1, 35, "111 Rue Colbert, 37000 Tours", 42, 2, 2, 0);

        listLogements.add(maison1);
        listLogements.add(maison2);
        listLogements.add(maison3);
        listLogements.add(appartement1);
        listLogements.add(appartement2);

        // Création de voyageurs
        Voyageur voyageur1 = new Voyageur("Alain", "Favre", 54);
        Voyageur voyageur2 = new Voyageur("Maxime", "Albert", 29);

        listVoyageurs.add(voyageur1);
        listVoyageurs.add(voyageur2);
    }

    private static void initFromXml(File xmlFile) {
        listHotes = new ArrayList<>();
        listLogements = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();
            String root = doc.getDocumentElement().getNodeName();

            NodeList nListLogements = doc.getChildNodes();

            for (int idxLgmt = 0; idxLgmt< nListLogements.getLength(); idxLgmt++){
                Node nNode = nListLogements.item(idxLgmt);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    //enregistrer le Hote
                    Node nodeHote = nNode.getFirstChild();
                    Element hoteElement = (Element) nodeHote;

                    Hote nouveauHote = new Hote(hoteElement.getAttribute("prenom"),
                            hoteElement.getAttribute("nom"),
                            Integer.parseInt(hoteElement.getAttribute("age")),
                            Integer.parseInt(hoteElement.getAttribute("delaiResponse")));
                    nouveauHote.afficher();

                    Menu.listHotes.add(nouveauHote);

                    //enregistrer le logement: récupérer les infos communes aux apparts et aux maisons
                    Element eElement = (Element) nNode;
                    String adresse = eElement.getAttribute("adresse");

                    int tarif = Integer.parseInt(eElement.getAttribute("tarifParNuit"));
                    int superficie = Integer.parseInt(eElement.getAttribute("superficie"));
                    int nbVoyageyrsMax = Integer.parseInt(eElement.getAttribute("nbVoyageyrsMax"));

                    //enregistrer l'appartement
                    if (nNode.getNodeName() == "Appartement") {

                        //(Hote proprietaire, int tarifNuit, String adresseLogement, int superficieLogement, int nombreVoyageursMax, int numEtage,int surfaceBalcon)
                        Appartement nouvelAppart = new Appartement(nouveauHote,
                                tarif,
                                adresse,
                                superficie,
                                nbVoyageyrsMax,
                                Integer.parseInt(eElement.getAttribute("numeroEtage")),
                                Integer.parseInt(eElement.getAttribute("superificieBalcon")));

                        Menu.listLogements.add(nouvelAppart);
                        nouvelAppart.afficher();
                    }

                    //enregistrer Maison
                    if (nNode.getNodeName() == "Maison") {

                        //(Hote proprietaire, int tarifNuit, String adresseLogement, int superficieLogement, int nombreVoyageursMax,
                        // //int superficieDuJardin, boolean isPiscine)
                        Maison nouvelleMaison = new Maison(nouveauHote,
                                tarif,
                                adresse,
                                superficie,
                                nbVoyageyrsMax,
                                Integer.parseInt(eElement.getAttribute("superficieJardin")),
                                (eElement.getAttribute("possedePiscine")=="1")?true:false);

                        Menu.listLogements.add(nouvelleMaison);
                        nouvelleMaison.afficher();
                    }
                }

            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    static void listerMenu(){

        int maxChoix =5;

        for (int i = 0; i < 30 ; i++) {
            System.out.print("-");
        }

        System.out.println("-");
        System.out.println(" Saisir une Option: ");
        System.out.println(" 1 : Liste des Hôtes");
        System.out.println(" 2 : Liste des logements");
        System.out.println(" 3 : Liste des voyageurs");
        System.out.println(" 4 : Liste des réservations");
        System.out.println(" 5 : Fermer le programme");

        switch (choix(maxChoix)){
            case 1:
                GestionHotes.listerHotes();
                break;
            case 2:
                GestionLogements.listerLogements();
                break;
            case 3:
                GestionVoyageurs.listerVoyageurs();
                break;
            case 4:
                GestionReservations.listerReservations();
                break;
            case 5:
                System.out.println("A bientôt!");
            default:
                break;
        }

    }

    static int choix(int maxValue){

        int choixUtilisateur;

        try {
            choixUtilisateur = scanner.nextInt();

            while ((choixUtilisateur < 1) || (choixUtilisateur > maxValue)) {
                System.out.println("Re Saisir une Option comprise entre 1 et " + maxValue);
                choixUtilisateur = scanner.nextInt();
            }
        } catch (Exception e){
            System.out.println("!!!!!!!Erreur de saisie!!!!!!");
            scanner.nextLine();
            choixUtilisateur = choix(maxValue);
        }
        return choixUtilisateur;
    }
}
