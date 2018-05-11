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

        ajouterUnPropriétaireButton.setEnabled(false);
        comboBoxListeProprietaires.setEnabled(false);
        proprioCheckBox.addActionListener(new EcouteurDeCheckBox());

        spinnerDateNaissance.setEnabled(false);
        dateDeNaissanceCheckBox.addActionListener(new EcouteurDeCheckBox());

        spinnerDatePuce.setEnabled(false);
        dateDAttributionPuceCheckBox.addActionListener(new EcouteurDeCheckBox());


        validerButton.addActionListener(new EcouteurDeBoutons());
        reinitialiserButton.addActionListener(new EcouteurDeBoutons());
        retourButton.addActionListener(new EcouteurDeBoutons());
        ajouterUnPropriétaireButton.addActionListener(new EcouteurDeBoutons());

        instanciationSpinnerPoids();
        instanciationSpinnerDate(spinnerDateNaissance);
        instanciationSpinnerDate(spinnerDatePuce);
        instanciationListeProprietaires();

        baseBorder = nomTextField.getBorder();
    }

    public PanneauAnimal(Controller controller, FenetrePrincipale fenetre, Animal animalModif){
        modification = true;
        this.fenetre = fenetre;
        this.controller = controller;
        this.animalModif = animalModif;
        //modifier titre...
        ajouterUnPropriétaireButton.setEnabled(false);
        comboBoxListeProprietaires.setEnabled(false);
        dateDeNaissanceCheckBox.addActionListener(new EcouteurDeCheckBox());
        dateDAttributionPuceCheckBox.addActionListener(new EcouteurDeCheckBox());
        if(animalModif.getProprietaire()!=null){
            instancieUnProprietaire(animalModif.getProprietaire());
            try{
                proprioCheckBox.doClick();
                Proprietaire proprietaire = controller.getUnProprietaire(animalModif.getProprietaire());
                comboBoxListeProprietaires.addItem(proprietaire);
            }
            catch (ProprietaireException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
            } catch (SingletonConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
            }
        }
        proprioCheckBox.addActionListener(new EcouteurDeCheckBox());
        if(animalModif.getNom() != null){
            nomTextField.setText(animalModif.getNom());
        }
        raceTextField.setText(animalModif.getRace());
        System.out.println(animalModif.getSexe());
        if(animalModif.getSexe() == "M"){
            maleRadioButton.doClick();
        }else{
            femelleRadioButton.doClick();
        }
        especeTextField.setText(animalModif.getEspece());
        couleurTextField.setText(animalModif.getCouleurDePeau());
        spinnerPoids.setValue(animalModif.getPoids());
        if(animalModif.isEstSterilise()){
            steriliseRadioButton.doClick();
        }else{
            nonSteriliseRadioButton.doClick();
        }
        if(animalModif.getDateNaissance() == null){
            instanciationSpinnerDate(spinnerDateNaissance);
        }
        else{
            spinnerDateNaissance.setModel(new SpinnerDateModel());
            spinnerDateNaissance.setEditor(new JSpinner.DateEditor(spinnerDateNaissance, "dd/MM/yyyy"));
            spinnerDateNaissance.setValue(animalModif.getDateNaissance().getTime());
            spinnerDateNaissance.setEnabled(false);
        }
        if(animalModif.getNumPuce() != null){
            numPuceTextField.setText(String.valueOf(animalModif.getNumPuce()));
        }
        if(animalModif.getLocalisationPuce() != null){
            localisationPuceTextField.setText(animalModif.getLocalisationPuce());
        }
        if(animalModif.getDateAttributionPuce() == null){
            instanciationSpinnerDate(spinnerDatePuce);
        }
        else{
            spinnerDatePuce.setModel(new SpinnerDateModel());
            spinnerDatePuce.setEditor(new JSpinner.DateEditor(spinnerDatePuce, "dd/MM/yyyy"));
            spinnerDatePuce.setValue(animalModif.getDateAttributionPuce().getTime());
            spinnerDatePuce.setEnabled(false);
        }
        if(animalModif.getNumTatouage() != null){
            numTatouageTextField.setText(String.valueOf(animalModif.getNumTatouage()));
        }
        if(animalModif.getLocalisationTatouage() != null){
            localisationTatouageTextField.setText(animalModif.getLocalisationTatouage());
        }
        baseBorder = nomTextField.getBorder();
        validerButton.addActionListener(new EcouteurDeBoutonModifierValidation());
        reinitialiserButton.addActionListener(new EcouteurDeBoutons());
        retourButton.addActionListener(new EcouteurDeBoutons());
        ajouterUnPropriétaireButton.addActionListener(new EcouteurDeBoutons());
    }


    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
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
            } catch (NumberFormatException e) {
                estValide = false;
                numPuceTextField.setBorder(BorderFactory.createCompoundBorder(border,
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
        instanciationSpinnerPoids();
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

    private Animal creationAnimal(){
        Animal animal = new Animal();
        try {
            if (nomTextField.getText().isEmpty()) {
                nomTextField.setText("");
            }
            animal.setNom(nomTextField.getText());

            animal.setPoids((Double) spinnerPoids.getValue());
            if (femelleRadioButton.isSelected())
                animal.setSexe("F");
            else
                animal.setSexe("M");
            /*if (especeTextField.getText().isEmpty()) {
                especeTextField.setText("");
            }*/
            animal.setEspece(especeTextField.getText());
            /*if (raceTextField.getText().isEmpty()) {
                raceTextField.setText("");
            }*/
            animal.setRace(raceTextField.getText());
            if (dateDeNaissanceCheckBox.isSelected()) {
                GregorianCalendar date = new GregorianCalendar();
                date.setTime((Date) spinnerDateNaissance.getValue());
                animal.setDateNaissance(date);
            }
            /*if (couleurTextField.getText().isEmpty()) {
                couleurTextField = null;
            }*/
            animal.setCouleurDePeau(couleurTextField.getText());

            try {
                animal.setNumPuce(Integer.parseInt(numPuceTextField.getText()));
            }
            catch (Exception error) {
                numPuceTextField = null;
            }

            if (localisationPuceTextField.getText() == "") {
                localisationPuceTextField = null;
            }
            /*if(localisationPuceTextField.getText().isEmpty()){
                localisationPuceTextField.setText("");
            }*/
            animal.setLocalisationPuce(localisationPuceTextField.getText());

            if (dateDAttributionPuceCheckBox.isSelected()) {
                GregorianCalendar date = new GregorianCalendar();
                date.setTime((Date) spinnerDatePuce.getValue());
                animal.setDateAttributionPuce(date);
            }

            try {
                animal.setNumTatouage(Integer.parseInt(numTatouageTextField.getText()));
            }
            catch (Exception erreur) {
                numTatouageTextField = null;
            }
            if (localisationTatouageTextField.getText() == "") {
                localisationTatouageTextField = null;
            }
            if(steriliseRadioButton.isSelected()){
                animal.setEstSterilise(true);
            }else{
                animal.setEstSterilise(false);
            }
            /*if(localisationTatouageTextField.getText().isEmpty()){
                localisationTatouageTextField.setText("");
            }*/
            animal.setLocalisationTatouage(localisationTatouageTextField.getText());
            if(proprioCheckBox.isSelected()){
                try {
                    Proprietaire proprietaire = (Proprietaire) comboBoxListeProprietaires.getSelectedItem();
                    animal.setProprietaire(proprietaire.getIdentifiantProprio());
                } catch (Exception erreur) {
                    comboBoxListeProprietaires = null;
                }
            }
            System.out.println(animal);
        }
        catch(AnimalException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
        if(modification){
            animal.setNumRegistre(animalModif.getNumRegistre());
        }
        return animal;
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

    private void instanciationSpinnerDate(JSpinner spinnerDate) {
        spinnerDate.setModel(new SpinnerDateModel());
        spinnerDate.setEditor(new JSpinner.DateEditor(spinnerDate, "dd/MM/yyyy"));
        spinnerDate.setValue(GregorianCalendar.getInstance().getTime());
    }

    private void instanciationSpinnerPoids() {
        spinnerPoids.setModel(new SpinnerNumberModel(0.00, 0.00, 10000, 0.10));
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
                erreurMessage = "Certains champs sont invalides !\nDe plus : \n";
                reinitialiserBorder();
                if (validationFormulaire()) {
                    try{
                        controller.ajouterAnimal(creationAnimal());
                        JOptionPane.showMessageDialog(null, "La fiche de l'animal a été correctement ajoutée à la base de données !",
                                "Confirmation!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch(AnimalException exception){
                        JOptionPane.showMessageDialog(null, "Animal exception :" + exception.getMessage());
                    }
                    catch(SingletonConnectionException exception){
                        JOptionPane.showMessageDialog(null, "Singleton exception :" + exception.getMessage());
                    }
                    catch(Exception exception) {
                        System.out.println("Exception : " + exception.getMessage());
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,erreurMessage, "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(e.getSource() == retourButton){
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
    private class EcouteurDeBoutonModifierValidation implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == validerButton){
                erreurMessage = "Certains champs sont invalides !\nDe plus : \n";
                reinitialiserBorder();
                if (validationFormulaire()) {
                    try{
                        controller.modifierAnimal(creationAnimal());
                        JOptionPane.showMessageDialog(null, "L'animal a été correctement modifié de la base de donnée !",
                                "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch(AnimalException exception){
                        JOptionPane.showMessageDialog(null, "Animal exception :" + exception.getMessage());
                    }
                    catch(SingletonConnectionException exception){
                        JOptionPane.showMessageDialog(null, "Singleton exception :" + exception.getMessage());
                    }
                    catch(Exception exception) {
                        System.out.println("Exception : " + exception.getMessage());
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,erreurMessage, "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}




