package modelPackage;

import java.util.GregorianCalendar;

public class VeterinaireOrdonnance {

    private Integer identifiantVeto;
    private String nomVeto;
    private String prenomVeto;
    private GregorianCalendar dateOrdonnance;

    public VeterinaireOrdonnance(){}

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

    public String getPrenomVeto() {
        return prenomVeto;
    }

    public void setPrenomVeto(String prenomVeto) {
        this.prenomVeto = prenomVeto;
    }
}
