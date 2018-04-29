package viewPackage;

import exceptionPackage.AnimalException;
import modelPackage.Animal;
import modelPackage.Proprietaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;

public class PanneauAccueil extends JPanel {
    //private JLabel texte;
    private JPanel panneauFormulaire;
    private JPanel panneauBoutons;
    private JLabel nomLabel, lieuxSPALabel, celluleLabel, numeroRegistreLabel, poidsLabel, dateRemplissageLabel, aEuthanasierLabel, raisonEuthanasieLabel;
    private JLabel dateArriveLabel, dateDepartLabel, especeLabel, raceLabel, pelagePeauLabel, dateNaissanceLabel, numeroPuceLabel, localisationLabel;
    private JLabel attributionLabel, numeroTatouageLabel, incertainLocalisationTatouageLabel, incertainDateTatouageLabel, dateTatouageLabel, titreProprietaireLabel;
    private JLabel remarqueLabel, localisationTatouageLabel;
    private JTextField localisationTatouage, nom, lieuxSPA, cellule, numeroRegistre, poids, raisonEuthanasie, espece, race, pelagePeau, numeroPuce, localisation, numeroTatouage;
    private PanneauSpinnerDate dateRemplissage, dateEuthanasie, dateArrive, dateDepart, dateNaissance, dateAttribution, dateTatouage;
    private JButton  retour, validation, reinnitialiser;
    private JCheckBox aEuthanasier, estIncertainDateTatouage, estIncertainLocalisationTatouage;
    private ButtonGroup sexeGroupeBouton, steriliseGroupeBouton;
    private JRadioButton boutonMale, boutonFemelle, boutonOuiSterilise, boutonNonSterilise;
    private JComboBox listeProprios;
    private JButton ajoutProprio;
    private JTextArea remarque;
    private JScrollPane scroll;
    private FenetreProprio fenetreProprio;

    public PanneauAccueil() {
        this.setLayout(new BorderLayout());

        panneauFormulaire = new JPanel();
        panneauBoutons = new JPanel();
        this.add(panneauFormulaire, BorderLayout.CENTER);
        this.add(panneauBoutons, BorderLayout.SOUTH);
        panneauFormulaire.setLayout(new GridLayout(15,4,5,5));
        scroll = new JScrollPane();
        this.add(scroll, BorderLayout.EAST);

        dateRemplissageLabel = new JLabel("Date actuelle :");
        dateRemplissageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(dateRemplissageLabel);
        dateRemplissage = new PanneauSpinnerDate();
        panneauFormulaire.add(dateRemplissage);

        nomLabel = new JLabel("Nom de l'animal :");
        nomLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(nomLabel);
        nom = new JTextField();
        panneauFormulaire.add(nom);

        lieuxSPALabel = new JLabel("SPA de :");
        lieuxSPALabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(lieuxSPALabel);
        lieuxSPA = new JTextField();
        panneauFormulaire.add(lieuxSPA);

        celluleLabel = new JLabel("N° de cellule :");
        celluleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(celluleLabel);
        cellule = new JTextField();
        panneauFormulaire.add(cellule);

        numeroRegistreLabel = new JLabel("N° de registre :");
        numeroRegistreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(numeroRegistreLabel);
        numeroRegistre = new JTextField();
        panneauFormulaire.add(numeroRegistre);

        poidsLabel = new JLabel("Poids de l'animale :");
        poidsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(poidsLabel);
        poids = new JTextField();
        panneauFormulaire.add(poids);

        aEuthanasier = new JCheckBox("à euthanasier :");
        aEuthanasier.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(aEuthanasier);

        aEuthanasierLabel = new JLabel("prévu pour le :");
        dateEuthanasie = new PanneauSpinnerDate();
        panneauFormulaire.add(dateEuthanasie);

        raisonEuthanasieLabel = new JLabel("Raison de l'ethanasie :");
        raisonEuthanasieLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(raisonEuthanasieLabel);
        raisonEuthanasie = new JTextField();
        panneauFormulaire.add(raisonEuthanasie);

        dateArriveLabel = new JLabel("Date d'arrivée :");
        dateArriveLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(dateArriveLabel);
        dateArrive = new PanneauSpinnerDate();
        panneauFormulaire.add(dateArrive);

        dateDepartLabel = new JLabel("Date de départ :");
        dateDepartLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(dateDepartLabel);
        dateDepart = new PanneauSpinnerDate();
        panneauFormulaire.add(dateDepart);

        especeLabel = new JLabel("Espèce :");
        especeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(especeLabel);
        espece = new JTextField();
        panneauFormulaire.add(espece);

        raceLabel = new JLabel("Race :");
        raceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(raceLabel);
        race = new JTextField();
        panneauFormulaire.add(race);

        boutonMale = new JRadioButton("Male");
        boutonMale.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(boutonMale);
        boutonFemelle = new JRadioButton("Femelle");
        boutonFemelle.setHorizontalAlignment(SwingConstants.LEFT);
        panneauFormulaire.add(boutonFemelle);
        sexeGroupeBouton = new ButtonGroup();
        sexeGroupeBouton.add(boutonMale);
        sexeGroupeBouton.add(boutonFemelle);

        boutonOuiSterilise = new JRadioButton("Stérilisé.e");
        boutonOuiSterilise.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(boutonOuiSterilise);
        boutonNonSterilise = new JRadioButton("Non stérilisé.e");
        boutonNonSterilise.setHorizontalAlignment(SwingConstants.LEFT);
        panneauFormulaire.add(boutonNonSterilise);
        steriliseGroupeBouton = new ButtonGroup();
        steriliseGroupeBouton.add(boutonOuiSterilise);
        steriliseGroupeBouton.add(boutonNonSterilise);

        dateNaissanceLabel = new JLabel("Date de naissance :");
        dateNaissanceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dateNaissance = new PanneauSpinnerDate();
        panneauFormulaire.add(dateNaissanceLabel);
        panneauFormulaire.add(dateNaissance);

        pelagePeauLabel = new JLabel("Pelage/Peau :");
        pelagePeauLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(pelagePeauLabel);
        pelagePeau = new JTextField();
        panneauFormulaire.add(pelagePeau);

        numeroPuceLabel = new JLabel("Numéro de puce :");
        numeroPuceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(numeroPuceLabel);
        numeroPuce = new JTextField();
        panneauFormulaire.add(numeroPuce);

        numeroTatouageLabel = new JLabel("Numéro de tatouage :");
        numeroTatouageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(numeroTatouageLabel);
        numeroTatouage = new JTextField();
        panneauFormulaire.add(numeroTatouage);

        localisationLabel = new JLabel("Localisation de la puce:");
        localisationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(localisationLabel);
        localisation = new JTextField();
        panneauFormulaire.add(localisation);

        incertainLocalisationTatouageLabel = new JLabel("Incertain :");
        incertainLocalisationTatouageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(incertainLocalisationTatouageLabel);
        estIncertainLocalisationTatouage = new JCheckBox();
        panneauFormulaire.add(estIncertainLocalisationTatouage);

        attributionLabel = new JLabel("Date d'attribution de la puce :");
        attributionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(attributionLabel);
        dateAttribution = new PanneauSpinnerDate();
        panneauFormulaire.add(dateAttribution);

        dateTatouageLabel = new JLabel("Date d'attribution du tatouage :");
        dateTatouageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(dateTatouageLabel);
        dateTatouage = new PanneauSpinnerDate();
        panneauFormulaire.add(dateTatouage);

        localisationTatouageLabel = new JLabel("Localisation du tatouage :");
        localisationTatouageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(localisationTatouageLabel);
        localisationTatouage = new JTextField();
        panneauFormulaire.add(localisationTatouage);

        incertainDateTatouageLabel = new JLabel("Incertain :");
        incertainDateTatouageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(incertainDateTatouageLabel);
        estIncertainDateTatouage = new JCheckBox();
        panneauFormulaire.add(estIncertainDateTatouage);

        titreProprietaireLabel = new JLabel("<html><h1>Propriétaire (si connu)</h1></html>");
        titreProprietaireLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(titreProprietaireLabel);

        String values[] = {"Faire la recherche dans la BD"};
        listeProprios = new JComboBox(values);
        panneauFormulaire.add(listeProprios);

        ajoutProprio = new JButton("Ajouter un propriétaire");
        ajoutProprio.addActionListener(new NouveauProprio());
        panneauFormulaire.add(ajoutProprio);

        panneauFormulaire.add(new JLabel(""));

        remarqueLabel = new JLabel("<html><h1>Remarques</h1></html>");
        remarqueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(remarqueLabel);

        remarque = new JTextArea(1,15);
        panneauFormulaire.add(remarque);

        panneauBoutons.setLayout(new FlowLayout());
        retour = new JButton("Retour");
        panneauBoutons.add(retour);
        retour.addActionListener(new EcouteurDeBouton());
        validation = new JButton("Valider");
        panneauBoutons.add(validation);
        validation.addActionListener(new EcouteurDeBouton());
        reinnitialiser = new JButton("Reinnitialiser");
        panneauBoutons.add(reinnitialiser);
    }


    private class NouveauProprio implements ActionListener {
        public void actionPerformed(ActionEvent event){
            fenetreProprio = new FenetreProprio();
        }
    }

    private class EcouteurDeBouton implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == validation){
                validationFormulaire();
            }
            if(e.getSource() == retour){
                PanneauAccueil.this.removeAll();
                PanneauBienvenue panneauBienvenue= new PanneauBienvenue();
                PanneauAccueil.this.repaint();
                PanneauAccueil.this.validate();
            }
        }
    }
    public void validationFormulaire(){
       //validation numero de cellule
        Integer numeroCellule = 0;
        try{
            numeroCellule = Integer.valueOf(cellule.getText());
        }
        catch (Exception error){
            numeroCellule = null;
        }
        finally {
            if(cellule.getText().isEmpty() || cellule == null || numeroCellule < 1){
                JOptionPane.showMessageDialog(null, "Numero de cellule incorrecte !", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        //validation numero de registre
        Integer numeroDeRegistre = 0;
        try{
            numeroDeRegistre = Integer.valueOf(cellule.getText());
        }
        catch (Exception error){
            numeroDeRegistre = null;
        }
        finally {
            if(numeroDeRegistre == null || numeroDeRegistre< 1){
                JOptionPane.showMessageDialog(null, "Numéro de registre incorrecte !", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        //validation espece (ne peut pas etre null)

        //valiation race (ne peut pas etre null)

        //validation sexe (ne peut pas etre null)

        //validation estSterilise (ne peut pas etre null)
        try{
            Integer numRegistreAnimal;
            String nomAnimal;
            GregorianCalendar dateArriveeAnimal;
            GregorianCalendar dateDepartAnimal;
            String especeAnimal;
            String raceAnimal;
            String sexeAnimal;
            boolean estSteriliseAnimal;
            String couleurDePeauAnimal;
            GregorianCalendar dateNaissanceAnimal;
            Double numPuceAnimal;
            String localisationPuceAnimal;
            GregorianCalendar dateAttributionPuceAnimal;
            Double numTatouageAnimal;
            String localisationTatouageAnimal;
            Double poidsAnimal;
            Proprietaire proprietaireAnimal = fenetreProprio.getProprietaire();

            numRegistreAnimal = Integer.valueOf(numeroRegistre.getText());
            nomAnimal = nom.getText();
            dateArriveeAnimal = dateArrive.getDate();
            dateDepartAnimal = dateDepart.getDate();
            especeAnimal = espece.getText();
            raceAnimal = race.getText();
            if(sexeGroupeBouton.getSelection() == boutonFemelle) {
                sexeAnimal = "F";
            }else sexeAnimal = "M";
            if(steriliseGroupeBouton.getSelection() == boutonOuiSterilise){
                estSteriliseAnimal = true;
            }else estSteriliseAnimal = false;
            couleurDePeauAnimal = pelagePeau.getText();
            dateNaissanceAnimal = dateNaissance.getDate();
            numPuceAnimal = Double.valueOf(numeroPuce.getText());
            localisationPuceAnimal = localisation.getText();
            dateAttributionPuceAnimal = dateAttribution.getDate();
            numTatouageAnimal = Double.valueOf(numeroTatouage.getText());
            if(estIncertainLocalisationTatouage.isSelected()){
                localisationTatouageAnimal = null;
            }else localisationTatouageAnimal = localisationTatouage.getText();
            poidsAnimal = Double.valueOf(poids.getText());

            Animal animal = new Animal(numRegistreAnimal, nomAnimal, dateArriveeAnimal, dateDepartAnimal, especeAnimal, raceAnimal, sexeAnimal, estSteriliseAnimal, couleurDePeauAnimal, dateNaissanceAnimal, numPuceAnimal, localisationPuceAnimal, dateAttributionPuceAnimal, numTatouageAnimal, localisationTatouageAnimal, poidsAnimal, proprietaireAnimal);
        }
        catch (AnimalException exception){System.out.println(exception.getMessage());}
    }
}
