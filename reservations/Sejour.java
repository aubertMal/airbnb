package aubert.airbnb.reservations;

import aubert.airbnb.logements.Logement;
import aubert.airbnb.outils.MaDate;
import aubert.airbnb.utilisateurs.Voyageur;

import java.util.Date;

public class Sejour implements SejourInterface{
    private MaDate dateArrivee;
    private int nbNuits;
    private Logement logement;
    private int nbVoyageurs;

    public Sejour(MaDate date,int nuitees, Logement logementSejour, int nombreVoyageurs){
        dateArrivee = date;
        nbNuits = nuitees;
        logement = logementSejour;
        nbVoyageurs = nombreVoyageurs;
    }


    /**
     * Cette méthode vérifie si le nombre de voyageurs ne dépaasent pas la capacité d'accueil
     * @return true or false
     */

    public boolean verificationNombreDeVoyageurs(){
        return nbVoyageurs <= logement.getNbVoyageursMax() && nbVoyageurs > 0;
    }

    public boolean verificationNombreDeNuits(){
        return (nbNuits >= 1 && nbNuits <= 31);
    }

    /**
     * Cette méthode prend en paramètres une date de séjour et vérifie si c'est après la date actuelle
     * @return true or false
     */
    public boolean verificationDateArrivee(){
        return dateArrivee.after(new Date());
    }

    public void afficher(){

        logement.afficher();
        System.out.println("La date d'arrivée est le " + dateArrivee.toString(dateArrivee) + " pour " + nbNuits + " nuits.");
        System.out.print("Le prix de ce séjour est de ");
    }
}

