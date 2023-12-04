-- ------------------------- DATABASE ---------------------------
USE irctcDB;

-- ------------------------ INSERTIONS --------------------------

-- STATION
DELETE FROM Station;
INSERT INTO Station (station_id, station_name) VALUES 
	("S1", "Agra"),
    ("S2", "Bombay"),
    ("S3", "Calcutta"),
    ("S4", "Delhi"),
    ("S5", "Ellora"),
    ("S6", "Fattehpur"),
    ("S7", "Gandhinagar"),
    ("S8", "Hyderabad"),
    ("S9", "Indore"),
    ("S10", "Jaipur"),
    ("S11", "Kanpur")
;

-- TRAIN
DELETE FROM Train;
INSERT INTO Train (train_id, train_name) VALUES 
	("T1", "Arrow"),
    ("T2", "Bullet"),
    ("T3", "Cannon")
;

-- SEATS
DELETE FROM Seats;
INSERT INTO Seats (train_id, seat_type, num_seats, seat_fare) VALUES 
	("T1", "AC", 5, 150),
    ("T1", "nonAC", 7, 100),
    ("T2", "AC", 2, 180),
    ("T2", "nonAC", 3, 120),
    ("T3", "AC", 4, 130),
    ("T3", "nonAC", 6, 80)
;
 
-- AVAILABLESEATS
DELETE FROM AvailableSeatsLog;
INSERT INTO AvailableSeatsLog (train_id, station_id, seat_type) VALUES 
	("T1", "S1", "AC"),
    ("T1", "S2", "AC"),
    ("T1", "S3", "AC"),
    ("T1", "S4", "AC"),
    ("T1", "S5", "AC"),
    ("T1", "S6", "AC"),
    
    ("T1", "S1", "nonAC"),
    ("T1", "S2", "nonAC"),
    ("T1", "S3", "nonAC"),
    ("T1", "S4", "nonAC"),
    ("T1", "S5", "nonAC"),
    ("T1", "S6", "nonAC"),
    
    ("T2", "S7", "AC"),
    ("T2", "S3", "AC"),
    ("T2", "S4", "AC"),
    ("T2", "S5", "AC"),
    ("T2", "S8", "AC"),
    
    ("T2", "S7", "nonAC"),
    ("T2", "S3", "nonAC"),
    ("T2", "S4", "nonAC"),
    ("T2", "S5", "nonAC"),
    ("T2", "S8", "nonAC"),
    
    ("T3", "S9", "AC"),
    ("T3", "S11", "AC"),
    ("T3", "S4", "AC"),
    ("T3", "S3", "AC"),
    ("T3", "S2", "AC"),
    ("T3", "S10", "AC"),
    
    ("T3", "S9", "nonAC"),
    ("T3", "S11", "nonAC"),
    ("T3", "S4", "nonAC"),
    ("T3", "S3", "nonAC"),
    ("T3", "S2", "nonAC"),
    ("T3", "S10", "nonAC")
;

UPDATE AvailableSeatsLog SET day1 = (
	SELECT num_seats
    FROM Seats
    WHERE train_id = "T3"
    AND seat_type = "nonAC"
)
WHERE train_id = "T3"
AND seat_type = "nonAC";


UPDATE AvailableSeatsLog SET day6 = day1;

-- DATEMAP
DELETE FROM DateMap;
INSERT INTO DateMap (day_id, date) VALUES 
	("day1", "2019-08-20"),
    ("day2", "2019-08-21"),
    ("day3", "2019-08-22"),
    ("day4", "2019-08-23"),
    ("day5", "2019-08-24"),
    ("day6", "2019-08-25"),
    ("day7", "2019-08-26")
;
  
-- ROUTELOG
DELETE FROM RouteLog;
INSERT INTO RouteLog (train_id, station_id, station_number, cumulative_distance) VALUES 
	("T1", "S1", 1, 0),
    ("T1", "S2", 2, 10),
    ("T1", "S3", 3, 30),
    ("T1", "S4", 4, 40),
    ("T1", "S5", 5, 70),
    ("T1", "S6", 6, 110),
    
    ("T2", "S7", 1, 0),
    ("T2", "S3", 2, 20),
    ("T2", "S4", 3, 40),
    ("T2", "S5", 4, 60),
    ("T2", "S8", 5, 80),
    
    ("T3", "S9", 1, 0),
    ("T3", "S11", 2, 10),
    ("T3", "S4", 3, 20),
    ("T3", "S3", 4, 30),
    ("T3", "S2", 5, 40),
    ("T3", "S10", 6, 50)
;