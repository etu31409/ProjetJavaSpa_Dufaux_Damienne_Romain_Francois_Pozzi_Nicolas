package exceptionPackage;

public class OrdonnanceException extends Exception  {
    String message;

    public OrdonnanceException(){

        message = "Erreur lors de la récuperation de l'ordonnance.";
    }

    public OrdonnanceException(String message){
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}