package controllerPackage;

import businessPackage.Business;
import exceptionPackage.*;
import modelPackage.Animal;
import modelPackage.Medicament;
import modelPackage.Veterinaire;

import java.util.ArrayList;

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

    public void getResultatRechercheProprietaire(Veterinaire selectedVet) throws SingletonConnectionException, ProprietaireException {
        business.getResultatRechercheProprietaire(selectedVet);
    }
}
