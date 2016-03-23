/**
 * Created by polesskiy on 18.03.16.
 */
class SensorData {
    constructor(id, date, data) {
        this.id = id;
        this.date = new Date(date);
        this.data = data;
    }
}

SensorData.fromObj = function (obj) {
    return new SensorData(obj.id, obj.date, obj.data);
}
