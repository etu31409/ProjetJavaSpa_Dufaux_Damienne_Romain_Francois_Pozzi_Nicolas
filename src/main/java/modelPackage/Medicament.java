package modelPackage;

import java.util.GregorianCalendar;

public class Medicament {
    private Integer identifiantMed;
    private GregorianCalendar datePreparation;
    private String stockage;
    private String dosage;
    private String nomMedic;

    public Medicament(){}
    public Medicament(GregorianCalendar datePreparation, String stockage, String dosage,
                      String nomMedic) {
        this.datePreparation = datePreparation;
        this.stockage = stockage;
        this.dosage = dosage;
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

    public String getDosage() {
        return dosage;
    }

    public String getNomMedic() {
        return nomMedic;
    }

    public void setDatePreparation(GregorianCalendar datePreparation) {
        this.datePreparation = datePreparation;
    }

    public void setStockage(String stockage) {
        this.stockage = stockage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setNomMedic(String nomMedic) {
        this.nomMedic = nomMedic;
    }

    public void setIdentifiantMed(Integer identifiantMed) {
        this.identifiantMed = identifiantMed;
    }

    public String toString (){
       return " #" + this.getIdentifiantMed() + " " + this.getNomMedic();
    }
}
