package viewPackage.tableModele;

import modelPackage.Animal;
import modelPackage.AnimalProprietaire;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TableModeleListeAnimaux  extends AbstractTableModel {

    private ArrayList<String> nomDesColonnes;
    private ArrayList<AnimalProprietaire> listingAnimaux;
    private DefaultTableCellRenderer centerRenderer;

    public TableModeleListeAnimaux(ArrayList<AnimalProprietaire> listingAnimaux){
        nomDesColonnes = new ArrayList<>();
        this.listingAnimaux = listingAnimaux;

        nomDesColonnes.add("Identifiant de l'animal");
        nomDesColonnes.add("Date d'arrivée");
        nomDesColonnes.add("Nom");
        nomDesColonnes.add("Espèce");
        nomDesColonnes.add("Race");
        nomDesColonnes.add("Sexe");
        nomDesColonnes.add("Stérilisé.e");
        nomDesColonnes.add("Couleur");
        nomDesColonnes.add("Date de naissance");
        nomDesColonnes.add("Poids");
        nomDesColonnes.add("Propriétaire");
        nomDesColonnes.add("Numéro de puce");
        nomDesColonnes.add("Localisation de la puce");
        nomDesColonnes.add("Date d'attribution de la puce");
        nomDesColonnes.add("Numéro de tatouage");
        nomDesColonnes.add("Localisation du tatouage");

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    }

    public int getColumnCount() {
        return nomDesColonnes.size();
    }

    public int getRowCount() {
        return listingAnimaux.size();
    }

    public String getColumnName(int colonne) {
        return nomDesColonnes.get(colonne);
    }

    public Object getValueAt(int ligne, int colonne) {
        AnimalProprietaire animalProprio = listingAnimaux.get(ligne);
        switch(colonne){
            case 0 : return animalProprio.getNumRegistre();
            case 1 : {
                if (animalProprio.getDateArrivee()!= null)
                    return new SimpleDateFormat("dd/MM/YYYY").format(animalProprio.getDateArrivee().getTime());
                else
                    return null;
            }
            case 2 : return animalProprio.getNom();
            case 3 : return animalProprio.getEspece();
            case 4 : return animalProprio.getRace();
            case 5 : return animalProprio.getSexe();
            case 6 : return animalProprio.isEstSterilise();
            case 7 : return animalProprio.getCouleurDePeau();
            case 8 : {
                if (animalProprio.getDateNaissance()!= null)
                    return new SimpleDateFormat("dd/MM/YYYY").format(animalProprio.getDateNaissance().getTime());
                else
                    return null;
            }
            case 9 : return animalProprio.getPoids();
            case 10 : return animalProprio.getDescriptionProprietaire();
            case 11 : return animalProprio.getNumPuce();
            case 12 : return animalProprio.getLocalisationPuce();
            case 13 : {
                if (animalProprio.getDateAttributionPuce()!= null)
                    return new SimpleDateFormat("dd/MM/YYYY").format(animalProprio.getDateAttributionPuce().getTime());
                else
                    return null;
            }
            case 14 : return animalProprio.getNumTatouage();
            case 15 : return animalProprio.getLocalisationTatouage();
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
            case 4 : c = String.class;
                break;
            case 5 : c = String.class;
                break;
            case 6 : c = Boolean.class;
                break;
            case 7 : c = String.class;
                break;
            case 8 : c = GregorianCalendar.class;
                break;
            case 9 : c = Double.class;
                break;
            case 10 : c = String.class;
                break;
            case 11 : c = Integer.class;
                break;
            case 12 : c = String.class;
                break;
            case 13 : c = GregorianCalendar.class;
                break;
            case 14 : c = Integer.class;
                break;
            case 15 : c = String.class;
                break;
            default : c = String.class;
        }
        return c;
    }

    public AnimalProprietaire getAnimalProprietaireSelectionne(int ligne){
        return listingAnimaux.get(ligne);
    }

    public DefaultTableCellRenderer getCenterRenderer() {
        return centerRenderer;
    }
}
