package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.ProprietaireException;
import modelPackage.Proprietaire;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class PanneauProprio {

    private FenetreProprio fenetreProprio;
    private Controller controller;

    private Proprietaire proprietaire;
    private PanneauAnimal panneauAnimal;
    private JPanel panneauContainerPrincipal;

    private JTextField textFieldNom;
    private JTextField textFieldPrenom;
    private JButton annulerButton;
    private JButton validerButton;

    public PanneauProprio(Controller controller, FenetreProprio fenetreProprio, PanneauAnimal panneauAnimal) {
        this.fenetreProprio = fenetreProprio;
        this.panneauAnimal  = panneauAnimal;
        this.controller = controller;
        validerButton.addActionListener(new ButtonListener());
        annulerButton.addActionListener(new ButtonListener());
    }


    public Boolean validationFormulaire() {
        Boolean estValide = true;
        if (textFieldNom.getText().equals("")) {
            estValide = false;
            Border border = BorderFactory.createLineBorder(Color.red);
            textFieldNom.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        }
        if (textFieldPrenom.getText().equals("")) {
            estValide = false;
            Border border = BorderFactory.createLineBorder(Color.red);
            textFieldPrenom.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        }
        return estValide;
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == validerButton) {
                try {
                    textFieldNom.setBorder(null);
                    textFieldPrenom.setBorder(null);
                    if (validationFormulaire()) {
                        proprietaire = new Proprietaire(textFieldNom.getText(), textFieldPrenom.getText());
                        //controller.ajouterNouveauProprio(proprietaire); //TODO
                        JOptionPane.showMessageDialog(null, "La fiche de soin a été correctement ajoutée à la base de données !");
                        panneauAnimal.instancieListeProprietaires();
                        fenetreProprio.dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Certains champs obligatoires ne sont pas remplis !");
                    }
                } catch (ProprietaireException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } else if (event.getSource() == annulerButton) {
                fenetreProprio.dispose();
            }
        }
    }
}
