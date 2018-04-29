package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class PanneauRechercheVeterinaires  extends JPanel {
    private Controller controller;
    private JPanel panneauRecherche, panneauListe, panneauTitre;
    private JLabel champTitre, vide;
    private PanneauSpinnerDate dateDebutRech,dateFinRech;
    private JButton rechercher;
    private JCheckBox dateDebut, dateFin;
    private JTable resultatRecherche;
    private JScrollPane jScrollPane;
    private GregorianCalendar dateDebutZoneRecherche, dateFinZoneRecherche;


    public PanneauRechercheVeterinaires(Controller controller){
        this.controller = controller;
        this.setLayout(new BorderLayout());
        panneauRecherche = new JPanel();
        panneauListe = new JPanel();
        panneauTitre = new JPanel();

        this.add(panneauRecherche, BorderLayout.WEST);
        this.add(panneauListe, BorderLayout.CENTER);
        this.add(panneauTitre, BorderLayout.NORTH);

        panneauRecherche.setLayout(new GridLayout(20, 2));
        panneauListe.setLayout(new GridLayout(3, 1));
        panneauTitre.setLayout(new GridLayout(1, 1));

        vide = new JLabel("");
        panneauListe.add(vide);

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
            if(jScrollPane != null)
                panneauListe.remove(jScrollPane);
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
                    else{
                        dateFinZoneRecherche = dateFinRech.getDate();
                    }
                    if (dateDebutZoneRecherche.getTimeInMillis() > dateFinZoneRecherche.getTimeInMillis()) {
                        JOptionPane.showMessageDialog(null, "La date de debut ne peut être postérieure à la date de fin");
                        return;
                    }

                    String[][] selectionRecherche = controller.getResultatRechercheVeterinaireDate(dateDebutZoneRecherche,
                            dateFinZoneRecherche);
                    String[] nomDesColonnes = {"Identifiant du vétérinaire", "Nom du vétérinaire", "Date de l'ordonnance"};
                    resultatRecherche = new JTable(selectionRecherche, nomDesColonnes);
                    jScrollPane = new JScrollPane(resultatRecherche);
                    panneauListe.add(jScrollPane);
                    panneauListe.doLayout();
                }
                catch (SingletonConnectionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                catch (VeterinaireException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }
}
