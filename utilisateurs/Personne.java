package aubert.airbnb.utilisateurs;

import aubert.airbnb.outils.Comparable;

public class Personne implements Comparable {
    private final String prenom;
    private final String nom;
    private final int age;

    public Personne(String prenomPersonne, String nomPersonne, int agePersonne){
        this.prenom = prenomPersonne;
        this.nom = nomPersonne;
        this.age = agePersonne;
    }

    @Override
    public String toString(){
        return (prenom +' '+ nom + '('+ age + "ans)");
    }
    public void afficher(){
        System.out.print(this);
    }

    @Override
    public int getValueToCompare(){
        return age;
    }
}
