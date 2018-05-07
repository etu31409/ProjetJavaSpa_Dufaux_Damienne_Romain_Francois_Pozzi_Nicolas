package businessPackage;

import dataAcessPackage.*;
import exceptionPackage.*;
import modelPackage.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Business {
    IAnimal daoAnimal;
    IMedicament daoMedicament;
    IProprietaire daoProprietaire;
    IVeterinaire daoVeterinaire;
    ISoinAvance daoSoinAvance;
    IOrdonnance daoOrdonnance;

    public Business() {
        daoAnimal = new DBDAOAnimal();
        daoMedicament = new DBDAOMedicament();
        daoProprietaire = new DBDAOProprietaire();
        daoVeterinaire = new DBDAOVeterinaire();
        daoSoinAvance = new DBDAOSoinAvance();
    }

    public ArrayList<Animal>getAnimaux() throws AnimalException, SingletonConnectionException {
        return daoAnimal.getAnimaux();
    }

    public ArrayList<Veterinaire>getVeterinaires() throws VeterinaireException, SingletonConnectionException {
        return daoVeterinaire.getVeterinaires();
    }

    public ArrayList<Medicament>getMedicaments() throws MedicamentException, SingletonConnectionException {
        return daoMedicament.getMedicaments();
    }

    public ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, SingletonConnectionException{
        return daoProprietaire.getProprietaires();
    }

    public String[][] getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws SingletonConnectionException, ProprietaireException {
        return daoProprietaire.getResultatRechercheProprietaire(selectionVeterinaire);
    }

    public String[][] getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut,
                    GregorianCalendar dateFin) throws SingletonConnectionException, VeterinaireException {
        return daoVeterinaire.getResultatRechercheVeterinaireDate(dateDebut, dateFin);
    }

    public String[][] getResultatRecherchAnimauxVeterinaire(Veterinaire selectionVeterinaire) throws AnimalException,
            SingletonConnectionException{
        return daoAnimal.getResultatRecherchAnimauxVeterinaire(selectionVeterinaire);
    }

    public String[][] getResultatRecherchAnimauxMedicamentVeto(Medicament selectionMedicament, Veterinaire selectionVeterinaire)
            throws AnimalException, SingletonConnectionException{
        return daoAnimal.getResultatRecherchAnimauxMedicamentVeto(selectionMedicament, selectionVeterinaire);
    }

    public String[][] getResultatRecherchAnimauxMedicament(Medicament selectionMedicament) throws AnimalException,
            SingletonConnectionException{
        return daoAnimal.getResultatRecherchAnimauxMedicament(selectionMedicament);
    }

    public void ajouterAnimal(Animal animal) throws AnimalException, SingletonConnectionException {
        daoAnimal.ajouterAnimal(animal);
    }

    public String[][] getSoinsTries(String critere) throws SoinException, SingletonConnectionException, VeterinaireException{
        return daoSoinAvance.getSoinsTries(critere);
    }

    public String[][] getAnimauxTries(String critere) throws AnimalException, SingletonConnectionException {
        return daoAnimal.getAnimauxTries(critere);
    }

    public void ajouterNouveauProprio(Proprietaire proprietaire)throws SingletonConnectionException, ProprietaireException {
        daoProprietaire.ajouterNouveauProprio(proprietaire);
    }

    /*public void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException, SingletonConnectionException{
        daoOrdonnance.ajouterOrdonnance(ordonnance);
    }

    public void ajouterMedicament(Medicament medicament) throws MedicamentException, SingletonConnectionException{
        daoMedicament.ajouterMedicament(medicament);
    }*/


    //tache metier

    public String[][] getStatistiquesMedicaments(GregorianCalendar dateDebutZoneRecherche, GregorianCalendar dateFinZoneRecherche)
            throws SingletonConnectionException, MedicamentException, OrdonnanceException{
        String[][]listeResultatRechercheOrdonnances = daoMedicament.getOrdonnancesEntreDeuxDates(dateDebutZoneRecherche,
                dateFinZoneRecherche);



        return null;
    }
}
