package viewPackage;

import modelPackage.Animal;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class tousLesAnimauxModele extends AbstractTableModel {
    private ArrayList<String> nomDesColonnes;
    private ArrayList<Animal> contenu;

    public tousLesAnimauxModele(ArrayList<Animal> animaux) {
        nomDesColonnes = new ArrayList<>();
        nomDesColonnes.add("numRegistre");
        nomDesColonnes.add("nom");
        nomDesColonnes.add("dateArrivee");
        nomDesColonnes.add("dateDepart");
        nomDesColonnes.add("espece");
        nomDesColonnes.add("race");
        nomDesColonnes.add("sexe");
        nomDesColonnes.add("estSterilise");
        nomDesColonnes.add("couleurDePeau");
        nomDesColonnes.add("dateNaissance");
        nomDesColonnes.add("numPuce");
        nomDesColonnes.add("localisationPuce");
        nomDesColonnes.add("dateAttributionPuce");
        nomDesColonnes.add("numTatouage");
        nomDesColonnes.add("localisationTatouage");
        nomDesColonnes.add("poids");
        nomDesColonnes.add("proprietaire");
    }
    public int getColumnCount(){
        return nomDesColonnes.size();
    }
    public int getRowCount(){
        return contenu.size();
    }
    public String getColumnName(int column){
        return nomDesColonnes.get(column);
    }
    public Object getValueAt(int row, int column){
        Animal animal = contenu.get(row);
        switch(column)
        {   case 0 : return animal.getNumRegistre();
            case 1: { if (animal.getNom() != null){
                return animal.getNom();
            }
            else return null;
            }
            case 2 : return animal.getDateArrivee();
            case 3: {
                if (animal.getDateDepart() != null) {
                    return animal.getDateDepart();
                } else return null;
            }
            case 4: return animal.getEspece();
            case 5:return animal.getRace();
            case 6 : return animal.getSexe();
            case 7 : return animal.isEstSterilise();
            case 8 : return animal.getCouleurDePeau();
            case 9 : {if(animal.getDateNaissance() != null){
            return animal.getDateNaissance();}
            else return null;}
            case 10 : {if(animal.getNumPuce() != null){
            return animal.getNumPuce();}
            else return null;}
            case 11: {if(animal.getLocalisationPuce() != null){
            return animal.getLocalisationPuce();}
            else return null;}
            case 12 : {if(animal.getDateAttributionPuce() != null){
            return animal.getDateAttributionPuce();}
            else return null;}
            case 13 : {if(animal.getNumTatouage() != null){
            return animal.getNumTatouage();}
            return null;}
            case 14 : {if(animal.getLocalisationTatouage() != null){
            return animal.getLocalisationTatouage();}
            else return null;}
            case 15 : return animal.getPoids();
            default : return null;
        }
    }
}
