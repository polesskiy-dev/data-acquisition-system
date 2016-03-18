/**
 * Created by polesskiy on 18.03.16.
 */
class Sensor {
    constructor(id, name, additionalInfo, sensorDataList) {
        this.id = id;
        this.name = name;
        this.additionalInfo = additionalInfo;
        this.sensorDataList = sensorDataList;
    }
}

Sensor.fromObj = function (obj) {
    var parsedSensorDataList = [];

    obj.sensorDataList.forEach(
        function (item, i, sensorDataList) {
            parsedSensorDataList.push("hello");
            //    TODO implement parsing objects to sensor-data
        }
    );

    return new Sensor(obj.id, obj.name, obj.additionalInfo, obj.parsedSensorDataList);
}

/*Sensor.fromJson = function (json){
 var obj = JSON.parse (json);
 return new Sensor (obj.id, obj.name, obj.additionalInfo, obj.sensorDataList);
 };*/