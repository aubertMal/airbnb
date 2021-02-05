package aubert.airbnb.logements;

import aubert.airbnb.utilisateurs.Hote;

public class Maison extends Logement{

    private final int superficieJardin;
    private final boolean possedePiscine;

    public Maison(Hote proprietaire, int tarifNuit, String adresseLogement, int superficieLogement, int nombreVoyageursMax, int superficieDuJardin, boolean isPiscine) {
        super(proprietaire, tarifNuit, adresseLogement, superficieLogement, nombreVoyageursMax);
        superficieJardin = superficieDuJardin;
        possedePiscine = isPiscine;
    }

    public int getSuperifcieTotal(){
        return (getSuperficie() + superficieJardin);
    }

    public boolean getPossedePiscine(){
        return possedePiscine;
    }

    public boolean possedeJardin(){
        return superficieJardin>0;
    }

    public void afficher(){
        System.out.println(""+getHote());
        System.out.println("Le séjour est dans une maison située à "+getAdresse());
        System.out.println("Superficie: " + getSuperficie() + "m².");
        System.out.println("Jardin: " + (superficieJardin>0?"Oui ( "+superficieJardin+"m²)":"Non"));
        System.out.println("Piscine : " + (possedePiscine?"Oui":"Non"));
    }

}
