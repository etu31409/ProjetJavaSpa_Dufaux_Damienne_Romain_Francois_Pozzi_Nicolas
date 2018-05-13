package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.*;
import modelPackage.Animal;
import modelPackage.Proprietaire;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class PanneauAnimal extends JPanel {

    //permet de mettre les bord en rouge
    private Border baseBorder;
    private Controller controller;
    private String erreurMessage;

    private JPanel panneauContainerPrincipal;

    private JSpinner spinnerPoids;
    private JSpinner spinnerDateNaissance;
    private JSpinner spinnerDatePuce;

    private JTextField nomTextField;
    private JTextField especeTextField;
    private JTextField raceTextField;
    private JTextField numTatouageTextField;
    private JTextField localisationTatouageTextField;
    private JTextField couleurTextField;
    private JTextField numPuceTextField;
    private JTextField localisationPuceTextField;

    private JRadioButton femelleRadioButton, maleRadioButton, steriliseRadioButton, nonSteriliseRadioButton;

    private JButton ajouterUnPropriétaireButton, validerButton, reinitialiserButton, retourButton;

    private JComboBox comboBoxListeProprietaires;

    private JCheckBox dateDeNaissanceCheckBox;
    private JCheckBox dateDAttributionPuceCheckBox;
    private JCheckBox proprioCheckBox;
    private JLabel titreDeLaPage;

    private ButtonGroup buttonGroupeSexe;
    private ButtonGroup buttonGroupeSterilise;

    private FenetrePrincipale fenetre;
    private boolean modification = false;
    private Animal animalModif;

    public PanneauAnimal(Controller controller, FenetrePrincipale fenetre) {
        this.fenetre = fenetre;
        this.controller = controller;
        baseBorder = nomTextField.getBorder();
        reinitialisation();

        proprioCheckBox.addActionListener(new EcouteurDeCheckBox());
        dateDeNaissanceCheckBox.addActionListener(new EcouteurDeCheckBox());
        dateDAttributionPuceCheckBox.addActionListener(new EcouteurDeCheckBox());
        validerButton.addActionListener(new EcouteurDeBoutons());
        reinitialiserButton.addActionListener(new EcouteurDeBoutons());
        retourButton.addActionListener(new EcouteurDeBoutons());
        ajouterUnPropriétaireButton.addActionListener(new EcouteurDeBoutons());

    }

    public PanneauAnimal(Controller controller, FenetrePrincipale fenetre, Animal animalModif) throws NullPointerException{
        modification = animalModif != null;
        if (modification == false){
            throw new NullPointerException("Erreur lors de la modification de l'animal!\nContacter votre administreur système!");
        }
        baseBorder = nomTextField.getBorder();
        this.fenetre = fenetre;
        this.controller = controller;;
        this.animalModif = animalModif;
        reinitialisation();

        titreDeLaPage.setText("Modification d'un animal");

        //ajout des listeners
        dateDeNaissanceCheckBox.addActionListener(new EcouteurDeCheckBox());
        dateDAttributionPuceCheckBox.addActionListener(new EcouteurDeCheckBox());
        proprioCheckBox.addActionListener(new EcouteurDeCheckBox());
        ajouterUnPropriétaireButton.addActionListener(new EcouteurDeBoutons());
        validerButton.addActionListener(new EcouteurDeBoutons());
        reinitialiserButton.addActionListener(new EcouteurDeBoutons());
        retourButton.addActionListener(new EcouteurDeBoutons());

        //Initialisation des valeurs de l'animal
        if(animalModif.getNom() != null){
            nomTextField.setText(animalModif.getNom());
        }

        spinnerPoids.setValue(animalModif.getPoids());

        if(animalModif.getSexe().equals("M"))
            buttonGroupeSexe.setSelected(maleRadioButton.getModel(), true);
        else if (animalModif.getSexe().equals("F"))
            buttonGroupeSexe.setSelected(femelleRadioButton.getModel(), true);
        else
            buttonGroupeSexe.clearSelection();

        especeTextField.setText(animalModif.getEspece());
        raceTextField.setText(animalModif.getRace());

        if (animalModif.getDateNaissance() != null){
            dateDeNaissanceCheckBox.setSelected(true);
            spinnerDateNaissance.setValue(animalModif.getDateNaissance().getTime());
            spinnerDateNaissance.setEnabled(true);
        }

        if(animalModif.isEstSterilise())
            buttonGroupeSterilise.setSelected(steriliseRadioButton.getModel(), true);
        else if (!animalModif.isEstSterilise())
            buttonGroupeSterilise.setSelected(nonSteriliseRadioButton.getModel(), true);
        else
            buttonGroupeSterilise.clearSelection();

        couleurTextField.setText(animalModif.getCouleurDePeau());

        if (animalModif.getNumPuce() != null){
            numPuceTextField.setText(String.valueOf(animalModif.getNumPuce()));
            localisationPuceTextField.setText(animalModif.getLocalisationPuce());
        }
        if (animalModif.getDateAttributionPuce() != null){
            dateDAttributionPuceCheckBox.setSelected(true);
            spinnerDatePuce.setValue(animalModif.getDateAttributionPuce().getTime());
            spinnerDatePuce.setEnabled(true);
        }
        if(animalModif.getLocalisationTatouage() != null){
            numTatouageTextField.setText(String.valueOf(animalModif.getNumTatouage()));
            localisationTatouageTextField.setText(animalModif.getLocalisationTatouage());
        }

        if(animalModif.getProprietaire() != null){
            proprioCheckBox.setSelected(true);
            ajouterUnPropriétaireButton.setEnabled(true);
            comboBoxListeProprietaires.setSelectedIndex(animalModif.getProprietaire()-1);
            comboBoxListeProprietaires.setEnabled(true);
        }
    }

    private Animal creationAnimal(){
        Animal animal = new Animal();
        try {
            if(nomTextField.getText().isEmpty())animal.setNom(null);
            else animal.setNom(nomTextField.getText());
            animal.setPoids((Double) spinnerPoids.getValue());
            if (femelleRadioButton.isSelected()) animal.setSexe("F");
            else animal.setSexe("M");
            animal.setEspece(especeTextField.getText());
            animal.setRace(raceTextField.getText());
            if (dateDeNaissanceCheckBox.isSelected()) {
                GregorianCalendar date = new GregorianCalendar();
                date.setTime((Date) spinnerDateNaissance.getValue());
                animal.setDateNaissance(date);
            }
            animal.setCouleurDePeau(couleurTextField.getText());
            try {
                animal.setNumPuce(Integer.parseInt(numPuceTextField.getText()));
            }
            catch (Exception error) {
                animal.setNumPuce(null);
            }
            if(localisationPuceTextField.getText().isEmpty())animal.setLocalisationPuce(null);
            else animal.setLocalisationPuce(localisationPuceTextField.getText());
            if (dateDAttributionPuceCheckBox.isSelected()) {
                GregorianCalendar date = new GregorianCalendar();
                date.setTime((Date) spinnerDatePuce.getValue());
                animal.setDateAttributionPuce(date);
            }
            try {
                animal.setNumTatouage(Integer.parseInt(numTatouageTextField.getText()));
            }
            catch (Exception erreur) {
                animal.setNumTatouage(null);
            }
            if(localisationTatouageTextField.getText().isEmpty())animal.setLocalisationTatouage(null);
            else animal.setLocalisationTatouage(localisationTatouageTextField.getText());
            if(steriliseRadioButton.isSelected()){
                animal.setEstSterilise(true);
            }else{
                animal.setEstSterilise(false);
            }
            if(proprioCheckBox.isSelected()){
                try {
                    Proprietaire proprietaire = (Proprietaire) comboBoxListeProprietaires.getSelectedItem();
                    animal.setProprietaire(proprietaire.getIdentifiantProprio());
                } catch (Exception erreur) {
                    comboBoxListeProprietaires = null;
                }
            }
        }
        catch(AnimalException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
        if(modification){
            animal.setNumRegistre(animalModif.getNumRegistre());
        }
        return animal;
    }

    private Boolean validationFormulaire(){
        Boolean  estValide = verifierDateLogique();;
        Border border = BorderFactory.createLineBorder(Color.red);

        if((Double)spinnerPoids.getValue() <= 0){
            estValide = false;
            spinnerPoids.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            erreurMessage += "Le poids doit être supérieur à 0\n";
        }
        if (!femelleRadioButton.isSelected() && !maleRadioButton.isSelected()){
            estValide = false;
            femelleRadioButton.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            maleRadioButton.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            erreurMessage += "Le sexe doit être défini\n";
        }
        if (especeTextField.getText().isEmpty()){
            estValide = false;
            especeTextField.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            erreurMessage += "L'espèce doit être définie\n";
        }
        if (raceTextField.getText().isEmpty()){
            estValide = false;
            raceTextField.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            erreurMessage += "La race doit être définie\n";
        }
        if (!steriliseRadioButton.isSelected() && !nonSteriliseRadioButton.isSelected())
        {
            estValide = false;
            steriliseRadioButton.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            nonSteriliseRadioButton.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            erreurMessage += "La stérilisation doit être définie\n";
        }
        if (couleurTextField.getText().isEmpty()){
            estValide = false;
            couleurTextField.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            erreurMessage += "La couleur doit être définie\n";
        }
        if (dateDAttributionPuceCheckBox.isSelected()){
            if (numPuceTextField.getText().isEmpty()){
                estValide = false;
                numPuceTextField.setBorder(BorderFactory.createCompoundBorder(border,
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                erreurMessage += "Le champ de numéro de puce ne peut être vide\n";
            }
            if (localisationPuceTextField.getText().isEmpty()){
                estValide = false;
                localisationPuceTextField.setBorder(BorderFactory.createCompoundBorder(border,
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                erreurMessage += "Le champ de localisation de la puce ne peut être vide\n";
            }
        }
        if (!numPuceTextField.getText().isEmpty()){
            try {
                Integer.parseInt(numPuceTextField.getText().replace("\\s", ""));
                if (numPuceTextField.getText().length() > 10){
                    estValide = false;
                    numPuceTextField.setBorder(BorderFactory.createCompoundBorder(border,
                            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                    erreurMessage += "Le champ de numéro de puce doit contenir un nombre entier plus petit que 2 147 483 647 \n";
                }
            } catch (NumberFormatException e) {
                estValide = false;
                numPuceTextField.setBorder(BorderFactory.createCompoundBorder(border,
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                erreurMessage += "Le champ de numéro de puce doit contenir un nombre entier plus petit que 2 147 483 647 \n";
            }
        }

        if (!numTatouageTextField.getText().isEmpty()){
            try {
                Integer.parseInt(numTatouageTextField.getText().replace("\\s", ""));
                if (numTatouageTextField.getText().length() > 10){
                    estValide = false;
                    numTatouageTextField.setBorder(BorderFactory.createCompoundBorder(border,
                            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                    erreurMessage += "Le champ de numéro de tatouage doit contenir un nombre entier plus petit que 2 147 483 647 \n";
                }
            } catch (NumberFormatException e) {
                estValide = false;
                numTatouageTextField.setBorder(BorderFactory.createCompoundBorder(border,
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                erreurMessage += "Le champ de numéro de tatouage doit contenir un nombre entier plus petit que 2 147 483 647 \n";
            }
        }

        return estValide;
    }

    private Boolean verifierDateLogique(){
        Boolean estValide = true;
        Border border = BorderFactory.createLineBorder(Color.red);
        if (dateDeNaissanceCheckBox.isSelected()){
            if (((Date)spinnerDateNaissance.getValue()).after(GregorianCalendar.getInstance().getTime())){
                estValide = false;
                spinnerDateNaissance.setBorder(BorderFactory.createCompoundBorder(border,
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                erreurMessage += "La date de naissance ne peut être postérieur à la date du jour!\n";
            }
        }
        if (dateDAttributionPuceCheckBox.isSelected()){
            if (((Date)spinnerDatePuce.getValue()).after(GregorianCalendar.getInstance().getTime())){
                estValide = false;
                spinnerDatePuce.setBorder(BorderFactory.createCompoundBorder(border,
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                erreurMessage += "La date de mise en place de la puce ne peut pas être postérieur à la date du jour!\n";
            }
        }
        if (dateDAttributionPuceCheckBox.isSelected() && dateDeNaissanceCheckBox.isSelected()){
            if (((Date)spinnerDatePuce.getValue()).before((Date)spinnerDateNaissance.getValue())){
                estValide = false;
                spinnerDatePuce.setBorder(BorderFactory.createCompoundBorder(border,
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                erreurMessage += "La date d'attribution de la puce ne peut êter antérieur à la date de naissance!\n";
            }
        }
        return estValide;
    }

    private void reinitialisation(){
        reinitialiserBorder();
        instanciationSpinnerPoids(0);
        instanciationSpinnerDate(spinnerDateNaissance);
        instanciationSpinnerDate(spinnerDatePuce);
        instanciationListeProprietaires();
        nomTextField.setText("");
        especeTextField.setText("");
        raceTextField.setText("");
        numTatouageTextField.setText("");
        localisationTatouageTextField.setText("");
        couleurTextField.setText("");
        numPuceTextField.setText("");
        localisationPuceTextField.setText("");
        spinnerDatePuce.setEnabled(false);
        proprioCheckBox.setSelected(false);
        ajouterUnPropriétaireButton.setEnabled(false);
        comboBoxListeProprietaires.setEnabled(false);
        buttonGroupeSexe.clearSelection();
        buttonGroupeSterilise.clearSelection();
        dateDAttributionPuceCheckBox.setSelected(false);
        spinnerDatePuce.setEnabled(false);
        dateDeNaissanceCheckBox.setSelected(false);
        spinnerDateNaissance.setEnabled(false);
    }

    private void reinitialiserBorder(){
        spinnerDatePuce.setBorder(baseBorder);
        spinnerDateNaissance.setBorder(baseBorder);
        spinnerPoids.setBorder(baseBorder);
        femelleRadioButton.setBorder(baseBorder);
        maleRadioButton.setBorder(baseBorder);
        especeTextField.setBorder(baseBorder);
        raceTextField.setBorder(baseBorder);
        couleurTextField.setBorder(baseBorder);
        numPuceTextField.setBorder(baseBorder);
        localisationPuceTextField.setBorder(baseBorder);
    }

    public void instanciationListeProprietaires() {
        comboBoxListeProprietaires.removeAllItems();
        try {
            for (Proprietaire p : controller.getProprietaires()) {
                comboBoxListeProprietaires.addItem(p);
            }
        } catch (ProprietaireException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void instancieUnProprietaire(Integer identifiantProprio){
        try{
            Proprietaire proprietaire = controller.getUnProprietaire(identifiantProprio);
            comboBoxListeProprietaires.addItem(proprietaire);
        }
        catch (ProprietaireException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void instanciationSpinnerDate(JSpinner spinnerDate, Date date) {
        spinnerDate.setModel(new SpinnerDateModel());
        spinnerDate.setEditor(new JSpinner.DateEditor(spinnerDate, "dd/MM/yyyy"));
        spinnerDate.setValue(date);
    }

    private void instanciationSpinnerDate(JSpinner spinnerDate) {
        instanciationSpinnerDate(spinnerDate, GregorianCalendar.getInstance().getTime());
    }

    private void instanciationSpinnerPoids(double valeur) {
        spinnerPoids.setModel(new SpinnerNumberModel(valeur, 0.00, 10000, 0.10));
    }

    private class EcouteurDeCheckBox implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == proprioCheckBox){
                if(proprioCheckBox.isSelected()){
                    ajouterUnPropriétaireButton.setEnabled(true);
                    comboBoxListeProprietaires.setEnabled(true);
                    if(modification){
                        instanciationListeProprietaires();
                    }
                }
                else{
                    ajouterUnPropriétaireButton.setEnabled(false);
                    comboBoxListeProprietaires.setEnabled(false);
                    if(modification){
                        comboBoxListeProprietaires.removeAllItems();
                        instancieUnProprietaire(animalModif.getProprietaire());
                    }
                }
            }
            if(e.getSource() == dateDeNaissanceCheckBox){
                if(dateDeNaissanceCheckBox.isSelected())
                    spinnerDateNaissance.setEnabled(true);
                else
                    spinnerDateNaissance.setEnabled(false);

            }
            if(e.getSource() == dateDAttributionPuceCheckBox){
                if(dateDAttributionPuceCheckBox.isSelected())
                    spinnerDatePuce.setEnabled(true);
                else
                    spinnerDatePuce.setEnabled(false);
            }
        }
    }

    private class EcouteurDeBoutons implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == validerButton){
                erreurMessage = "Certains champs sont invalides !\n";
                reinitialiserBorder();
                if (validationFormulaire()) {
                    try{
                        if (modification){
                            controller.modifierAnimal(creationAnimal());
                            JOptionPane.showMessageDialog(null, "L'animal a été correctement modifié de la base de donnée !",
                                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                            fenetre.afficherListingAnimaux();
                        }
                        else {
                            controller.ajouterAnimal(creationAnimal());
                            JOptionPane.showMessageDialog(null, "La fiche de l'animal a été correctement ajoutée à la base de données !",
                                    "Confirmation!", JOptionPane.INFORMATION_MESSAGE);
                            reinitialisation();
                        }
                    }
                    catch(AnimalException exception){
                        JOptionPane.showMessageDialog(null, "Animal exception :" + exception.getMessage());
                    }
                    catch(SingletonConnectionException exception){
                        JOptionPane.showMessageDialog(null, "Singleton exception :" + exception.getMessage());
                    }
                    catch(Exception exception){
                        JOptionPane.showMessageDialog(null, "Une erreur imprévue semble être survenue !", "Erreur !", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,erreurMessage, "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(e.getSource() == retourButton){
                if (modification)
                    fenetre.afficherListingAnimaux();
                 else
                    fenetre.retourAccueil();
            }
            if(e.getSource() == reinitialiserButton){
                reinitialisation();
            }
            if(e.getSource() == ajouterUnPropriétaireButton){
                new FenetreProprio(controller,PanneauAnimal.this);
            }
        }
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }
}




