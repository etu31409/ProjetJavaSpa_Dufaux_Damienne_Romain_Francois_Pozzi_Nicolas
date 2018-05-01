package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private JCheckBox dateDeDébutCheckBox;
    private JCheckBox dateDeFinCheckBox;
    private JPanel panneauContainerPrincipal;
    private JSpinner dateDeDébutSpinner;
    private JSpinner dateDeFinSpinner;
    private JLabel titreFacteurRecherche;
    private JPanel panneauListeRecherche;
    private JButton rechercherButton;


    public PanneauRechercheVeterinaires(Controller controller){
        this.controller = controller;
        rechercherButton.addActionListener(new RechercheListener());
        instanciationSpinner();
    }

    private void instanciationSpinner() {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1950");

            dateDeDébutSpinner.setModel(new SpinnerDateModel());
            dateDeDébutSpinner.setEditor(new JSpinner.DateEditor(dateDeDébutSpinner, "dd/MM/yyyy"));
            dateDeDébutSpinner.setValue(date);

            dateDeFinSpinner.setModel(new SpinnerDateModel());
            dateDeFinSpinner.setEditor(new JSpinner.DateEditor(dateDeFinSpinner, "dd/MM/yyyy"));
            dateDeFinSpinner.setValue(GregorianCalendar.getInstance().getTime());

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du découpage de la date!", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
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
