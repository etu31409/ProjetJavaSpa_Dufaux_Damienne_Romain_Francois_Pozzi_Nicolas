package viewPackage.tableModele;

import modelPackage.Animal;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TableModeleListeAnimaux  extends AbstractTableModel {

    private ArrayList<String> nomDesColonnes;
    private ArrayList<Animal> animaux;
    private DefaultTableCellRenderer centerRenderer;

    public TableModeleListeAnimaux(ArrayList<Animal> animaux){
        nomDesColonnes = new ArrayList<>();
        this.animaux = animaux;

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
        nomDesColonnes.add("Identifiant du propriétaire");
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
        return animaux.size();
    }

    public String getColumnName(int colonne) {
        return nomDesColonnes.get(colonne);
    }

    public Object getValueAt(int ligne, int colonne) {
        Animal animal = animaux.get(ligne);
        switch(colonne){
            case 0 : return animal.getNumRegistre();
            case 1 : {
                if (animal.getDateArrivee()!= null)
                    return new SimpleDateFormat("dd/MM/YYYY").format(animal.getDateArrivee().getTime());
                else
                    return null;
            }
            case 2 : return animal.getNom();
            case 3 : return animal.getEspece();
            case 4 : return animal.getRace();
            case 5 : return animal.getSexe();
            case 6 : return animal.isEstSterilise();
            case 7 : return animal.getCouleurDePeau();
            case 8 : {
                if (animal.getDateNaissance()!= null)
                    return new SimpleDateFormat("dd/MM/YYYY").format(animal.getDateNaissance().getTime());
                else
                    return null;
            }
            case 9 : return animal.getPoids();
            case 10 : return animal.getProprietaire();
            case 11 : return animal.getNumPuce();
            case 12 : return animal.getLocalisationPuce();
            case 13 : {
                if (animal.getDateAttributionPuce()!= null)
                    return new SimpleDateFormat("dd/MM/YYYY").format(animal.getDateAttributionPuce().getTime());
                else
                    return null;
            }
            case 14 : return animal.getNumTatouage();
            case 15 : return animal.getLocalisationTatouage();
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
            case 10 : c = Integer.class;
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

    public DefaultTableCellRenderer getCenterRenderer() {
        return centerRenderer;
    }
}
