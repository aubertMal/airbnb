package aubert.airbnb.utils;

import aubert.airbnb.logements.Appartement;
import aubert.airbnb.logements.Logement;
import aubert.airbnb.logements.Maison;
import aubert.airbnb.menu.Menu;

import java.util.ArrayList;

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
                if (logement.getName().contains("Maison")) {
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
                if (logement.getName().contains("Maison")) {
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
                if (logement.getName().contains("Appartement")) {
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
