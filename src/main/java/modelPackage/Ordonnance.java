package modelPackage;

import java.util.GregorianCalendar;

public class Ordonnance {
    private SoinAvance soinAvance;
    private Animal animal;
    private Medicament medicament;

    public Ordonnance(SoinAvance soinAvance, Animal animal, Medicament medicament) {
        setSoinAvance(soinAvance);
        setAnimal(animal);
        setMedicament(medicament);
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

    public void setSoinAvance(SoinAvance soinAvance) {
        this.soinAvance = soinAvance;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }
}
