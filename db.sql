/*csd4845-csd5031-csd5020 
HY360 Database project 
SQL file*/

CREATE TABLE IF NOT EXISTS Registration
(
    registration_id INTEGER not null AUTO_INCREMENT,
    customer_id INTEGER not null,
    username VARCHAR(20) not null unique,
    pass VARCHAR(20) not null,
    registration_date DATE,
    PRIMARY KEY(registration_id),
    FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE IF NOT EXISTS Customer
(
    customer_id INTEGER not null AUTO_INCREMENT,
    registration_id INTEGER not null,
    first_name VARCHAR(20) not null,
    last_name VARCHAR(20),
    street VARCHAR(20),
    date_of_birth INTEGER,
    drivers_licence_number INTEGER not null unique,
    credit_card_number VARCHAR(20) not null unique,
    expiration_date DATE not null,
    security_code INTEGER not null,
    city VARCHAR(20),
    region VARCHAR(20),
    zip_code INTEGER,
    age INTEGER,
    drivers VARCHAR(100),
    PRIMARY KEY(customer_id),
    FOREIGN KEY(registration_id) REFERENCES Registration(registration_id)
);

CREATE TABLE IF NOT EXISTS Vehicle
(
    vehicle_id INTEGER not null unique,
    color VARCHAR(20),
    brand VARCHAR(20),
    autonomy INTEGER,
    daily_rental_cost INTEGER,
    daily_insurance_cost INTEGER,
    available BOOLEAN,
    PRIMARY KEY(vehicle_id)
);

CREATE TABLE IF NOT EXISTS Rental
(
    rental_id INTEGER not null,
    customer_id INTEGER not null,
    vehicle_id INTEGER not null,
    first_name VARCHAR(20) not null,
    last_name VARCHAR(20) not null,
    rental_date DATE,
    duration TIME,
    cost INTEGER,
    PRIMARY KEY(rental_id),
    FOREIGN KEY(customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY(vehicle_id) REFERENCES Vehicle(vehicle_id)
);

CREATE TABLE IF NOT EXISTS Report
(
    report_id INTEGER not null unique,
    malfunction_description VARCHAR(100),
    report_date DATE,
    insurance_paid BOOLEAN,
    repair_cost DOUBLE,
    PRIMARY KEY(report_id),
    FOREIGN KEY(customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY(vehicle_id) REFERENCES Vehicle(vehicle_id)
);

CREATE TABLE IF NOT EXISTS Car
(
    vehicle_id INTEGER not null,
    registration_number INTEGER not null unique,
    car_type VARCHAR(20),
    number_of_passengers INTEGER,
    FOREIGN KEY(vehicle_id) REFERENCES Vehicle(vehicle_id)
);

CREATE TABLE IF NOT EXISTS Motorcycle
(
    vehicle_id INTEGER not null,
    registration_number INTEGER not null unique,
    FOREIGN KEY(vehicle_id) REFERENCES Vehicle(vehicle_id)
);

CREATE TABLE IF NOT EXISTS Bicycle
(
    vehicle_id INTEGER not null,
    special_number INTEGER not null unique,
    FOREIGN KEY(vehicle_id) REFERENCES Vehicle(vehicle_id)
);

CREATE TABLE IF NOT EXISTS Electric Scooter
(
    vehicle_id INTEGER not null,
    special_number INTEGER not null unique,
    FOREIGN KEY(vehicle_id) REFERENCES Vehicle(vehicle_id)
);

