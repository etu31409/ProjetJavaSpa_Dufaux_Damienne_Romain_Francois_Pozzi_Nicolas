package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.OrdonnanceException;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;
import modelPackage.modelJointure.VeterinaireSoinAvanceOrdonnanceRecherche;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PanneauRechercheVeterinaires  extends JPanel {
    private Controller controller;
    private JPanel panneauRecherche, panneauListe, panneauTitre;
    private JLabel champTitre, vide, titre;
    private PanneauSpinnerDate dateDebutRech, dateFinRech;
    private JButton rechercher;
    private JCheckBox dateDebut, dateFin;
    private JTable resultatRecherche;
    //private TableColumn IDVeto, nomVeto, dateOrdonnance;
    private GregorianCalendar dateDebutZoneRecherche, dateFinZoneRecherche;


    public PanneauRechercheVeterinaires(Controller controller){
        this.controller = controller;
        this.setLayout(new BorderLayout());
        panneauRecherche = new JPanel();
        panneauListe = new JPanel();
        panneauTitre = new JPanel();
        titre = new JLabel();

        this.add(titre, BorderLayout.NORTH);
        this.add(panneauRecherche, BorderLayout.WEST);
        this.add(panneauListe, BorderLayout.CENTER);
        this.add(panneauTitre, BorderLayout.CENTER);

        panneauRecherche.setLayout(new GridLayout(20, 2, 10, 5));
        panneauListe.setLayout(new GridLayout(20, 1, 10, 5));

        vide = new JLabel("");

        champTitre = new JLabel("<html><h1>Recherche des vétérinaires qui ont préscrit des ordonnances</h1></html>");
        champTitre.setHorizontalAlignment(SwingConstants.CENTER);
        panneauTitre.add(champTitre);
        panneauRecherche.add(vide);

        dateDebut = new JCheckBox("Date début");
        dateDebut.setHorizontalAlignment(SwingConstants.CENTER);
        panneauRecherche.add(dateDebut);

        dateDebutRech = new PanneauSpinnerDate();
        panneauRecherche.add(dateDebutRech);

        dateFin = new JCheckBox("Date fin");
        dateFin.setHorizontalAlignment(SwingConstants.CENTER);
        panneauRecherche.add(dateFin);

        dateFinRech = new PanneauSpinnerDate();
        panneauRecherche.add(dateFinRech);

        rechercher = new JButton("Rechercher");
        panneauRecherche.add(rechercher);
        rechercher.addActionListener(new RechercheListener());
    }

    private class RechercheListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            //resultatRecherche.clearSelection();
            //resultatRecherche.addColumn(IDVeto);
            //resultatRecherche.addColumn(nomVeto);
            //resultatRecherche.addColumn(dateOrdonnance);
            //par sécurité
            if(event.getSource() == rechercher){
                try {
                    if(!dateDebut.isSelected()){
                        dateDebutZoneRecherche = new GregorianCalendar(1700, 01, 01);
                    }
                    else{
                        dateDebutZoneRecherche = dateDebutRech.getDate();
                    }
                    if(!dateFin.isSelected()){
                        dateFinZoneRecherche = new GregorianCalendar();
                        dateFinZoneRecherche.setTime(GregorianCalendar.getInstance().getTime());
                    }
                    else {
                        dateFinZoneRecherche = dateFinRech.getDate();
                    }
                    if (dateDebutZoneRecherche.getTimeInMillis() > dateFinZoneRecherche.getTimeInMillis()) {
                        JOptionPane.showMessageDialog(null, "La date de debut ne peut être postérieure à la date de fin");
                        return;
                    }
                    ArrayList<VeterinaireSoinAvanceOrdonnanceRecherche> resultRecherche = controller.getResultatRechercheVeterinaireDate(dateDebutZoneRecherche,
                            dateFinZoneRecherche);
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
