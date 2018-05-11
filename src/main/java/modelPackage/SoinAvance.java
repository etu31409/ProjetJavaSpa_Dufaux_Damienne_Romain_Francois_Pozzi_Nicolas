package modelPackage;

import java.util.GregorianCalendar;

public class SoinAvance {
    private Integer numSoin;
    private Integer numRegistre;
    private String intitule;
    private String partieDuCorps;
    private GregorianCalendar dateSoin;
    private Integer veterinaire;
    private Boolean estUrgent;
    private String remarque;

    public SoinAvance(){}

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
}
