package modelPackage.modelJointure;

import java.util.GregorianCalendar;

public class VeterinaireSoinAvanceOrdonnance {
    Integer idVeterinaire;
    String nomVeterinaire;
    GregorianCalendar dateOrdonnance;

    public VeterinaireSoinAvanceOrdonnance(){}

    public VeterinaireSoinAvanceOrdonnance(Integer idVeterinaire, String nomVeterinaire, GregorianCalendar dateOrdonnance) {
        this.idVeterinaire = idVeterinaire;
        this.nomVeterinaire = nomVeterinaire;
        this.dateOrdonnance = dateOrdonnance;
    }

    public Integer getIdVeterinaire() {
        return idVeterinaire;
    }

    public void setIdVeterinaire(Integer idVeterinaire) {
        this.idVeterinaire = idVeterinaire;
    }

    public String getNomVeterinaire() {
        return nomVeterinaire;
    }

    public void setNomVeterinaire(String nomVeterinaire) {
        this.nomVeterinaire = nomVeterinaire;
    }

    public GregorianCalendar getDateOrdonnance() {
        return dateOrdonnance;
    }

    public void setDateOrdonnance(GregorianCalendar dateOrdonnance) {
        this.dateOrdonnance = dateOrdonnance;
    }
}
