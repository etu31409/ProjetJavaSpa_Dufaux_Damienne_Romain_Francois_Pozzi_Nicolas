package viewPackage;

import exceptionPackage.ProprietaireException;
import modelPackage.Proprietaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanneauProprio extends JPanel {

    private FenetreProprio fenetreProprio;
    private JPanel panneauFormulaire, panneauBouton;
    private JScrollPane scroll;
    private JLabel prenomLabel, nomLabel;
    private JTextField prenom, nom;
    private JButton retour, valider, reinnitialiser;
    private Proprietaire proprietaire;

    public PanneauProprio(FenetreProprio fen){
        fenetreProprio = fen;
        this.setLayout(new BorderLayout());

        panneauFormulaire = new JPanel();
        panneauBouton = new JPanel();
        this.add(panneauFormulaire, BorderLayout.CENTER);
        this.add(panneauBouton, BorderLayout.SOUTH);
        panneauFormulaire.setLayout(new GridLayout(4,2,2,2));
        scroll = new JScrollPane();

        nomLabel = new JLabel("Nom :");
        nomLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panneauFormulaire.add(nomLabel);
        nom = new JTextField();
        panneauFormulaire.add(nom);

        prenomLabel = new JLabel("Prénom :");
        prenomLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panneauFormulaire.add(prenomLabel);
        prenom = new JTextField();
        panneauFormulaire.add(prenom);

        valider = new JButton("Valider");
        valider.addActionListener(new ButtonListener());
        retour = new JButton("Retour");
        retour.addActionListener(new ButtonListener());
        reinnitialiser = new JButton("Réinnitialiser");
        reinnitialiser.addActionListener(new ButtonListener());

        panneauBouton.add(retour);
        panneauBouton.add(valider);
        panneauBouton.add(reinnitialiser);
    }


    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            fenetreProprio.dispose();
            if (event.getSource() == valider){
                validationFormulaire();
            }
            else if (event.getSource() == reinnitialiser){
                fenetreProprio = new FenetreProprio();
            }
        }
    }

    public void validationFormulaire(){
        Integer numero = 0;
        try{
            String nomProprio;
            String prenomProprio;

            nomProprio = nom.getText();
            prenomProprio = prenom.getText();
            proprietaire = new Proprietaire(nomProprio, prenomProprio);
        }
        catch(ProprietaireException exception){System.out.println(exception.getMessage());}
    }
    Proprietaire getProprietaire(){
        return proprietaire;
    }
}
