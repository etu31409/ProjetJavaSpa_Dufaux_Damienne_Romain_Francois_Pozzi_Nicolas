package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.*;
import modelPackage.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PanneauFicheDeSoin extends JPanel {
    private final Controller controller;
    private FenetreMedicament fenetreMedicament;
    private FenetrePrincipale fenetre;
    private ArrayList<Medicament> medicamentsArrayList;
    private DefaultListModel medicamentsDisposModele, medicamentsChoisisModele;

    private JPanel panneauContainerPrincipal;
    private JCheckBox urgenceCheckBox;
    private JComboBox comboBoxAnimaux, comboBoxVeterinaires;
    private JTextArea textAreaIntituleSoin, textAreaPartieDuCorps, textAreaRemarque;
    private JList listMedicamentsDispos, listMedicamentsChoisis;
    private JButton ajouterButton, retirerButton, ajouterUnMédicamentButton, validerButton, réinitialiserButton, retourButton;
    private JPanel listeMedicamentsDisposJPanel;
    private JPanel listeMedicamentsChoisisJPanel;
    private JScrollPane listeMedicamentsChoisisJScrollPane, listeMedicamentsDisposJScrollPane;
    private JLabel label;


    public PanneauFicheDeSoin(Controller controller, FenetrePrincipale fenetre) {
        this.fenetre = fenetre;
        this.controller = controller;

        instancieListeAnimaux();
        instancieListeVeterinaire();
        instancieListeMedicamentsDispos();

        ajouterButton.addActionListener(new EcouteurBouton());
        retirerButton.addActionListener(new EcouteurBouton());
        ajouterUnMédicamentButton.addActionListener(new EcouteurBouton());
        validerButton.addActionListener(new EcouteurBouton());
        réinitialiserButton.addActionListener(new EcouteurBouton());
        retourButton.addActionListener(new EcouteurBouton());

    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    public void instancieListeVeterinaire() {
        comboBoxVeterinaires.removeAllItems();
        try {
            for (Veterinaire veto : controller.getVeterinaires()) {
                comboBoxVeterinaires.addItem(veto);
            }
        } catch (VeterinaireException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void instancieListeAnimaux() {
        comboBoxAnimaux.removeAllItems();
        try {
            for (Animal a : controller.getAnimaux()) {
                comboBoxAnimaux.addItem(a);
            }
        } catch (AnimalException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void instancieListeMedicamentsDispos() {
        try {

            medicamentsArrayList = controller.getMedicaments();
            medicamentsDisposModele = new DefaultListModel();
            for (Medicament medicament: medicamentsArrayList) {
                medicamentsDisposModele.addElement(medicament);
            }
            listMedicamentsDispos = new JList(medicamentsDisposModele);
            listeMedicamentsDisposJScrollPane.setViewportView(listMedicamentsDispos);

            medicamentsChoisisModele = new DefaultListModel();
            listMedicamentsChoisis = new JList(medicamentsChoisisModele);
            listeMedicamentsChoisisJScrollPane.setViewportView(listMedicamentsChoisis);

        } catch (MedicamentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private boolean validerChamps() {
        boolean valide = true;
        textAreaIntituleSoin.setBorder(null);
        if (textAreaIntituleSoin.getText().isEmpty() || isDigit(textAreaIntituleSoin.getText())) {
            Border border = BorderFactory.createLineBorder(Color.red);
            textAreaIntituleSoin.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            valide = false;
        }
        textAreaPartieDuCorps.setBorder(null);
        if (textAreaPartieDuCorps.getText().isEmpty() || isDigit(textAreaPartieDuCorps.getText())) {
            Border border = BorderFactory.createLineBorder(Color.red);
            textAreaPartieDuCorps.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            valide = false;
        }
        if (listMedicamentsChoisis.isSelectionEmpty()) {
            Border border = BorderFactory.createLineBorder(Color.red);
            listMedicamentsChoisis.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            valide = false;
        }
        return valide;
    }

    private boolean isDigit(String chaine) {
        try {
            Integer.parseInt(chaine);
        } catch (NumberFormatException e){
            return false;
        }return true;
    }

    public void ajouterMedicamentAListeMedicamentsDispos(Medicament medicament) {
        medicamentsDisposModele.addElement(medicament);
    }

    private SoinAvance nouveauSoinAvance() throws SoinException{
        SoinAvance soinAvance = new SoinAvance();
        soinAvance.setNumRegistre(((Animal)comboBoxAnimaux.getSelectedItem()).getNumRegistre());
        soinAvance.setIntitule(textAreaIntituleSoin.getText());
        soinAvance.setPartieDuCorps(textAreaPartieDuCorps.getText());
        /*
         * if(dateDAttributionPuceCheckBox.isSelected()){
            GregorianCalendar date = new GregorianCalendar();
            date.setTime((Date)spinnerDatePuce.getValue());
            animal.setDateNaissance(date);
        }
         */
        soinAvance.setDateSoin(new GregorianCalendar());
        soinAvance.setHeure(new GregorianCalendar());
        soinAvance.setVeterinaire((Veterinaire)comboBoxVeterinaires.getSelectedItem());
        soinAvance.setEstUrgent(urgenceCheckBox.isSelected());
        soinAvance.setRemarque(textAreaRemarque.getText());
        return soinAvance;
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Ordonnance ordonnance;
            int nbMed;
            if (event.getSource() == ajouterButton) {
                Medicament elementSelectionne = (Medicament) listMedicamentsDispos.getSelectedValue();
                medicamentsDisposModele.removeElement(elementSelectionne);
                medicamentsChoisisModele.addElement(elementSelectionne);
            }
            if (event.getSource() == retirerButton) {
                Medicament elementSelectionne = (Medicament) listMedicamentsChoisis.getSelectedValue();
                medicamentsChoisisModele.removeElement(elementSelectionne);
                medicamentsDisposModele.addElement(elementSelectionne);
            }
            if (event.getSource() == validerButton) {
                if(validerChamps()) {
                    nbMed = medicamentsChoisisModele.getSize();
                    for (int i = 0; i < nbMed; i++) {
                        try {
                            //Créer une fiche de soins (SoinAvance)
                            controller.ajouterFicheDeSoins(nouveauSoinAvance());
                            ordonnance = new Ordonnance();
                            //controller.ajouterOrdonnance(); //TODO
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    }
                    JOptionPane.showMessageDialog(null, "La fiche de soin a été correctement ajoutée à la base de données !");
                }
                JOptionPane.showMessageDialog(null, "Certains champs obligatoires ne sont pas remplis !");
            }
            if (event.getSource() == réinitialiserButton) {
                urgenceCheckBox.setSelected(false);
                instancieListeVeterinaire();
                instancieListeAnimaux();
                textAreaIntituleSoin.setBorder(null);
                textAreaIntituleSoin.setText("");
                textAreaPartieDuCorps.setBorder(null);
                textAreaPartieDuCorps.setText("");
                instancieListeMedicamentsDispos();
                listMedicamentsChoisis.removeAll();
                textAreaRemarque.setText("");
                if (fenetreMedicament != null){
                    fenetreMedicament.dispose();
                    fenetreMedicament = null;
                }
            }
            if (event.getSource() == retourButton) {
                fenetre.retourAccueil();
            }
            if (event.getSource() == ajouterUnMédicamentButton) {
                fenetreMedicament = new FenetreMedicament(controller, PanneauFicheDeSoin.this);
            }
        }
    }
}

