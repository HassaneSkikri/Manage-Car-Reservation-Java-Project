import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Location {



    private int id;
    private int idVehicule;
    private int idClient;
    private String dateDebut;
    private String dateFin;
    private String EtatVehicule;
    private float kilometrage;

    //constructeur
    // ________________________________________________________________________________________________________//

    public Location(int id, int idVehicule, int idClient, String dateDebut, String dateFin, String etatVehicule,
                    float kilometrage) {
        this.id = id;
        this.idVehicule = idVehicule;
        this.idClient = idClient;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        EtatVehicule = etatVehicule;
        this.kilometrage = kilometrage;
    }


    // getters et setters
    // ________________________________________________________________________________________________________//

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdVehicule() {
        return idVehicule;
    }
    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public String getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }
    public String getDateFin() {
        return dateFin;
    }
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
    public String getEtatVehicule() {
        return EtatVehicule;
    }
    public void setEtatVehicule(String etatVehicule) {
        EtatVehicule = etatVehicule;
    }
    public float getKilometrage() {
        return kilometrage;
    }
    public void setKilometrage(float kilometrage) {
        this.kilometrage = kilometrage;}



    // to string
    // ________________________________________________________________________________________________________//

    @Override
    public String toString() {
        return "Location [id=" + id + ", idVehicule=" + idVehicule + ", idClient=" + idClient + ", dateDebut=" + dateDebut
                + ", dateFin=" + dateFin + ", EtatVehicule=" + EtatVehicule + ", kilometrage=" + kilometrage + "]";
    }



}
