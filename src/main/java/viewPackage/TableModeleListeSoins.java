package viewPackage;

import modelPackage.SoinAvance;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TableModeleListeSoins  extends AbstractTableModel {
    private ArrayList<String> nomDesColonnes;
    private ArrayList<SoinAvance> soins;
    private DefaultTableCellRenderer centerRenderer;

    public TableModeleListeSoins(ArrayList<SoinAvance> soins){
        nomDesColonnes = new ArrayList<>();
        this.soins = soins;

        nomDesColonnes.add("Identifiant du soin");
        nomDesColonnes.add("Identifiant de l'animal");
        nomDesColonnes.add("Intitulé du soin");
        nomDesColonnes.add("Partie du corps concernée");
        nomDesColonnes.add("Date du soin");
        nomDesColonnes.add("Identifiant du vétérinaire");
        nomDesColonnes.add("En urgence");
        nomDesColonnes.add("Remarque");

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    }

    public int getColumnCount() {
        return nomDesColonnes.size();
    }

    public int getRowCount() {
        return soins.size();
    }

    public String getColumnName(int colonne) {
        return nomDesColonnes.get(colonne);
    }

    public Object getValueAt(int ligne, int colonne) {
        SoinAvance soin = soins.get(ligne);
        switch(colonne){
            case 0 : return soin.getNumSoin();
            case 1 : return soin.getNumRegistre();
            case 2 : return soin.getIntitule();
            case 3 : return soin.getPartieDuCorps();
            case 4 : {
                if (soin.getDateSoin()!= null)
                    return new SimpleDateFormat("dd/MM/YYYY").format(soin.getDateSoin().getTime());
                else
                    return null;
            }
            case 5 : return soin.getVeterinaire();
            case 6 : return soin.getEstUrgent();
            case 7 : return soin.getRemarque();
            default : return null;
        }
    }

    //afficher correctement les colonnes en fonction de leur type
    public Class getColumnClass(int colonne) {
        Class c;
        switch(colonne){
            case 0 : c = Integer.class;
                break;
            case 1 : c = Integer.class;
                break;
            case 2 : c = String.class;
                break;
            case 3 : c = String.class;
                break;
            case 4 : c = GregorianCalendar.class;
                break;
            case 5 : c = Integer.class;
                break;
            case 6 : c = Boolean.class;
                break;
            case 7 : c = String.class;
                break;
            default : c = String.class;
        }
        return c;
    }


    //Permet de modifier les cellules
    public boolean isCellEditable(int row, int col){
        if (col == 0) return false;
        return true;
    }

    public DefaultTableCellRenderer getCenterRenderer() {
        return centerRenderer;
    }
}
