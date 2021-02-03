package aubert.airbnb.utilisateurs;

import aubert.airbnb.outils.Comparable;

public class Hote extends Personne implements Comparable {

    private final int delaiDeReponse;

    public Hote(String prenomPersonne, String nomPersonne, int agePersonne, int delai) {
        super(prenomPersonne, nomPersonne, agePersonne);
        delaiDeReponse = delai;
    }

    @Override
    public void afficher(){

        String delaiAAfficher = (delaiDeReponse>1)?(" les "+delaiDeReponse+" heures"):"l'heure";

        super.afficher();
        System.out.println(" qui s'engage à répondre dans" + delaiAAfficher );
    }

    @Override
    public int getValueToCompare(){
        return delaiDeReponse;
    }
}
