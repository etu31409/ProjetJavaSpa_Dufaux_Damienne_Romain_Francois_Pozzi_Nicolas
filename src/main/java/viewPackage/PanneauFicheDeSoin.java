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
    private ArrayList<Medicament> medicamentsDeLaFiche;
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
    private boolean modification;
    private SoinAvance soinAvanceAvantModification;


    public PanneauFicheDeSoin(Controller controller, FenetrePrincipale fenetre) {
        this.fenetre = fenetre;
        this.controller = controller;

        reinitialiser();

        ajouterButton.addActionListener(new EcouteurBouton());
        retirerButton.addActionListener(new EcouteurBouton());
        ajouterUnMedicamentButton.addActionListener(new EcouteurBouton());
        validerButton.addActionListener(new EcouteurBouton());
        reinitialiserButton.addActionListener(new EcouteurBouton());
        retourButton.addActionListener(new EcouteurBouton());

    }

    public PanneauFicheDeSoin(Controller controller, FenetrePrincipale fenetre, SoinAvance soinAvanceAvantModification) {
        titreDeLaPage.setText("Modifier une fiche de soin");
        modification = (soinAvanceAvantModification != null);
        if (!modification) {
            throw new NullPointerException("Erreur lors de la modification de la fiche de soin!\n" +
                    "Contactez votre administrateur système!");
        }
        this.fenetre = fenetre;
        this.controller = controller;
        this.soinAvanceAvantModification = soinAvanceAvantModification;

        reinitialiser();

        ajouterButton.addActionListener(new EcouteurBouton());
        retirerButton.addActionListener(new EcouteurBouton());
        ajouterUnMedicamentButton.addActionListener(new EcouteurBouton());
        validerButton.addActionListener(new EcouteurBouton());
        reinitialiserButton.addActionListener(new EcouteurBouton());
        retourButton.addActionListener(new EcouteurBouton());

        textAreaIntituleSoin.setText(soinAvanceAvantModification.getIntitule());
        textAreaPartieDuCorps.setText(soinAvanceAvantModification.getPartieDuCorps());

        if (soinAvanceAvantModification.getRemarque() != null) {
            textAreaRemarque.setText(soinAvanceAvantModification.getRemarque());
        }
        if (soinAvanceAvantModification.getEstUrgent()) {
            urgenceCheckBox.setSelected(true);
        }
        comboBoxAnimaux.setSelectedIndex(soinAvanceAvantModification.getNumRegistre()-1);
        comboBoxVeterinaires.setSelectedIndex(soinAvanceAvantModification.getVeterinaire()-1);

        try {

            medicamentsDeLaFiche = controller.getMedicamentsDeLaFiche(soinAvanceAvantModification.getNumSoin());
            Integer tallieJlist = medicamentsDisposModele.size();
            for (int i = 0; i < medicamentsDeLaFiche.size(); i++) {
                for (int j = 0; j < tallieJlist; j++) {
                    Medicament med = (Medicament) medicamentsDisposModele.getElementAt(j);
                    if (med.getIdentifiantMed() == ( medicamentsDeLaFiche.get(i)).getIdentifiantMed()) {
                        medicamentsDisposModele.removeElement(med);
                        tallieJlist--;
                        break;
                    }
                }
                medicamentsChoisisModele.addElement(medicamentsDeLaFiche.get(i));
            }


        } catch (MedicamentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        } catch (ConnexionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void instancieListeVeterinaire() {
        comboBoxVeterinaires.removeAllItems();
        try {
            for (Veterinaire veto : controller.getVeterinaires()) {
                comboBoxVeterinaires.addItem(veto);
            }
        } catch (VeterinaireException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        } catch (ConnexionException e) {
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
        } catch (ConnexionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void instancieListeMedicamentsDispos() {
        try {
            medicamentsDisposModele = new DefaultListModel();
            for (Medicament medicament : controller.getMedicaments()) {
                medicamentsDisposModele.addElement(medicament);
            }
            listMedicamentsDispos = new JList(medicamentsDisposModele);
            listeMedicamentsDisposJScrollPane.setViewportView(listMedicamentsDispos);

            medicamentsChoisisModele = new DefaultListModel();
            listMedicamentsChoisis = new JList(medicamentsChoisisModele);
            listeMedicamentsChoisisJScrollPane.setViewportView(listMedicamentsChoisis);

        } catch (ConnexionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        } catch (MedicamentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reinitialiser() {
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

    private boolean validerChamps() throws TextAreaException {
        textAreaIntituleSoin.setBorder(null);
        if (textAreaIntituleSoin.getText().isEmpty() || estUnNombre(textAreaIntituleSoin.getText())) {
            throw new TextAreaException(textAreaIntituleSoin, "L'intitulé du soin doit être une chaîne de caractères non vide !");
        }
        textAreaPartieDuCorps.setBorder(null);
        if (textAreaPartieDuCorps.getText().isEmpty() || estUnNombre(textAreaPartieDuCorps.getText())) {
            throw new TextAreaException(textAreaPartieDuCorps, "La partie du corps doit être une chaîne de caractères non vide !");
        }
        return true;
    }

    private boolean estUnNombre(String chaine) {
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
        if (modification) {
            soinAvance.setNumSoin(soinAvanceAvantModification.getNumSoin());
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
            throw new SoinException("Erreur lors de la création de l'ordonnance");
        }
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == ajouterButton) {
                Medicament elementSelectionne = (Medicament) listMedicamentsDispos.getSelectedValue();
                if (elementSelectionne != null) {
                    medicamentsDisposModele.removeElement(elementSelectionne);
                    medicamentsChoisisModele.addElement(elementSelectionne);
                }
            }
            if (event.getSource() == retirerButton) {
                Medicament elementSelectionne = (Medicament) listMedicamentsChoisis.getSelectedValue();
                if (elementSelectionne != null) {
                    medicamentsChoisisModele.removeElement(elementSelectionne);
                    medicamentsDisposModele.addElement(elementSelectionne);
                }
            }
            if (event.getSource() == validerButton) {
                try {
                    if (validerChamps()) {
                        if (!modification) {
                            SoinAvance soinAvance = nouveauSoinAvance();
                            Integer identifiantSoinAvance = controller.ajouterFicheDeSoins(soinAvance);
                            for (int i = 0; i < medicamentsChoisisModele.getSize(); i++) {
                                controller.ajouterOrdonnance(nouvelleOrdonance(identifiantSoinAvance, soinAvance,
                                        (Medicament) medicamentsChoisisModele.getElementAt(i)));
                            }
                            JOptionPane.showMessageDialog(null, "La fiche de soin a été correctement ajoutée à la base de données !",
                                    "Confirmation!", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            ArrayList<Medicament> listeDeMedocsAvantModif =
                                    controller.getMedicamentsDeLaFiche(soinAvanceAvantModification.getNumSoin());

                            for (int j = 0; j < listeDeMedocsAvantModif.size(); j++) {
                                for (int i = 0; i < medicamentsDisposModele.getSize(); i++) {
                                    int idMedocDeListeAvantModif = listeDeMedocsAvantModif.get(j).getIdentifiantMed();
                                    int idMedocDeListeDispo = ((Medicament) medicamentsDisposModele.getElementAt(i)).getIdentifiantMed();
                                    if (idMedocDeListeAvantModif == idMedocDeListeDispo)
                                        controller.supprimerOrdonnance(soinAvanceAvantModification, (Medicament) medicamentsDisposModele.getElementAt(i));
                                }
                            }

                            for (int i = 0; i < medicamentsChoisisModele.getSize(); i++) {
                                if (listeDeMedocsAvantModif.isEmpty()) {
                                    controller.ajouterOrdonnance(nouvelleOrdonance(soinAvanceAvantModification.getNumSoin(),
                                            soinAvanceAvantModification, (Medicament) medicamentsChoisisModele.getElementAt(i)));
                                }
                                else {
                                    Boolean estDejaEnBaseDeDonnees = false;
                                    for (int j = 0; j < listeDeMedocsAvantModif.size(); j++) {
                                        int idMedocDeListeAvantModif = listeDeMedocsAvantModif.get(j).getIdentifiantMed();
                                        int idMedocDeListeChoisis = ((Medicament) medicamentsChoisisModele.getElementAt(i)).getIdentifiantMed();
                                        if (idMedocDeListeAvantModif == idMedocDeListeChoisis) {
                                            estDejaEnBaseDeDonnees = true;
                                        }
                                    }
                                    if (!estDejaEnBaseDeDonnees) {
                                        controller.ajouterOrdonnance(nouvelleOrdonance(soinAvanceAvantModification.getNumSoin(),
                                                soinAvanceAvantModification, (Medicament) medicamentsChoisisModele.getElementAt(i)));
                                    }
                                }
                            }

                            SoinAvance soinAvanceAModifier = nouveauSoinAvance();
                            soinAvanceAModifier.setNumSoin(soinAvanceAvantModification.getNumSoin());
                            soinAvanceAModifier.setDateSoin(soinAvanceAvantModification.getDateSoin());
                            if(soinAvanceAModifier.getNumRegistre() != soinAvanceAvantModification.getNumRegistre())
                                controller.modifierOrdonnances(soinAvanceAModifier);
                            controller.modifierSoin(soinAvanceAModifier);

                            JOptionPane.showMessageDialog(null, "La fiche de soin a été correctement modifiée à la base de données !",
                                    "Confirmation!", JOptionPane.INFORMATION_MESSAGE);
                            fenetre.afficherListingFichesDeSoin();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Certains champs obligatoires ne sont pas remplis !",
                                "Attention!", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SoinException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                } catch (ConnexionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                } catch (TextAreaException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                } catch (OrdonnanceException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                } catch (MedicamentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (event.getSource() == reinitialiserButton) {
                reinitialiser();
            }
            if (event.getSource() == retourButton) {
                if (modification)
                    fenetre.afficherListingFichesDeSoin();
                else
                    fenetre.retourAccueil();
            }
            if (event.getSource() == ajouterUnMedicamentButton) {
                fenetreMedicament = new FenetreMedicament(controller, PanneauFicheDeSoin.this);
            }
        }
    }
}

