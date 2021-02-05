package aubert.airbnb.logements;

import aubert.airbnb.outils.Comparable;
import aubert.airbnb.utilisateurs.Hote;


public abstract class Logement implements Comparable {
    private String nom;
    private final Hote hote;
    private final int tarifParNuit;
    private final String adresse;
    private final int superficie;
    private final int nbVoyageursMax;

    public Logement(Hote proprietaire, int tarifNuit, String adresseLogement, int superficieLogement,int nombreVoyageursMax){
        hote = proprietaire;
        tarifParNuit = tarifNuit;
        adresse= adresseLogement;
        superficie = superficieLogement;
        nbVoyageursMax = nombreVoyageursMax;
    }

    public int getTarifParNuit(){
        return tarifParNuit;
    }

    public Hote getHote() {
        return hote;
    }

    public String getAdresse(){
        return adresse;
    }

    public int getSuperficie(){
        return superficie;
    }

    public int getNbVoyageursMax() {
        return nbVoyageursMax;
    }

    public String getName(){
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int getValueToCompare(){
        return tarifParNuit;
    }

    public abstract int getSuperifcieTotal();
    public abstract void afficher();
}
