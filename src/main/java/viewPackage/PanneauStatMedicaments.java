package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.ConnexionException;
import exceptionPackage.MedicamentException;
import modelPackage.StatMedicament;
import viewPackage.tableModele.TableModeleStatistiquesMed;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class PanneauStatMedicaments {

    private GregorianCalendar dateDebutZoneRecherche, dateFinZoneRecherche;
    private JTable resultatStatistiques;

    private JPanel panneauContainerPrincipal;
    private JCheckBox checkBoxDateDebut;
    private JCheckBox checkBoxDateFin;
    private JSpinner spinnerDateDebut;
    private JSpinner spinnerDateFin;
    private JScrollPane scrollPane;
    private JButton rechercherButton;

    private Controller controller;

    public PanneauStatMedicaments(Controller controller) {
        this.controller = controller;
        rechercherButton.addActionListener(new RechercheListener());
        instanciationSpinner();
    }

    private void instanciationSpinner() {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1950");

            spinnerDateDebut.setModel(new SpinnerDateModel());
            spinnerDateDebut.setEditor(new JSpinner.DateEditor(spinnerDateDebut, "dd/MM/yyyy"));
            spinnerDateDebut.setValue(date);

            spinnerDateFin.setModel(new SpinnerDateModel());
            spinnerDateFin.setEditor(new JSpinner.DateEditor(spinnerDateFin, "dd/MM/yyyy"));
            spinnerDateFin.setValue(GregorianCalendar.getInstance().getTime());

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du découpage de la date!", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private class RechercheListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            if(event.getSource() == rechercherButton){
                try {
                    dateDebutZoneRecherche = new GregorianCalendar();
                    dateFinZoneRecherche = new GregorianCalendar();
                    if (!checkBoxDateDebut.isSelected()) {
                        dateDebutZoneRecherche = new GregorianCalendar(1950, 01, 01);
                    } else {
                        dateDebutZoneRecherche.setTime((Date) spinnerDateDebut.getValue());
                    }
                    if (!checkBoxDateFin.isSelected()) {
                        dateFinZoneRecherche.setTime(GregorianCalendar.getInstance().getTime());
                    } else {
                        dateFinZoneRecherche.setTime((Date) spinnerDateFin.getValue());
                    }
                    if (dateDebutZoneRecherche.getTimeInMillis() > dateFinZoneRecherche.getTimeInMillis()) {
                        JOptionPane.showMessageDialog(null, "La date de début ne peut être postérieure à la date de fin",
                        "Attention!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    TableColumn colonne;
                    TableModeleStatistiquesMed modele;
                    ArrayList<StatMedicament> stats;

                    stats = controller.getStatistiquesMedicaments(dateDebutZoneRecherche,
                            dateFinZoneRecherche);
                    modele = new TableModeleStatistiquesMed(stats);
                    resultatStatistiques = new JTable(modele);
                    resultatStatistiques.setDefaultRenderer(String.class, modele.getCenterRenderer());
                    resultatStatistiques.setDefaultRenderer(Integer.class, modele.getCenterRenderer());

                    scrollPane.setViewportView(resultatStatistiques);

                    colonne = resultatStatistiques.getColumnModel().getColumn(1);
                    colonne.setPreferredWidth(150);
                    resultatStatistiques.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    scrollPane.createHorizontalScrollBar();
                    scrollPane.createVerticalScrollBar();

                }
                catch (ConnexionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
                catch (MedicamentException e){
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
