package viewPackage.tableModele;

import modelPackage.StatMedicament;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.ArrayList;

public class TableModeleStatistiquesMed  extends AbstractTableModel {
    private ArrayList<String> nomDesColonnes;
    private ArrayList<StatMedicament> stats;
    private DefaultTableCellRenderer centerRenderer;

    public TableModeleStatistiquesMed(ArrayList<StatMedicament> stats){
        nomDesColonnes = new ArrayList<>();
        this.stats = stats;

        nomDesColonnes.add("Nom du médicament");
        nomDesColonnes.add("Pourcentage d'utilisation dans l'interval de temps");

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    }

    public int getColumnCount() {
        return nomDesColonnes.size();
    }

    public int getRowCount() {
        return stats.size();
    }

    public String getColumnName(int colonne) {
        return nomDesColonnes.get(colonne);
    }

    public Object getValueAt(int ligne, int colonne) {
        StatMedicament stat = stats.get(ligne);
        switch(colonne){
            case 0 : return stat.getNomMedic();
            case 1 : return  String.format("%.1f", stat.getPourcentage()*100) + " %";
            default : return null;
        }
    }

    public Class getColumnClass(int colonne) {
        Class c;
        switch(colonne){
            case 0 : c = String.class;
                break;
            case 1 : c = Integer.class;
                break;
            default : c = String.class;
        }
        return c;
    }

    public DefaultTableCellRenderer getCenterRenderer() {
        return centerRenderer;
    }
}
