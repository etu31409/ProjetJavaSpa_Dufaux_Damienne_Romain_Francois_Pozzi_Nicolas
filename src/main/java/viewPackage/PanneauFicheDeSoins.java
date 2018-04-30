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
//p-ê juste importer animal...


public class PanneauFicheDeSoins extends JPanel {
    private final Controller controller;
    private FenetreFicheDeSoins fenetreFicheDeSoins;
    private JButton retour, confirmerFiche, reinnitialiser, ajouterMedicament;
    private JRadioButton boutonUrgent;
    private JPanel panneauFormulaire, panneauBoutons, panneauTitre;
    private JLabel nouvellePrescription, veterinaireResponsableLabel,
            datePrescriptionLabel, heurePrescriptionLabel,
            animal, medicamentLabel, animalConcerne;
    private JLabel titreSelectionPosologie, titreSelectionStockage, titreSelectionMedicament, remarqueLabel, intituleSoin,
            intitulePartieDuCorps;
    private PanneauSpinnerDate datePrescription, heurePrescription;
    private JComboBox veterinaires, animaux;
    private ComboBoxPosologie posologie;
    private ComboBoxStockage stockage;
    private ComboBoxMedicament medicaments;
    private JLabel vide;
    private JTextArea remarque;
    private JTextField intitule, partieDuCorps;

    public PanneauFicheDeSoins(FenetreFicheDeSoins fenetreFicheDeSoins, Controller controller) {
        this.controller = controller;
        this.fenetreFicheDeSoins = fenetreFicheDeSoins;
        this.setLayout(new BorderLayout());
        panneauBoutons = new JPanel();
        panneauFormulaire = new JPanel();
        panneauTitre = new JPanel();

        this.add(panneauFormulaire, BorderLayout.CENTER);
        this.add(panneauBoutons, BorderLayout.SOUTH);
        this.add(panneauTitre, BorderLayout.NORTH);

        panneauFormulaire.setLayout(new GridLayout(16, 2, 5, 5));
        panneauTitre.setLayout(new GridLayout());

        nouvellePrescription = new JLabel("<html><h1>Nouvelle fiche de soins");
        nouvellePrescription.setHorizontalAlignment(SwingConstants.CENTER);
        panneauTitre.add(nouvellePrescription);

        veterinaireResponsableLabel = new JLabel("Vétérinaire responsable :");
        veterinaireResponsableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(veterinaireResponsableLabel);

        veterinaires = new JComboBox();
        instancieListeVeterinaire();
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

        animal = new JLabel("<html><h3>Animal</h3></html>");
        animal.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(animal);

        animalConcerne = new JLabel("Animal concerné :");
        animalConcerne.setHorizontalAlignment(SwingConstants.CENTER);
        panneauFormulaire.add(animalConcerne);

        animaux = new JComboBox();
        instancieListeAnimaux();
        panneauFormulaire.add(animaux);

        boutonUrgent = new JRadioButton("Fiche urgente");
        boutonUrgent.setHorizontalAlignment(SwingConstants.LEFT);
        panneauFormulaire.add(boutonUrgent);
        panneauFormulaire.add(boutonUrgent);

        intituleSoin = new JLabel("Intitle du soin");
        intituleSoin.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(intituleSoin);
        intitule = new JTextField();
        panneauFormulaire.add(intitule);

        intitulePartieDuCorps = new JLabel("Partie du corps");
        intitulePartieDuCorps.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(intitulePartieDuCorps);
        partieDuCorps = new JTextField();
        panneauFormulaire.add(partieDuCorps);

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

    public void instancieListeVeterinaire() {
        veterinaires.removeAllItems();
        try {
            for (Veterinaire v : controller.getVeterinaires()) {
                veterinaires.addItem(v);
            }
        } catch (VeterinaireException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void instancieListeAnimaux() {
        animaux.removeAllItems();
        try {
            for (Animal a : controller.getAnimaux()) {
                animaux.addItem(a);
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

