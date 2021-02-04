package aubert.airbnb.utils;

import aubert.airbnb.logements.Logement;

import java.util.ArrayList;

public class Search {

    int nbVoyageurs;
    int tarifMinParNuit;
    int tarifMaxParNuit;
    int possedePiscine;
    int possedeJardin;
    int possedeBalson;

    private Search(SearchBuilder searchBuilder){
        nbVoyageurs = searchBuilder.nbVoyageurs;
        tarifMaxParNuit = searchBuilder.tarifMaxParNuit;
        tarifMinParNuit = searchBuilder.tarifMinParNuit;
        possedePiscine = searchBuilder.possedePiscine;
        possedeJardin = searchBuilder.possedeJardin;
        possedeBalson = searchBuilder.possedeBalson;
    }

    public ArrayList<Logement> result(){
        return null;
    }
    public static class SearchBuilder{
        int nbVoyageurs;
        int tarifMinParNuit;
        int tarifMaxParNuit;
        int possedePiscine;
        int possedeJardin;
        int possedeBalson;

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

        public SearchBuilder setPossedePiscine (int isPiscine){
            possedePiscine = isPiscine;
            return this;
        }

        public SearchBuilder setPossedeJardin (int isJardin){
            possedeJardin = isJardin;
            return this;
        }

        public SearchBuilder setPossedeBalcon (int isBalcon){
            possedeBalson = isBalcon;
            return this;
        }

        public Search build(){
            return new Search(this);
        }


    }
}
