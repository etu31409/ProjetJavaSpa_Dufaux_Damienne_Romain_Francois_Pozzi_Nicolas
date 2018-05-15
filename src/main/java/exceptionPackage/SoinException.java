package exceptionPackage;

public class SoinException extends Exception {
    String message;

    public SoinException(){
        message = "Erreur lors de la récupération du soin.";
    }

    public SoinException(String message){
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

}
