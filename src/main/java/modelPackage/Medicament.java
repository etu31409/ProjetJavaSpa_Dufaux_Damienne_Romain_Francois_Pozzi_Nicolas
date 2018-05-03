package modelPackage;

import java.util.GregorianCalendar;

public class Medicament {
    private Integer identifiantMed;
    private String stockage;
    private String dosage;
    private String nomMedic;

    public Medicament(){}
    public Medicament(String stockage, String dosage,
                      String nomMedic) {
        this.stockage = stockage;
        this.dosage = dosage;
        this.nomMedic = nomMedic;
    }

    public Integer getIdentifiantMed() {
        return identifiantMed;
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
