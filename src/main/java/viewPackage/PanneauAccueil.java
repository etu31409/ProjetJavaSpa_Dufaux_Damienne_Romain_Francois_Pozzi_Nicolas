package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Animal;
import modelPackage.Proprietaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;

public class PanneauAccueil {
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
    private JTextField couleurTextField;
    private JTextField numPuceTextField;
    private JTextField localisationPuceTextField;
    private JTextField raisonTextField;
    private JTextField numTatouageTextField;
    private JTextField localisationTatouageTextField;

    private JRadioButton femelleRadioButton, maleRadioButton, steriliseRadioButton, nonSteriliseRadioButton;
    private JButton ajouterUnPropriétaireButton, validerButton, reinitialiserButton, retourButton;
    private JCheckBox aEuthanasierCheckBox;
    private JComboBox comboBoxListeProprietaires;


    public PanneauAccueil(Controller controller) {
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
                PanneauBienvenue panneauBienvenue = new PanneauBienvenue();
                panneauBienvenue.repaint();
                panneauBienvenue.validate();
            }
            if(e.getSource() == reinitialiserButton){
                panneauContainerPrincipal.removeAll();
                panneauContainerPrincipal.repaint();
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
        try{
            String nomAnimal;
            GregorianCalendar dateArriveeAnimal;
            String especeAnimal;
            String raceAnimal;
            String sexeAnimal;
            Boolean estSteriliseAnimal;
            String couleurDePeauAnimal;
            GregorianCalendar dateNaissanceAnimal;
            Integer numPuceAnimal;
            String localisationPuceAnimal;
            GregorianCalendar dateAttributionPuceAnimal;
            Integer numTatouageAnimal;
            String localisationTatouageAnimal;
            Double poidsAnimal;
            Proprietaire proprietaireAnimal = fenetreProprio.getProprietaire();

            nomAnimal = nomTextField.getText();
            //dateArriveeAnimal = GregorianCalendar.getInstance().getTime();
            especeAnimal = especeTextField.getText();
            raceAnimal = raceTextField.getText();

            if(femelleRadioButton.isSelected()) {
                sexeAnimal = "F";
            }
            else {
                sexeAnimal = "M";
            }
            if(steriliseRadioButton.isSelected()){
                estSteriliseAnimal = true;
            }
            else {
                estSteriliseAnimal = false;
            }

            couleurDePeauAnimal = couleurTextField.getText();
            //dateNaissanceAnimal = spinnerDateNaissance.getDate();
            numPuceAnimal = Integer.valueOf(numPuceTextField.getText());
            localisationPuceAnimal = localisationPuceTextField.getText();
            //dateAttributionPuceAnimal = spinnerDatePuce.getDate();
            numTatouageAnimal = Integer.valueOf(numTatouageTextField.getText());
            localisationTatouageAnimal = localisationTatouageTextField.getText();


            poidsAnimal = Double.valueOf((Double) spinnerPoids.getValue());

            //Animal animal = new Animal(nomAnimal, dateArriveeAnimal, especeAnimal,
                    //raceAnimal, sexeAnimal, estSteriliseAnimal, couleurDePeauAnimal, dateNaissanceAnimal, numPuceAnimal,
                    //localisationPuceAnimal, dateAttributionPuceAnimal, numTatouageAnimal, localisationTatouageAnimal,
                    //poidsAnimal, proprietaireAnimal);

            //controller.ajouterAnimal(animal);
        }
        catch (Exception exception){System.out.println(exception.getMessage());}
    }
}
