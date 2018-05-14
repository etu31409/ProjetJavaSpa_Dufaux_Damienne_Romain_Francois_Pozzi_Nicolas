package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.ConnexionException;
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
        validerButton.addActionListener(new EcouteurBouton());
        annulerButton.addActionListener(new EcouteurBouton());
    }

    private Boolean validationFormulaire() {
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

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == validerButton) {
                try {
                    textFieldNom.setBorder(null);
                    textFieldPrenom.setBorder(null);
                    if (validationFormulaire()) {
                        proprietaire = new Proprietaire(textFieldNom.getText(), textFieldPrenom.getText());
                        controller.ajouterNouveauProprio(proprietaire);
                        JOptionPane.showMessageDialog(null, "Le propriétaire a été correctement ajouté à la base de données !",
                                "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        panneauAnimal.instanciationListeProprietaires();
                        fenetreProprio.dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Certains champs obligatoires ne sont pas remplis !", "Erreur !",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ProprietaireException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                } catch (ConnexionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            } else if (event.getSource() == annulerButton) {
                fenetreProprio.dispose();
            }
        }
    }
}
