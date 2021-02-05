package aubert.airbnb.utils;

import aubert.airbnb.logements.Appartement;
import aubert.airbnb.logements.Logement;
import aubert.airbnb.logements.Maison;
import aubert.airbnb.menu.Menu;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Search {

    int nbVoyageurs;
    int tarifMinParNuit = 0;
    int tarifMaxParNuit = 0;
    int possedePiscine = 3;
    int possedeJardin = 3;
    int possedeBalcon = 3;

    private Search(SearchBuilder searchBuilder){
        nbVoyageurs = searchBuilder.nbVoyageurs;
        tarifMaxParNuit = searchBuilder.tarifMaxParNuit;
        tarifMinParNuit = searchBuilder.tarifMinParNuit;
        possedePiscine = searchBuilder.possedePiscine;
        possedeJardin = searchBuilder.possedeJardin;
        possedeBalcon = searchBuilder.possedeBalcon;
    }

    public ArrayList<Logement> result(){
        Stream<Logement> stream= Menu.airBnBData.getListLogements().stream();

        return stream.filter(predicateNbVoyageurs())
                .filter(predicateTarif())
                .filter(predicatePiscine())
                .filter(predicateBalcon())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Predicate<Logement> predicateNbVoyageurs(){
        return l -> l.getNbVoyageursMax()>=nbVoyageurs;
    }

    public Predicate<Logement> predicateTarif(){
        return l -> l.getTarifParNuit()>=tarifMinParNuit && l.getTarifParNuit()<=tarifMaxParNuit;
    }

    public Predicate<Logement> predicatePiscine(){
        return l -> {
            if (l instanceof Maison){
                Maison maison = (Maison) l;
                return((maison.getPossedePiscine() && possedePiscine == 1) || (!maison.getPossedePiscine() && possedePiscine == 0));
            }
            else
                return possedePiscine==0?true:false; //on est sur un appart, si on veut une piscine alors on ne veut pas de ce logement sinon on le garde

        };
    }

    public Predicate<Logement> predicateBalcon(){
        return l -> {
            if (l instanceof Appartement){
                Appartement appart = (Appartement) l;
                return((appart.possedeBalcon() && possedeBalcon == 1) || (!appart.possedeBalcon() && possedeBalcon == 0));
            }
            else
                return possedeBalcon==0?true:false; //on est sur une maison donc si on veut un balcon on ne veut pas de ce logement
        };
    }

    public ArrayList<Logement> resultOld(){
        ArrayList<Logement> listAParcourir = Menu.airBnBData.getListLogements();
        ArrayList<Logement> listARetourner = (ArrayList<Logement>)listAParcourir.clone();

        for (Logement logement: listAParcourir) {
            if (logement.getNbVoyageursMax() < nbVoyageurs)
                listARetourner.remove(logement);
        }

        listAParcourir = (ArrayList<Logement>) listARetourner.clone();
        if (listARetourner.size()==0)
            return listARetourner;

        if (tarifMaxParNuit !=0){
            for (Logement logement: listAParcourir) {
                if (logement.getTarifParNuit() > tarifMaxParNuit)
                    listARetourner.remove(logement);
            }
        }

        listAParcourir = (ArrayList<Logement>) listARetourner.clone();
        if (listAParcourir.size()==0)
            return listARetourner;

        if (tarifMinParNuit !=0){
            for (Logement logement: listAParcourir) {
                if (logement.getTarifParNuit() < tarifMinParNuit)
                    listARetourner.remove(logement);
            }
        }

        listAParcourir = (ArrayList<Logement>) listARetourner.clone();
        if (listAParcourir.size()==0)
            return listARetourner;

        if (possedePiscine < 2){
            for (Logement logement: listAParcourir) {
                if (logement instanceof Maison) {
                    Maison maison = (Maison) logement;
                    if ((maison.getPossedePiscine() && possedePiscine == 0) || (!maison.getPossedePiscine() && possedePiscine == 1))
                        listARetourner.remove(logement);
                }
            }
        }

        listAParcourir = (ArrayList<Logement>) listARetourner.clone();
        if (listAParcourir.size()==0)
            return listARetourner;

        if (possedeJardin < 2){
            for (Logement logement: listAParcourir) {
                if (logement instanceof Maison) {
                    Maison maison = (Maison) logement;
                    if ((maison.possedeJardin() && possedeJardin == 0) || (!maison.possedeJardin() && possedeJardin == 1))
                        listARetourner.remove(logement);
                }
            }
        }

        listAParcourir = (ArrayList<Logement>) listARetourner.clone();
        if (listAParcourir.size()==0)
            return listARetourner;

        if (possedeBalcon < 2){
            for (Logement logement: listAParcourir) {
                if (logement instanceof Appartement) {
                    Appartement appartement = (Appartement) logement;
                    if ((appartement.possedeBalcon() && possedeBalcon == 0) || (!appartement.possedeBalcon() && possedeBalcon == 1))
                        listARetourner.remove(logement);
                }
            }
        }

        return listARetourner;
    }
    public static class SearchBuilder{
        int nbVoyageurs;
        int tarifMinParNuit;
        int tarifMaxParNuit;
        int possedePiscine;
        int possedeJardin;
        int possedeBalcon;

        public SearchBuilder(int nombreVoyageurs){
            nbVoyageurs = nombreVoyageurs;
        }

        public SearchBuilder setTarifMinParNuit(int tarifMin){
            tarifMinParNuit = tarifMin;
            return this;
        }

        public SearchBuilder setTarifMaxParNuit (int tarifMax){
            tarifMaxParNuit = tarifMax;
            return this;
        }

        public SearchBuilder setPossedePiscine (boolean isPiscine){
            possedePiscine = isPiscine?1:!isPiscine?0:2;
            return this;
        }

        public SearchBuilder setPossedeJardin (boolean isJardin){
            possedeJardin = isJardin?1:!isJardin?0:2;
            return this;
        }

        public SearchBuilder setPossedeBalcon (boolean isBalcon){
            possedeBalcon = isBalcon?1:!isBalcon?0:2;
            return this;
        }

        public Search build(){
            return new Search(this);
        }


    }
}
