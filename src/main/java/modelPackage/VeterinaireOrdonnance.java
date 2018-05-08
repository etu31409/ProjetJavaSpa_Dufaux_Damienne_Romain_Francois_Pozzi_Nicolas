package modelPackage;

import java.util.GregorianCalendar;

public class VeterinaireOrdonnance {
    //pabd.veterinaire.identifiantVeto, spabd.veterinaire.nom, " +
    //                    "spabd.ordonnance.dateOrdonnance

    private Integer identifiantVeto;
    private String nomVeto;
    private GregorianCalendar dateOrdonnance;

    public VeterinaireOrdonnance(){}
    public VeterinaireOrdonnance(Integer identifiantVeto, String nomVeto, GregorianCalendar dateOrdonnance) {
        setIdentifiantVeto(identifiantVeto);
        setNomVeto(nomVeto);
        setDateOrdonnance(dateOrdonnance);
    }

    public Integer getIdentifiantVeto() {
        return identifiantVeto;
    }

    public void setIdentifiantVeto(Integer identifiantVeto) {
        this.identifiantVeto = identifiantVeto;
    }

    public String getNomVeto() {
        return nomVeto;
    }

    public void setNomVeto(String nomVeto) {
        this.nomVeto = nomVeto;
    }

    public GregorianCalendar getDateOrdonnance() {
        return dateOrdonnance;
    }

    public void setDateOrdonnance(GregorianCalendar dateOrdonnance) {
        this.dateOrdonnance = dateOrdonnance;
    }
}
