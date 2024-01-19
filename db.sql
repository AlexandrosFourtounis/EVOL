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



INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('1','Blue','Nissan','500','500','500','no')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('2','Black','Lamborghini','360','360','360','yes')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('3','White','Opel','550','550','550','no')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('4','Red','Ferrari','250','250','250','yes')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('5','Gray','Mercedes','700','700','700','no')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('6','Blue','Nissan','500','500','500','no')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('7','Black','Lamborghini','360','360','360','yes')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('8','White','Opel','550','550','550','no')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('9','Red','Ferrari','250','250','250','yes')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('10','Gray','Mercedes','700','700','700','no')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('11','Blue','Nissan','500','500','500','no')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('12','Black','Lamborghini','360','360','360','yes')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('13','White','Opel','550','550','550','no')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('14','Red','Ferrari','250','250','250','yes')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('15','Gray','Mercedes','700','700','700','no')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('16','Blue','Nissan','500','500','500','no')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('17','Black','Lamborghini','360','360','360','yes')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('18','White','Opel','550','550','550','no')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('19','Red','Ferrari','250','250','250','yes')

INSERT INTO  Vehicle (vehicle_id,color,brand,autonomy,daily_rental_cost,daily_insurance_cost,available) VALUES ('20','Gray','Mercedes','700','700','700','no')

INSERT INTO Report (report_id, malfunction_description, report_date, insurance_paid, repair_cost, customer_id, vehicle_id) VALUES ('1','Disko/Plato','2024-10-30','FALSE','600.0','1','10')

INSERT INTO Report (report_id, malfunction_description, report_date, insurance_paid, repair_cost, customer_id, vehicle_id) VALUES ('2','Zygostathmisi','2024-7-23','TRUE','100.0','2','2')

INSERT INTO Report (report_id, malfunction_description, report_date, insurance_paid, repair_cost, customer_id, vehicle_id) VALUES ('3','Mpataria','2024-01-19','FALSE','500.0','3','13')

INSERT INTO Report (report_id, malfunction_description, report_date, insurance_paid, repair_cost, customer_id, vehicle_id) VALUES ('4','Provolakia Omixlhs','2024-5-5','TRUE','30.0','4','14')

INSERT INTO Report (report_id, malfunction_description, report_date, insurance_paid, repair_cost, customer_id, vehicle_id) VALUES ('5','Skasmeno Lastixo','2024-03-25','FALSE','250.0','5','1')

INSERT INTO Rental (rental_id, first_name, last_name, rental_date, duration, cost, customer_id, vehicle_id) VALUES ('101','akis','papakis','2022-04-16','10','450','1','1')

INSERT INTO Rental (rental_id, first_name, last_name, rental_date, duration, cost, customer_id, vehicle_id) VALUES ('202','manos','papas','2023-08-11','5','1400','2','2')

INSERT INTO Rental (rental_id, first_name, last_name, rental_date, duration, cost, customer_id, vehicle_id) VALUES ('303','ariadni','mixail','2023-02-18','2','84','3','3')

INSERT INTO Rental (rental_id, first_name, last_name, rental_date, duration, cost, customer_id, vehicle_id) VALUES ('404','charalabos','arampatzis','2024-01-02','1','470','4','4')

INSERT INTO Rental (rental_id, first_name, last_name, rental_date, duration, cost, customer_id, vehicle_id) VALUES ('505','aggela','eftimiou','2023-06-06','3','720','5','5')

INSERT INTO  car (vehicle_id,registration_number,type,number_of_passengers) VALUES ('1','123','porshe','4')

INSERT INTO  car (vehicle_id,registration_number,type,number_of_passengers) VALUES ('2','1232','fiat panda','2')

INSERT INTO  car (vehicle_id,registration_number,type,number_of_passengers) VALUES ('3','1323','mercedes','4')

INSERT INTO  car (vehicle_id,registration_number,type,number_of_passengers) VALUES ('4','4123','lambo','2')

INSERT INTO  car (vehicle_id,registration_number,type,number_of_passengers) VALUES ('5','155','skoda','4')

INSERT INTO  Motorcycle (vehicle_id,registration_number) VALUES ('6','1223')

INSERT INTO  Motorcycle (vehicle_id,registration_number) VALUES ('7','222')

INSERT INTO  Motorcycle (vehicle_id,registration_number) VALUES ('8','12223')

INSERT INTO  Motorcycle (vehicle_id,registration_number) VALUES ('9','12238')

INSERT INTO  Motorcycle (vehicle_id,registration_number) VALUES ('10','12239')

INSERT INTO  Bicycle (Vehicle_id,Special_number) VALUES ('11','123')

INSERT INTO  Bicycle (Vehicle_id,Special_number) VALUES ('12','456')

INSERT INTO  Bicycle (Vehicle_id,Special_number) VALUES ('13','789')

INSERT INTO  Bicycle (Vehicle_id,Special_number) VALUES ('14','101')

INSERT INTO  Bicycle (Vehicle_id,Special_number) VALUES ('15','102')

INSERT INTO  Electric_Scooter (Vehicle_id,Special_number) VALUES ('16','123')

INSERT INTO  Electric_Scooter (Vehicle_id,Special_number) VALUES ('17','456')

INSERT INTO  Electric_Scooter (Vehicle_id,Special_number) VALUES ('18','789')

INSERT INTO  Electric_Scooter (Vehicle_id,Special_number) VALUES ('19','101')

INSERT INTO  Electric_Scooter (Vehicle_id,Special_number) VALUES ('20','102')
