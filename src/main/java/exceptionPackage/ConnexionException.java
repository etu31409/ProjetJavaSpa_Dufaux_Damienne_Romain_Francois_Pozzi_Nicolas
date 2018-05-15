package exceptionPackage;

public class ConnexionException extends Exception {
    String message;

    public ConnexionException(){
        message = "Erreur lors de l'accès à la connexion.";
    }

    public ConnexionException(String message){
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
