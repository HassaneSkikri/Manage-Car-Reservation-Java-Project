import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    // connexion base de donnees
    private String URL = "jdbc:mysql://localhost:3306/manage_vehicule";
    private String User= "root";
    private String Pass = "H0635607145h";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, User, Pass);
    }


    // les methodes de Vehicule
    // ---------------------------------------------------------------------------------------------- //


    // ---------------------------------------------------------------------------------------------- //
    public void ajouterV (Vehicule vehicule) throws SQLException {
        String sql_query = "INSERT INTO vehicules (id, marque, modele, annee, estDisponible) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement PStatement = getConnection().prepareStatement(sql_query)) {
            PStatement.setInt(1, vehicule.getId());
            PStatement.setString(2, vehicule.getMarque());
            PStatement.setString(3, vehicule.getModele());
            PStatement.setInt(4, vehicule.getAnnee());
            PStatement.setBoolean(5, vehicule.getEstDisponible());
            PStatement.executeUpdate();
        }
    }
    // ---------------------------------------------------------------------------------------------- //

    public void modifierV(Vehicule vehicule) throws SQLException {
        String sql_query = "UPDATE vehicules SET marque = ?, modele = ?, annee = ?, estDisponible = ? WHERE id = ?";
        try (PreparedStatement PStatement = getConnection().prepareStatement(sql_query)) {
            PStatement.setString(1, vehicule.getMarque());
            PStatement.setString(2, vehicule.getModele());
            PStatement.setInt(3, vehicule.getAnnee());
            PStatement.setBoolean(4, vehicule.getEstDisponible());
            PStatement.setInt(5, vehicule.getId());
            PStatement.executeUpdate();
        }
    }

    // ---------------------------------------------------------------------------------------------- //

    public void supprimerV(int idVehicule) throws SQLException {
        String sql_query = "DELETE FROM vehicules WHERE id = ?";
        try (PreparedStatement PStatement = getConnection().prepareStatement(sql_query)) {
            PStatement.setInt(1, idVehicule);
            PStatement.executeUpdate();
        }
    }

    // ---------------------------------------------------------------------------------------------- //

    // les methodes de client
    // ____________________________________________________________________________________________________//

    // ajouter un clien a la base de donnee

    public void ajouterClient(Client client) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO clients (id, nom, prenom, adresse) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstatement = conn.prepareStatement(sql)) {
                pstatement.setInt(1, client.getId());
                pstatement.setString(2, client.getNom());
                pstatement.setString(3, client.getPrenom());
                pstatement.setString(4, client.getAdresse());
                pstatement.executeUpdate();
            }
        }
    }


    // modefier le s information d'un client dans la base de donne
    // _________________________________________________________________________________________________//

    public void modifierC(Client client) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE clients SET nom = ?, prenom = ?, adresse = ? WHERE id = ?";
            try (PreparedStatement pstatement = conn.prepareStatement(sql)) {
                pstatement.setString(1, client.getNom());
                pstatement.setString(2, client.getPrenom());
                pstatement.setString(3, client.getAdresse());
                pstatement.setInt(4, client.getId());
                pstatement.executeUpdate();
            }
        }
    }


    // supprimer un client de la base de donnee
    // _________________________________________________________________________________________________//


    public void supprimerC(int clientId) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM clients WHERE id = ?";
            try (PreparedStatement pstatement = conn.prepareStatement(sql)) {
                pstatement.setInt(1, clientId);
                pstatement.executeUpdate();
            }
        }
    }
    // rechercher un client dans la base de donnee
    // _________________________________________________________________________________________________//

    public Client chercherClientParId(int clientId) throws SQLException {
        Client client = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM clients WHERE id = ?";
            try (PreparedStatement pstatement = conn.prepareStatement(sql)) {
                pstatement.setInt(1, clientId);
                try (ResultSet rs = pstatement.executeQuery()) {
                    if (rs.next()) {
                        client = new Client(
                                rs.getInt("id"),
                                rs.getString("nom"),
                                rs.getString("prenom"),
                                rs.getString("adresse")
                        );
                    }
                }
            }
        }
        return client;
    }


    // les methodes de location
    // ________________________________________________________________________________________________________//

    // ajouter une location
    // ________________________________________________________________________________________________________//

    public void ajouterL(Location location) throws SQLException {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO locations (id, idVehicule, idClient, dateDebut, dateFin, EtatVehicule, kilometrage) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstatement = conn.prepareStatement(sql)) {
                pstatement.setInt(1, location.getId());
                pstatement.setInt(2, location.getIdVehicule());
                pstatement.setInt(3, location.getIdClient());
                pstatement.setString(4, location.getDateDebut());
                pstatement.setString(5, location.getDateFin());
                pstatement.setString(6, location.getEtatVehicule());
                pstatement.setFloat(7, location.getKilometrage());
                pstatement.executeUpdate();
            }
        }
    }

    // visualizer une location
    // ________________________________________________________________________________________________________//

    public List<Location> visualizerL() throws SQLException {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT * FROM locations WHERE dateFin IS NULL OR dateFin > CURRENT_DATE";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                locations.add(new Location(
                        rs.getInt("id"),
                        rs.getInt("idVehicule"),
                        rs.getInt("idClient"),
                        rs.getString("dateDebut"),
                        rs.getString("dateFin"),
                        rs.getString("EtatVehicule"),
                        rs.getFloat("kilometrage")
                ));
            }
        }
        return locations;
    }

    // cloture une location
    // ________________________________________________________________________________________________________//

    public void cloturerL(int id, String dateFin, String etatVehicule, float kilometrage) throws SQLException {
        String sql = "UPDATE locations SET dateFin = ?, EtatVehicule = ?, kilometrage = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement Pstatement = conn.prepareStatement(sql)) {
            Pstatement.setString(1, dateFin);
            Pstatement.setString(2, etatVehicule);
            Pstatement.setFloat(3, kilometrage);
            Pstatement.setInt(4, id);
            Pstatement.executeUpdate();
        }
    }









}
