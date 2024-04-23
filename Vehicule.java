public class Vehicule {



    // les variables utilises sont le suivant:
    private int id;
    private String la_marque;
    private String modele;
    private int annee;
    private boolean estDisponible = true;

    // constructeur
    // ---------------------------------------------------------------------------------------------- //

    public Vehicule(int id, String marque, String modele, int annee, boolean estDisponible) {
        this.id = id;
        this.la_marque = marque;
        this.modele = modele;
        this.annee = annee;
    }

    //geters et seters
    // ---------------------------------------------------------------------------------------------- //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return la_marque;
    }

    public void setMarque(String marque) {
        this.la_marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public boolean getEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    // to string
    // ---------------------------------------------------------------------------------------------- //

    @Override
    public String toString() {
        return "Vehicule [id=" + id + ", la_marque=" + la_marque + ", modele=" + modele + ", annee=" + annee
                + ", estDisponible=" + estDisponible+ "]";
    }


}