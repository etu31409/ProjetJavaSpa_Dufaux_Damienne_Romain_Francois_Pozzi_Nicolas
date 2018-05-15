package exceptionPackage;

public class AnimalException extends Exception{

    String message;

    public AnimalException(){
            message = "Erreur lors de la récuperation de l'animal.";
    }

    public AnimalException(String message){
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}

