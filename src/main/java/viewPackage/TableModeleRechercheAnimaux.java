package viewPackage;

import modelPackage.Animal;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModeleRechercheAnimaux  extends AbstractTableModel {
    private ArrayList<String> nomDesColonnes;
    private ArrayList<Animal> animaux;

    public TableModeleRechercheAnimaux(ArrayList<Animal> animaux){
        nomDesColonnes = new ArrayList<>();
        this.animaux = animaux;

        nomDesColonnes.add("Identifiant de l'animal");
        nomDesColonnes.add("Nom");
    }

    public int getColumnCount() {
        return nomDesColonnes.size();
    }

    public int getRowCount() {
        return animaux.size();
    }

    public String getColumnName(int colonne) {
        return nomDesColonnes.get(colonne);
    }

    public Object getValueAt(int ligne, int colonne) {
        Animal animal = animaux.get(ligne);
        switch(colonne){
            case 0 : return animal.getNumRegistre();
            case 1 : return animal.getNom();
            default : return null;
        }
    }

    //afficher correctement les colonnes en fonction de leur type
    public Class getColumnClass(int colonne) {
        Class c;
        switch(colonne){
            case 0 : c = Integer.class;
                break;
            case 1 : c = String.class;
                break;
            default : c = String.class;
        }
        return c;
    }
}
