package exceptionPackage;

public class MedicamentException extends Exception  {
    String message;

    public MedicamentException(){

        message = "Erreur lors de la récuperation du médicament.";

    }
}
