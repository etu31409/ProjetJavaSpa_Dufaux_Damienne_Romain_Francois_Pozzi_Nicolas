package modelPackage;

import java.util.GregorianCalendar;

public class Ordonnance {
    private SoinAvance ficheDeSoins;
    private Animal animal;
    private Medicament medicament;
    private GregorianCalendar dateOrdonnance;

    public Ordonnance(SoinAvance ficheDeSoins, Animal animal, Medicament medicament, GregorianCalendar dateOrdonnance) {
        this.ficheDeSoins = ficheDeSoins;
        this.animal = animal;
        this.medicament = medicament;
        this.dateOrdonnance = dateOrdonnance;
    }
    
    public Ordonnance(){

    }

    public SoinAvance getFicheDeSoins() {
        return ficheDeSoins;
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

    public void setFicheDeSoins(SoinAvance ficheDeSoins) {
        this.ficheDeSoins = ficheDeSoins;
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
