-----    Foncier   ---------   SqlServer
create DATABASE Land;

use  Land;

CREATE table foncier (
   id INT NOT NULL PRIMARY KEY identity(1,1),
   id_cin [NVARCHAR](12),
   partielle [NVARCHAR](15)  NOT NULL,
   localisation [NVARCHAR](15),
   prix double PRECISION,
   heritage int not null ,
   superficie double PRECISION
);
INSERT INTO foncier (id_cin, partielle, localisation, prix, heritage, superficie)
VALUES
  ('CIN0001', 'Parcelle A', 'Adresse A', 100000, 1, 250.123),
  ('CIN0001', 'Parcelle B', 'Adresse B', 150000, 0, 300.456),
  ('CIN0002', 'Parcelle C', 'Adresse C', 75000, 1, 180.789);

CREATE TABLE borne (
    id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    id_foncier INT NOT NULL,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    FOREIGN KEY (id_foncier) REFERENCES foncier(id),
    CONSTRAINT UC_LatitudeLongitude UNIQUE (latitude, longitude)
);

-- Insérer une borne pour un foncier spécifique à Madagascar
INSERT INTO borne (id_foncier, latitude, longitude)
VALUES (1, -18.8792, 46.7969);

-- Insérer une autre borne pour un autre foncier à Madagascar
INSERT INTO borne (id_foncier, latitude, longitude)
VALUES (2, -20.348404, 44.317820);

-- Insérer une troisième borne pour un foncier différent à Madagascar
INSERT INTO borne (id_foncier, latitude, longitude)
VALUES (3, -19.015438, 47.196373);
-- Insérer une quatrième borne pour un foncier à Madagascar
INSERT INTO borne (id_foncier, latitude, longitude)
VALUES (3, -18.8792, 46.7969);

-- Insérer une cinquième borne pour un foncier différent à Madagascar
INSERT INTO borne (id_foncier, latitude, longitude)
VALUES (1, -20.348404, 44.317820);

-- Insérer une sixième borne pour un foncier à Madagascar
INSERT INTO borne (id_foncier, latitude, longitude)
VALUES (2, -19.015438, 47.196373);

-- Insérer une septième borne pour un foncier différent à Madagascar
INSERT INTO borne (id_foncier, latitude, longitude)
VALUES (1, -18.8792, 46.7969);

-- Insérer une huitième borne pour un foncier à Madagascar
INSERT INTO borne (id_foncier, latitude, longitude)
VALUES (3, -20.348404, 44.317820);

-- Insérer une neuvième borne pour un foncier différent à Madagascar
INSERT INTO borne (id_foncier, latitude, longitude)
VALUES (1, -19.015438, 47.196373);

-- Insérer une dixième borne pour un foncier à Madagascar
INSERT INTO borne (id_foncier, latitude, longitude)
VALUES (3, -18.8792, 46.7969);



------- Bank ----------- PostgreSql
CREATE DATABASE Bank;

\c Bank;

CREATE SEQUENCE SeqBanque;
CREATE TABLE banque (
    id VARCHAR not NULL PRIMARY key,
    nom  VARCHAR(50) 
);
-- Ajout de données réelles à la table "banque"
INSERT INTO banque (id, nom) VALUES ('BNK00' || NEXTVAL('SeqBanque'), 'Banque Internationale');
INSERT INTO banque (id, nom) VALUES ('BNK00' || NEXTVAL('SeqBanque'), 'Banque Nationale');
INSERT INTO banque (id, nom) VALUES ('BNK00' || NEXTVAL('SeqBanque'), 'Banque Populaire');
INSERT INTO banque (id, nom) VALUES ('BNK00' || NEXTVAL('SeqBanque'), 'Banque de l Économie');
INSERT INTO banque (id, nom) VALUES ('BNK00' || NEXTVAL('SeqBanque'), 'Banque Royale');


CREATE TABLE compte (
    id VARCHAR not NULL PRIMARY KEY,
    num_cin  VARCHAR(12),
    id_banque varchar NOT NULL,
    num_compte   VARCHAR(50) , 
    code VARCHAR,    
   FOREIGN key (id_banque) REFERENCES banque(id)
);
CREATE SEQUENCE SeqCompte;
-- Exemple d'insertion de données dans la table "compte"
INSERT INTO compte (id, num_cin, id_banque, num_compte, code)
VALUES
    ('CMPT00' || NEXTVAL('SeqCompte'), '123456', 'BNK001', 'C123456789', 'ABC123'),
    ('CMPT00' || NEXTVAL('SeqCompte'), '789012', 'BNK001', 'S987654321', 'XYZ789'),
    ('CMPT00' || NEXTVAL('SeqCompte'), '456789', 'BNK002', 'C987654321', 'DEF456');


CREATE TABLE TRANSACTION (
    id VARCHAR(15) NOT NULL PRIMARY KEY,
    nom VARCHAR(50)
);
CREATE SEQUENCE SeqTransaction;
-- Ajout de données à la table TRANSACTION en utilisant la séquence
INSERT INTO TRANSACTION (id, nom) VALUES ('TRN00' || NEXTVAL('SeqTransaction'), 'Transfert');
INSERT INTO TRANSACTION (id, nom) VALUES ('TRN00' || NEXTVAL('SeqTransaction'), 'Dépôt');
INSERT INTO TRANSACTION (id, nom) VALUES ('TRN00' || NEXTVAL('SeqTransaction'), 'Retrait');

CREATE SEQUENCE SeqTransaction_faite;
CREATE table transaction_faite (
    id varchar(15) not null PRIMARY key,
    id_transaction varchar(15),
    id_compte1 varchar(15),
    id_compte2 varchar(15),
    prix DECIMAL(15,3),
    date_transcription date
);
-- Insérer des données dans la table transaction_faite
INSERT INTO transaction_faite (id, id_transaction, id_compte1, id_compte2, prix, date_transcription)
VALUES
    ('TRF00' || nextval('SeqTransaction_faite'), 'TRN001', '123456', '150000', 500.00, '2023-09-21'),
    ('TRF00' || nextval('SeqTransaction_faite'), 'TRN002', '123456', '123456', 750.00, '2023-09-22'),
    ('TRF00' || nextval('SeqTransaction_faite'), 'TRN002', '123456', '150000', 750.00, '2023-09-22'),
    ('TRF00' || nextval('SeqTransaction_faite'), 'TRN003', '123456', '123456', 300.00, '2023-09-23');

-------- Devise -------
CREATE SEQUENCE SeqDevise;
-- Création de la table "devise"
CREATE TABLE devise (
    id varchar(15) PRIMARY KEY,  -- Colonne d'ID auto-incrémentée
    code VARCHAR(3) NOT NULL,  -- Code de la devise (par exemple, USD, EUR, etc.)
    nom VARCHAR(50) NOT NULL,  -- Nom de la devise (par exemple, Dollar américain, Euro, etc.)
    pays VARCHAR(50)  -- Pays associé à la devise
);
-- Ajout de données dans la table "devise"
INSERT INTO devise (id, code, nom, pays)
VALUES
    ('DVS00' || nextval('SeqDevise'), 'USD', 'Dollar americain', 'Etats-Unis'),
    ('DVS00' || nextval('SeqDevise'), 'EUR', 'Euro', 'Union européenne'),
    ('DVS00' || nextval('SeqDevise'), 'GBP', 'Livre sterling', 'Royaume-Uni'),
    ('DVS00' || nextval('SeqDevise'), 'AR', 'Ariary', 'Madagasikara'),
    ('DVS00' || nextval('SeqDevise'), 'JPY', 'Yen japonais', 'Japon');

CREATE SEQUENCE SeqCours;
CREATE TABLE cours (
    id varchar(15) PRIMARY KEY,
    id_devise VARCHAR(15) NOT NULL,
    valeur decimal(15,3),
    taux_achat DECIMAL(15,3),
    taux_vente DECIMAL(15,3),
    dates date
);
INSERT INTO cours (id, id_devise, valeur,taux_achat,taux_vente, dates)
VALUES
    ('DCR00' || nextval('SeqCours'), 'DVS001', 3500.00,4930,4450, '2023-10-15'),
    ('DCR00' || nextval('SeqCours'),  'DVS002', 4000.00,5150,4890, '2023-10-15'),
    ('DCR00' || nextval('SeqCours'), 'DVS004', 1,1,1, '2023-10-16');


-- Vous pouvez ajouter d'autres devises de la même manière.





------ Health ------- Oracle
user health pwd health
GRANT CONNECT TO nom_utilisateur;

CREATE DATABASE Health;

USE Health;

-- CREATE table person (
--     id INT NOT NULL PRIMARY KEY identity(1,1),
--     nom [NVARCHAR](50) ,
--     prenom [NVARCHAR](50) ,
--     dtn DATE ,
--     lieu_n [NVARCHAR](50) ,
--     adresse [NVARCHAR](50) ,
--     profession [NVARCHAR](50) 
-- );
CREATE table person (
    id VARCHAR NOT NULL PRIMARY KEY ,
    nom varchar(50) ,
    prenom varchar(50) ,
    dtn TIMESTAMP ,
    lieu_n varchar(50) ,
    adresse varchar(50) ,
    profession varchar(50) 
);
CREATE SEQUENCE SeqPerson;
INSERT INTO person (id,nom, prenom, dtn, lieu_n, adresse, profession)
VALUES
  ('PRS001','Doe', 'John', '1990-01-15', 'New York', '123 Main Street', 'Ingénieur'),
  ('PRS002','Smith', 'Jane', '1985-05-20', 'Los Angeles', '456 Elm Street', 'Médecin'),
  ('PRS003','Johnson', 'Robert', '1978-09-10', 'Chicago', '789 Oak Avenue', 'Avocat');


-- CREATE TABLE cin (
--     id INT NOT NULL PRIMARY KEY identity(1,1),
--     id_person int not null,
--     nom_pere [NVARCHAR](100) ,  
--     profession_pere [NVARCHAR](50) ,  
--     nom_mere [NVARCHAR](100) ,
--     profession_mere [NVARCHAR](50) ,
--     date_cin    DATE,
--     num_cin [NVARCHAR](12) ,
--     FOREIGN KEY (id_person) REFERENCES person(id)
-- );
CREATE TABLE cin (
    id VARCHAR NOT NULL PRIMARY KEY ,
    id_person VARCHAR,
    nom_pere varchar(100) ,  
    profession_pere varchar(50) ,  
    nom_mere varchar(100) ,
    profession_mere varchar(50) ,
    date_cin    TIMESTAMP,
    num_cin varchar(12) ,
    FOREIGN KEY (id_person) REFERENCES person(id)
);
CREATE SEQUENCE SeqCin;
INSERT INTO cin (id,id_person, nom_pere, profession_pere, nom_mere, profession_mere, date_cin, num_cin)
VALUES
  ('CIN0001','PRS001', 'Doe', 'Ingénieur', 'Smith', 'Médecin', '1995-03-10', '123456'),
  ('CIN0002','PRS002', 'Johnson', 'Avocat', 'Williams', 'Enseignante', '1998-07-25', '150000'),
  ('CIN0003','PRS003', 'Brown', 'Architecte', 'Davis', 'Infirmière', '1990-12-15', '75000');


-- CREATE TABLE maladie (
--     id int auto increment PRIMARY key ,
--     nom VARCHAR
-- );
CREATE TABLE maladie (
    id VARCHAR PRIMARY key ,
    nom VARCHAR
);
CREATE SEQUENCE SeqMaladie ;
-- Exemple d'insertion de données dans la table "maladie"
INSERT INTO maladie (id, nom)
VALUES
    ('MLD001', 'Grippe'),
    ('MLD002', 'Rhume'),
    ('MLD003', 'Migraine'),
    ('MLD004', 'Hypertension'),
    ('MLD005', 'Diabète');


-- CREATE TABLE operation (
--     id int auto increment PRIMARY key ,
--     nom VARCHAR
-- );
CREATE TABLE operation (
    id VARCHAR PRIMARY key ,
    nom VARCHAR
);
CREATE SEQUENCE SeqOperation;
-- Exemple d'insertion de données dans la table "operation"
INSERT INTO operation (id, nom)
VALUES
    ('OPRT001', 'Chirurgie du genou'),
    ('OPRT002', 'Extraction dentaire'),
    ('OPRT003', 'Accouchement'),
    ('OPRT004', 'Transfusion sanguine'),
    ('OPRT005', 'Endoscopie');



-- CREATE table operation_pers (
--     id int auto increment primary key,
--     id_cin int,
--     id_operation int,
--     date_operation DATE,
--     prix DOUBLE,
--     docteur VARCHAR(50),
--     lieu VARCHAR(50),
--     FOREIGN key (id_operation) REFERENCES operation(id)
-- );
CREATE table operation_pers (
    id VARCHAR primary key,
    id_cin varchar,
    id_operation VARCHAR,
    date_operation TIMESTAMP,
    prix DECIMAL,
    docteur VARCHAR(50),
    lieu VARCHAR(50),
    FOREIGN key (id_operation) REFERENCES operation(id),
    FOREIGN key (id_cin) REFERENCES cin(id)
);
CREATE Sequence SeqOperation_pers;
-- Exemple d'insertion de données dans la table "operation_pers"
INSERT INTO operation_pers (id, id_cin, id_operation, date_operation, prix, docteur, lieu)
VALUES
    ('OPP001', 'CIN0001', 'OPRT001', '2023-09-20 10:00:00', 1500.00, 'Dr. Smith', 'Hôpital A'),
    ('OPP002', 'CIN0001', 'OPRT002', '2023-09-21 14:30:00', 800.00, 'Dr. Johnson', 'Hôpital B'),
    ('OPP003', 'CIN0002', 'OPRT003', '2023-09-22 08:15:00', 2000.00, 'Dr. Williams', 'Hôpital C'),
    ('OPP004', 'CIN0003', 'OPRT004', '2023-09-23 11:45:00', 1200.00, 'Dr. Davis', 'Hôpital D'),
    ('OPP005', 'CIN0002', 'OPRT005', '2023-09-24 16:00:00', 950.00, 'Dr. Anderson', 'Hôpital E');


-- CREATE TABLE maladie_pers (
--     id int auto increment primary key,
--     id_cin int,
--     id_maladie int,
--     type int,
--     traitement int,
--     geuris int,
--     dateDebut date,
--     dateFin date,
--     FOREIGN key (id_maladie) REFERENCES maladie(id)
-- );
CREATE TABLE maladie_pers (
    id VARCHAR primary key,
    id_cin varchar,
    id_maladie VARCHAR,
    heridite int,
    traitement int,
    geuris int,
    dateDebut date,
    dateFin date,
    FOREIGN key (id_maladie) REFERENCES maladie(id),
    FOREIGN key (id_cin) REFERENCES cin(id)
);
CREATE SEQUENCE SeqMaladie_pers;
-- Exemple d'insertion de données dans la table "maladie_pers"
INSERT INTO maladie_pers (id, id_cin, id_maladie, heridite, traitement, geuris, dateDebut, dateFin)
VALUES
    ('MLP001', 'CIN0001', 'MLD001', 0, 1, 1, '2023-09-15', '2023-09-20'),
    ('MLP002', 'CIN0003', 'MLD001', 1, 0, 1, '2023-09-18', '2023-09-25'),
    ('MLP003', 'CIN0003', 'MLD004', 0, 1, 1, '2023-09-20', '2023-09-27'),
    ('MLP004', 'CIN0002', 'MLD002', 1, 1, 0, '2023-09-22', '2023-09-30'),
    ('MLP005', 'CIN0001', 'MLD003', 0, 1, 1, '2023-09-25', '2023-10-02');


    