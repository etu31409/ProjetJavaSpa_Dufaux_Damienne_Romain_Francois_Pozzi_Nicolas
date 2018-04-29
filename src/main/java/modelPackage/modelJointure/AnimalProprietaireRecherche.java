package modelPackage.modelJointure;

public class AnimalProprietaireRecherche {
    Integer idAnimal;
    String nomAnimal;
    Integer idProprietaire;
    String nomProprietaire;

    public AnimalProprietaireRecherche(){

    }

    public AnimalProprietaireRecherche(Integer idAnimal, String nomAnimal, Integer idProprietaire, String nomProprietaire) {
        this.idAnimal = idAnimal;
        this.nomAnimal = nomAnimal;
        this.idProprietaire = idProprietaire;
        this.nomProprietaire = nomProprietaire;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public Integer getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(Integer idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    public String getNomProprietaire() {
        return nomProprietaire;
    }

    public void setNomProprietaire(String nomProprietaire) {
        this.nomProprietaire = nomProprietaire;
    }
}
