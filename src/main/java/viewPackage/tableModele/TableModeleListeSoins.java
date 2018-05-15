package viewPackage.tableModele;

import modelPackage.SoinAnimalVeto;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TableModeleListeSoins  extends AbstractTableModel {
    private ArrayList<String> nomDesColonnes;
    private ArrayList<SoinAnimalVeto> listingSoins;
    private DefaultTableCellRenderer centerRenderer;

    public TableModeleListeSoins(ArrayList<SoinAnimalVeto> listingSoins){
        nomDesColonnes = new ArrayList<>();
        this.listingSoins = listingSoins;

        nomDesColonnes.add("Identifiant du soin");
        nomDesColonnes.add("Date du soin");
        nomDesColonnes.add("Intitulé du soin");
        nomDesColonnes.add("Partie du corps concernée");
        nomDesColonnes.add("Identifiant de l'animal");
        nomDesColonnes.add("Animal");
        nomDesColonnes.add("Identifiant du vétérinaire");
        nomDesColonnes.add("Vétérinaire");
        nomDesColonnes.add("En urgence");
        nomDesColonnes.add("Remarque");

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    }

    public int getColumnCount() {
        return nomDesColonnes.size();
    }

    public int getRowCount() {
        return listingSoins.size();
    }

    public String getColumnName(int colonne) {
        return nomDesColonnes.get(colonne);
    }

    public Object getValueAt(int ligne, int colonne) {
        SoinAnimalVeto soin = listingSoins.get(ligne);
        switch(colonne){
            case 0 : return soin.getNumSoin();
            case 1 : {
                if (soin.getDateSoin()!= null)
                    return new SimpleDateFormat("dd/MM/YYYY").format(soin.getDateSoin().getTime());
                else
                    return null;
            }
            case 2 : return soin.getIntitule();
            case 3 : return soin.getPartieDuCorps();
            case 4 : return soin.getNumRegistre();
            case 5 : return soin.getDescriptionAnimal();
            case 6 : return soin.getVeterinaire();
            case 7 : return soin.getDescriptionVeterinaire();
            case 8 : return soin.getEstUrgent();
            case 9 : return soin.getRemarque();
            default : return null;
        }
    }

    public Class getColumnClass(int colonne) {
        Class c;
        switch(colonne){
            case 0 : c = Integer.class;
                break;
            case 1 : c = GregorianCalendar.class;
                break;
            case 2 : c = String.class;
                break;
            case 3 : c = String.class;
                break;
            case 4 : c = Integer.class;
                break;
            case 5 : c = String.class;
                break;
            case 6 : c = Integer.class;
                break;
            case 7 : c = String.class;
                break;
            case 8 : c = Boolean.class;
                break;
            case 9 : c = String.class;
                break;
            default : c = String.class;
        }
        return c;
    }


    public SoinAnimalVeto getSoinAnimalVetoSelectionne(int ligne){
        return listingSoins.get(ligne);
    }

    public DefaultTableCellRenderer getCenterRenderer() {
        return centerRenderer;
    }
}
