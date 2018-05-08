package modelPackage;

import exceptionPackage.AnimalException;

import java.util.GregorianCalendar;

public class Animal {
    private Integer numRegistre;

    private String nom;
    private GregorianCalendar dateArrivee;
    private String espece;
    private String race;
    private String sexe;
    private boolean estSterilise;
    private String couleurDePeau;
    private GregorianCalendar dateNaissance;
    private Integer numPuce;
    private String localisationPuce;
    private GregorianCalendar dateAttributionPuce;
    private Integer numTatouage;
    private String localisationTatouage;
    private Double poids;
    private Proprietaire proprietaire;

    public Animal(){}

    public Animal(String nom, GregorianCalendar dateArrivee,
                  String espece, String race, String sexe, boolean estSterilise, String couleurDePeau,
                  GregorianCalendar dateNaissance, Integer numPuce, String localisationPuce,
                  GregorianCalendar dateAttributionPuce, Integer numTatouage, String localisationTatouage, Double poids,
                  Proprietaire proprietaire) throws AnimalException {
        setNom(nom);
        setDateArrivee(dateArrivee);
        setEspece(espece);
        setRace(race);
        setSexe(sexe);
        setEstSterilise(estSterilise);
        setCouleurDePeau(couleurDePeau);
        setDateNaissance(dateNaissance);
        setNumPuce(numPuce);
        setLocalisationPuce(localisationPuce);
        setDateAttributionPuce(dateAttributionPuce);
        setNumTatouage(numTatouage);
        setLocalisationTatouage(localisationTatouage);
        setPoids(poids);
        setProprietaire(proprietaire);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateArrivee(GregorianCalendar dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public void setEspece(String espece) throws AnimalException{
        if(espece.isEmpty())
            throw new AnimalException("Champ espece vide !");
        this.espece = espece;
    }

    public void setRace(String race) throws AnimalException {
        if(race.isEmpty()){
            throw new AnimalException("Champ race vide");
        }
        this.race = race;
    }

    public void setSexe(String sexe) throws AnimalException {
        if(sexe.isEmpty())
            throw new AnimalException("Champ sexe vide");
        this.sexe = sexe;
    }

    public void setEstSterilise(boolean estSterilise) {
        this.estSterilise = estSterilise;
    }

    public void setCouleurDePeau(String couleurDePeau)throws AnimalException {
        if(couleurDePeau.isEmpty())
            throw new AnimalException("Champ couleur de peau vide");
        this.couleurDePeau = couleurDePeau;
    }

    public void setDateNaissance(GregorianCalendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setNumPuce(Integer numPuce) {
        this.numPuce = numPuce;
    }

    public void setLocalisationPuce(String localisationPuce) {
        this.localisationPuce = localisationPuce;
    }

    public void setDateAttributionPuce(GregorianCalendar dateAttributionPuce) {
        this.dateAttributionPuce = dateAttributionPuce;
    }

    public void setNumTatouage(Integer numTatouage) {
        this.numTatouage = numTatouage;
    }

    public void setLocalisationTatouage(String localisationTatouage) {
        this.localisationTatouage = localisationTatouage;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getNom() {
        if(nom == null || nom.isEmpty())
            return "'Inconnu'";
        else
            return nom;
    }

    public void setNumRegistre(Integer numRegistre) {
        this.numRegistre = numRegistre;
    }

    public Integer getNumRegistre() {
        return numRegistre;
    }

    public GregorianCalendar getDateArrivee() {
        return dateArrivee;
    }

    public String getEspece() {
        return espece;
    }

    public String getRace() {
        return race;
    }

    public String getSexe() {
        return sexe;
    }

    public boolean isEstSterilise() {
        return estSterilise;
    }

    public String getCouleurDePeau() {
        return couleurDePeau;
    }

    public GregorianCalendar getDateNaissance() {
        return dateNaissance;
    }

    public Integer getNumPuce() {
        return numPuce;
    }

    public String getLocalisationPuce() {
        return localisationPuce;
    }

    public GregorianCalendar getDateAttributionPuce() {
        return dateAttributionPuce;
    }

    public Integer getNumTatouage() {
        return numTatouage;
    }

    public String getLocalisationTatouage() {
        return localisationTatouage;
    }

    public Double getPoids() {
        return poids;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public String toString(){
        return " #" + this.getNumRegistre() + " " + this.getNom() + " (" + this.getEspece() + " " + this.getRace() +")";
    }
}
