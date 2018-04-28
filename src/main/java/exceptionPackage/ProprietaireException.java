package exceptionPackage;

public class ProprietaireException extends Exception {
    String message;

    public ProprietaireException(){

        message = "Erreur lors de la récuperation du propriétaire.";

    }
}
