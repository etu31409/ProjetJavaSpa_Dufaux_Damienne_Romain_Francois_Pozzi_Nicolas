package viewPackage;

import modelPackage.SoinAvance;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TableModeleListeSoins  extends AbstractTableModel {
    private ArrayList<String> nomDesColonnes;
    private ArrayList<SoinAvance> soins;

    public TableModeleListeSoins(ArrayList<SoinAvance> soins){
        nomDesColonnes = new ArrayList<>();
        this.soins = soins;

        nomDesColonnes.add("Identifiant du soin");
        nomDesColonnes.add("Identifiant de l'animal");
        nomDesColonnes.add("Intitulé du soin");
        nomDesColonnes.add("Partie du corps concerné");//
        nomDesColonnes.add("Date du soin");
        nomDesColonnes.add("Identifiant du vétérinaire");
        nomDesColonnes.add("En urgence");
        nomDesColonnes.add("Remarque");//
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
            //case 0 : return soin.get();
            case 1 : return soin.getNumRegistre();
            case 2 : return soin.getIntitule();
            case 3 : return soin.getPartieDuCorps();
            case 4 : return soin.getDateSoin().getTimeInMillis();
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
}
