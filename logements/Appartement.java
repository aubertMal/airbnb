package aubert.airbnb.logements;

import aubert.airbnb.utilisateurs.Hote;

public class Appartement extends Logement{

    private final int numeroEtage;
    private final int superficieBalcon;

    public Appartement(Hote proprietaire, int tarifNuit, String adresseLogement, int superficieLogement, int nombreVoyageursMax, String nomAppart, int numEtage,int surfaceBalcon) {
        super(proprietaire, tarifNuit, adresseLogement, superficieLogement, nombreVoyageursMax,nomAppart);
        numeroEtage = numEtage;
        superficieBalcon=surfaceBalcon;
    }

    public int getSuperifcieTotal(){
        return (getSuperficie()+superficieBalcon);
    }

    public boolean possedeBalcon(){
        return superficieBalcon>0;
    }

    public void afficher(){
        System.out.println(""+getHote());
        System.out.println("Le séjour est dans un appartement situé à "+getAdresse()+" au"+(numeroEtage==0?" rez-de-chaussée":numeroEtage==1?numeroEtage+"er":numeroEtage+"ème"));
        System.out.println("Superficie: " + getSuperficie() + "m².");
        System.out.println("Balcon: " + (superficieBalcon>0?"Oui ( "+superficieBalcon+"m²)":"Non"));
    }
}
