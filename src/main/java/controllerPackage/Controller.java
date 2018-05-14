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


    //animaux
    public ArrayList<Animal>getAnimaux() throws AnimalException, SingletonConnectionException {
        return business.getAnimaux();
    }

    public ArrayList<Animal> getAnimauxTries(String critere) throws AnimalException, SingletonConnectionException {
        return business.getAnimauxTries(critere);
    }

    /*public Animal getUnAnimal(Integer numRegistre) throws SingletonConnectionException, AnimalException{
        return business.getUnAnimal(numRegistre);
    }*/

    public void ajouterAnimal(Animal animal) throws AnimalException, SingletonConnectionException{
        business.ajouterAnimal(animal);
    }
    public void supprimerAnimal(Animal animal) throws AnimalException, SingletonConnectionException{
        business.supprimerAnimal(animal);
    }

    public void modifierAnimal(Animal animal) throws  AnimalException, SingletonConnectionException{
        business.modifierAnimal(animal);
    }

    //medicaments
    public ArrayList<Medicament>getMedicaments() throws MedicamentException, SingletonConnectionException {
        return business.getMedicaments();
    }

    public ArrayList<Medicament> getMedicamentsDeLaFiche(Integer numSoin)throws MedicamentException{
        return business.getMedicamentsDeLaFiche(numSoin);
    }

    public void ajouterMedicament(Medicament medicament) throws MedicamentException, SingletonConnectionException{
        business.ajouterMedicament(medicament);
    }

    //ordonnances

    public void supprimerOrdonnance(SoinAvance soin, Medicament medicament) throws OrdonnanceException, SingletonConnectionException {
        business.supprimerOrdonnance(soin, medicament);
    }

    public void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException{
        business.ajouterOrdonnance(ordonnance);
    }

    //soins
    public Integer ajouterFicheDeSoins(SoinAvance soinAvance) throws SoinException, SingletonConnectionException{
        return business.ajouterFicheDeSoins(soinAvance);
    }

    public SoinAvance getUnSoinAvance(Integer numRegistre) throws SoinException, SingletonConnectionException{
        return business.getUnSoinAvance(numRegistre);
    }

    public ArrayList<SoinAvance> getSoinsTries(String critere) throws SoinException, SingletonConnectionException, VeterinaireException{
        return business.getSoinsTries(critere);
    }
    public void supprimerSoin(SoinAvance soin) throws SoinException, SingletonConnectionException {
        business.supprimerSoin(soin);
    }

    public void modifierSoin(SoinAvance soin) throws SoinException, SingletonConnectionException {
        business.modifierSoin(soin);
    }

    //veterinaires
    public ArrayList<Veterinaire> getVeterinaires() throws VeterinaireException, SingletonConnectionException {
        return business.getVeterinaires();
    }

    //proprietaires
    public ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, SingletonConnectionException{
        return business.getProprietaires();
    }

    public void ajouterNouveauProprio(Proprietaire proprietaire)throws SingletonConnectionException, ProprietaireException {
        business.ajouterNouveauProprio(proprietaire);
    }

    //recherches
    public ArrayList<ProprietaireAnimal> getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws SingletonConnectionException, ProprietaireException {
        return business.getResultatRechercheProprietaire(selectionVeterinaire);
    }

    public ArrayList<VeterinaireOrdonnance> getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut, GregorianCalendar dateFin)
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

    //tâche metier
    public ArrayList<StatMedicament> getStatistiquesMedicaments(GregorianCalendar dateDebutZoneRecherche,
                                                                GregorianCalendar dateFinZoneRecherche)
            throws SingletonConnectionException, MedicamentException{
        return business.getStatistiquesMedicaments(dateDebutZoneRecherche, dateFinZoneRecherche);
    }

    //fermer connexion
    public void closeBaseDeDonnees() throws SingletonConnectionException, ConnexionException{
        business.closeBaseDeDonnees();
    }
}
