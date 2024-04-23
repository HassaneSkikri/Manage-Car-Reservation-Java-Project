import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DatabaseManager dbManager = new DatabaseManager();
    private static int idCounter = 1;
    private static boolean estDisponible = false;
    private static Client clientManager = new Client(0, "", "", "");
    private static Vehicule vehiculeManager = new Vehicule(0, "", "", 0, estDisponible);
    private static Location locationManager = new Location(0, 0, 0, "", "", "", 0);


    // -----------------------------------------------------------------------------------------//
    public static void main(String[] args) throws SQLException {
        boolean quit = false;
        while (!quit) {
            System.out.println("\n _____Menu de notre application______");
            System.out.println("1. Manage Vehicles");
            System.out.println("2. Manage Clients");
            System.out.println("3. Manage Location");
            System.out.println("4. Quit");

            System.out.println("Enter votre choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageVehicles();
                    break;
                case 2:
                    manageClients();
                    break;
                case 3:
                    manageLocation();
                    break;
                case 4:
                    System.out.println("Quitting.");
                    quit = true;
                    break;
                default:
                    System.out.println("votre choice est introvable.");
            }
        }
        scanner.close();
    }
    public static void manageClients() throws SQLException  {
        while (true) {
            System.out.println("---Menu---:\n" +
                    "1. Ajouter Client\n" +
                    "2. rechercher un Client\n" +
                    "3. modefier Client\n" +
                    "4. supprimer Client\n" +
                    "0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            Client client;
            String nom, prenom, adresse;
            switch (choice) {
                case 1:

                    System.out.println("Enter le nom du client: ");
                    nom = scanner.nextLine();
                    System.out.println("Enter le prenom du client: ");
                    prenom = scanner.nextLine();
                    System.out.println("Enter l'address du client: ");
                    adresse = scanner.nextLine();
                    client = new Client(idCounter++, nom, prenom, adresse);
                    dbManager.ajouterClient(client);
                    System.out.println("Client est ajouter.");
                    break;
                case 2:
                    int id;
                    System.out.println("Entrer l'id du client: ");
                    id = scanner.nextInt();
                    client = dbManager.chercherClientParId(id);
                    if (client != null) {
                        System.out.println(client);
                    } else {
                        System.out.println("Client not found.");
                    }
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("Enter l'id du client que tu veut modefier: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter la nouvell nom du client: ");
                    nom = scanner.nextLine();
                    System.out.println("Enter la nouvell prenom du client: ");
                    prenom = scanner.nextLine();
                    System.out.println("Enter la nouvell adress du client: ");
                    adresse = scanner.nextLine();
                    client = new Client(id, nom, prenom, adresse);
                    dbManager.modifierC(client);
                    System.out.println("Client est modefier.");
                    break;
                case 4:
                    System.out.println("Enter l'id du client que tu veut supprimer: ");
                    id = scanner.nextInt();
                    dbManager.supprimerC(id);
                    System.out.println("Client deleted successfully.");
                    scanner.nextLine(); // Consume newline left-over
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // -----------------------------------------------------------------------------------------//

    public static void manageVehicles() throws SQLException {
        while (true) {
            System.out.println("_____Menu____:\n" +
                    "1. Ajouter Vehicle\n" +
                    "2. modefier Vehicle\n" +
                    "3. supprimer Vehicle\n" +
                    "0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            int id, annee;
            String marque, modele;
            boolean estDisponible;

            switch (choice) {
                case 1:
                    System.out.println("Enter la marque du vehicule: ");
                    marque = scanner.nextLine();
                    System.out.println("Entrer le model du vehicule : ");
                    modele = scanner.nextLine();
                    System.out.println("Enter l'anne du vehicule: ");
                    annee = scanner.nextInt();
                    System.out.println("est ce que le vehicule est disponible ou non? (true/false): ");
                    estDisponible = scanner.nextBoolean();
                    Vehicule vehicule = new Vehicule(idCounter++, marque, modele, annee, estDisponible);
                    dbManager.ajouterV(vehicule);
                    System.out.println("la vehicule est ajouter.");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("Enter l'id du vehicule pour modefier: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter la nouvell model: ");
                    marque = scanner.nextLine();
                    System.out.println("Enter la nouvell modele : ");
                    modele = scanner.nextLine();
                    System.out.println("Enter nouvelle year: ");
                    annee = scanner.nextInt();
                    System.out.println("est disposable ou non ? (true/false): ");
                    estDisponible = scanner.nextBoolean();
                    Vehicule updatedVehicule = new Vehicule(id, marque, modele, annee, estDisponible);
                    dbManager.modifierV(updatedVehicule);
                    System.out.println("Vehicle est modifier.");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("Enter l'id du vehicule pour le supprimer: ");
                    id = scanner.nextInt();
                    dbManager.supprimerV(id);
                    System.out.println("Vehicle est supprimer.");
                    scanner.nextLine();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    // -----------------------------------------------------------------------------------------//


    public static void manageLocation() throws SQLException{

        while (true) {
            System.out.println("-----Menu-----:\n" +
                    "1. Ajouter Location\n" +
                    "2. Visualizer Locations\n" +
                    "3. Close Location\n" +
                    "0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            int id, idVehicule, idClient;
            String dateDebut, dateFin, etatVehicule;
            float kilometrage;

            switch (choice) {
                case 1:
                    System.out.println("Enter l'id du vehicule: ");
                    idVehicule = scanner.nextInt();
                    System.out.println("Enter l'id du client: ");
                    idClient = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter la date de debut (YYYY-MM-DD): ");
                    dateDebut = scanner.nextLine();
                    System.out.println("Enter la date de la fin (YYYY-MM-DD): ");
                    dateFin = scanner.nextLine();
                    System.out.println("Enter l'etat du vehicule: ");
                    etatVehicule = scanner.nextLine();
                    System.out.println("Enter le kilometrage du vehicule: ");
                    kilometrage = scanner.nextFloat();
                    Location location = new Location(idCounter++, idVehicule, idClient, dateDebut, dateFin, etatVehicule, kilometrage);
                    dbManager.ajouterL(location);
                    System.out.println("Location est ajouter.");
                    scanner.nextLine();
                    break;
                case 2:
                    List<Location> locations = dbManager.visualizerL();
                    for (Location loc : locations) {
                        System.out.println(loc);
                    }
                    break;
                case 3:
                    System.out.println("Enter l'id du vehicule pour la fermeture : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter la date de la fin (YYYY-MM-DD): ");
                    dateFin = scanner.nextLine();
                    System.out.println("Enter l'etat du vehicule: ");
                    etatVehicule = scanner.nextLine();
                    System.out.println("Enter le kilometrage du vehicule : ");
                    kilometrage = scanner.nextFloat();
                    dbManager.cloturerL(id, dateFin, etatVehicule, kilometrage);
                    System.out.println("Location est close.");
                    scanner.nextLine();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    // -----------------------------------------------------------------------------------------//



}

