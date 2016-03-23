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
    var parsedSensorDataList = obj.sensorDataList.map(
        function (item) {
            parsedSensorDataList.push(SensorData.fromObj(item));
        }
    );

    return new Sensor(obj.id, obj.name, obj.additionalInfo, parsedSensorDataList);
};

/**
 * Create panel with charts of sensors data
 * @param sensor
 * @returns {Element}
 */
var constructSensorPanel = function (sensor) {
    const GRID_LAYOUT_CLASS_HALF = 'col-lg-6 col-md-6 col-sm-12 col-xs-12';

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

    divPanelHeading.innerHTML = sensor.name;
    divPanelFooter.innerHTML = sensor.additionalInfo;

    /*
     Test chart data
     */


    var chartDataArr = Object.keys(sensor.sensorDataList[0].data).map(
        function (parameterName) {
            var parameterValues = sensor.sensorDataList.map(
                function (sensorData) {
                    return ([new Date(sensorData.date), sensorData.data[parameterName]]);
                });

            return new ChartData(parameterName, parameterValues);
        }
    )

    console.log(chartDataArr);

    var testChartData = chartDataArr[0];//new ChartData("test value", [[new Date(1458322515000), 0], [new Date(1458322516000), 10], [new Date(1458322517000), 13], [new Date(1458322518000), 17]]);

    divPanelBody.appendChild(testChartData.createChart());

    divPanel.appendChild(divPanelHeading);
    divPanel.appendChild(divPanelBody);
    divPanel.appendChild(divPanelFooter);

    divGridLayout.appendChild(divPanel);

    return divGridLayout;
}

