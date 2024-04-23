import java.sql.*;


public class Client {




    private int id;
    private String nom;
    private String prenom;
    private String adresse;

    //Constructeur
    // ____________________________________________________________________________________________________//
    public Client(int id, String nom, String prenom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }
    // seters et geters
    // ____________________________________________________________________________________________________//


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;}

    // toString
    // ____________________________________________________________________________________________________//

    @Override
    public String toString() {
        return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + "]";

    }



}