package controllerPackage;

import businessPackage.Business;
import exceptionPackage.*;
import modelPackage.Animal;
import modelPackage.Medicament;
import modelPackage.Veterinaire;
import modelPackage.modelJointure.VeterinaireSoinAvanceOrdonnanceRecherche;

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

    public void getResultatRechercheProprietaire(Veterinaire selectedVet) throws SingletonConnectionException, ProprietaireException {
        business.getResultatRechercheProprietaire(selectedVet);
    }

    public ArrayList<VeterinaireSoinAvanceOrdonnanceRecherche> getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut,
                                                                                                   GregorianCalendar dateFin) throws SingletonConnectionException, VeterinaireException, OrdonnanceException {
        return business.getResultatRechercheVeterinaireDate(dateDebut, dateFin);
    }
}
