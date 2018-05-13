package modelPackage;

public class Ordonnance {
    private Integer soinAvance;
    private Integer numRegistre;
    private Integer medicament;

    public Ordonnance(){}

    public Integer getSoinAvance() {
        return soinAvance;
    }

    public Integer getNumRegistre() {
        return numRegistre;
    }

    public Integer getMedicament() {
        return medicament;
    }

    public void setSoinAvance(Integer soinAvance) {
        this.soinAvance = soinAvance;
    }

    public void setNumRegistre(Integer numRegistre) { this.numRegistre = numRegistre; }

    public void setMedicament(Integer medicament) {
        this.medicament = medicament;
    }
}
