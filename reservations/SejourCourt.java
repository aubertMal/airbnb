package aubert.airbnb.reservations;

import aubert.airbnb.logements.Logement;
import aubert.airbnb.outils.MaDate;

public class SejourCourt extends Sejour implements ConditionsTarifiairesInterface{
    private int tarif;

    public SejourCourt(MaDate date, int nuitees, Logement logementSejour, int nombreVoyageurs) {
        super(date, nuitees, logementSejour, nombreVoyageurs);
        tarif = (logementSejour.getTarifParNuit())*nuitees;
    }

    public boolean beneficiePromotion(){
        return false;
    }

    public int getTarif(){
        return tarif;
    }

    @Override
    public void afficher(){
        super.afficher();
        System.out.println(tarif + "€");
    }
}
