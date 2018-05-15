package businessPackage;

import dataAcessPackage.*;
import exceptionPackage.*;
import modelPackage.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Business {
    private IAnimal daoAnimal;
    private IMedicament daoMedicament;
    private IProprietaire daoProprietaire;
    private IVeterinaire daoVeterinaire;
    private ISoinAvance daoSoinAvance;
    private IOrdonnance daoOrdonnance;
    private Connection connectionUnique;

    public Business() {
        daoAnimal = new DBDAOAnimal();
        daoMedicament = new DBDAOMedicament();
        daoProprietaire = new DBDAOProprietaire();
        daoVeterinaire = new DBDAOVeterinaire();
        daoSoinAvance = new DBDAOSoinAvance();
        daoOrdonnance = new DBDAOOrdonnance();
    }

    //animaux
    public ArrayList<Animal>getAnimaux() throws AnimalException, ConnexionException {
        return daoAnimal.getAnimaux();
    }

    public ArrayList<AnimalProprietaire> getAnimauxTries(String critere) throws AnimalException,
            ConnexionException, ProprietaireException {
        return daoAnimal.getAnimauxTries(critere);
    }

    public Animal getUnAnimal(Integer numRegistre) throws ConnexionException, AnimalException{
        if(numRegistre <= 0)
            throw new AnimalException("Le numéro de registre doit être supérieur à 0");
        if(numRegistre > 2147483647)
            throw new AnimalException("Le numéro de registre ne peut être supérieur à 2 147 483 647");
        return daoAnimal.getUnAnimal(numRegistre);
    }

    public void ajouterAnimal(Animal animal) throws AnimalException, ConnexionException{
        daoAnimal.ajouterAnimal(animal);
    }

    public void supprimerAnimal(Integer animal) throws AnimalException, ConnexionException{
        daoAnimal.supprimerAnimal(animal);
    }

    public void modifierAnimal(Animal animal) throws  AnimalException, ConnexionException{
        daoAnimal.modifierAnimal(animal);
    }

    //medicaments
    public ArrayList<Medicament>getMedicaments() throws MedicamentException, ConnexionException {
        return daoMedicament.getMedicaments();
    }

    public ArrayList<Medicament> getMedicamentsDeLaFiche(Integer numSoin) throws  MedicamentException, ConnexionException{
        return daoMedicament.getMedicamentsDeLaFiche(numSoin);
    }

    public void ajouterMedicament(Medicament medicament) throws MedicamentException, ConnexionException{
        daoMedicament.ajouterMedicament(medicament);
    }

    //ordonnances
    public void supprimerOrdonnance(SoinAvance soin, Medicament medicament) throws OrdonnanceException, ConnexionException {
        daoOrdonnance.supprimerOrdonnance(soin, medicament);
    }

    public void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException, ConnexionException{
        daoOrdonnance.ajouterOrdonnance(ordonnance);
    }

    //soins
    public Integer ajouterFicheDeSoins(SoinAvance soinAvance) throws SoinException, ConnexionException{
        return daoSoinAvance.ajouterFicheDeSoins(soinAvance);
    }

    public SoinAvance getUnSoinAvance(Integer numSoin) throws SoinException, ConnexionException{
        if(numSoin <= 0)
            throw new SoinException("Le numéro de registre ne peut être plus petit que 0 !");
        if(numSoin > 2147483647)
            throw new SoinException("Le numéro de registre ne peut être plus grand que 2147483647 !");
        return daoSoinAvance.getUnSoinAvance(numSoin);
    }

    public ArrayList<SoinAnimalVeto> getSoinsTries(String critere) throws SoinException,
            ConnexionException, VeterinaireException, AnimalException {
        return daoSoinAvance.getSoinsTries(critere);
    }

    public void supprimerSoin(Integer soin) throws SoinException, ConnexionException {
        daoSoinAvance.supprimerSoin(soin);
    }

    public void modifierSoin(SoinAvance soin) throws SoinException, ConnexionException {
        daoSoinAvance.modifierSoin(soin);
    }

    //veterinaires
    public ArrayList<Veterinaire> getVeterinaires() throws VeterinaireException, ConnexionException {
        return daoVeterinaire.getVeterinaires();
    }

    public Veterinaire getUnVeterinaire(Integer identifiantVeto) throws VeterinaireException, ConnexionException{
        return daoVeterinaire.getUnVeterinaire(identifiantVeto);
    }

    //proprietaires
    public ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, ConnexionException{
        return daoProprietaire.getProprietaires();
    }

    public Proprietaire getUnProprietaire(Integer identifiantProprietaire)
            throws ConnexionException, ProprietaireException{
        return daoProprietaire.getUnProprietaire(identifiantProprietaire);
    }

    public void ajouterNouveauProprio(Proprietaire proprietaire)throws ConnexionException, ProprietaireException {
        daoProprietaire.ajouterNouveauProprio(proprietaire);
    }

    //recherches
    public ArrayList<ProprietaireAnimal> getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws ConnexionException, ProprietaireException {
        return daoProprietaire.getResultatRechercheProprietaire(selectionVeterinaire);
    }

    public ArrayList<VeterinaireOrdonnance> getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut,
                    GregorianCalendar dateFin) throws ConnexionException, VeterinaireException {
        return daoVeterinaire.getResultatRechercheVeterinaireDate(dateDebut, dateFin);
    }

    public ArrayList<Animal> getResultatRecherchAnimauxVeterinaire(Veterinaire selectionVeterinaire) throws AnimalException,
            ConnexionException{
        return daoAnimal.getResultatRecherchAnimauxVeterinaire(selectionVeterinaire);
    }

    public ArrayList<Animal> getResultatRecherchAnimauxMedicamentVeto(Medicament selectionMedicament, Veterinaire selectionVeterinaire)
            throws AnimalException, ConnexionException{
        return daoAnimal.getResultatRecherchAnimauxMedicamentVeto(selectionMedicament, selectionVeterinaire);
    }

    public ArrayList<Animal> getResultatRecherchAnimauxMedicament(Medicament selectionMedicament) throws AnimalException,
            ConnexionException{
        return daoAnimal.getResultatRecherchAnimauxMedicament(selectionMedicament);
    }

    //tache metier
    public ArrayList<StatMedicament> getStatistiquesMedicaments(GregorianCalendar dateDebutZoneRecherche,
                                                                GregorianCalendar dateFinZoneRecherche)
            throws ConnexionException, MedicamentException {

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

    //fermer connexion
    public void closeBaseDeDonnees() throws ConnexionException{
        try{
            if(connectionUnique == null){
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            connectionUnique.close();
        }catch (ConnexionException e) {
            throw new ConnexionException("Erreur lors de la fermeture de la connexion !");
        }
        catch(SQLException e){
            throw new ConnexionException("Erreur lors de la fermeture de la connexion !");
        }
    }
}
