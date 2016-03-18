/**
 * Created by polesskiy on 18.03.16.
 */
class SensorData {
    constructor(id, date, data) {
        this.id = id;
        this.date = date;
        this.data = data;
    }

    get dataNames() {
        return "hello";
    }
}

SensorData.fromObj = function (obj) {
    return new SensorData(obj.id, obj.date, obj.data);
}
