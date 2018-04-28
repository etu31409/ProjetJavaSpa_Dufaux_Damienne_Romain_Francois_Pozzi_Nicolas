package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanneauProprio extends JPanel {

    private FenetreProprio fenetreProprio;
    private JPanel panneauFormulaire, panneauBouton;
    private JScrollPane scroll;
    private JLabel prenomLabel, nomLabel, rueLabel, codePostalLabel, villeLabel, paysLabel, numeroAdresseLabel;
    private JTextField prenom, nom, rue, codePostal, ville, numeroAdresse;
    private JComboBox pays;
    private String paysValues[] = {"Belgique", "France"};;
    private JButton retour, valider, reinnitialiser;

    public PanneauProprio(FenetreProprio fen){
        fenetreProprio = fen;
        this.setLayout(new BorderLayout());

        panneauFormulaire = new JPanel();
        panneauBouton = new JPanel();
        this.add(panneauFormulaire, BorderLayout.CENTER);
        this.add(panneauBouton, BorderLayout.SOUTH);
        panneauFormulaire.setLayout(new GridLayout(7,2,5,5));
        scroll = new JScrollPane();

        nomLabel = new JLabel("Nom :");
        nomLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(nomLabel);
        nom = new JTextField();
        panneauFormulaire.add(nom);

        prenomLabel = new JLabel("Prénom :");
        prenomLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(prenomLabel);
        prenom = new JTextField();
        panneauFormulaire.add(prenom);

        rueLabel = new JLabel("Rue :");
        rueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(rueLabel);
        rue = new JTextField();
        panneauFormulaire.add(rue);

        numeroAdresseLabel = new JLabel("Numéro : ");
        numeroAdresseLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(numeroAdresseLabel);
        numeroAdresse = new JTextField();
        panneauFormulaire.add(numeroAdresse);

        codePostalLabel = new JLabel("Code postal :");
        codePostalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(codePostalLabel);
        codePostal = new JTextField();
        panneauFormulaire.add(codePostal);

        villeLabel = new JLabel("Ville :");
        villeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(villeLabel);
        ville = new JTextField();
        panneauFormulaire.add(ville);

        paysLabel = new JLabel("Pays :");
        paysLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(paysLabel);
        pays = new JComboBox(paysValues);
        panneauFormulaire.add(pays);

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

            }
            else if (event.getSource() == reinnitialiser){
                fenetreProprio = new FenetreProprio();
            }
        }
    }
}
