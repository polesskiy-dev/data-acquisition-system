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
        function (item) {
            parsedSensorDataList.push(SensorData.fromObj(item));
        }
    );

    return new Sensor(obj.id, obj.name, obj.additionalInfo, parsedSensorDataList);
};

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

    var sensorDataArr = [[0, 0,], [1, 10], [2, 23], [10, 17]];
    divPanelBody.appendChild(createChartFromSensorData(sensorDataArr, sensor.name));

    divPanel.appendChild(divPanelHeading);
    divPanel.appendChild(divPanelBody);
    divPanel.appendChild(divPanelFooter);

    divGridLayout.appendChild(divPanel);

    return divGridLayout;
}

var createChartFromSensorData = function (sensorDataArr, paramName) {
    var data = new google.visualization.DataTable();
    data.addColumn('number', '');
    data.addColumn('number', '');

    data.addRows(sensorDataArr);

    var options = {
        hAxis: {
            title: 'Time'
        },
        vAxis: {
            title: paramName
        }
    };

    var divChart = document.createElement('div');
    var chart = new google.visualization.LineChart(divChart);

    chart.draw(data, options);

    return divChart;
}