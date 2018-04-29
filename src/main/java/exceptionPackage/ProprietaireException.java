package exceptionPackage;

public class ProprietaireException extends Exception {
    String message;

    public ProprietaireException(){

        message = "Erreur lors de la récuperation du propriétaire.";

    }

    public ProprietaireException(String message){

        this.message = message;

    }

    public String getMessage()
    {
        return message;
    }
}
