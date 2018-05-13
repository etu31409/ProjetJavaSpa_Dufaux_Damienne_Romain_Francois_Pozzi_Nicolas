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

    public Animal getUnAnimal(Integer numRegistre) throws SingletonConnectionException, AnimalException{
        return daoAnimal.getUnAnimal(numRegistre);
    }

    public void ajouterAnimal(Animal animal) throws AnimalException, SingletonConnectionException{
        daoAnimal.ajouterAnimal(animal);
    }
    public void supprimerAnimal(Animal animal) throws AnimalException, SingletonConnectionException{
        daoAnimal.supprimerAnimal(animal);
    }

    public void modifierAnimal(Animal animal) throws  AnimalException, SingletonConnectionException{
        daoAnimal.modifierAnimal(animal);
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

    public SoinAvance getUnSoinAvance(Integer numRegistre) throws SoinException, SingletonConnectionException{
        return daoSoinAvance.getUnSoinAvance(numRegistre);
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

    public Veterinaire getUnVeterinaire(Integer identifiantVeto) throws SingletonConnectionException, VeterinaireException {
        return daoVeterinaire.getUnVeterinaire(identifiantVeto);
    }

    //proprietaires
    public ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, SingletonConnectionException{
        return daoProprietaire.getProprietaires();
    }

    public void ajouterNouveauProprio(Proprietaire proprietaire)throws SingletonConnectionException, ProprietaireException {
        daoProprietaire.ajouterNouveauProprio(proprietaire);
    }

    public Proprietaire getUnProprietaire(Integer identifiantProprietaire) throws SingletonConnectionException, ProprietaireException{
        return daoProprietaire.getUnProprietaire(identifiantProprietaire);
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

    public ArrayList<StatMedicament> getStatistiquesMedicaments(GregorianCalendar dateDebutZoneRecherche,
                                                                GregorianCalendar dateFinZoneRecherche)
            throws SingletonConnectionException, MedicamentException {

        HashMap<String, Double> statistiques = new HashMap<>();
        ArrayList<StatMedicament> listeResultatRechercheOrdonnances =
                daoMedicament.getMedicamentsEntreDeuxDates(dateDebutZoneRecherche, dateFinZoneRecherche);

        ArrayList<Medicament> listeMedicaments = daoMedicament.getMedicaments();
        ArrayList<StatMedicament> resultatStatistiques = new ArrayList<>();

        Integer compteurGlobal = 0;

        for (StatMedicament sm : listeResultatRechercheOrdonnances) {
            compteurGlobal++;
            instanciationHashMap(statistiques, sm);
        }

        for(Medicament med : listeMedicaments){
            instanciationListeMedicaments(statistiques, med);
        }

        for (String nomMedic : statistiques.keySet()) {
            instanciationResultatStatistiques(statistiques, compteurGlobal, nomMedic, resultatStatistiques);
        }
        return resultatStatistiques;
    }

    public void instanciationHashMap(HashMap<String, Double> statistiques, StatMedicament sm){
        if (!statistiques.containsKey(sm.getNomMedic())) {
            statistiques.put(sm.getNomMedic(), 1.);
        }
        else {
            statistiques.replace(sm.getNomMedic(), ((statistiques.get(sm.getNomMedic()))+1));
        }
    }

    public void instanciationListeMedicaments(HashMap<String, Double> statistiques, Medicament med){
        if(!statistiques.containsKey(med.getNomMedic())){
            statistiques.put(med.getNomMedic(), 0.);
        }
    }

    public void instanciationResultatStatistiques(HashMap<String, Double> statistiques, Integer compteurGlobal,
                                                  String nomMedic, ArrayList<StatMedicament> resultatStatistiques){
        StatMedicament stat = new StatMedicament();
        Double pourcentageParMedicament;
        if(compteurGlobal != 0) {
            Double base = statistiques.get(nomMedic);
            pourcentageParMedicament = calculPourcentageParMedicament(base, compteurGlobal);
        }
        else {
            pourcentageParMedicament = 0.;
        }
        stat.setNomMedic(nomMedic);
        stat.setPourcentage(pourcentageParMedicament);
        resultatStatistiques.add(stat);
    }

    public Double calculPourcentageParMedicament(Double base, Integer compteurGlobal)throws  IllegalArgumentException{
        if( compteurGlobal != 0)
            return  base / compteurGlobal;
        else
            throw  new IllegalArgumentException("La division par 0 est impossible !");
    }
}
