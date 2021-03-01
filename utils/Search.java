package aubert.airbnb.utils;

import aubert.airbnb.logements.Appartement;
import aubert.airbnb.logements.Logement;
import aubert.airbnb.logements.Maison;
import aubert.airbnb.menu.Menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Search {

    int nbVoyageurs;
    int tarifMinParNuit;
    int tarifMaxParNuit;
    int possedePiscine;
    int possedeJardin;
    int possedeBalcon;

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
                .sorted(Comparator.comparingInt(Logement::getTarifParNuit))
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
                return possedePiscine == 0; //on est sur un appart, si on veut une piscine alors on ne veut pas de ce logement sinon on le garde

        };
    }

    public Predicate<Logement> predicateBalcon(){
        return l -> {
            if (l instanceof Appartement){
                Appartement appart = (Appartement) l;
                return((appart.possedeBalcon() && possedeBalcon == 1) || (!appart.possedeBalcon() && possedeBalcon == 0));
            }
            else
                return possedeBalcon == 0; //on est sur une maison donc si on veut un balcon on ne veut pas de ce logement
        };
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
            possedePiscine = isPiscine?1:0;
            return this;
        }

        public SearchBuilder setPossedeJardin (boolean isJardin){
            possedeJardin = isJardin?1:0;
            return this;
        }

        public SearchBuilder setPossedeBalcon (boolean isBalcon){
            possedeBalcon = isBalcon?1:0;
            return this;
        }

        public Search build(){
            return new Search(this);
        }


    }
}
