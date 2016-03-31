/**
 * Sensor object
 */
class Sensor {
    constructor(name, additionalInfo, sensorDataList) {
        this.name = name;
        this.additionalInfo = additionalInfo;
        this.sensorDataList = sensorDataList;
    }

    /**
     * Create panel with charts of sensors data
     * @returns {Element}
     */
    constructSensorPanel() {
        const GRID_LAYOUT_CLASS_HALF = 'col-lg-6 col-md-6 col-sm-12 col-xs-12';
        const FIRST_SENSOR_DATA_INDEX = 0;

        /*
         Construct html template
         */
        var divGridLayout = document.createElement('div');
        divGridLayout.setAttribute('class', GRID_LAYOUT_CLASS_HALF);

        var divPanel = document.createElement('div');
        divPanel.setAttribute('class', 'panel panel-default');

        var divPanelHeading = document.createElement('div');
        divPanelHeading.setAttribute('class', 'panel-heading');

        var divPanelBody = document.createElement('div');
        divPanelBody.setAttribute('class', 'panel-body');

        var divPanelFooter = document.createElement('div');
        divPanelFooter.setAttribute('class', 'panel-footer');

        divPanelHeading.innerHTML = this.name;
        divPanelFooter.innerHTML = this.additionalInfo;

        divPanel.appendChild(divPanelHeading);
        divPanel.appendChild(divPanelBody);
        divPanel.appendChild(divPanelFooter);

        divGridLayout.appendChild(divPanel);

        /*
         Construct array of Charts obj from this Sensor,
         each Chart displays single parameter from SensorData obj array.
         Chart need data points array [[xAxisData1, yAxisData1],...,[xAxisDataN, yAxisDataN]] to be drawn.
         */
        var sensorDataParamNamesArr = Object.keys(this.sensorDataList[FIRST_SENSOR_DATA_INDEX].data);

        //each single parameter
        var chartDataArr = sensorDataParamNamesArr.map(parameterName=>
            //create chart from name and data points
            new Chart(parameterName, this.sensorDataList.map(sensorData=>
                //data point from Date and parameter value (get by key from Map struct obj)
                ([sensorData.date, sensorData.data[parameterName]])
            )));

        /*
         Draw charts and append them to panel body
         */
        chartDataArr.forEach(chartDataObj=> divPanelBody.appendChild(chartDataObj.drawChart()));

        return divGridLayout;
    }

}
/**
 * Narrow cast object to Sensor
 * @param obj
 * @returns {Sensor}
 */
Sensor.fromObj = function (obj) {
    //narrow cast internal objects to SensorData
    var parsedSensorDataList = obj.sensorDataList.map(item=>SensorData.fromObj(item));

    return new Sensor(obj.name, obj.additionalInfo, parsedSensorDataList);
};


