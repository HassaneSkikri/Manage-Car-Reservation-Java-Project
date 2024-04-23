# Vehicle Management System

This Java application is a straightforward vehicle management system that interfaces with a MySQL database to handle vehicles, clients, and rental agreements.

## Steps to Complete this Project

### 1. Create a MySQL Dataset Named "manage_vehicule"
Begin by creating a new dataset in MySQL dedicated to vehicle management.

### 2. Connect the Database to the Project
Set up a connection from your project to the MySQL database.

### 3. Create the Three Tables: location, client, vehicle
Execute the following SQL commands to construct the necessary tables:

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

### 4. Develop the Three Classes for Client, Location, and Vehicle
Implement the three corresponding Java classes for clients, rentals, and vehicles.

### 5. Develop the DatabaseManager Class to Ensure Connection with the "manage_vehicule" Dataset
Create a DatabaseManager class to manage the database connection and transactions.

### 6. Develop the Main Class and Create a Menu to Test All Functions
Develop the Main class and design a menu to assess all the functionalities.

## Getting Started
Follow these instructions to get the project running on your local machine:

Install MySQL and configure the manage_vehicule dataset.
Update the database connection information in the DatabaseManager.java file.
Compile the Java files and execute the Main.java file to launch the application.

## Built With
Java: The core programming language used.
MySQL: The database management system utilized for data storage.

