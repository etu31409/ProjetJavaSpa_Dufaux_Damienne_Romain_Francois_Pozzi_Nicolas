package modelPackage;

import java.util.GregorianCalendar;

public class Ordonnance {
    private FicheDeSoins ficheDeSoins;
    private Animal animal;
    private Medicament medicament;
    private GregorianCalendar dateOrdonnance;

    public Ordonnance(FicheDeSoins ficheDeSoins, Animal animal, Medicament medicament, GregorianCalendar dateOrdonnance) {
        this.ficheDeSoins = ficheDeSoins;
        this.animal = animal;
        this.medicament = medicament;
        this.dateOrdonnance = dateOrdonnance;
    }

    public FicheDeSoins getFicheDeSoins() {
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

    public void setFicheDeSoins(FicheDeSoins ficheDeSoins) {
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
