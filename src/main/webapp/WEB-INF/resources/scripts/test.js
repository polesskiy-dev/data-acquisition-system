/**
 * Created by polesskiy on 23.03.16.
 */
class MapContainer {
    constructor() {
        this.maps = [{key: "value1"}, {key: "value1"}, {key: "value1"}];
    }

    updateMapErr() {
        this.maps.forEach(function (mapItem) {
            Object.keys(mapItem).forEach(function (key) {
                this.maps[key] += "additional value";
            })
        })
    }

    updateMapSuccess() {
        //fix!
        this.maps.forEach(mapItem=>
            Object.keys(mapItem).forEach(key=>
                this.maps[key] += "additional value"
            )
        )
    }
}

//I want to update each value of map in [maps]
new MapContainer().updateMapSuccess();
