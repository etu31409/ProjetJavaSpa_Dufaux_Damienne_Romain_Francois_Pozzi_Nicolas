package modelPackage;

public class ProprietaireAnimal {

    private Integer numRegistreAnimal;
    private String nomAnimal;
    private Integer identifiantProprio;
    private String nomProprio;
    private String prenomProprio;

    public ProprietaireAnimal(){}
    public ProprietaireAnimal(Integer numRegistreAnimal, String nomAnimal, Integer identifiantProprio, String prenomProprio,
                              String nomProprio) {
        setNumRegistreAnimal(numRegistreAnimal);
        setNomAnimal(nomAnimal);
        setIdentifiantProprio(identifiantProprio);
        setPrenomProprio(prenomProprio);
        setNomProprio(nomProprio);
    }

    public Integer getNumRegistreAnimal() {
        return numRegistreAnimal;
    }

    public void setNumRegistreAnimal(Integer numRegistreAnimal) {
        this.numRegistreAnimal = numRegistreAnimal;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public Integer getIdentifiantProprio() {
        return identifiantProprio;
    }

    public void setIdentifiantProprio(Integer identifiantProprio) {
        this.identifiantProprio = identifiantProprio;
    }

    public String getNomProprio() {
        return nomProprio;
    }

    public void setNomProprio(String nomProprio) {
        this.nomProprio = nomProprio;
    }

    public String getPrenomProprio() {
        return prenomProprio;
    }

    public void setPrenomProprio(String prenomProprio) {
        this.prenomProprio = prenomProprio;
    }
}
