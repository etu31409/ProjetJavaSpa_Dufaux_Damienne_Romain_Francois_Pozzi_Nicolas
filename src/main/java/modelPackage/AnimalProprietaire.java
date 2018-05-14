package modelPackage;

import java.util.GregorianCalendar;

public class AnimalProprietaire {
    private Integer numRegistre;
    private String nom;
    private GregorianCalendar dateArrivee;
    private String espece;
    private String race;
    private String sexe;
    private Boolean estSterilise;
    private String couleurDePeau;
    private GregorianCalendar dateNaissance;
    private Integer numPuce;
    private String localisationPuce;
    private GregorianCalendar dateAttributionPuce;
    private Integer numTatouage;
    private String localisationTatouage;
    private Double poids;
    private String nomProprio;
    private String prenomProprio;

    public AnimalProprietaire(){}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public Boolean isEstSterilise() {
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

    public void setDateArrivee(GregorianCalendar dateArrivee) {
        this.dateArrivee = dateArrivee;
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

    public Boolean getEstSterilise() {
        return estSterilise;
    }

    public void setEstSterilise(Boolean estSterilise) {
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

    public void setNumTatouage(Integer numTatouage) {
        this.numTatouage = numTatouage;
    }

    public void setLocalisationTatouage(String localisationTatouage) {
        this.localisationTatouage = localisationTatouage;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public void setNomProprio(String nomProprio) {
        this.nomProprio = nomProprio;
    }

    public void setPrenomProprio(String prenomProprio) {
        this.prenomProprio = prenomProprio;
    }

    public String getDescriptionProprietaire(){
        if(prenomProprio != null && nomProprio != null)
            return prenomProprio + " " + nomProprio;
        return "";
    }
}
