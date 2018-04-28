package exceptionPackage;

public class SoinException extends Exception {
    String message;

    public SoinException(){

        message = "Erreur lors de la récuperation du soin.";

    }
}
