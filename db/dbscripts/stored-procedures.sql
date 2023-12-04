-- ------------------------- DATABASE ---------------------------
USE irctcDB;


-- ------------------------ PROCEDURES --------------------------
-- Get All Records from a Table
DELIMITER $$
CREATE PROCEDURE getAllRecords(TableName varchar(35))
BEGIN
	SELECT * FROM TableName;
END $$
DELIMITER ;

-- Procedure to get Source Station of a Train
DROP PROCEDURE IF EXISTS getSource; 
DELIMITER $$
CREATE PROCEDURE getSource(train_id varchar(5))
BEGIN
	SELECT RouteLog.station_id 
    FROM RouteLog
    HAVING RouteLog.station_number = (
		SELECT MIN(RouteLog.station_number)
        FROM RouteLog
        WHERE RouteLog.train_id = train_id
    ) AND RouteLog.train_id = train_id;
END $$
DELIMITER ;

-- Procedure to get Destination Station of a Train
DROP PROCEDURE IF EXISTS insertdefaultroles; 
DELIMITER //
 CREATE PROCEDURE get_source(
	IN train_id int,
    OUT station_id varchar(5)
 )
 BEGIN
	-- Source Station of a Train
    SELECT Routes.station_id 
    FROM Routes
    HAVING Routes.station_id = (
		SELECT MAX(Routes.station_number)
        FROM Routes
        WHERE Routes.train_id = train_id
    );
 END
//
DELIMITER ;

-- Procedure to get Route of a Train

-- Procedure to get train ids from route map given station
DELIMITER  $$
CREATE PROCEDURE getTrains(source_station_id varchar(5), destination_station_id varchar(5))
proc_label: BEGIN
	SELECT *
    FROM Train
    WHERE Train.train_id = (
		SELECT train_id
        FROM RouteLog AS source
        JOIN RouteLog AS destination
        USING (train_id)
        WHERE source.station_number <= destination.station_number
        AND source.station_id = source_station_id
        AND destination.station_id = destination_station_id
    )
    ORDER BY Train.train_name;
    LEAVE proc_label;
END $$
DELIMITER ;