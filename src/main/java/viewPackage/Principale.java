package viewPackage;

import exceptionPackage.ConnexionException;
import exceptionPackage.SingletonConnectionException;

import javax.swing.*;

public class Principale {
    public static void main(String args[])throws  SingletonConnectionException, ConnexionException
    {
        try{
            FenetrePrincipale fenetrePrincipale = new FenetrePrincipale();
        }catch (SingletonConnectionException s) {
            JOptionPane.showMessageDialog(null, s.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
        catch(ConnexionException s){
            JOptionPane.showMessageDialog(null, s.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
