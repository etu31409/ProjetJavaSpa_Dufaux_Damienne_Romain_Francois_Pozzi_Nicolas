package modelPackage;

import java.util.GregorianCalendar;

public class Medicament {
    private Integer identifiantMed;
    private GregorianCalendar datePreparation;
    private String stockage;
    private String posologie;
    private String nomMedic;

    public Medicament(){}
    public Medicament(Integer identifiantMed, GregorianCalendar datePreparation, String stockage, String posologie,
                      String nomMedic) {
        this.identifiantMed = identifiantMed;
        this.datePreparation = datePreparation;
        this.stockage = stockage;
        this.posologie = posologie;
        this.nomMedic = nomMedic;
    }

    public Integer getIdentifiantMed() {
        return identifiantMed;
    }

    public GregorianCalendar getDatePreparation() {
        return datePreparation;
    }

    public String getStockage() {
        return stockage;
    }

    public String getPosologie() {
        return posologie;
    }

    public String getNomMedic() {
        return nomMedic;
    }

    public void setIdentifiantMed(Integer identifiantMed) {
        this.identifiantMed = identifiantMed;
    }

    public void setDatePreparation(GregorianCalendar datePreparation) {
        this.datePreparation = datePreparation;
    }

    public void setStockage(String stockage) {
        this.stockage = stockage;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }

    public void setNomMedic(String nomMedic) {
        this.nomMedic = nomMedic;
    }
}
