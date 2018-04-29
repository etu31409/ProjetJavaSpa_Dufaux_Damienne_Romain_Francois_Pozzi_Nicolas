package businessPackage;

import dataAcessPackage.*;
import exceptionPackage.*;
import modelPackage.Animal;
import modelPackage.Medicament;
import modelPackage.Veterinaire;

import java.util.ArrayList;

public class Business {
    IAnimal daoAnimal;
    IMedicament daoMedicament;
    IProprietaire daoProprietaire;
    IVeterinaire daoVeterinaire;

    public Business() {

        daoAnimal = new DBDAOAnimal();
        daoMedicament = new DBDAOMedicament();
        daoProprietaire = new DBDAOProprietaire();
        daoVeterinaire = new DBDAOVeterinaire();
    }

    public ArrayList<Animal>getAnimaux() throws AnimalException, SingletonConnectionException, ProprietaireException {
        return daoAnimal.getAnimaux();
    }

    public ArrayList<Animal> getIdentifiantsAnimaux() throws AnimalException, SingletonConnectionException, ProprietaireException {
        return daoAnimal.getAnimaux();
    }

    public ArrayList<Medicament> getIdentifiantsMedicaments() throws MedicamentException, SingletonConnectionException {
        return daoMedicament.getMedicaments();
    }

    public ArrayList<Veterinaire> getIdentifiantsVeterinaires() throws VeterinaireException, SingletonConnectionException {
        return daoVeterinaire.getVeterinaires();
    }

    public void getResultatRechercheProprietaire(Veterinaire selectedVet) throws SingletonConnectionException, ProprietaireException {
        daoProprietaire.getResultatRechercheProprietaire(selectedVet);
    }

    //tache metier
}
