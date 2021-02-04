package aubert.airbnb.reservations;

import aubert.airbnb.logements.Logement;
import java.util.Date;

public abstract class Sejour implements SejourInterface{
    private Date dateArrivee;
    protected int nbNuits;
    protected Logement logement;
    private int nbVoyageurs;
    private int tarif;

    public Sejour(Date date,int nuitees, Logement logementSejour, int nombreVoyageurs){
        dateArrivee = date;
        nbNuits = nuitees;
        logement = logementSejour;
        nbVoyageurs = nombreVoyageurs;
        tarif = miseAJourDuTarif();
    }


    /**
     * Cette méthode vérifie si le nombre de voyageurs ne dépaasent pas la capacité d'accueil
     * @return true or false
     */

    public boolean verificationNombreDeVoyageurs(){
        return nbVoyageurs <= logement.getNbVoyageursMax() && nbVoyageurs > 0;
    }

    /**
     * Cette méthode prend en paramètres une date de séjour et vérifie si c'est après la date actuelle
     * @return true or false
     */
    public boolean verificationDateArrivee(){
        return dateArrivee.after(new Date());
    }

    public abstract int miseAJourDuTarif();

    public void afficher(){

        logement.afficher();
        System.out.println("La date d'arrivée est le " + dateArrivee + " pour " + nbNuits + " nuits.");
        System.out.print("Le prix de ce séjour est de " + tarif + " €");
    }
}

