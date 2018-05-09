package modelPackage;

import java.util.GregorianCalendar;

public class Ordonnance {
    private SoinAvance soinAvance;
    private Integer numRegistre;
    private Medicament medicament;

    public Ordonnance(SoinAvance soinAvance, Integer numRegistre, Medicament medicament) {
        setSoinAvance(soinAvance);
        setNumRegistre(numRegistre);
        setMedicament(medicament);
    }

    public Ordonnance(){}

    public SoinAvance getSoinAvance() {
        return soinAvance;
    }

    public Integer getNumRegistre() {
        return numRegistre;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setSoinAvance(SoinAvance soinAvance) {
        this.soinAvance = soinAvance;
    }

    public void setNumRegistre(Integer numRegistre) { this.numRegistre = numRegistre; }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }
}
