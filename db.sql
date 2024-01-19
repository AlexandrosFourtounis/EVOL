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

/*Query2*/
SELECT v.vehicle_id, v.brand, r.rental_date, r.duration, c.first_name, c.last_name 
FROM vehicle v 
JOIN rental r ON v.vehicle_id = r.vehicle_id 
JOIN customer c ON r.customer_id = c.customer_id 
WHERE r.rental_date 
BETWEEN '2020-01-01' AND '2024-04-16' AND v.available = "yes";

/*Query3 */
SELECT
    MAX(CASE WHEN c.vehicle_id  THEN r.duration END) AS max_duration_cars,
    MIN(CASE WHEN c.vehicle_id  THEN r.duration END) AS min_duration_cars,
    AVG(CASE WHEN c.vehicle_id  THEN r.duration END) AS avg_duration_cars,
    MAX(CASE WHEN m.vehicle_id  THEN r.duration END) AS max_duration_motorcycles,
    MIN(CASE WHEN m.vehicle_id  THEN r.duration END) AS min_duration_motorcycles,
    AVG(CASE WHEN m.vehicle_id  THEN r.duration END) AS avg_duration_motorcycles,
    MAX(CASE WHEN b.vehicle_id  THEN r.duration END) AS max_duration_bicycles,
    MIN(CASE WHEN b.vehicle_id  THEN r.duration END) AS min_duration_bicycles,
    AVG(CASE WHEN b.vehicle_id  THEN r.duration END) AS avg_duration_bicycles,
    MAX(CASE WHEN es.vehicle_id  THEN r.duration END) AS max_duration_scooters,
    MIN(CASE WHEN es.vehicle_id  THEN r.duration END) AS min_duration_scooters,
    AVG(CASE WHEN es.vehicle_id  THEN r.duration END) AS avg_duration_scooters
FROM Rental r
LEFT JOIN Car c ON r.vehicle_id = c.vehicle_id
LEFT JOIN Motorcycle m ON r.vehicle_id = m.vehicle_id
LEFT JOIN Bicycle b ON r.vehicle_id = b.vehicle_id
LEFT JOIN Electric_scooter es ON r.vehicle_id = es.vehicle_id;

/*Query1*/
SELECT v.brand, v.color, v.autonomy, v.daily_rental_cost, v.daily_insurance_cost, 
       CASE WHEN v.available THEN 'Available' ELSE 'Rented' END AS status,
       CASE 
            WHEN c.vehicle_id IS NOT NULL THEN 'Car'
            WHEN m.vehicle_id IS NOT NULL THEN 'Motorcycle'
            WHEN b.vehicle_id IS NOT NULL THEN 'Bicycle'
            WHEN e.vehicle_id IS NOT NULL THEN 'Electric Scooter'
       END AS category
FROM Vehicle v
LEFT JOIN Car c ON v.vehicle_id = c.vehicle_id
LEFT JOIN Motorcycle m ON v.vehicle_id = m.vehicle_id
LEFT JOIN Bicycle b ON v.vehicle_id = b.vehicle_id
LEFT JOIN Electric_scooter e ON v.vehicle_id = e.vehicle_id
ORDER BY category;


/*Query4*/
SELECT
    'Car' AS category,
    SUM(CASE WHEN c.vehicle_id IS NOT NULL THEN r.cost END) AS total_profit_cars,
    'Motorcycle' AS category,
    SUM(CASE WHEN m.vehicle_id IS NOT NULL THEN r.cost END) AS total_profit_motorcycles,
    'Bicycle' AS category,
    SUM(CASE WHEN b.vehicle_id IS NOT NULL THEN r.cost END) AS total_profit_bicycles,
    'Electric Scooter' AS category,
    SUM(CASE WHEN es.vehicle_id IS NOT NULL THEN r.cost END) AS total_profit_scooters
FROM Rental r
LEFT JOIN Car c ON r.vehicle_id = c.vehicle_id
LEFT JOIN Motorcycle m ON r.vehicle_id = m.vehicle_id
LEFT JOIN Bicycle b ON r.vehicle_id = b.vehicle_id
LEFT JOIN Electric_scooter es ON r.vehicle_id = es.vehicle_id
WHERE r.rental_date BETWEEN '2020-01-01' AND '2023-01-01';