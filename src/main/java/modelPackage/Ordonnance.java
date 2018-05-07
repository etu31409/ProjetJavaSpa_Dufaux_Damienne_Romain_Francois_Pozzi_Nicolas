package modelPackage;

import java.util.GregorianCalendar;

public class Ordonnance {
    private SoinAvance soinAvance;
    private Animal animal;
    private Medicament medicament;
    private GregorianCalendar dateOrdonnance;

    public Ordonnance(SoinAvance soinAvance, Animal animal, Medicament medicament, GregorianCalendar dateOrdonnance) {
        this.soinAvance = soinAvance;
        this.animal = animal;
        this.medicament = medicament;
        this.dateOrdonnance = dateOrdonnance;
    }

    public Ordonnance(){}

    public SoinAvance getSoinAvance() {
        return soinAvance;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public GregorianCalendar getDateOrdonnance() {
        return dateOrdonnance;
    }

    public void setSoinAvance(SoinAvance soinAvance) {
        this.soinAvance = soinAvance;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public void setDateOrdonnance(GregorianCalendar dateOrdonnance) {
        this.dateOrdonnance = dateOrdonnance;
    }
}
