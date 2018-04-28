package modelPackage;

public class Proprietaire {
    private int identifiantProprio;
    private String nom;
    private String prenom;
    private String rue;
    private String numero;
    private String localite;
    private Integer codePostal;
    private String pays;

    public Proprietaire(int identifiantProprio, String nom, String prenom, String rue, String numero, String localite,
                      Integer codePostal, String pays) {
        this.identifiantProprio = identifiantProprio;
        this.nom = nom;
        this.prenom = prenom;
        this.rue = rue;
        this.numero = numero;
        this.localite = localite;
        this.codePostal = codePostal;
        this.pays = pays;
    }

    public int getIdentifiantProprio() {
        return identifiantProprio;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getRue() {
        return rue;
    }

    public String getNumero() {
        return numero;
    }

    public String getLocalite() {
        return localite;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public String getPays() {
        return pays;
    }

    public void setIdentifiantProprio(int identifiantProprio) {
        this.identifiantProprio = identifiantProprio;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
}
