package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.AnimalException;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;
import modelPackage.Animal;
import modelPackage.Veterinaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauFicheDeSoin extends JPanel {
    private final Controller controller;
    private FenetreFicheDeSoins fenetreFicheDeSoins;
    private FenetreMedicament fenetreMedicament;

    private JTextArea remarque;
    private JPanel panneauContainerPrincipal;
    private JCheckBox urgenceCheckBox;
    private JComboBox comboBoxAnimaux, comboBoxVeterinaires;
    private JTextArea textAreaIntituleSoin, textAreaPartieDuCorps, textAreaRemarque;
    private JList listMedicamentsDispos, listMdicamentsChoisis;
    private JButton ajouterButton, retirerButton, ajouterUnMédicamentButton, validerButton, réinitialiserButton, retourButton;


    public PanneauFicheDeSoin(Controller controller) {
        this.controller = controller;

        instancieListeAnimaux();
        instancieListeVeterinaire();

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

    private class EcouteurBouton implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == ajouterButton){
                //TODO
            }
            if(e.getSource() == retirerButton) {
                //TODO
            }
            if(e.getSource() == validerButton){
                //TODO
            }
            if(e.getSource() == réinitialiserButton){
                //TODO
            }
            if(e.getSource() == retourButton){
                //TODO
            }
            if(e.getSource() == ajouterUnMédicamentButton){
                fenetreMedicament = new FenetreMedicament();
            }
        }
    }
}

