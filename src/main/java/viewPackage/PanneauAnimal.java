package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
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
    private JSpinner spinnerDateEuthanasie;
    private JSpinner spinnerDateNaissance;
    private JSpinner spinnerDatePuce;

    private JTextField nomTextField;
    private JTextField especeTextField;
    private JTextField raceTextField;
    private JTextField raisonTextField;
    private JTextField numTatouageTextField;
    private JTextField localisationTatouageTextField;
    private JTextField couleurTextField;
    private JTextField numPuceTextField;
    private JTextField localisationPuceTextField;

    private JRadioButton femelleRadioButton, maleRadioButton, steriliseRadioButton, nonSteriliseRadioButton;

    private JButton ajouterUnPropriétaireButton, validerButton, reinitialiserButton, retourButton;

    private JComboBox comboBoxListeProprietaires;

    private JCheckBox aEuthanasierCheckBox;
    private JCheckBox dateDeNaissanceCheckBox;
    private JCheckBox dateDAttributionPuceCheckBox;
    private JCheckBox proprioCheckBox;

    private ButtonGroup buttonGroupeSexe;
    private ButtonGroup buttonGroupeSterilise;

    private FenetrePrincipale fenetre;

    public PanneauAnimal(Controller controller, FenetrePrincipale fenetre) {
        this.fenetre = fenetre;
        this.controller = controller;

        raisonTextField.setEnabled(false);
        raisonTextField.setBackground(Color.GRAY);
        spinnerDateEuthanasie.setEnabled(false);
        aEuthanasierCheckBox.addActionListener(new EcouteurDeCheckBox());

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
        instanciationSpinnerDate(spinnerDateEuthanasie);
        instanciationSpinnerDate(spinnerDateNaissance);
        instanciationSpinnerDate(spinnerDatePuce);
        instanciationListeProprietaires();

        erreurMessage = "Certains champs sont invalides !\n";

        baseBorder = nomTextField.getBorder();
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private void reinitialiserBorder(){
        spinnerDatePuce.setBorder(baseBorder);
        spinnerDateNaissance.setBorder(baseBorder);
        spinnerDateEuthanasie.setBorder(baseBorder);
        spinnerPoids.setBorder(baseBorder);
        femelleRadioButton.setBorder(baseBorder);
        maleRadioButton.setBorder(baseBorder);
        especeTextField.setBorder(baseBorder);
        raceTextField.setBorder(baseBorder);
        raisonTextField.setBorder(baseBorder);
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
            erreurMessage += "Il faut que le sexe soit défini\n";
        }
        if (especeTextField.getText().isEmpty()){
            estValide = false;
            especeTextField.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        }
        if (raceTextField.getText().isEmpty()){
            estValide = false;
            raceTextField.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        }
        if (aEuthanasierCheckBox.isSelected() && raisonTextField.getText().isEmpty()){
            estValide = false;
            raisonTextField.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            erreurMessage += "Le champ de la raison d'euthanasie ne peut être vide\n";
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
        }
        if (dateDAttributionPuceCheckBox.isSelected()){
            if (numPuceTextField.getText().isEmpty()){
                estValide = false;
                numPuceTextField.setBorder(BorderFactory.createCompoundBorder(border,
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                erreurMessage += "Le champ de numero de puce ne peut être vide\n";
            }
            if (localisationPuceTextField.getText().isEmpty()){
                estValide = false;
                localisationPuceTextField.setBorder(BorderFactory.createCompoundBorder(border,
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                erreurMessage += "Le champ localisation puce ne peut être vide\n";
            }
        }
        if (!numPuceTextField.getText().isEmpty()){
            try {
                Integer.parseInt(numPuceTextField.getText().replace("\\s", ""));
            } catch (NumberFormatException e) {
                estValide = false;
                numPuceTextField.setBorder(BorderFactory.createCompoundBorder(border,
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                erreurMessage += "Le champ de numero de puce doit contenir un nombre entier plus petit que 2 147 483 647 \n";
            }
        }
        if (!numTatouageTextField.getText().isEmpty()){
            try {
                Integer.parseInt(numTatouageTextField.getText().replace("\\s", ""));
            } catch (NumberFormatException e) {
                estValide = false;
                numPuceTextField.setBorder(BorderFactory.createCompoundBorder(border,
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                erreurMessage += "Le champ de numero de tatouage doit contenir un nombre entier plus petit que 2 147 483 647 \n";
            }
        }
        return estValide;
    }

    private Boolean verifierDateLogique(){
        Boolean estValide = true;
        Border border = BorderFactory.createLineBorder(Color.red);
        if (aEuthanasierCheckBox.isSelected()){
            if (((Date)spinnerDateEuthanasie.getValue()).before(GregorianCalendar.getInstance().getTime())){
                estValide = false;
                spinnerDateEuthanasie.setBorder(BorderFactory.createCompoundBorder(border,
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                erreurMessage += "La date d'euthanasie ne peut être antérieur à la date du jour!\n";
            }
        }
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
        instanciationSpinnerDate(spinnerDateEuthanasie);
        instanciationSpinnerDate(spinnerDateNaissance);
        instanciationSpinnerDate(spinnerDatePuce);
        instanciationListeProprietaires();
        aEuthanasierCheckBox.setSelected(false);
        spinnerDateEuthanasie.setEnabled(false);
        raisonTextField.setText("");
        raisonTextField.setEnabled(false);
        raisonTextField.setBackground(Color.GRAY);
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
        animal.setNom(nomTextField.getText());
        animal.setPoids((Double)spinnerPoids.getValue());
        if (femelleRadioButton.isSelected())
            animal.setSexe("F");
        else
            animal.setSexe("M");
        animal.setEspece(especeTextField.getText());
        animal.setRace(raceTextField.getText());
        if(dateDeNaissanceCheckBox.isSelected()){
            GregorianCalendar date = new GregorianCalendar();
            date.setTime((Date)spinnerDateNaissance.getValue());
            animal.setDateNaissance(date);
        }
        animal.setCouleurDePeau(couleurTextField.getText());
        animal.setNumPuce(Integer.parseInt(numPuceTextField.getText()));
        animal.setLocalisationPuce(localisationPuceTextField.getText());
        if(dateDAttributionPuceCheckBox.isSelected()){
            GregorianCalendar date = new GregorianCalendar();
            date.setTime((Date)spinnerDatePuce.getValue());
            animal.setDateNaissance(date);
        }
        animal.setNumTatouage(Integer.parseInt(numTatouageTextField.getText()));
        animal.setLocalisationPuce(localisationPuceTextField.getText());
        animal.setProprietaire((Proprietaire)comboBoxListeProprietaires.getSelectedItem());
        return animal;
    }

    public void instanciationListeProprietaires() {
        comboBoxListeProprietaires.removeAllItems();
        try {
            for (Proprietaire p : controller.getProprietaires()) {
                comboBoxListeProprietaires.addItem(p);
            }
        } catch (ProprietaireException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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
            if(e.getSource() == aEuthanasierCheckBox){
                if(aEuthanasierCheckBox.isSelected()){
                    raisonTextField.setEnabled(true);
                    raisonTextField.setBackground(Color.white);
                    spinnerDateEuthanasie.setEnabled(true);
                }
                else{
                    raisonTextField.setEnabled(false);
                    raisonTextField.setBackground(Color.GRAY);
                    raisonTextField.setText("");
                    spinnerDateEuthanasie.setEnabled(false);
                }
            }
            if(e.getSource() == proprioCheckBox){
                if(proprioCheckBox.isSelected()){
                    ajouterUnPropriétaireButton.setEnabled(true);
                    comboBoxListeProprietaires.setEnabled(true);
                }
                else{
                    ajouterUnPropriétaireButton.setEnabled(false);
                    comboBoxListeProprietaires.setEnabled(false);
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
                    //TODO controller.ajouterFicheDeSoin(creationAnimal());
                    JOptionPane.showMessageDialog(null, "La fiche de l'animal a été correctement ajoutée à la base de données !");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,erreurMessage);
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
}




