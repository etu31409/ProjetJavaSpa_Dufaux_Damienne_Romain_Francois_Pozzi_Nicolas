package viewPackage;

import modelPackage.VeterinaireOrdonnance;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TableModeleRechercheVeterinaires extends AbstractTableModel {
    private ArrayList<String> nomDesColonnes;
    private ArrayList<VeterinaireOrdonnance> veterinairesOrdonnances;
    private DefaultTableCellRenderer centerRenderer;

    public TableModeleRechercheVeterinaires(ArrayList<VeterinaireOrdonnance> veterinairesOrdonnances){
        nomDesColonnes = new ArrayList<>();
        this.veterinairesOrdonnances = veterinairesOrdonnances;

        nomDesColonnes.add("Identifiant du vétérinaire");
        nomDesColonnes.add("Nom du vétérinaire");
        nomDesColonnes.add("Date de l'ordonnance");

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    }

    public int getColumnCount() {
        return nomDesColonnes.size();
    }

    public int getRowCount() {
        return veterinairesOrdonnances.size();
    }

    public String getColumnName(int colonne) {
        return nomDesColonnes.get(colonne);
    }

    public Object getValueAt(int ligne, int colonne) {
        VeterinaireOrdonnance veterinaireOrdonnance = veterinairesOrdonnances.get(ligne);
        switch(colonne){
            case 0 : return veterinaireOrdonnance.getIdentifiantVeto();
            case 1 : return veterinaireOrdonnance.getNomVeto();
            case 2 :{
                if(veterinaireOrdonnance.getDateOrdonnance()!= null)
                    return new SimpleDateFormat("dd/MM/YYYY").format(veterinaireOrdonnance.getDateOrdonnance().getTime());
                else
                    return null;
            }
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
            case 2 : c = GregorianCalendar.class;
            default : c = String.class;
        }
        return c;
    }

    public DefaultTableCellRenderer getCenterRenderer() {
        return centerRenderer;
    }
}
