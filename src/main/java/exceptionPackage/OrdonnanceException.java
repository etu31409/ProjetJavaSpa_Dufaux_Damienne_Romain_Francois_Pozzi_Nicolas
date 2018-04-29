package exceptionPackage;

public class OrdonnanceException extends Exception  {
    String message;

    public OrdonnanceException(){

        message = "Erreur lors de la récuperation de l'ordonnance.";
    }

    public String getMessage()
    {
        return message;
    }
}