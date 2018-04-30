package modelPackage;

import exceptionPackage.AnimalException;

import java.util.GregorianCalendar;

public class Animal {
    private Integer numRegistre;
    private String nom;
    private GregorianCalendar dateArrivee;
    private GregorianCalendar dateDepart;
    private String espece;
    private String race;
    private String sexe;
    private boolean estSterilise;
    private String couleurDePeau;
    private GregorianCalendar dateNaissance;
    private Integer numPuce;
    private String localisationPuce;
    private GregorianCalendar dateAttributionPuce;
    private Double numTatouage;
    private String localisationTatouage;
    private Double poids;
    private Proprietaire proprietaire;

    public Animal(){}

    public Animal(Integer numRegistre, String nom, GregorianCalendar dateArrivee, GregorianCalendar dateDepart,
                  String espece, String race, String sexe, boolean estSterilise, String couleurDePeau,
                  GregorianCalendar dateNaissance, Integer numPuce, String localisationPuce,
                  GregorianCalendar dateAttributionPuce, Double numTatouage, String localisationTatouage, Double poids,
                  Proprietaire proprietaire)  {
        setNumRegistre(numRegistre);
        setNom(nom);
        setDateArrivee(dateArrivee);
        setDateDepart(dateDepart);
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

    public void setNumRegistre(Integer numRegistre) {
        this.numRegistre = numRegistre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateArrivee(GregorianCalendar dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public void setDateDepart(GregorianCalendar dateDepart) {
        this.dateDepart = dateDepart;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setEstSterilise(boolean estSterilise) {
        this.estSterilise = estSterilise;
    }

    public void setCouleurDePeau(String couleurDePeau) {
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

    public void setNumTatouage(Double numTatouage) {
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

    public Integer getNumRegistre() {
        return numRegistre;
    }

    public GregorianCalendar getDateArrivee() {
        return dateArrivee;
    }

    public GregorianCalendar getDateDepart() {
        return dateDepart;
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

    public Double getNumTatouage() {
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
        return " #" + this.getNumRegistre() + " " + this.getNom() + " " + this.getEspece() + " " + this.getRace();
    }
}
