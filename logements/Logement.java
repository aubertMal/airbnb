package aubert.airbnb.logements;

import aubert.airbnb.utilisateurs.Hote;


public abstract class Logement {
    private Hote hote;
    private int tarifParNuit;
    private String adresse;
    private int superficie;
    private int nbVoyageursMax;
    private String nom;

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

    public abstract int getSuperifcieTotal();
    public abstract void afficher();
}
