package aubert.airbnb;
import aubert.airbnb.outils.Comparable;

public class Compare <T extends Comparable>{
    private T object1;
    private T object2;

    public Compare(T object1, T object2){
        this.object1 = object1;
        this.object2 = object2;
    }

    public T getHigher(){

        if(object1.getValueToCompare() > object2.getValueToCompare()) {
            return object1;
        }
        return object2;

    }

    public T getLower(){
        return (object1.getValueToCompare() < object2.getValueToCompare()) ? object1 : object2;
    }

}
