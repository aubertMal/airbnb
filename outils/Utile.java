package aubert.airbnb.outils;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Utile {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * la méthode prend une date en chaine de caractère au format jj/mm/yyyy er retourne une date initialisée au format Date
     * @param  dateChoisie : la date en String à convertir
     * @return  dateInitialisee: la date au format Date initialisée avec les valeurs passées en chaine de caractères
     */

    public static Date setDateFromString(String dateChoisie){

        Date dateInitialisee = new Date();

        String mots[] = dateChoisie.split("/");

        dateInitialisee.setDate(Integer.valueOf(mots[0]));
        dateInitialisee.setMonth(Integer.valueOf(mots[1])-1);
        dateInitialisee.setYear(Integer.valueOf(mots[2])-1900);

        return dateInitialisee;
    }

    /**
     * la méthode prend une date au format Date et la reconvertit en chaine de caractère au format "jj/MM/YYYY"
     * @param  dateAFormater: l'objet au format Date à convertir en String
     * @return  dateToString: la date formattée en châine de caractères à partir des informations de l'objet Date.
     */

    public static String setStringFromDate(Date dateAFormater){
        return simpleDateFormat.format(dateAFormater);
    }
}
