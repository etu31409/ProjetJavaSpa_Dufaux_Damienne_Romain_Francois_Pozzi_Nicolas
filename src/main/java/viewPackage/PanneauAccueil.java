package viewPackage;

import controllerPackage.Controller;
import dataAcessPackage.SingletonConnection;
import exceptionPackage.AnimalException;
import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Animal;
import modelPackage.Proprietaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PanneauAccueil extends JPanel {
    private Controller controller;

    private FenetreProprio fenetreProprio;
    private JPanel panneauContainerPrincipal;

    private JSpinner spinnerPoids;
    private JSpinner spinnerDateEuthanasie;
    private JSpinner spinnerDateNaissance;
    private JSpinner spinnerDatePuce;
    private JSpinner spinnerDateTatouage;
    private JTextField nomTextField;
    private JTextField numCelluleTextField;
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
    private JCheckBox aEuthanasierCheckBox;
    private JComboBox comboBoxListeProprietaires;

    private FenetrePrincipale fenetre;

    public PanneauAccueil(Controller controller, FenetrePrincipale fenetre) {
        this.fenetre = fenetre;
        this.controller = controller;

        raisonTextField.setEnabled(false);
        raisonTextField.setBackground(Color.GRAY);
        aEuthanasierCheckBox.addActionListener(new EcouteurDeCheckBox());
        validerButton.addActionListener(new EcouteurDeBoutons());
        reinitialiserButton.addActionListener(new EcouteurDeBoutons());
        retourButton.addActionListener(new EcouteurDeBoutons());
        ajouterUnPropriétaireButton.addActionListener(new EcouteurDeBoutons());

        instanciationSpinnerPoids();
        instanciationSpinnerDate(spinnerDateEuthanasie);
        instanciationSpinnerDate(spinnerDateNaissance);
        instanciationSpinnerDate(spinnerDatePuce);
        instanciationSpinnerDate(spinnerDateTatouage);
        instancieListeProprietaires();
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    public void instancieListeProprietaires() {
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
                aEuthanasierCheckBox.isSelected();
            }
            if(aEuthanasierCheckBox.isSelected()){
                raisonTextField.setEnabled(true);
                raisonTextField.setBackground(Color.white);
            }
            else{
                raisonTextField.setEnabled(false);
                raisonTextField.setBackground(Color.GRAY);
            }
        }
    }

    private class EcouteurDeBoutons implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == validerButton){
                validationFormulaire();
            }
            if(e.getSource() == retourButton){
                fenetre.accueil();
            }
            if(e.getSource() == reinitialiserButton){
                instanciationSpinnerPoids();
                instanciationSpinnerDate(spinnerDateEuthanasie);
                instanciationSpinnerDate(spinnerDateNaissance);
                instanciationSpinnerDate(spinnerDatePuce);
                instanciationSpinnerDate(spinnerDateTatouage);
                instancieListeProprietaires();
                aEuthanasierCheckBox.setSelected(false);
                raisonTextField.setText("");
                raisonTextField.setEnabled(false);
                raisonTextField.setBackground(Color.GRAY);
                nomTextField.setText("");
                numCelluleTextField.setText("");
                especeTextField.setText("");
                raceTextField.setText("");
                numTatouageTextField.setText("");
                localisationTatouageTextField.setText("");
                couleurTextField.setText("");
                numPuceTextField.setText("");
                localisationPuceTextField.setText("");
            }
            if(e.getSource() == ajouterUnPropriétaireButton){
                fenetreProprio = new FenetreProprio();
            }
        }
    }

    public void validationFormulaire(){
       //validation numero de cellule
        Integer numeroCellule = 0;
        try{
            numeroCellule = Integer.valueOf(numCelluleTextField.getText());
        }
        catch (Exception error){
            numeroCellule = null;
        }
        finally {
            if(numCelluleTextField.getText().isEmpty() || numCelluleTextField == null || numeroCellule < 1){
                // mettre la case en rouge(Color.RED);
            }
        }
        //validation espece (ne peut pas etre null)
        if(especeTextField.getText().isEmpty()){

        }
        //valiation race (ne peut pas etre null)
        if(raceTextField.getText().isEmpty()){

        }
        //validation sexe (ne peut pas etre null)
        if(!femelleRadioButton.isSelected() || !maleRadioButton.isSelected()){

        }

        //validation estSterilise (ne peut pas etre null)
        if(!steriliseRadioButton.isSelected() || !nonSteriliseRadioButton.isSelected()){
            //mettre la case en rouge
        }
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
        Integer numTatouageAnimal;
        String localisationTatouageAnimal;
        Double poidsAnimal;
        try {
            nomAnimal = nomTextField.getText();
            dateArriveeAnimal = new GregorianCalendar();
            especeAnimal = especeTextField.getText();
            raceAnimal = raceTextField.getText();

            Proprietaire proprietaireAnimal = (Proprietaire) comboBoxListeProprietaires.getSelectedItem();

            if (femelleRadioButton.isSelected()) {
                sexeAnimal = "F";
            } else {
                sexeAnimal = "M";
            }
            if (steriliseRadioButton.isSelected()) {

                estSteriliseAnimal = true;
            } else {
                estSteriliseAnimal = false;
            }

            localisationPuceAnimal = localisationPuceTextField.getText();

            numTatouageAnimal = Integer.valueOf(numTatouageTextField.getText());
            couleurDePeauAnimal = couleurTextField.getText();
            Calendar dateNaissance = new GregorianCalendar();
            dateNaissance.setTime((Date) spinnerDateNaissance.getValue());
            dateNaissanceAnimal = (GregorianCalendar) dateNaissance;
            numPuceAnimal = Integer.valueOf(numPuceTextField.getText());
            localisationPuceAnimal = localisationPuceTextField.getText();
            Calendar datePuce = new GregorianCalendar();
            datePuce.setTime((Date) spinnerDatePuce.getValue());
            dateAttributionPuceAnimal = (GregorianCalendar) datePuce;
            numTatouageAnimal = Integer.valueOf(numTatouageTextField.getText());
            localisationTatouageAnimal = localisationTatouageTextField.getText();
            poidsAnimal = Double.valueOf((Double) spinnerPoids.getValue());

            Animal animal = new Animal(nomAnimal, dateArriveeAnimal, especeAnimal,
                    raceAnimal, sexeAnimal, estSteriliseAnimal, couleurDePeauAnimal, dateNaissanceAnimal, numPuceAnimal,
                    localisationPuceAnimal, dateAttributionPuceAnimal, numTatouageAnimal, localisationTatouageAnimal,
                    poidsAnimal, proprietaireAnimal);

            controller.ajouterAnimal(animal);
        }
        catch(SingletonConnectionException e){System.out.println(e.getMessage());}
        catch(AnimalException e){System.out.println(e.getMessage());}
    }

    public void reinitialisation(){
        nomTextField.setText("");
        numCelluleTextField.setText("");
        especeTextField.setText("");
        raceTextField.setText("");
        raisonTextField.setText("");
        numTatouageTextField.setText("");
        localisationTatouageTextField.setText("");
        couleurTextField.setText("");
        numPuceTextField.setText("");
        localisationPuceTextField.setText("");

        femelleRadioButton.setSelected(false);
        maleRadioButton.setSelected(false);;
        steriliseRadioButton.setSelected(false);;
        nonSteriliseRadioButton.setSelected(false);
        aEuthanasierCheckBox.setSelected(false);;
        comboBoxListeProprietaires.removeAllItems();
    }
}




