package exceptionPackage;

public class SingletonConnectionException extends Exception{

    private String message;

    public SingletonConnectionException()
    {
        message = "Erreur lors de la récuperation du singleton. Il semblerait que la connexion à la bd pose problème!";
    }

    public String getMessage()
    {
        return message;
    }
}
