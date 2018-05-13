package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.*;
import modelPackage.*;

import javax.swing.*;
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
    private JButton ajouterButton, retirerButton, ajouterUnMedicamentButton, validerButton, reinitialiserButton, retourButton;
    private JPanel listeMedicamentsDisposJPanel;
    private JPanel listeMedicamentsChoisisJPanel;
    private JScrollPane listeMedicamentsChoisisJScrollPane, listeMedicamentsDisposJScrollPane;
    private JLabel titreDeLaPage;
    private JLabel label;
    private boolean modification;
    private SoinAvance soinAvanceModif;


    public PanneauFicheDeSoin(Controller controller, FenetrePrincipale fenetre) {
        this.fenetre = fenetre;
        this.controller = controller;

        instancieListeAnimaux();

        instancieListeVeterinaire();
        instancieListeMedicamentsDispos();

        ajouterButton.addActionListener(new EcouteurBouton());
        retirerButton.addActionListener(new EcouteurBouton());
        ajouterUnMedicamentButton.addActionListener(new EcouteurBouton());
        validerButton.addActionListener(new EcouteurBouton());
        reinitialiserButton.addActionListener(new EcouteurBouton());
        retourButton.addActionListener(new EcouteurBouton());

    }

    public PanneauFicheDeSoin(Controller controller, FenetrePrincipale fenetre, SoinAvance soinAvanceModif) {
        titreDeLaPage.setText("Modifier une fiche de soin");
        modification = true;
        this.fenetre = fenetre;
        this.controller = controller;
        this.soinAvanceModif = soinAvanceModif;
        instancieUnAnimal();
        comboBoxAnimaux.addActionListener(new ComboBoxListener());
        instancieUnVeterinaire();
        instancieListeMedicamentsDispos();

        textAreaIntituleSoin.setText(soinAvanceModif.getIntitule());
        textAreaPartieDuCorps.setText(soinAvanceModif.getPartieDuCorps());
        if(soinAvanceModif.getRemarque() != null){
            textAreaRemarque.setText(soinAvanceModif.getRemarque());
        }
        if(soinAvanceModif.getEstUrgent()){
            urgenceCheckBox.doClick();
        }

        ajouterButton.addActionListener(new EcouteurBouton());
        retirerButton.addActionListener(new EcouteurBouton());
        ajouterUnMedicamentButton.addActionListener(new EcouteurBouton());
        validerButton.addActionListener(new EcouteurBoutonModification());
        reinitialiserButton.addActionListener(new EcouteurBouton());
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void instancieUnVeterinaire(){
        comboBoxVeterinaires.removeAllItems();
        try{
            comboBoxVeterinaires.addItem(controller.getUnVeterinaire(soinAvanceModif.getVeterinaire()));
        }catch (VeterinaireException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void instancieListeAnimaux() {
        comboBoxAnimaux.removeAllItems();
        try {
            for (Animal a : controller.getAnimaux()) {
                comboBoxAnimaux.addItem(a);
            }
        } catch (AnimalException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void instancieUnAnimal(){
        comboBoxAnimaux.removeAllItems();
        try{
            comboBoxAnimaux.addItem(controller.getUnAnimal(soinAvanceModif.getNumRegistre()));
        }catch (AnimalException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void instancieListeMedicamentsDispos() {
        try {
            medicamentsArrayList = controller.getMedicaments();
            medicamentsDisposModele = new DefaultListModel();
            for (Medicament medicament : medicamentsArrayList) {
                medicamentsDisposModele.addElement(medicament);
            }
            listMedicamentsDispos = new JList(medicamentsDisposModele);
            listeMedicamentsDisposJScrollPane.setViewportView(listMedicamentsDispos);

            medicamentsChoisisModele = new DefaultListModel();
            listMedicamentsChoisis = new JList(medicamentsChoisisModele);
            listeMedicamentsChoisisJScrollPane.setViewportView(listMedicamentsChoisis);

        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        } catch (MedicamentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validerChamps() throws TextAreaException {
        textAreaIntituleSoin.setBorder(null);
        if (textAreaIntituleSoin.getText().isEmpty() || isDigit(textAreaIntituleSoin.getText())) {
            throw new TextAreaException(textAreaIntituleSoin, "L'intitulé du soin doit être une chaîne de caractères non vide !");
        }
        textAreaPartieDuCorps.setBorder(null);
        if (textAreaPartieDuCorps.getText().isEmpty() || isDigit(textAreaPartieDuCorps.getText())) {
            throw new TextAreaException(textAreaPartieDuCorps, "La partie du corps doit être une chaîne de caractères non vide !");
        }
        return true;
    }

    private boolean isDigit(String chaine) {
        try {
            Integer.parseInt(chaine);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void ajouterMedicamentAListeMedicamentsDispos(Medicament medicament) {
        medicamentsDisposModele.addElement(medicament);
    }

    private SoinAvance nouveauSoinAvance() {
        SoinAvance soinAvance = new SoinAvance();
        soinAvance.setNumRegistre(((Animal) comboBoxAnimaux.getSelectedItem()).getNumRegistre());
        soinAvance.setIntitule(textAreaIntituleSoin.getText());
        soinAvance.setPartieDuCorps(textAreaPartieDuCorps.getText());
        soinAvance.setDateSoin(new GregorianCalendar());
        soinAvance.setVeterinaire(((Veterinaire) comboBoxVeterinaires.getSelectedItem()).getIdentifiantVeto());
        soinAvance.setEstUrgent(urgenceCheckBox.isSelected());
        if (textAreaRemarque.getText().isEmpty()) soinAvance.setRemarque(null);
        else soinAvance.setRemarque(textAreaRemarque.getText());
        if(modification){
            soinAvance.setNumSoin(soinAvanceModif.getNumSoin());
        }
        return soinAvance;
    }

    private Ordonnance nouvelleOrdonance(Integer idSoinAvance, SoinAvance soinAvance, Medicament medicament) throws SoinException {
        try {
            Ordonnance ord = new Ordonnance();
            ord.setMedicament(medicament.getIdentifiantMed());
            ord.setNumRegistre(soinAvance.getNumRegistre());
            ord.setSoinAvance(idSoinAvance);
            return ord;
        } catch (Exception e) {
        throw new SoinException("Erreur création ordonnance");
    }
}

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
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
                try {

                    if (validerChamps()) {
                        SoinAvance soinAvance = nouveauSoinAvance();
                        Integer identifiantSoinAvance = controller.ajouterFicheDeSoins(soinAvance);
                        for (int i = 0; i < medicamentsChoisisModele.getSize(); i++) {
                            controller.ajouterOrdonnance(nouvelleOrdonance(identifiantSoinAvance, soinAvance,
                                    (Medicament) medicamentsChoisisModele.getElementAt(i)));
                        }
                        JOptionPane.showMessageDialog(null, "La fiche de soin a été correctement ajoutée à la base de données !",
                                "Confirmation!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Certains champs obligatoires ne sont pas remplis !",
                                "Attention!", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SoinException e) {
                    e.printStackTrace();
                } catch (SingletonConnectionException e) {
                    e.printStackTrace();
                } catch (TextAreaException e) {
                    e.printStackTrace();
                } catch (OrdonnanceException e) {
                    e.printStackTrace();
                }
            }
            if (event.getSource() == reinitialiserButton) {
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
                if (fenetreMedicament != null) {
                    fenetreMedicament.dispose();
                    fenetreMedicament = null;
                }
            }
            if (event.getSource() == retourButton) {
                fenetre.retourAccueil();
            }
            if (event.getSource() == ajouterUnMedicamentButton) {
                fenetreMedicament = new FenetreMedicament(controller, PanneauFicheDeSoin.this);
            }
        }
    }

    private class EcouteurBoutonModification implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == validerButton) {
                try {
                    if (validerChamps()) {
                        SoinAvance soinAvance = nouveauSoinAvance();
                        controller.modifierSoin(soinAvance);
                        for (int i = 0; i < medicamentsChoisisModele.getSize(); i++) {
                            controller.ajouterOrdonnance(nouvelleOrdonance(soinAvance.getNumSoin(), soinAvance,
                                    (Medicament)medicamentsChoisisModele.getElementAt(i)));
                        }
                        JOptionPane.showMessageDialog(null, "La fiche de soin a été correctement modifiée à la base de données !",
                                "Confirmation!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Certains champs obligatoires ne sont pas remplis !",
                                "Attention!", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SoinException e) {
                    e.printStackTrace();
                } catch (SingletonConnectionException e) {
                    e.printStackTrace();
                } catch (TextAreaException e) {
                    e.printStackTrace();
                } catch (OrdonnanceException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ComboBoxListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() ==  comboBoxAnimaux){
                instancieListeAnimaux();
            }
            if(event.getSource() == comboBoxVeterinaires){
                instancieListeVeterinaire();
            }
        }
    }
}

