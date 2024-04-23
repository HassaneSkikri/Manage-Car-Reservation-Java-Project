## Étapes pour realiser ce projet

### 1. crée une dataset dans MYSQL "manage_vehicule"
### 2. Connecter la base de données au projet
### 3. create les 3 tableux location, client,vehicule:
```mysql
CREATE TABLE vehicules (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           marque VARCHAR(255),
                           modele VARCHAR(255),
                           annee INT,
                           estDisponible BOOLEAN
);

CREATE TABLE clients (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         nom VARCHAR(255) NOT NULL,
                         prenom VARCHAR(255) NOT NULL,
                         adresse VARCHAR(255) NOT NULL
);

CREATE TABLE locations (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           idVehicule INT NOT NULL,
                           idClient INT NOT NULL,
                           dateDebut DATE NOT NULL,
                           dateFin DATE,
                           EtatVehicule VARCHAR(255),
                           kilometrage FLOAT NOT NULL,
                           FOREIGN KEY (idVehicule) REFERENCES vehicules(id),
                           FOREIGN KEY (idClient) REFERENCES clients(id)
);

```

### 4. developper les 3 class pour le client location et vehicule

### 5. developper databaseManager class et assurer la connction avec manager_vehicule dataset.

### 6. developper le Main class et cree une Menu qui test tout ces fonctions.

#  _______Fin__________