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

    public ArrayList<Animal>getAnimaux() throws AnimalException, SingletonConnectionException, ProprietaireException {
        return business.getAnimaux();
    }

    public ArrayList<Animal> getIdentifiantsAnimaux() throws AnimalException, ProprietaireException, SingletonConnectionException {
        return business.getIdentifiantsAnimaux();
    }

    public ArrayList<Veterinaire> getIdentifiantsVeterinaires() throws VeterinaireException, SingletonConnectionException {
        return business.getIdentifiantsVeterinaires();
    }

    public ArrayList<Medicament> getIdentifiantsMedicaments() throws MedicamentException, SingletonConnectionException {
        return business.getIdentifiantsMedicaments();
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
}
