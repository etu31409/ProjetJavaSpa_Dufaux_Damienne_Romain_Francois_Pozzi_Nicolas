package viewPackage;

import modelPackage.ProprietaireAnimal;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.ArrayList;

public class TableModeleRechercheProprietaires  extends AbstractTableModel {
    private ArrayList<String> nomDesColonnes;
    private ArrayList<ProprietaireAnimal> proprietairesAnimaux;
    private DefaultTableCellRenderer centerRenderer;

    public TableModeleRechercheProprietaires(ArrayList<ProprietaireAnimal> proprietairesAnimaux){
        nomDesColonnes = new ArrayList<>();
        this.proprietairesAnimaux = proprietairesAnimaux;

        nomDesColonnes.add("Identifiant de l'animal");
        nomDesColonnes.add("Nom de l'animal");
        nomDesColonnes.add("Identifiant du propriétaire");
        nomDesColonnes.add("Prénom du propriétaire");
        nomDesColonnes.add("Nom du propriétaire");

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    }

    public int getColumnCount() {
        return nomDesColonnes.size();
    }

    public int getRowCount() {
        return proprietairesAnimaux.size();
    }

    public String getColumnName(int colonne) {
        return nomDesColonnes.get(colonne);
    }

    public Object getValueAt(int ligne, int colonne) {
        ProprietaireAnimal proprietaireAnimal = proprietairesAnimaux.get(ligne);
        switch(colonne){
            case 0 : return proprietaireAnimal.getNumRegistreAnimal();
            case 1 : return proprietaireAnimal.getNomAnimal();
            case 2 : return proprietaireAnimal.getIdentifiantProprio();
            case 3 : return proprietaireAnimal.getPrenomProprio();
            case 4 : return proprietaireAnimal.getNomProprio();
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
            case 2 : c = Integer.class;
                break;
            case 3 : c = String.class;
                break;
            case 4 : c = String.class;
                break;
            default : c = String.class;
        }
        return c;
    }

    public DefaultTableCellRenderer getCenterRenderer() {
        return centerRenderer;
    }
}
