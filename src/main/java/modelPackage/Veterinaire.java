package modelPackage;

public class Veterinaire {
    private Integer identifiantVeto;
    private String nom;
    private String prenom;

    public Veterinaire(){}
    public Veterinaire(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Integer getIdentifiantVeto() {
        return identifiantVeto;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setIdentifiantVeto(Integer identifiantVeto) {
        this.identifiantVeto = identifiantVeto;
    }

    public String toString (){
        return " #" + this.getIdentifiantVeto() + " " + this.getPrenom() + " " + this.getNom();
    }
}
