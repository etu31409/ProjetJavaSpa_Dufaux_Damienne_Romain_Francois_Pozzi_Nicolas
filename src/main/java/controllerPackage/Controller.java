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

    //Animaux

    public ArrayList<Animal>getAnimaux() throws AnimalException, SingletonConnectionException {
        return business.getAnimaux();
    }
    public String[][] getAnimauxTries(String critere) throws AnimalException, SingletonConnectionException {
        return business.getAnimauxTries(critere);
    }
    public void ajouterAnimal(Animal animal) throws AnimalException, SingletonConnectionException {
        business.ajouterAnimal(animal);
    }

    //Vétérinaires

    public ArrayList<Veterinaire>getVeterinaires() throws VeterinaireException, SingletonConnectionException {
        return business.getVeterinaires();
    }

    //Médicaments

    public ArrayList<Medicament>getMedicaments() throws MedicamentException, SingletonConnectionException {
        return business.getMedicaments();
    }
    public void ajouterMedicament(Medicament medicament) throws MedicamentException, SingletonConnectionException{
        business.ajouterMedicament(medicament);
    }

    //Propriétaires

    public ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, SingletonConnectionException{
        return business.getProprietaires();
    }
    public void ajouterNouveauProprio(Proprietaire proprietaire)throws SingletonConnectionException, ProprietaireException {
        business.ajouterNouveauProprio(proprietaire);
    }

    //Soins

    public String[][] getSoinsTries(String critere) throws SoinException, SingletonConnectionException, VeterinaireException{
        return business.getSoinsTries(critere);
    }
    public void ajouterFicheDeSoins(){}

    //Ordonnances

    public void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException, SingletonConnectionException{
        business.ajouterOrdonnance(ordonnance);
    }

    //Recherches

    public String[][] getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws SingletonConnectionException, ProprietaireException {
        return business.getResultatRechercheProprietaire(selectionVeterinaire);
    }

    public String[][] getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut,
                                                          GregorianCalendar dateFin) throws SingletonConnectionException, VeterinaireException {
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

    //Tâche métier

    public String [][] getStatistiquesMedicaments(GregorianCalendar dateDebutZoneRecherche, GregorianCalendar dateFinZoneRecherche)
            throws SingletonConnectionException, MedicamentException{
        return business.getStatistiquesMedicaments(dateDebutZoneRecherche, dateFinZoneRecherche);
    }
}
