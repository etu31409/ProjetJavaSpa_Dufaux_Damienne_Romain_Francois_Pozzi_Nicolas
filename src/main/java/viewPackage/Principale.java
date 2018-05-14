package viewPackage;

import exceptionPackage.ConnexionException;

import javax.swing.*;

public class Principale {
    public static void main(String args[])throws  ConnexionException, ConnexionException
    {
        try{
            FenetrePrincipale fenetrePrincipale = new FenetrePrincipale();
        }catch (ConnexionException s) {
            JOptionPane.showMessageDialog(null, s.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
