package modelPackage;

import java.util.GregorianCalendar;

public class SoinAnimalVeto {
    private Integer numSoin;
    private Integer numRegistre;
    private String intitule;
    private String partieDuCorps;
    private GregorianCalendar dateSoin;
    private Integer veterinaire;
    private Boolean estUrgent;
    private String remarque;
    private String nomVeto;
    private String prenomVeto;
    private String nomAnimal;
    private String espece;

    public SoinAnimalVeto(){}

    public Integer getNumSoin() {
        return numSoin;
    }

    public void setNumSoin(Integer numSoin) {
        this.numSoin = numSoin;
    }

    public Integer getNumRegistre() {
        return numRegistre;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getPartieDuCorps() {
        return partieDuCorps;
    }

    public GregorianCalendar getDateSoin() {
        return dateSoin;
    }

    public Integer getVeterinaire() {
        return veterinaire;
    }

    public Boolean getEstUrgent() {
        return estUrgent;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setNumRegistre(Integer numRegistre) {
        this.numRegistre = numRegistre;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setPartieDuCorps(String partieDuCorps) {
        this.partieDuCorps = partieDuCorps;
    }

    public void setDateSoin(GregorianCalendar dateSoin) {
        this.dateSoin = dateSoin;
    }

    public void setVeterinaire(Integer veterinaire) {
        this.veterinaire = veterinaire;
    }

    public void setEstUrgent(Boolean estUrgent) {
        this.estUrgent = estUrgent;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getNomVeto() {
        return nomVeto;
    }

    public void setNomVeto(String nomVeto) {
        this.nomVeto = nomVeto;
    }

    public String getPrenomVeto() {
        return prenomVeto;
    }

    public void setPrenomVeto(String prenomVeto) {
        this.prenomVeto = prenomVeto;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public String getDescriptionAnimal(){
        if(nomAnimal != null && espece != null)
            return nomAnimal + " " + espece;
        return "";
    }

    public String getDescriptionVeterinaire(){
        return prenomVeto + " " + nomVeto;
    }
}


