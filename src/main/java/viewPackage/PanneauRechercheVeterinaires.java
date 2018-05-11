package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;
import modelPackage.Veterinaire;
import modelPackage.VeterinaireOrdonnance;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class PanneauRechercheVeterinaires{
    private Controller controller;
    private JTable resultatRecherche;
    private GregorianCalendar dateDebutZoneRecherche, dateFinZoneRecherche;
    private JCheckBox dateDeDebutCheckBox;
    private JCheckBox dateDeFinCheckBox;
    private JPanel panneauContainerPrincipal;
    private JSpinner dateDeDebutSpinner;
    private JSpinner dateDeFinSpinner;
    private JPanel panneauListeRecherche;
    private JButton rechercherButton;
    private JScrollPane scrollPane;


    public PanneauRechercheVeterinaires(Controller controller){
        this.controller = controller;
        rechercherButton.addActionListener(new RechercheListener());
        instanciationSpinner();
    }

    private void instanciationSpinner() {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1950");

            dateDeDebutSpinner.setModel(new SpinnerDateModel());
            dateDeDebutSpinner.setEditor(new JSpinner.DateEditor(dateDeDebutSpinner, "dd/MM/yyyy"));
            dateDeDebutSpinner.setValue(date);

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

            TableModeleRechercheVeterinaires modele;
            ArrayList<VeterinaireOrdonnance> resultatRequeteRecherche = new ArrayList<>();

            if(event.getSource() == rechercherButton){
                try {
                    dateDebutZoneRecherche = new GregorianCalendar();
                    dateFinZoneRecherche = new GregorianCalendar();
                    if(!dateDeDebutCheckBox.isSelected()){
                        dateDebutZoneRecherche = new GregorianCalendar(1950, 01, 01);
                    }
                    else{
                        dateDebutZoneRecherche.setTime((Date)dateDeDebutSpinner.getValue());
                    }
                    if(!dateDeFinCheckBox.isSelected()){
                        dateFinZoneRecherche.setTime(GregorianCalendar.getInstance().getTime());
                    }
                    else{
                        dateFinZoneRecherche.setTime((Date)dateDeFinSpinner.getValue());
                    }
                    if (dateDebutZoneRecherche.getTimeInMillis() > dateFinZoneRecherche.getTimeInMillis()) {
                        JOptionPane.showMessageDialog(null, "La date de debut ne peut être postérieure à la date de fin", "Erreur !", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    resultatRequeteRecherche = controller.getResultatRechercheVeterinaireDate(dateDebutZoneRecherche,
                            dateFinZoneRecherche);

                    modele = new TableModeleRechercheVeterinaires(resultatRequeteRecherche);
                    resultatRecherche = new JTable(modele);
                    resultatRecherche.setAutoCreateRowSorter(true);

                    resultatRecherche.setDefaultRenderer(String.class, modele.getCenterRenderer());
                    resultatRecherche.setDefaultRenderer(Integer.class, modele.getCenterRenderer());
                    resultatRecherche.setDefaultRenderer(GregorianCalendar.class, modele.getCenterRenderer());

                    scrollPane.setViewportView(resultatRecherche);
                    resultatRecherche.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    resultatRecherche.setFillsViewportHeight(true);
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
