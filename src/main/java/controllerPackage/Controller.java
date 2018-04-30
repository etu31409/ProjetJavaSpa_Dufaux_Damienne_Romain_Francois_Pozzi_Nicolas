package controllerPackage;

import businessPackage.Business;
import exceptionPackage.*;
import modelPackage.Animal;
import modelPackage.Medicament;
import modelPackage.Veterinaire;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Controller {
    private Business business;

    public Controller(){
        business = new Business();
    }

    public ArrayList<Animal>getAnimaux() throws AnimalException, SingletonConnectionException {
        return business.getAnimaux();
    }

    public ArrayList<Veterinaire>getVeterinaires() throws VeterinaireException, SingletonConnectionException {
        return business.getVeterinaires();
    }

    public ArrayList<Medicament>getMedicaments() throws MedicamentException, SingletonConnectionException {
        return business.getMedicaments();
    }

    public String[][] getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws SingletonConnectionException, ProprietaireException {
        return business.getResultatRechercheProprietaire(selectionVeterinaire);
    }

    public String[][] getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut, GregorianCalendar dateFin)
            throws SingletonConnectionException, VeterinaireException {
        return business.getResultatRechercheVeterinaireDate(dateDebut, dateFin);
    }
    public String[][] getResultatRecherchAnimauxVeterinaire(Veterinaire selectionVeterinaire) throws AnimalException,
            SingletonConnectionException{
        return business.getResultatRecherchAnimauxVeterinaire(selectionVeterinaire);
    }

    public String[][] getResultatRecherchAnimauxMedicamentVeto(Medicament selectionMedicament, Veterinaire selectionVeterinaire)
            throws AnimalException, SingletonConnectionException{
        return business.getResultatRecherchAnimauxMedicamentVeto(selectionMedicament, selectionVeterinaire);
    }

    public String[][] getResultatRecherchAnimauxMedicament(Medicament selectionMedicament) throws AnimalException,
            SingletonConnectionException{
        return business.getResultatRecherchAnimauxMedicament(selectionMedicament);
    }
    public void ajouterAnimal(Animal animal) throws AnimalException, SingletonConnectionException{
        business.ajouterAnimal(animal);
    }
}
