package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.ProprietaireException;
import modelPackage.Proprietaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanneauProprio{

    private FenetreProprio fenetreProprio;
    private Controller controller;

    private Proprietaire proprietaire;
    private JPanel panneauContainerPrincipal;

    private JTextField textFieldNom;
    private JTextField textFieldPrenom;
    private JButton annulerButton;
    private JButton validerButton;

    public PanneauProprio(Controller controller) {
        this.controller = controller;
        validerButton.addActionListener(new ButtonListener());
        annulerButton.addActionListener(new ButtonListener());
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            fenetreProprio.dispose();
            if (event.getSource() == validerButton){
                //validationFormulaire();
            }
            else if (event.getSource() == annulerButton){
                //fenetreProprio = new FenetreProprio();
            }
        }
    }

    public void validationFormulaire(){
        try{
            String nomProprio;
            String prenomProprio;

            nomProprio = textFieldNom.getText();
            prenomProprio = textFieldPrenom.getText();
            proprietaire = new Proprietaire(nomProprio, prenomProprio);
        }
        catch(ProprietaireException exception){System.out.println(exception.getMessage());}
    }
    public Proprietaire getProprietaire(){
        return proprietaire;
    }
}
