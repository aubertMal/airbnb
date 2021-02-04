package aubert.airbnb.reservations;

import aubert.airbnb.logements.Logement;
import aubert.airbnb.outils.MaDate;

import java.util.Date;

public class SejourCourt extends Sejour implements ConditionsTarifiairesInterface{

    public SejourCourt(Date date, int nuitees, Logement logementSejour, int nombreVoyageurs) {
        super(date, nuitees, logementSejour, nombreVoyageurs);
    }

    @Override
    public int miseAJourDuTarif() {
        return super.logement.getTarifParNuit() * super.nbNuits;
    }

    public boolean beneficiePromotion(){
        return false;
    }

    public boolean verificationNombreDeNuits(){
        return (super.nbNuits >= 1 && super.nbNuits <= 31);
    }

    @Override
    public void afficher(){
        super.afficher();
    }
}
