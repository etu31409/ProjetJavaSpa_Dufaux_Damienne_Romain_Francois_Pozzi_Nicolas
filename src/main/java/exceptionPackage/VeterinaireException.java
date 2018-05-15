package exceptionPackage;

public class VeterinaireException extends Exception {
    String message;

    public VeterinaireException(){
        message = "Erreur lors de la récupération du vétérinaire.";
    }
    public VeterinaireException(String message){
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
