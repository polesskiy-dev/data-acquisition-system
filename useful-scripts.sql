#
SHOW TABLES;

#USERS
SELECT * FROM USERS;

#SENSORS
SELECT * FROM SENSORS;

SELECT * FROM SENSORS WHERE SENSORS.LOGIN = 'user@test' AND SENSORS.ID=16;

#SENSOR_DATA
SELECT * FROM SENSORS_DATA;