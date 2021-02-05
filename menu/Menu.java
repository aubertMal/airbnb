package aubert.airbnb.menu;
import aubert.airbnb.logements.Appartement;
import aubert.airbnb.logements.Logement;
import aubert.airbnb.logements.Maison;
import aubert.airbnb.reservations.Reservation;
import aubert.airbnb.utilisateurs.Hote;
import aubert.airbnb.utilisateurs.Voyageur;
import aubert.airbnb.utils.AirBnBData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    static Scanner scanner;
    static File xmlFile = new File("C:/Workspace/logements.xml");

    public static AirBnBData airBnBData = AirBnBData.getInstance();

    static ArrayList<Reservation> listReservations = new ArrayList<>();

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

        listerMenu();

        scanner.close();
    }

    private static void init() {

        ArrayList<Hote> listHotesTemp = new ArrayList<>();
        ArrayList<Logement> listLogementsTemp = new ArrayList<>();
        ArrayList<Voyageur> listVoyageursTemp = new ArrayList<>();

        // Création des Hotes
        Hote hote1 = new Hote("Peter", "Bardu", 28, 12);
        Hote hote2 = new Hote("Patrick", "Martin", 32, 12);
        Hote hote3 = new Hote("Jeanne", "Voisin", 26, 24);
        Hote hote4 = new Hote("Maurice", "Meunier", 46, 12);

        listHotesTemp.add(hote1);
        listHotesTemp.add(hote2);
        listHotesTemp.add(hote3);
        listHotesTemp.add(hote4);

        airBnBData.setListHotes(listHotesTemp);

        // Création de Logement
        Maison maison1 = new Maison(hote1, 40, "18 Bis rue Romain Rolland, 37230 Fondettes", 140, 5, "Maison1",500, true);
        Maison maison2 = new Maison(hote1, 120, "146 Rue George Sand, 59553 Cuincy", 120, 2, "Maison2",200, false);
        Maison maison3 = new Maison(hote1, 150, "13 Rue de la Liberté, 62800 Liévin", 90, 4, "Maison3", 2000, true);
        Appartement appartement1 = new Appartement(hote1, 180, "46 Rue des Canonniers, 59800 Lille", 72, 6, "Appartement1", 3, 20);
        Appartement appartement2 = new Appartement(hote1, 150, "111 Rue Colbert, 37000 Tours", 80, 5, "Appartement2",2, 20);

        listLogementsTemp.add(maison1);
        listLogementsTemp.add(maison2);
        listLogementsTemp.add(maison3);
        listLogementsTemp.add(appartement1);
        listLogementsTemp.add(appartement2);

        airBnBData.setListLogements(listLogementsTemp);

        // Création de voyageurs
        Voyageur voyageur1 = new Voyageur("Alain", "Favre", 54);
        Voyageur voyageur2 = new Voyageur("Maxime", "Albert", 29);

        listVoyageursTemp.add(voyageur1);
        listVoyageursTemp.add(voyageur2);

        airBnBData.setListVoyageurs(listVoyageursTemp);
    }

    private static void initFromXml(File xmlFile) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            //Rechercher les appartements
            NodeList nListAppartements = doc.getElementsByTagName("Appartement");
            System.out.println(nListAppartements.getLength() + "Appartements");

            if (nListAppartements.getLength()>1)
                addElement(nListAppartements);

            //Rechercher les maisons
            NodeList nListMaisons = doc.getElementsByTagName("Maison");
            System.out.println(nListMaisons.getLength() + "Maisons");

            if (nListAppartements.getLength()>1)
                addElement(nListMaisons);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void addElement(NodeList nList) {

        ArrayList<Hote> listHotesTemp = new ArrayList<>();
        ArrayList<Logement> listLogementsTemp = new ArrayList<>();

        //On boucle sur les logements
        for (int idxLgmt = 0; idxLgmt< nList.getLength(); idxLgmt++){
            Node nNode = nList.item(idxLgmt);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                String tarif ="";
                String adresse ="";
                String superficie = "";
                String nbVoyageursMax ="";
                String numeroEtage = "";
                String superificeBalcon = "";
                String superficieJardin = "";
                String possedePiscine = "";

                String nom = "";
                String prenom = "";
                String age = "";
                String delai = "";

                //On boucle sur les infos d'un logement
                NodeList infosLogement = nNode.getChildNodes();
                for (int idxInfoLogement = 0; idxInfoLogement < infosLogement.getLength(); idxInfoLogement++) {
                    //On parcourt les éléments d'un logement un par un
                    Node nInfoLogement = infosLogement.item(idxInfoLogement);
                    //Si c'est un élément on récupère l'info
                    if (nInfoLogement.getNodeType() == Node.ELEMENT_NODE){
                        //On regarde de quel élément il s'agit
                        String nodeName = nInfoLogement.getNodeName();
                        switch (nodeName){
                            case "hote":
                                //enregistrer l'Hote

                                Node nodeHote = nNode.getFirstChild().getNextSibling();

                                NodeList listInfos = nodeHote.getChildNodes();
                                for (int i = 0; i < listInfos.getLength(); i++) {
                                    Node infoNode = listInfos.item(i);
                                    if (infoNode.getNodeType() == Node.ELEMENT_NODE) {

                                        switch (infoNode.getNodeName()) {
                                            case "nom":
                                                nom = infoNode.getTextContent();
                                                break;
                                            case "prenom":
                                                prenom = infoNode.getTextContent();
                                                break;
                                            case "age":
                                                age = infoNode.getTextContent();
                                                break;
                                            case "delaiReponse":
                                                delai = infoNode.getTextContent();
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }

                                break;

                            case "tarifParNuit":
                                tarif = nInfoLogement.getTextContent();
                                break;

                            case "adresse":
                                adresse = nInfoLogement.getTextContent();
                                break;

                            case "superficie":
                                superficie = nInfoLogement.getTextContent();
                                break;

                            case "nbVoyageursMax":
                                nbVoyageursMax = nInfoLogement.getTextContent();
                                break;

                            case "numeroEtage":
                                numeroEtage = nInfoLogement.getTextContent();
                                break;

                            case "superficieBalcon":
                                superificeBalcon = nInfoLogement.getTextContent();
                                break;

                            case "superficieJardin":
                                superficieJardin = nInfoLogement.getTextContent();
                                break;

                            case "possedePiscine":
                                possedePiscine = nInfoLogement.getTextContent();
                                break;

                            default:
                                break;
                        }

                    }
                }

                //enregistrer l'Hote
                Hote nouveauHote = new Hote(nom,
                        prenom,
                        Integer.parseInt(age),
                        Integer.parseInt(delai));
                nouveauHote.afficher();

                listHotesTemp.add(nouveauHote);

                String nomLogement = nNode.getAttributes().item(0).getTextContent();
                //enregistrer l'appartement
                if (nNode.getNodeName() == "Appartement") {

                    Appartement nouvelAppart = new Appartement(nouveauHote,
                            Integer.parseInt(tarif),
                            adresse,
                            Integer.parseInt(superficie),
                            Integer.parseInt(nbVoyageursMax),
                            nomLogement,
                            Integer.parseInt(numeroEtage),
                            Integer.parseInt(superificeBalcon));

                    listLogementsTemp.add(nouvelAppart);
                    nouvelAppart.afficher();
                }

                //enregistrer Maison
                if (nNode.getNodeName() == "Maison") {

                    Maison nouvelleMaison = new Maison(nouveauHote,
                            Integer.parseInt(tarif),
                            adresse,
                            Integer.parseInt(superficie),
                            Integer.parseInt(nbVoyageursMax),
                            nomLogement,
                            Integer.parseInt(superficieJardin),
                            (possedePiscine=="1")?true:false);

                    listLogementsTemp.add(nouvelleMaison);
                    nouvelleMaison.afficher();
                }
            }

        }

        airBnBData.setListLogements(listLogementsTemp);
        airBnBData.setListHotes(listHotesTemp);
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
