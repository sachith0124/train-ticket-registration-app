-- ------------------------- DATABASE ---------------------------
USE irctcDB;

-- ------------------------ INSERTIONS --------------------------
-- -------------- Importing Data from CSV Files -------------------

-- STATION
LOAD DATA LOCAL INFILE 'C:/Users/Sachith_Janjirala/OneDrive - EPAM/RD_Training/IRCTC/db/csvs/station.csv'
INTO TABLE Station
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(station_id, station_name);

LOAD DATA LOCAL INFILE 'C:/Users/Sachith_Janjirala/OneDrive - EPAM/RD_Training/IRCTC/db/csvs/station.csv' 
INTO TABLE Station 
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
IGNORE 1 ROWS;

-- TRAIN

-- SEATS
 
-- AVAILABLESEATS
  
-- STOPSAT