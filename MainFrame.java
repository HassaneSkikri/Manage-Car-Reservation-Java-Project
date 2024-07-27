//_____________________________ GUI application -------------------------//

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("_____Gestion des vehicule________");

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane(); // pour afficher plusieurs panels 3 panels

        JPanel vehiclePanel = new JPanel(new BorderLayout());
        JPanel clientPanel = new JPanel(new BorderLayout());
        JPanel locationPanel = new JPanel(new BorderLayout());

        setupVehiclePanel(vehiclePanel);
        setupClientPanel(clientPanel);
        setupLocationPanel(locationPanel);

        tabbedPane.addTab("Vehicles", vehiclePanel); // vehiclePanel pour un titre Vehicles
        tabbedPane.addTab("Clients", clientPanel);
        tabbedPane.addTab("Locations", locationPanel);

        add(tabbedPane, BorderLayout.CENTER); // pour ajouter le tabbed pane au centre
    }

    private void ajouterField(JPanel formPanel, String labelString, JComponent field, GridBagConstraints gbc) { //grid bag constraints pour span more than one column
        formPanel.add(new JLabel(labelString), gbc);
        formPanel.add(field, gbc);
    }

    private JButton creeButton(String labelString) {
        JButton button = new JButton(labelString);
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        return button;
    }
    public void setupVehiclePanel(JPanel panel) {
        panel.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2);


        JTextField idField = new JTextField();
        JTextField brandField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField AnneField = new JTextField();
        JCheckBox availableCheckBox = new JCheckBox();

        ajouterField(formPanel, "ID:", idField, gbc);
        ajouterField(formPanel, "marque:", brandField, gbc);
        ajouterField(formPanel, "Model:", modelField, gbc);
        ajouterField(formPanel, "Anne:", AnneField, gbc);
        ajouterField(formPanel, "Available:", availableCheckBox, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = creeButton("Add");
        JButton modifierButton = creeButton("modifier");
        JButton SupprimerButton = creeButton("Supprimer");

        buttonPanel.add(addButton);
        buttonPanel.add(modifierButton);
        buttonPanel.add(SupprimerButton);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String marque = brandField.getText().trim();
                String model = modelField.getText().trim();
                int Anne = Integer.parseInt(AnneField.getText().trim());
                boolean isAvailable = availableCheckBox.isSelected();

                Vehicule newVehicle = new Vehicule(id, marque, model, Anne, isAvailable);
                DatabaseManager.getInstance().ajouterV(newVehicle);
                JOptionPane.showMessageDialog(null, "votre vehicule est ajouter (:");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number format.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error l'or de l'ajout: ):" + ex.getMessage());
            }
        });

        modifierButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String marque = brandField.getText().trim();
                String model = modelField.getText().trim();
                int Anne = Integer.parseInt(AnneField.getText().trim());
                boolean isAvailable = availableCheckBox.isSelected();

                Vehicule modifierdVehicle = new Vehicule(id, marque, model, Anne, isAvailable);
                DatabaseManager.getInstance().modifierV(modifierdVehicle);
                JOptionPane.showMessageDialog(null, "votre vehicule est modifier (:");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number format.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error l'or de la modification: " + ex.getMessage());
            }
        });

        SupprimerButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                DatabaseManager.getInstance().supprimerV(id);
                JOptionPane.showMessageDialog(null, "votre vehicule est supprimer.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number format.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error deleting vehicle: " + ex.getMessage());
            }
        });
    }

    public void setupClientPanel(JPanel panel) {
        panel.setLayout(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2);

        JTextField idField = new JTextField();
        JTextField nomField = new JTextField();
        JTextField PrenomField = new JTextField();
        JTextField addressField = new JTextField();

        ajouterField(formPanel, "ID:", idField, gbc);
        ajouterField(formPanel, "nom:", nomField, gbc);
        ajouterField(formPanel, "Prenom:", PrenomField, gbc);
        ajouterField(formPanel, "Address:", addressField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = creeButton("Add");
        JButton modifierButton = creeButton("modifier");
        JButton SupprimerButton = creeButton("Supprimer");

        buttonPanel.add(addButton);
        buttonPanel.add(modifierButton);
        buttonPanel.add(SupprimerButton);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String nom = nomField.getText().trim();
                String Prenom = PrenomField.getText().trim();
                String address = addressField.getText().trim();

                Client client = new Client(id, nom, Prenom, address);
                DatabaseManager.getInstance().ajouterClient(client);
                JOptionPane.showMessageDialog(null, "votre client est ajouter.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number format.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error l'or de l'ajout: " + ex.getMessage());
            }
        });
        modifierButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String nom = nomField.getText().trim();
                String Prenom = PrenomField.getText().trim();
                String address = addressField.getText().trim();

                Client client = new Client(id, nom, Prenom, address);
                DatabaseManager.getInstance().modifierC(client);
                JOptionPane.showMessageDialog(null, "Votre client est modifier.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number format.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error l'or de la modification: " + ex.getMessage());
            }
        });
        SupprimerButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                DatabaseManager.getInstance().supprimerC(id);
                JOptionPane.showMessageDialog(null, "Votre client est supprimer.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number format.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error l'or de la suppression: " + ex.getMessage());
            }
        });
    }

    public void setupLocationPanel(JPanel panel) {
        panel.setLayout(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2);

        JTextField idField = new JTextField();
        JTextField vehicleIdField = new JTextField();
        JTextField clientIdField = new JTextField();
        JTextField startDateField = new JTextField();
        JTextField endDateField = new JTextField();
        JTextField EtatVehiculeField = new JTextField();
        JTextField kilometrageField = new JTextField();

        ajouterField(formPanel, "ID:", idField, gbc);
        ajouterField(formPanel, "Vehicle ID:", vehicleIdField, gbc);
        ajouterField(formPanel, "Client ID:", clientIdField, gbc);
        ajouterField(formPanel, "Start Date (YYYY-MM-DD):", startDateField, gbc);
        ajouterField(formPanel, "End Date (YYYY-MM-DD):", endDateField, gbc);
        ajouterField(formPanel, "EtatVehicule:", EtatVehiculeField, gbc);
        ajouterField(formPanel, "kilometrage:", kilometrageField, gbc);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = creeButton("Add");
        JButton viewButton = creeButton("View Active");
        JButton closeButton = creeButton("Close");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(closeButton);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                int vehicleId = Integer.parseInt(vehicleIdField.getText().trim());
                int clientId = Integer.parseInt(clientIdField.getText().trim());
                String startDate = startDateField.getText().trim();
                String endDate = endDateField.getText().trim();
                String EtatVehicule = EtatVehiculeField.getText().trim();
                float kilometrage = Float.parseFloat(kilometrageField.getText().trim());

                Location location = new Location(id, vehicleId, clientId, startDate, endDate, EtatVehicule, kilometrage);
                DatabaseManager.getInstance().ajouterL(location);
                JOptionPane.showMessageDialog(null, "votre location est ajouter.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number format.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error l'or de l'ajout: " + ex.getMessage());
            }
        });
        viewButton.addActionListener(e -> {
            try {
                List<Location> locations = DatabaseManager.getInstance().visualizerL();
                locations.forEach(location -> System.out.println(location));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fetching locations: " + ex.getMessage());
            }
        });
        closeButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String endDate = endDateField.getText().trim();
                String EtatVehicule = EtatVehiculeField.getText().trim();
                float kilometrage = Float.parseFloat(kilometrageField.getText().trim());

                DatabaseManager.getInstance().cloturerL(id, endDate, EtatVehicule, kilometrage);
                JOptionPane.showMessageDialog(null, "votre location est cloturer.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number format.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error l'or de la modification: " + ex.getMessage());
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

}


