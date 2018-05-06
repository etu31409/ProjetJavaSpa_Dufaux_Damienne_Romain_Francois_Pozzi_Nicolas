package modelPackage;

import exceptionPackage.MedicamentException;

public class Medicament {
    private Integer identifiantMed;
    private String stockage;
    private String dosage;
    private String nomMedic;

    public Medicament(){}
    public Medicament(String stockage, String dosage,String nomMedic) throws MedicamentException {
        setStockage(stockage);
        setDosage(dosage);
        setNomMedic(nomMedic);
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

    public void setStockage(String stockage) throws MedicamentException {
        if (stockage.isEmpty())
            throw new MedicamentException("Le nom du stockage du medicament est incorrect");
        this.stockage = stockage;
    }

    public void setDosage(String dosage) throws MedicamentException {
        if (dosage.isEmpty())
            throw new MedicamentException("Le nom du dosage du medicament est incorrect");
        this.dosage = dosage;
    }

    public void setNomMedic(String nomMedic) throws MedicamentException {
        if (nomMedic.isEmpty())
            throw new MedicamentException("Le nom du medicament est incorrect");
        this.nomMedic = nomMedic;
    }

    public void setIdentifiantMed(Integer identifiantMed) throws MedicamentException {
        if (identifiantMed == null)
            throw new MedicamentException("L'identifiant du medicament est incorrect");
        this.identifiantMed = identifiantMed;

    }

    public String toString (){
       return this.getNomMedic() + " " + this.getStockage() + " " +
         this.getDosage();
    }
}
