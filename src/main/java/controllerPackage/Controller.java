package controllerPackage;

import businessPackage.Business;
import exceptionPackage.*;
import modelPackage.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

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

    public ArrayList<ProprietaireAnimal> getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws SingletonConnectionException, ProprietaireException {
        return business.getResultatRechercheProprietaire(selectionVeterinaire);
    }

    public String[][] getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut, GregorianCalendar dateFin)
            throws SingletonConnectionException, VeterinaireException {
        return business.getResultatRechercheVeterinaireDate(dateDebut, dateFin);
    }
    public ArrayList<Animal> getResultatRecherchAnimauxVeterinaire(Veterinaire selectionVeterinaire) throws AnimalException,
            SingletonConnectionException{
        return business.getResultatRecherchAnimauxVeterinaire(selectionVeterinaire);
    }

    public ArrayList<Animal> getResultatRecherchAnimauxMedicamentVeto(Medicament selectionMedicament, Veterinaire selectionVeterinaire)
            throws AnimalException, SingletonConnectionException{
        return business.getResultatRecherchAnimauxMedicamentVeto(selectionMedicament, selectionVeterinaire);
    }

    public ArrayList<Animal> getResultatRecherchAnimauxMedicament(Medicament selectionMedicament) throws AnimalException,
            SingletonConnectionException{
        return business.getResultatRecherchAnimauxMedicament(selectionMedicament);
    }

    public void ajouterAnimal(Animal animal) throws AnimalException, SingletonConnectionException{
        business.ajouterAnimal(animal);
    }

    public ArrayList<SoinAvance> getSoinsTries(String critere) throws SoinException, SingletonConnectionException, VeterinaireException{
        return business.getSoinsTries(critere);
    }

    public ArrayList<Animal> getAnimauxTries(String critere) throws AnimalException, SingletonConnectionException {
        return business.getAnimauxTries(critere);
    }

    public void ajouterNouveauProprio(Proprietaire proprietaire)throws SingletonConnectionException, ProprietaireException {
        business.ajouterNouveauProprio(proprietaire);
    }


    public void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException, SingletonConnectionException{
        business.ajouterOrdonnance(ordonnance);
    }

    public void ajouterFicheDeSoins(SoinAvance soinAvance) throws SoinException, SingletonConnectionException{
        business.ajouterFicheDeSoins(soinAvance);
    }

    public void ajouterMedicament(Medicament medicament) throws MedicamentException, SingletonConnectionException{
        business.ajouterMedicament(medicament);

    }

    public HashMap<String, Double> getStatistiquesMedicaments(GregorianCalendar dateDebutZoneRecherche, GregorianCalendar dateFinZoneRecherche)
            throws SingletonConnectionException, MedicamentException{
        return business.getStatistiquesMedicaments(dateDebutZoneRecherche, dateFinZoneRecherche);
    }
}
