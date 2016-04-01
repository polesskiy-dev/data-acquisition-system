/**
 * Sensor
 */
class Sensor {
    constructor(id, name, additionalInfo, sensorDataList) {
        this.id = id;
        this.name = name;
        this.additionalInfo = additionalInfo;
        this.sensorDataList = sensorDataList;
    }
}

/**
 * Narrow cast Object to Sensor
 * @param obj
 * @returns {Sensor}
 */
Sensor.fromObj = function (obj) {
    //narrow cast internal objects to SensorData
    var parsedSensorDataList = obj.sensorDataList.map(item=>SensorData.fromObj(item));

    return new Sensor(obj.id, obj.name, obj.additionalInfo, parsedSensorDataList);
};


