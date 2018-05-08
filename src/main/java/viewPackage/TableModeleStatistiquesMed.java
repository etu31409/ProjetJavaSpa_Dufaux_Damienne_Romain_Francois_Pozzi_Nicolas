package viewPackage;

import modelPackage.StatMedicament;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModeleStatistiquesMed  extends AbstractTableModel {
    private ArrayList<String> nomDesColonnes;
    private ArrayList<StatMedicament> stats;

    public TableModeleStatistiquesMed(ArrayList<StatMedicament> stats){
        nomDesColonnes = new ArrayList<>();
        this.stats = stats;

        nomDesColonnes.add("Nom du médicament");
        nomDesColonnes.add("Pourcentage d'utilisation dans l'interval de temps");
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
            case 1 : return Math.round(stat.getPourcentage()*100) + " %";
            default : return null;
        }
    }

    //afficher correctement les colonnes en fonction de leur type
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
}
