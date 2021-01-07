package aubert.airbnb.reservations;

import aubert.airbnb.logements.Logement;
import aubert.airbnb.outils.MaDate;

public class SejourLong extends Sejour implements ConditionsTarifiairesInterface{

    private final int PROMOTION_EN_POURCENTAGE = 20;
    private int promotion;

    public SejourLong(MaDate date, int nuitees, Logement logementSejour, int nombreVoyageurs) {
        super(date, nuitees, logementSejour, nombreVoyageurs);
    }

    public boolean beneficiePromotion(){
        return true;
    }

    public boolean verificationNombreDeNuits(){
        return (super.nbNuits >= 1 && super.nbNuits <= 31);
    }

    @Override
    public int miseAJourDuTarif() {
        int tarifAvantPromo = super.logement.getTarifParNuit() * super.nbNuits;
        promotion = tarifAvantPromo*PROMOTION_EN_POURCENTAGE/100;
        return tarifAvantPromo - promotion;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("( "+ promotion + "€ de promotion).");
    }


}
