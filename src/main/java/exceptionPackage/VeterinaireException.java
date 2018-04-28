package exceptionPackage;

public class VeterinaireException extends Exception {
    String message;

    public VeterinaireException(){

        message = "Erreur lors de la récuperation du vétérinaire.";

    }

    public String getMessage()
    {
        return message;
    }
}
