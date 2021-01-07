package aubert.airbnb.utilisateurs;

public class Personne {
    private String prenom;
    private String nom;
    private int age;

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
}
