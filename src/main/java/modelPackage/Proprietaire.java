package modelPackage;

import exceptionPackage.ProprietaireException;

public class Proprietaire {
    private int identifiantProprio;
    private String nom;
    private String prenom;

    public Proprietaire(){}
    public Proprietaire(String nom, String prenom) throws ProprietaireException{
        setNom(nom);
        setPrenom(prenom);
    }

    public int getIdentifiantProprio() {
        return identifiantProprio;
    }

    public String getNom(){
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setIdentifiantProprio(int identifiantProprio) {
        this.identifiantProprio = identifiantProprio;
    }

    public void setNom(String nom)throws ProprietaireException {
        if(nom.isEmpty()){
            throw new ProprietaireException();
        }
        else{
            this.nom = nom;
        }
    }

    public void setPrenom(String prenom)throws ProprietaireException {
        if(nom.isEmpty()){
            throw new ProprietaireException();
        }
        else{
            this.prenom = prenom;
        }
    }

    public String toString(){
        return " #" + this.getIdentifiantProprio() + " " + this.getNom() + " " + this.getPrenom();
    }
}
