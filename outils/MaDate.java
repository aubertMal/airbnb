package aubert.airbnb.outils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MaDate extends Date {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * le constructeur prend une date en chaine de caractère au format jj/mm/yyyy er construit une date initialisée au format Date
     * @param  dateEnTexte : la date en String à convertir
     */
    public MaDate(String dateEnTexte){

        String mots[] = dateEnTexte.split("/");
        super.setDate(Integer.valueOf(mots[0]));
        super.setMonth(Integer.valueOf(mots[1])-1);
        super.setYear(Integer.valueOf(mots[2])-1900);

    }

    /**
     * la méthode prend une date au format Date et la reconvertit en chaine de caractère au format "jj/MM/YYYY"
     * @param  dateAFormater: l'objet au format Date à convertir en String
     * @return  dateToString: la date formattée en châine de caractères à partir des informations de l'objet Date.
     */
    public String toString(Date dateAFormater) {
        return simpleDateFormat.format(dateAFormater);
    }
}
