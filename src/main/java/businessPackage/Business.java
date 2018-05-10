package businessPackage;

import dataAcessPackage.*;
import exceptionPackage.*;
import modelPackage.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

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
        daoOrdonnance = new DBDAOOrdonnance();
    }

    //animaux
    public ArrayList<Animal>getAnimaux() throws AnimalException, SingletonConnectionException {
        return daoAnimal.getAnimaux();
    }

    public ArrayList<Animal> getAnimauxTries(String critere) throws AnimalException, SingletonConnectionException {
        return daoAnimal.getAnimauxTries(critere);
    }

    public void ajouterAnimal(Animal animal) throws AnimalException, SingletonConnectionException{
        daoAnimal.ajouterAnimal(animal);
    }
    public void supprimerAnimal(Animal animal) throws AnimalException, SingletonConnectionException{
        daoAnimal.supprimerAnimal(animal);
    }

    //medicaments
    public ArrayList<Medicament>getMedicaments() throws MedicamentException, SingletonConnectionException {
        return daoMedicament.getMedicaments();
    }

    public Medicament getUnMedicament(int identifiantMed)throws MedicamentException{
        return daoMedicament.getUnMedicament(identifiantMed);
    }

    public void ajouterMedicament(Medicament medicament) throws MedicamentException, SingletonConnectionException{
        daoMedicament.ajouterMedicament(medicament);
    }

    //ordonnances
    public void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException{
        daoOrdonnance.ajouterOrdonnance(ordonnance);
    }

    //soins
    public void ajouterFicheDeSoins(SoinAvance soinAvance) throws SoinException, SingletonConnectionException{
        daoSoinAvance.ajouterFicheDeSoins(soinAvance);
    }

    public SoinAvance getUnSoinAvance(Integer numSoin) throws SoinException, SingletonConnectionException{
        return daoSoinAvance.getUnSoinAvance(numSoin);
    }

    public ArrayList<SoinAvance> getSoinsTries(String critere) throws SoinException, SingletonConnectionException, VeterinaireException{
        return daoSoinAvance.getSoinsTries(critere);
    }
    public void supprimerSoin(SoinAvance soin) throws SoinException, SingletonConnectionException {
        daoSoinAvance.supprimerSoin(soin);
    }

    public void modifierSoin(SoinAvance soin) throws SoinException, SingletonConnectionException {
        daoSoinAvance.modifierSoin(soin);
    }

    //veterinaires
    public ArrayList<Veterinaire> getVeterinaires() throws VeterinaireException, SingletonConnectionException {
        return daoVeterinaire.getVeterinaires();
    }

    //proprietaires
    public ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, SingletonConnectionException{
        return daoProprietaire.getProprietaires();
    }

    public void ajouterNouveauProprio(Proprietaire proprietaire)throws SingletonConnectionException, ProprietaireException {
        daoProprietaire.ajouterNouveauProprio(proprietaire);
    }

    //recherches

    public ArrayList<ProprietaireAnimal> getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws SingletonConnectionException, ProprietaireException {
        return daoProprietaire.getResultatRechercheProprietaire(selectionVeterinaire);
    }

    public ArrayList<VeterinaireOrdonnance> getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut,
                    GregorianCalendar dateFin) throws SingletonConnectionException, VeterinaireException {
        return daoVeterinaire.getResultatRechercheVeterinaireDate(dateDebut, dateFin);
    }

    public ArrayList<Animal> getResultatRecherchAnimauxVeterinaire(Veterinaire selectionVeterinaire) throws AnimalException,
            SingletonConnectionException{
        return daoAnimal.getResultatRecherchAnimauxVeterinaire(selectionVeterinaire);
    }

    public ArrayList<Animal> getResultatRecherchAnimauxMedicamentVeto(Medicament selectionMedicament, Veterinaire selectionVeterinaire)
            throws AnimalException, SingletonConnectionException{
        return daoAnimal.getResultatRecherchAnimauxMedicamentVeto(selectionMedicament, selectionVeterinaire);
    }

    public ArrayList<Animal> getResultatRecherchAnimauxMedicament(Medicament selectionMedicament) throws AnimalException,
            SingletonConnectionException{
        return daoAnimal.getResultatRecherchAnimauxMedicament(selectionMedicament);
    }


    //tache metier
    public ArrayList<StatMedicament> getStatistiquesMedicaments(GregorianCalendar dateDebutZoneRecherche, GregorianCalendar dateFinZoneRecherche)
            throws SingletonConnectionException, MedicamentException {

        ArrayList<StatMedicament> listeResultatRechercheOrdonnances = daoMedicament.getMedicamentsEntreDeuxDates(dateDebutZoneRecherche,
                dateFinZoneRecherche);
        ArrayList<Medicament> listeMedicaments = daoMedicament.getMedicaments();

        ArrayList<StatMedicament> resultatStatistiques = new ArrayList<>();
        HashMap<String, Double> statistiques = new HashMap<>();
        Double pourcentageParMedicament;
        Integer compteurGlobal = 0;

        for (StatMedicament sm : listeResultatRechercheOrdonnances) {
            compteurGlobal++;
            if (!statistiques.containsKey(sm.getNomMedic())) {
                statistiques.put(sm.getNomMedic(), 1.);
            }
            else {
                statistiques.replace(sm.getNomMedic(), ((statistiques.get(sm.getNomMedic()))+1));
            }
        }

        for(Medicament med : listeMedicaments){
            if(!statistiques.containsKey(med.getNomMedic())){
                statistiques.put(med.getNomMedic(), 0.);
            }
        }

        for (String nomMedic : statistiques.keySet()) {
            StatMedicament stat = new StatMedicament();
            pourcentageParMedicament = (statistiques.get(nomMedic)) / compteurGlobal;
            stat.setNomMedic(nomMedic);
            stat.setPourcentage(pourcentageParMedicament);
            resultatStatistiques.add(stat);
        }

        return resultatStatistiques;
    }
}
