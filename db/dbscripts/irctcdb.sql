-- ----------------------- IRCTC DB SCHEMA -------------------------

-- ------------------------- DATABASE ---------------------------
DROP DATABASE IF EXISTS irctcDB;
CREATE DATABASE irctcDB;
USE irctcDB;

-- ------------------------- ENTITIES ---------------------------
-- STATION
DROP TABLE IF EXISTS Station;
CREATE TABLE Station(
	station_id varchar(5),
    station_name varchar(32) NOT NULL,
    PRIMARY KEY(station_id)
);

-- TRAIN
 DROP TABLE IF EXISTS Train;
 CREATE TABLE Train(
	train_id varchar(5),
    train_name varchar(32) NOT NULL,
    PRIMARY KEY(train_id)
 );
 
 -- SEATS
 DROP TABLE IF EXISTS Seats;
 CREATE TABLE Seats(
	train_id varchar(5),
    seat_type varchar(5) NOT NULL,
    num_seats int,
    seat_fare int,
    PRIMARY KEY(train_id, seat_type),
    FOREIGN KEY(train_id) REFERENCES Train(train_id)
 );
 
 -- AVAILABLESEATSLOG
 DROP TABLE IF EXISTS AvailableSeatsLog;
 CREATE TABLE AvailableSeatsLog(
	train_id varchar(5),
    station_id varchar(5),
    seat_type varchar(5),
    day1 int,
    day2 int,
    day3 int,
    day4 int,
    day5 int,
    day6 int,
    PRIMARY KEY(train_id, station_id, seat_type),
    FOREIGN KEY(train_id) REFERENCES Train(train_id),
    FOREIGN KEY(station_id) REFERENCES Station(station_id)
 );
 
 -- Date Mapping
DROP TABLE IF EXISTS DateMap;
 CREATE TABLE DateMap (
    day_id varchar(10),
	date varchar(11) UNIQUE,
    PRIMARY KEY(day_id)
 );
 
 -- Passenger
 DROP TABLE IF EXISTS Passenger;
 CREATE TABLE Passenger (
	pnr int(8),
    passenger_name varchar(32) NOT NULL,
    date_of_birth varchar(11) NOT NULL,
    gender varchar(1) NOT NULL,
    FOREIGN KEY(pnr) REFERENCES Ticket(pnr)
 );
 
 -- ------------------------- RELATIONSHIPS ---------------------------
 
 -- STOPSAT
 DROP TABLE IF EXISTS RouteLog;
 CREATE TABLE RouteLog (
	train_id varchar(5),
    station_id varchar(5),
    station_number int NOT NULL,
    cumulative_distance int NOT NULL,
    FOREIGN KEY(train_id) REFERENCES Train(train_id),
    FOREIGN KEY(station_id) REFERENCES Station(station_id)
 ); 
    -- arrival_time time NOT NULL,
    -- departure_time time NOT NULL,
    
    
-- Books Train Seats
DROP TABLE IF EXISTS Ticket;
CREATE TABLE Ticket (
	pnr int(8),
    date varchar(11) NOT NULL,
    train_id varchar(5) NOT NULL,
    source_station_id varchar(5) NOT NULL,
    destination_station_id varchar(5) NOT NULL,
    num_passengers int CHECK (num_passengers > 0),
    seat_type varchar(5) DEFAULT "nonAC",
    ticket_fare int NOT NULL,
	PRIMARY KEY(pnr),
    FOREIGN KEY(train_id) REFERENCES Train(train_id),
    FOREIGN KEY(source_station_id) REFERENCES Station(station_id),
    FOREIGN KEY(destination_station_id) REFERENCES Station(station_id)
);
	-- journey_start_time
    -- journey_end_time