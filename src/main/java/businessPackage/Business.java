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

    //medicaments
    public ArrayList<Medicament>getMedicaments() throws MedicamentException, SingletonConnectionException {
        return daoMedicament.getMedicaments();
    }

    public void ajouterMedicament(Medicament medicament) throws MedicamentException, SingletonConnectionException{
        daoMedicament.ajouterMedicament(medicament);
    }

    //ordonnances
    public void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException, SingletonConnectionException{
        daoOrdonnance.ajouterOrdonnance(ordonnance);
    }

    //soins
    public void ajouterFicheDeSoins(SoinAvance soinAvance) throws SoinException, SingletonConnectionException{
        daoSoinAvance.ajouterFicheDeSoins(soinAvance);
    }

    public ArrayList<SoinAvance> getSoinsTries(String critere) throws SoinException, SingletonConnectionException, VeterinaireException{
        return daoSoinAvance.getSoinsTries(critere);
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

        ArrayList<StatMedicament> resultatStatistiques = new ArrayList<>();
        HashMap<String, Double> statistiques = new HashMap<>();
        Double compteurParMedicament;
        Double pourcentageParMedicament;
        Integer compteurGlobal = 0;

        for (StatMedicament sm : listeResultatRechercheOrdonnances) {
            compteurGlobal++;
            if (!statistiques.containsKey(sm.getNomMedic())) {
                statistiques.put(sm.getNomMedic(), 1.);
            }
            else {
                compteurParMedicament = (statistiques.get(sm.getNomMedic()))+1;
                statistiques.replace(sm.getNomMedic(), compteurParMedicament);
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
