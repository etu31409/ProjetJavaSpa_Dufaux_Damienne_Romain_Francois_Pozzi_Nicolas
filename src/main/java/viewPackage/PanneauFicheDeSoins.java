package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//p-ê juste importer animal...


public class PanneauFicheDeSoins extends JPanel {
    private FenetreFicheDeSoins fenetreFicheDeSoins;
    private JButton retour, confirmerFiche, reinnitialiser;
    private JPanel panneauFormulaire, panneauBoutons;
    private JLabel nouvellePrescription, veterinaireResponsableLabel,
            datePrescriptionLabel, heurePrescriptionLabel,
            numeroAnimalLabel, especeLabel, raceLabel, poidLabel,
            medicamentLabel;
    private JLabel titreSelectionPosologie, titreSelectionStockage, titreSelectionMedicament, remarqueLabel;
    private PanneauSpinnerDate datePrescription, heurePrescription;
    private JComboBox veterinaires;
    private ComboBoxPosologie posologie;
    private ComboBoxStockage stockage;
    private ComboBoxMedicament medicaments;
    private JLabel vide;
    private JButton ajouterMedicament;
    private JTextArea remarque;

    public PanneauFicheDeSoins(FenetreFicheDeSoins fenetreFicheDeSoins) {
        this.fenetreFicheDeSoins = fenetreFicheDeSoins;
        this.setLayout(new BorderLayout());
        panneauBoutons = new JPanel();
        panneauFormulaire = new JPanel();
        this.add(panneauFormulaire, BorderLayout.CENTER);
        this.add(panneauBoutons, BorderLayout.SOUTH);
        panneauFormulaire.setLayout(new GridLayout(16, 2, 5, 5));

        nouvellePrescription = new JLabel("<html><h1>Nouvelle fiche de soins");
        nouvellePrescription.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(nouvellePrescription);

        //nouvellePrescription = new JLabel("");
        //nouvellePrescription.setHorizontalAlignment(SwingConstants.CENTER);
        //panneauFormulaire.add(nouvellePrescription);

        vide = new JLabel("");
        panneauFormulaire.add(vide);

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

        heurePrescriptionLabel = new JLabel("Heure :");
        heurePrescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(heurePrescriptionLabel);
        heurePrescription = new PanneauSpinnerDate();
        panneauFormulaire.add(heurePrescription);

        numeroAnimalLabel = new JLabel("<html><h5>Animal</h5></html>");
        numeroAnimalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(numeroAnimalLabel);
        numeroAnimalLabel = new JLabel("");
        numeroAnimalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(numeroAnimalLabel);

        medicamentLabel = new JLabel("<html><h1>Médicament</h1></html>");
        medicamentLabel.setHorizontalAlignment(SwingConstants.CENTER);;
        panneauFormulaire.add(medicamentLabel);
        medicamentLabel = new JLabel("");
        medicamentLabel.setHorizontalAlignment(SwingConstants.CENTER);;
        panneauFormulaire.add(medicamentLabel);

        titreSelectionMedicament = new JLabel("Selection du médicament");
        titreSelectionMedicament.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(titreSelectionMedicament);
        medicaments = new ComboBoxMedicament();
        panneauFormulaire.add(medicaments);

        /*vide = new JLabel("");
        panneauFormulaire.add(vide);*/

        titreSelectionStockage = new JLabel("Selection du stockage");
        titreSelectionStockage.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(titreSelectionStockage);
        stockage = new ComboBoxStockage();
        panneauFormulaire.add(stockage);

        titreSelectionPosologie = new JLabel("Selection de la posologie");
        titreSelectionPosologie.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(titreSelectionPosologie);
        posologie = new ComboBoxPosologie();
        panneauFormulaire.add(posologie);

        remarqueLabel = new JLabel("Remarque :");
        remarqueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(remarqueLabel);
        remarque = new JTextArea();
        panneauFormulaire.add(remarque);

        panneauBoutons.setLayout(new FlowLayout());
        retour = new JButton("Retour");
        retour.addActionListener(new EcouteurBouton());
        panneauBoutons.add(retour);
        confirmerFiche = new JButton("Confirmer");
        confirmerFiche.addActionListener(new EcouteurBouton());
        panneauBoutons.add(confirmerFiche);
        reinnitialiser = new JButton("Reinnitialiser");
        reinnitialiser.addActionListener(new EcouteurBouton());
        panneauBoutons.add(reinnitialiser);
    }

    private class EcouteurBouton implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == retour){
                fenetreFicheDeSoins.dispose();
            }
            if(e.getSource() == reinnitialiser) {
                fenetreFicheDeSoins.dispose();
                fenetreFicheDeSoins = new FenetreFicheDeSoins();
            }
            if(e.getSource() == confirmerFiche){
                //si pas de vétérinaire choisi erreur

                //si pas de médicament choisi erreur

            }
        }
    }
}

