package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.AnimalException;
import exceptionPackage.MedicamentException;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;
import modelPackage.Animal;
import modelPackage.Medicament;
import modelPackage.Veterinaire;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JList listMedicamentsDispos, listMdicamentsChoisis;
    private JButton ajouterButton, retirerButton, ajouterUnMédicamentButton, validerButton, réinitialiserButton, retourButton;
    private JPanel listeMedicamentsDisposJPanel;
    private JPanel listeMedicamentsChoisisJPanel;
    private JScrollPane listeMedicamentsChoisisJScrollPane, listeMedicamentsDisposJScrollPane;


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
            for (Veterinaire v : controller.getVeterinaires()) {
                comboBoxVeterinaires.addItem(v);
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
            listMdicamentsChoisis = new JList(medicamentsChoisisModele);
            listeMedicamentsChoisisJScrollPane.setViewportView(listMdicamentsChoisis);

        } catch (MedicamentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private Boolean validerChamps(){
        Boolean validationChamp = true;
        textAreaIntituleSoin.setBorder(null);
        if (textAreaIntituleSoin.getText().equals("")){
            Border border = BorderFactory.createLineBorder(Color.red);
            textAreaIntituleSoin.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            validationChamp = false;
        }
        return validationChamp;
    }

    public void ajouterMedicamentAListeMedicamentsDispos(Medicament medicament) {
        medicamentsDisposModele.addElement(medicament);
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == ajouterButton) {
                Medicament elementSelectionne = (Medicament) listMedicamentsDispos.getSelectedValue();
                medicamentsDisposModele.removeElement(elementSelectionne);
                medicamentsChoisisModele.addElement(elementSelectionne);

            }
            if (event.getSource() == retirerButton) {
                Medicament elementSelectionne = (Medicament) listMdicamentsChoisis.getSelectedValue();
                medicamentsChoisisModele.removeElement(elementSelectionne);
                medicamentsDisposModele.addElement(elementSelectionne);
            }
            if (event.getSource() == validerButton) {
                if (validerChamps()) {
                    //controller.ajouterFicheDeSoin(); //TODO
                    JOptionPane.showMessageDialog(null, "La fiche de soin a été correctement ajoutée à la base de données !");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Certains champs obligatoires ne sont pas remplis !");
                }
            }
            if (event.getSource() == réinitialiserButton) {
                urgenceCheckBox.setSelected(false);
                instancieListeVeterinaire();
                instancieListeAnimaux();
                textAreaIntituleSoin.setBorder(null);
                textAreaIntituleSoin.setText("");
                textAreaPartieDuCorps.setText("");
                instancieListeMedicamentsDispos();
                listMdicamentsChoisis.removeAll();
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

