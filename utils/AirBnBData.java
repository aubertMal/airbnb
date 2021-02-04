package aubert.airbnb.utils;

import aubert.airbnb.logements.Logement;
import aubert.airbnb.utilisateurs.Hote;
import aubert.airbnb.utilisateurs.Voyageur;

import java.util.ArrayList;

public class AirBnBData {

    private ArrayList<Hote> listHotes;
    private ArrayList<Voyageur> listVoyageurs;
    private ArrayList<Logement> listLogements;

    private AirBnBData(){
        listHotes = new ArrayList<>();;
        listVoyageurs = new ArrayList<>();
        listLogements = new ArrayList<>();
    }

    private static final AirBnBData instance = new AirBnBData();

    public static AirBnBData getInstance(){
        return instance;
    }

    public ArrayList<Hote> getListHotes(){
        return listHotes;
    }

    public ArrayList<Voyageur> getListVoyageurs(){
        return listVoyageurs;
    }

    public ArrayList<Logement> getListLogements(){
        return listLogements;
    }

    public void setListLogements(ArrayList<Logement> nouvelleListeLogements){
        listLogements = nouvelleListeLogements;
    }

    public void setListHotes (ArrayList<Hote> nouvelleListeHotes){
        listHotes = nouvelleListeHotes;
    }

    public void setListVoyageurs(ArrayList<Voyageur> nouvelleListVoyageurs){
        listVoyageurs = nouvelleListVoyageurs;
    }
}
