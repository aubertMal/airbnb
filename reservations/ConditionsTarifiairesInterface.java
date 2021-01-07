package aubert.airbnb.reservations;

public interface ConditionsTarifiairesInterface {

    static boolean beneficiePromotion(){
        return false;
    }

    static int getTarif(){
        return 0;
    }
}
