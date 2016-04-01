/**
 * Functions for sensor objects representation
 */

/**
 * Create panel from sensor object
 * Panel contains charts of sensors data
 * @param sensor sensor to be represented
 * @returns {Element} container with panel
 */
function createSensorChartsPanel(sensor) {
    const GRID_LAYOUT_CLASS_HALF = 'col-lg-6 col-md-6 col-sm-12 col-xs-12';


    /** Construct html template */
    var divGridLayout = document.createElement('div');
    divGridLayout.setAttribute('class', GRID_LAYOUT_CLASS_HALF);

    var divPanel = document.createElement('div');
    divPanel.setAttribute('class', 'panel panel-default');

    var divPanelHeading = document.createElement('div');
    divPanelHeading.setAttribute('class', 'panel-heading');

    var divPanelBody = createSensorChartsPanelBody(sensor);

    var divPanelFooter = document.createElement('div');
    divPanelFooter.setAttribute('class', 'panel-footer');

    divPanelHeading.innerHTML = "ID: " + sensor.id + ", " + sensor.name;
    divPanelFooter.innerHTML = sensor.additionalInfo;

    divPanel.appendChild(divPanelHeading);
    divPanel.appendChild(divPanelBody);
    divPanel.appendChild(divPanelFooter);

    divGridLayout.appendChild(divPanel);

    return divGridLayout;
}

/**
 * Creates Panel Body which contains rendered sensor
 * @param sensor sensor to be represented
 * @returns {Element} panel body with charts
 */
function createSensorChartsPanelBody(sensor) {
    const FIRST_SENSOR_DATA_INDEX = 0;

    var divPanelBody = document.createElement('div');
    divPanelBody.setAttribute('class', 'panel-body');

    /**
     Construct array of Charts obj from sensor,
     each Chart displays single parameter from SensorData obj array.
     Chart need data points array [[xAxisData1, yAxisData1],...,[xAxisDataN, yAxisDataN]] to be drawn.
     */
    var sensorDataParamNamesArr = Object.keys(sensor.sensorDataList[FIRST_SENSOR_DATA_INDEX].data);

    //each single parameter
    var chartDataArr = sensorDataParamNamesArr.map(parameterName=>
        //create chart from name and data points
        new Chart(parameterName, sensor.sensorDataList.map(sensorData=>
            //data point from Date and parameter value (get by key from Map struct obj)
            ([sensorData.date, sensorData.data[parameterName]])
        )));

    /** Draw charts and append them to panel body */
    chartDataArr.forEach(chartDataObj=> divPanelBody.appendChild(chartDataObj.drawChart()));

    return divPanelBody;
}



