package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.OrdonnanceException;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class PanneauRechercheVeterinaires  extends JPanel {
    private Controller controller;
    private JPanel panneauRecherche, panneauListe;
    private JLabel champTitre, vide;
    private PanneauSpinnerDate dateDebutRech, dateFinRech;
    private JButton rechercher;
    private JCheckBox dateDebut, dateFin;
    private JTable resultatRecherche;
    private TableColumn IDVeto, nomVeto, dateOrdonnance;
    private GregorianCalendar dateDebutZoneRecherche, dateFinZoneRecherche;


    public PanneauRechercheVeterinaires(Controller controller){
        this.controller = new Controller();
        this.setLayout(new BorderLayout());
        panneauRecherche = new JPanel();
        panneauListe = new JPanel();

        this.add(panneauRecherche, BorderLayout.WEST);
        this.add(panneauListe, BorderLayout.CENTER);

        panneauRecherche.setLayout(new GridLayout(20, 2, 10, 5));
        panneauListe.setLayout(new GridLayout(20, 1, 10, 5));

        vide = new JLabel("");

        champTitre = new JLabel("<html><h1>Recherche des vétérinaires qui ont préscrit des ordonnances</h1></html>");
        champTitre.setHorizontalAlignment(SwingConstants.CENTER);
        panneauRecherche.add(champTitre);
        panneauRecherche.add(vide);

        dateDebut = new JCheckBox("Date début");
        dateDebut.setHorizontalAlignment(SwingConstants.CENTER);
        panneauRecherche.add(dateDebut);

        dateDebutRech = new PanneauSpinnerDate();
        panneauRecherche.add(dateDebutRech);
        dateDebutZoneRecherche = dateDebutRech.getDate();

        dateFin = new JCheckBox("Date fin");
        dateFin.setHorizontalAlignment(SwingConstants.CENTER);
        panneauRecherche.add(dateFin);

        dateFinRech = new PanneauSpinnerDate();
        panneauRecherche.add(dateFinRech);
        dateFinZoneRecherche = dateFinRech.getDate();

        rechercher = new JButton("Rechercher");
        panneauRecherche.add(rechercher);
        rechercher.addActionListener(new RechercheListener());
    }

    private class RechercheListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            resultatRecherche.clearSelection();
            resultatRecherche.addColumn(IDVeto);
            resultatRecherche.addColumn(nomVeto);
            resultatRecherche.addColumn(dateOrdonnance);
            //par sécurité
            if(event.getSource() == rechercher){
                try {
                    /*for (){
                        resultatRecherche;
                    }*/
                }
                catch (SingletonConnectionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                catch (OrdonnanceException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                catch (VeterinaireException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }
}
