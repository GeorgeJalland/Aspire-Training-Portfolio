DROP DATABASE IF EXISTS Supers;
CREATE DATABASE Supers;
USE Supers;

CREATE TABLE heroes_villains (
    hv_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name CHAR(50) NOT NULL,
    description CHAR(50) NOT NULL,
    super_power CHAR(50) NOT NULL
);

CREATE TABLE organisation (
    org_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name CHAR(50) NOT NULL,
    description CHAR(50) NOT NULL,
    address CHAR(100) NOT NULL
);

CREATE TABLE hero_villain_organisation (
    hv_id INT NOT NULL,
    org_id INT NOT NUlL,
    PRIMARY KEY (hv_id,org_id),
    FOREIGN KEY (hv_id) REFERENCES heroes_villains(hv_id) ON DELETE CASCADE,
    FOREIGN KEY (org_id) REFERENCES organisation(org_id) ON DELETE CASCADE
);

CREATE TABLE location (
    location_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,   
    name CHAR(50) NOT NULL,
    description CHAR(50) NOT NULL,
    address CHAR(100) NOT NULL,
    longitude CHAR(50) NOT NULL,
    latitude CHAR(50) NOT NULL
);

CREATE TABLE sightings (
    sighting_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
    location_id INT NOT NULL,
    hero_villain_id INT NOT NULL,
    sight_date DATE NOT NULL,
    FOREIGN KEY (location_id) REFERENCES location(location_id) ON DELETE CASCADE,
    FOREIGN KEY (hero_villain_id) REFERENCES heroes_villains(hv_id) ON DELETE CASCADE
);

DROP DATABASE IF EXISTS SupersTest;
CREATE DATABASE SupersTest;
USE SupersTest;

CREATE TABLE heroes_villains (
    hv_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name CHAR(50) NOT NULL,
    description CHAR(50) NOT NULL,
    super_power CHAR(50) NOT NULL
);

CREATE TABLE organisation (
    org_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name CHAR(50) NOT NULL,
    description CHAR(50) NOT NULL,
    address CHAR(100) NOT NULL
);

CREATE TABLE hero_villain_organisation (
    hv_id INT NOT NULL,
    org_id INT NOT NUlL,
    PRIMARY KEY (hv_id,org_id),
    FOREIGN KEY (hv_id) REFERENCES heroes_villains(hv_id) ON DELETE CASCADE,
    FOREIGN KEY (org_id) REFERENCES organisation(org_id) ON DELETE CASCADE
);

CREATE TABLE location (
    location_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,   
    name CHAR(50) NOT NULL,
    description CHAR(50) NOT NULL,
    address CHAR(100) NOT NULL,
    longitude CHAR(50) NOT NULL,
    latitude CHAR(50) NOT NULL
);

CREATE TABLE sightings (
    sighting_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
    location_id INT NOT NULL,
    hero_villain_id INT NOT NULL,
    sight_date DATE NOT NULL,
    FOREIGN KEY (location_id) REFERENCES location(location_id) ON DELETE CASCADE,
    FOREIGN KEY (hero_villain_id) REFERENCES heroes_villains(hv_id) ON DELETE CASCADE
);
