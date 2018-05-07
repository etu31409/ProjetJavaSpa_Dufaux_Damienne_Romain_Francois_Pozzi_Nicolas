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

    public void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException, SingletonConnectionException{
        daoOrdonnance.ajouterOrdonnance(ordonnance);
    }

    public void ajouterMedicament(Medicament medicament) throws MedicamentException, SingletonConnectionException{
        daoMedicament.ajouterMedicament(medicament);
    }


    //tache metier

    public String[][] getStatistiquesMedicaments(GregorianCalendar dateDebutZoneRecherche, GregorianCalendar dateFinZoneRecherche)
            throws SingletonConnectionException, MedicamentException {

        ArrayList<StatMedicament> listeResultatRechercheOrdonnances = daoMedicament.getMedicamentsEntreDeuxDates(dateDebutZoneRecherche,
                dateFinZoneRecherche);

        HashMap<String, Double> statistiques = new HashMap<>();
        Double compteurParMedicament = 1.;
        Double pourcentageParMedicament;
        Integer compteurGlobal = 0;

        for (StatMedicament sm : listeResultatRechercheOrdonnances) {
            compteurGlobal++;
            if (!statistiques.containsKey(sm.getNomMedic())) {
                statistiques.put(sm.getNomMedic(), 1.);
            }
            else {
                Double test;
                test = statistiques.get(sm.getNomMedic());
                compteurParMedicament = statistiques.get(sm.getNomMedic())+1;
                statistiques.replace(sm.getNomMedic(), compteurParMedicament);
            }
        }

        for (String nomMedic : statistiques.keySet()) {
            for (StatMedicament statMedicament : listeResultatRechercheOrdonnances) {
                if (statMedicament.getNomMedic().equals(nomMedic)) {
                    pourcentageParMedicament = statistiques.get(nomMedic) / compteurGlobal;
                    statistiques.replace(nomMedic, pourcentageParMedicament);
                }
            }
        }

        String[][] resultatStatistiques = new String[statistiques.size()][2];

        int i = 0;
        for(String nomMedic : statistiques.keySet()){
            resultatStatistiques[i][0] = nomMedic;
            i++;
        }
        String resultat;
        i = 0;
        for(Double pourcentage : statistiques.values()){
            resultat = Double.toString(pourcentage*100);
            resultatStatistiques[i][1] = resultat + " %";
            i++;
        }
        //TODO
        return resultatStatistiques;
    }
}
