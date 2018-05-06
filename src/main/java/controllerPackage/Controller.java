package controllerPackage;

import businessPackage.Business;
import exceptionPackage.*;
import modelPackage.*;

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

    public ArrayList<Medicament>getMedicaments() throws MedicamentException, SingletonConnectionException {
        return business.getMedicaments();
    }

    public ArrayList<Veterinaire> getVeterinaires() throws VeterinaireException, SingletonConnectionException {
        return business.getVeterinaires();
    }

    public ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, SingletonConnectionException{
        return business.getProprietaires();
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

    public String[][] getSoinsTries(String critere) throws SoinException, SingletonConnectionException, VeterinaireException{
        return business.getSoinsTries(critere);
    }

    public String[][] getAnimauxTries(String critere) throws AnimalException, SingletonConnectionException {
        return business.getAnimauxTries(critere);
    }

    public void ajouterNouveauProprio(Proprietaire proprietaire)throws SingletonConnectionException, ProprietaireException {
        business.ajouterNouveauProprio(proprietaire);
    }

    /*public void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException, SingletonConnectionException{
        business.ajouterOrdonnance(ordonnance);
    }

    public void ajouterMedicament(Medicament medicament) throws MedicamentException, SingletonConnectionException{
        business.ajouterMedicament(medicament);
    }*/
}
