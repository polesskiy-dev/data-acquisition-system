/**
 * Created by polesskiy on 18.03.16.
 */
class SensorData {
    constructor(date, data) {
        this.date = new Date(date);
        this.data = data;
    }
}

SensorData.fromObj = function (obj) {
    return new SensorData(obj.date, obj.data);
}
