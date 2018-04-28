package modelPackage;

import java.util.GregorianCalendar;

public class FicheDeSoins {
    private Integer numRegistre;
    private String intitule;
    private String partieDuCorps;
    private GregorianCalendar dateSoin;
    private GregorianCalendar heure;
    private Proprietaire veterinaire;
    private Boolean estUrgent;
    private String remarque;

    public FicheDeSoins(Integer numRegistre, String intitule, String partieDuCorps, GregorianCalendar dateSoin,
                        GregorianCalendar heure, Proprietaire veterinaire, Boolean estUrgent, String remarque) {
        this.numRegistre = numRegistre;
        this.intitule = intitule;
        this.partieDuCorps = partieDuCorps;
        this.dateSoin = dateSoin;
        this.heure = heure;
        this.veterinaire = veterinaire;
        this.estUrgent = estUrgent;
        this.remarque = remarque;
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

    public GregorianCalendar getHeure() {
        return heure;
    }

    public Proprietaire getVeterinaire() {
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

    public void setHeure(GregorianCalendar heure) {
        this.heure = heure;
    }

    public void setVeterinaire(Proprietaire veterinaire) {
        this.veterinaire = veterinaire;
    }

    public void setEstUrgent(Boolean estUrgent) {
        this.estUrgent = estUrgent;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
}
