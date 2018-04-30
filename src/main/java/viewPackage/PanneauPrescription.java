package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//p-ê juste importer animal...


public class PanneauPrescription extends JPanel {
    private FenetrePrescription fenetrePrescription;
    private JButton retour, confirmerPrescription, reinnitialiser;
    private JPanel panneauFormulaire, panneauBoutons;
    private JLabel nouvellePrescription, veterinaireResponsableLabel, datePrescriptionLabel, numeroAnimalLabel, especeLabel, raceLabel, poidLabel, medicamentLabel;
    private JLabel titreSelectionPosologie, titreSelectionStockage, remarqueLabel;
    private PanneauSpinnerDate datePrescription;
    private JComboBox medicaments, veterinaires;
    private ComboBoxPosologie posologie;
    private ComboBoxStockage stockage;
    private JLabel vide;
    private JButton ajouterMedicament;
    private JTextArea remarque;

    public PanneauPrescription(FenetrePrescription fenetrePrescription) {
        this.fenetrePrescription = fenetrePrescription;
        this.setLayout(new BorderLayout());
        panneauBoutons = new JPanel();
        panneauFormulaire = new JPanel();
        this.add(panneauFormulaire, BorderLayout.CENTER);
        this.add(panneauBoutons, BorderLayout.SOUTH);
        panneauFormulaire.setLayout(new GridLayout(16, 2, 5, 5));

        nouvellePrescription = new JLabel("<html><h1>Nouvelle prescription");
        nouvellePrescription.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(nouvellePrescription);
        nouvellePrescription = new JLabel("");
        nouvellePrescription.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(nouvellePrescription);

        veterinaireResponsableLabel = new JLabel("Vétérinaire responsable :");
        veterinaireResponsableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(veterinaireResponsableLabel);
        veterinaires = new JComboBox();
        panneauFormulaire.add(veterinaires);

        datePrescriptionLabel = new JLabel("Date :");
        datePrescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(datePrescriptionLabel);
        datePrescription = new PanneauSpinnerDate();
        panneauFormulaire.add(datePrescription);

        numeroAnimalLabel = new JLabel("<html><h1>Animal#1234</h1></html>");
        numeroAnimalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(numeroAnimalLabel);
        numeroAnimalLabel = new JLabel("");
        numeroAnimalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(numeroAnimalLabel);

        especeLabel = new JLabel("Espece :");
        especeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(especeLabel);
        especeLabel = new JLabel("Chien");
        //especeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(especeLabel);

        raceLabel = new JLabel("Race :");
        raceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(raceLabel);
        raceLabel = new JLabel("Colley");
        panneauFormulaire.add(raceLabel);

        //récupérer la valeur du jSpinner au dessus
        poidLabel = new JLabel("Poids au 26/03/18 :");
        poidLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(poidLabel);
        poidLabel = new JLabel("20 kg");
       // poidLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(poidLabel);

        medicamentLabel = new JLabel("<html><h1>Médicament</h1></html>");
        medicamentLabel.setHorizontalAlignment(SwingConstants.CENTER);;
        panneauFormulaire.add(medicamentLabel);
        medicamentLabel = new JLabel("");
        medicamentLabel.setHorizontalAlignment(SwingConstants.CENTER);;
        panneauFormulaire.add(medicamentLabel);

        medicaments = new JComboBox();
        panneauFormulaire.add(medicaments);
        vide = new JLabel("");
        panneauFormulaire.add(vide);

        titreSelectionStockage = new JLabel("<html><h3>Selection du stockage</h3></html>");
        titreSelectionStockage.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(titreSelectionStockage);
        stockage = new ComboBoxStockage();
        panneauFormulaire.add(stockage);

        titreSelectionPosologie = new JLabel("<html><h3>Selection de la posologie</h3></html>");
        titreSelectionPosologie.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(titreSelectionPosologie);
        posologie = new ComboBoxPosologie();
        panneauFormulaire.add(posologie);

        ajouterMedicament = new JButton("Ajouter un médicament");
        panneauFormulaire.add(ajouterMedicament);
        vide = new JLabel("");
        panneauFormulaire.add(vide);

        remarqueLabel = new JLabel("Remarque :");
        remarqueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(remarqueLabel);
        remarque = new JTextArea();
        panneauFormulaire.add(remarque);

        panneauBoutons.setLayout(new FlowLayout());
        retour = new JButton("Retour");
        retour.addActionListener(new EcouteurBouton());
        panneauBoutons.add(retour);
        confirmerPrescription = new JButton("Confirmer prescription");
        confirmerPrescription.addActionListener(new EcouteurBouton());
        panneauBoutons.add(confirmerPrescription);
        reinnitialiser = new JButton("Reinnitialiser");
        reinnitialiser.addActionListener(new EcouteurBouton());
        panneauBoutons.add(reinnitialiser);
    }

    private class EcouteurBouton implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == retour){
                fenetrePrescription.dispose();
            }
            if(e.getSource() == reinnitialiser) {
                fenetrePrescription.dispose();
                fenetrePrescription = new FenetrePrescription();
            }
            if(e.getSource() == confirmerPrescription){
                //si pas de vétérinaire choisi erreur

                //si pas de médicament choisi erreur

            }
        }
    }
}

