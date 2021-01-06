package aubert.airbnb.logements;

import aubert.airbnb.utilisateurs.Hote;

public class Maison extends Logement{

    private int superficieJardin = 0;
    private boolean possedePiscine = false;

    public Maison(Hote proprietaire, int tarifNuit, String adresseLogement, int superficieLogement, int nombreVoyageursMax, int superficieDuJardin, boolean isPiscine) {
        super(proprietaire, tarifNuit, adresseLogement, superficieLogement, nombreVoyageursMax);
        superficieJardin = superficieDuJardin;
        possedePiscine = isPiscine;
    }

    public int getSuperifcieTotal(){
        return (getSuperficie() + superficieJardin);
    }

    public void afficher(){
        System.out.println(""+getHote());
        System.out.println("Le séjour est dans une maison située à "+getAdresse());
        System.out.println("Superficie: " + getSuperficie() + "m².");
        System.out.println("Jardin: " + (superficieJardin>0?"Oui ( "+superficieJardin+"m²)":"Non"));
        System.out.println("Piscine : " + (possedePiscine?"Oui":"Non"));
    }
}
