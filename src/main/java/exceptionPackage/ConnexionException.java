package exceptionPackage;

public class ConnexionException extends Exception {
    String message;

    public ConnexionException(){

        message = "Erreur lors de l'acces à la connexion.";

    }

    public ConnexionException(String message){
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
