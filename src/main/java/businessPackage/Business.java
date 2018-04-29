package businessPackage;

import dataAcessPackage.*;
import exceptionPackage.*;
import modelPackage.Animal;
import modelPackage.Medicament;
import modelPackage.Veterinaire;
import modelPackage.modelJointure.VeterinaireSoinAvanceOrdonnanceRecherche;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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

    public ArrayList<VeterinaireSoinAvanceOrdonnanceRecherche> getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut,
                    GregorianCalendar dateFin) throws SingletonConnectionException, VeterinaireException, OrdonnanceException {
        return daoVeterinaire.getResultatRechercheVeterinaireDate(dateDebut, dateFin);
    }
    //tache metier
}
