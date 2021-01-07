package aubert.airbnb.reservations;

import aubert.airbnb.logements.Logement;
import aubert.airbnb.outils.MaDate;
import aubert.airbnb.utilisateurs.Voyageur;

import java.sql.SQLOutput;

public class SejourLong extends Sejour implements ConditionsTarifiairesInterface{

    private final int PROMOTION_EN_POURCENTAGE = 20;
    private int promotion;
    private int tarif;

    public SejourLong(MaDate date, int nuitees, Logement logementSejour, int nombreVoyageurs) {
        super(date, nuitees, logementSejour, nombreVoyageurs);
        int tarifAvantPromo = logementSejour.getTarifParNuit() * nuitees;
        promotion = tarifAvantPromo*PROMOTION_EN_POURCENTAGE/100;
        tarif = tarifAvantPromo - promotion;
    }

    public boolean beneficiePromotion(){
        return true;
    }

    public int getTarif(){
        return tarif;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println(tarif + "( "+ promotion + "€ de promotion).");
    }


}
