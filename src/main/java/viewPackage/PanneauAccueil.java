package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.AnimalException;
import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Animal;
import modelPackage.Proprietaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;

public class PanneauAccueil extends JPanel {
    private Controller controller;

    private JPanel panneauFormulaire, panneauBoutons;
    private JLabel nomLabel, celluleLabel, poidsLabel, dateRemplissageLabel, aEuthanasierLabel, raisonEuthanasieLabel,
            dateArriveLabel, especeLabel, raceLabel, pelagePeauLabel, dateNaissanceLabel, numeroPuceLabel, localisationLabel,
            attributionLabel, numeroTatouageLabel, incertainLocalisationTatouageLabel, incertainDateTatouageLabel,
            dateTatouageLabel, titreProprietaireLabel, remarqueLabel, localisationTatouageLabel;
    private JTextField localisationTatouage, nom, cellule, poids, raisonEuthanasie, espece, race, pelagePeau, numeroPuce, localisation, numeroTatouage;
    private PanneauSpinnerDate dateRemplissage, dateEuthanasie, dateArrive, dateNaissance, dateAttribution, dateTatouage;
    private JButton  retour, validation, reinnitialiser;
    private JCheckBox aEuthanasier, estIncertainDateTatouage, estIncertainLocalisationTatouage;
    private ButtonGroup sexeGroupeBouton, steriliseGroupeBouton;
    private JRadioButton boutonMale, boutonFemelle, boutonOuiSterilise, boutonNonSterilise;
    private JComboBox proprietaires;
    private JButton ajoutProprio;
    private JTextArea remarque;
    private JScrollPane scroll;
    private FenetreProprio fenetreProprio;

    public PanneauAccueil(Controller controller) {
        this.controller = controller;
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

        celluleLabel = new JLabel("N° de cellule :");
        celluleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(celluleLabel);
        cellule = new JTextField();
        panneauFormulaire.add(cellule);

        poidsLabel = new JLabel("Poids de l'animal :");
        poidsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(poidsLabel);
        poids = new JTextField();
        panneauFormulaire.add(poids);
        //box model ?

        aEuthanasier = new JCheckBox("à euthanasier :");
        aEuthanasier.setHorizontalAlignment(SwingConstants.RIGHT);
        aEuthanasier.addItemListener(new EcouteurDeCheckBox());
        panneauFormulaire.add(aEuthanasier);

        aEuthanasierLabel = new JLabel("Prévu pour le :");
        dateEuthanasie = new PanneauSpinnerDate();
        panneauFormulaire.add(dateEuthanasie);

        raisonEuthanasieLabel = new JLabel("Raison de l'ethanasie :");
        raisonEuthanasieLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(raisonEuthanasieLabel);
        raisonEuthanasie = new JTextField();
        raisonEuthanasie.setEnabled(false);
        raisonEuthanasie.setBackground(Color.gray);
        panneauFormulaire.add(raisonEuthanasie);

        dateArriveLabel = new JLabel("Date d'arrivée :");
        dateArriveLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(dateArriveLabel);
        dateArrive = new PanneauSpinnerDate();
        panneauFormulaire.add(dateArrive);

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

        incertainLocalisationTatouageLabel = new JLabel("Incertain de l'endroit du tatouage :");
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

        incertainDateTatouageLabel = new JLabel("Incertain de la date du tatouage :");
        incertainDateTatouageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(incertainDateTatouageLabel);
        estIncertainDateTatouage = new JCheckBox();
        panneauFormulaire.add(estIncertainDateTatouage);

        titreProprietaireLabel = new JLabel("<html><h1>Propriétaire (si connu)</h1></html>");
        titreProprietaireLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauFormulaire.add(titreProprietaireLabel);

        proprietaires = new JComboBox();
        instancieListeProprietaires();
        panneauFormulaire.add(proprietaires);

        ajoutProprio = new JButton("Ajouter un propriétaire");
        ajoutProprio.addActionListener(new NouveauProprio());
        panneauFormulaire.add(ajoutProprio);

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

    public void instancieListeProprietaires() {
        proprietaires.removeAllItems();
        try {
            for (Proprietaire p : controller.getProprietaires()) {
                proprietaires.addItem(p);
            }
        } catch (ProprietaireException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private class EcouteurDeCheckBox implements ItemListener
    {
        public void itemStateChanged(ItemEvent e){
            if(e.getSource() == aEuthanasier){
                if(e.getStateChange() == e.SELECTED){
                    aEuthanasier.isSelected();
                }
            }
            if(aEuthanasier.isSelected()){
                raisonEuthanasie.setEnabled(true);
                raisonEuthanasie.setBackground(Color.white);
            }
            if(!aEuthanasier.isSelected()){
                raisonEuthanasie.setEnabled(false);
                raisonEuthanasie.setBackground(Color.GRAY);
            }
        }
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

        //validation espece (ne peut pas etre null)
        if(espece.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Le champ ESPECE est obligatoire !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        //valiation race (ne peut pas etre null)
        if(race.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Le champ RACE est obligatoire !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        //validation sexe (ne peut pas etre null)
        if(!boutonFemelle.isSelected() && !boutonMale.isSelected()){
            JOptionPane.showMessageDialog(null, "Vous devez choisir un sexe !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        //validation estSterilise (ne peut pas etre null)
        if(!boutonNonSterilise.isSelected() && !boutonOuiSterilise.isSelected()){
            JOptionPane.showMessageDialog(null, "Vous devez indiquer si l'animal est stérilisé !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        try{
            String nomAnimal;
            GregorianCalendar dateArriveeAnimal;
            String especeAnimal;
            String raceAnimal;
            String sexeAnimal;
            boolean estSteriliseAnimal;
            String couleurDePeauAnimal;
            GregorianCalendar dateNaissanceAnimal;
            Integer numPuceAnimal;
            String localisationPuceAnimal;
            GregorianCalendar dateAttributionPuceAnimal;
            Double numTatouageAnimal;
            String localisationTatouageAnimal;
            Double poidsAnimal;
//            Proprietaire proprietaireAnimal = fenetreProprio.getProprietaire();
            Proprietaire proprietaireAnimal = (Proprietaire) proprietaires.getSelectedItem();
            nomAnimal = nom.getText();
            dateArriveeAnimal = dateArrive.getDate();
            especeAnimal = espece.getText();
            raceAnimal = race.getText();

            if(sexeGroupeBouton.getSelection() == boutonFemelle) {
                sexeAnimal = "F";
            }
            else {
                sexeAnimal = "M";
            }
            if(boutonOuiSterilise.isSelected()){
                estSteriliseAnimal = true;
            }
            else {
                estSteriliseAnimal = false;
            }
            couleurDePeauAnimal = pelagePeau.getText();
            dateNaissanceAnimal = dateNaissance.getDate();

            try{
                numPuceAnimal = Integer.valueOf(numeroPuce.getText());
            }
            catch (Exception error){
                numPuceAnimal  = null;
            }
            finally {
                if(numeroPuce.getText().isEmpty() || numeroPuce == null ||  Integer.valueOf(numeroPuce.getText()) < 0){
                    JOptionPane.showMessageDialog(null, "Numéro de puce invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            localisationPuceAnimal = localisation.getText();
            dateAttributionPuceAnimal = dateAttribution.getDate();

            try{
                numTatouageAnimal = Double.valueOf(numeroTatouage.getText());
            }
            catch (Exception error){
                numTatouageAnimal = null;
            }
            finally {
                if(numeroTatouage.getText().isEmpty() || numeroTatouage == null || Double.valueOf(numeroTatouage.getText()) < 0){
                    JOptionPane.showMessageDialog(null, "Numéro de tatouage invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(estIncertainLocalisationTatouage.isSelected()){
                localisationTatouageAnimal = null;
            }
            else{
                localisationTatouageAnimal = localisationTatouage.getText();
            }

            poidsAnimal = Double.valueOf(poids.getText());
            Animal animal = new Animal(nomAnimal, dateArriveeAnimal, especeAnimal,
                    raceAnimal, sexeAnimal, estSteriliseAnimal, couleurDePeauAnimal, dateNaissanceAnimal, numPuceAnimal,
                    localisationPuceAnimal, dateAttributionPuceAnimal, numTatouageAnimal, localisationTatouageAnimal,
                    poidsAnimal, proprietaireAnimal);

            controller.ajouterAnimal(animal);
        }
        catch(AnimalException e){JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);}
        catch(SingletonConnectionException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        //catch (Exception exception){System.out.println(exception.getMessage());}
    }
}
