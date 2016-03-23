/**
 * Chart data
 * contains data for drawing chart on canvas
 */
class Chart {

    constructor(parameterName, parametersArray) {
        this.parameterName = parameterName;
        this.parametersArray = parametersArray;
    }

    drawChart() {
        const X_AXIS_DATA_INDEX = 0;
        const Y_AXIS_DATA_INDEX = 1;
        const NAN_VALUE_STUB = 0;

        var data = new google.visualization.DataTable();

        data.addColumn('datetime', 'Date');
        data.addColumn('number', this.parameterName);

        data.addRows(this.parametersArray.map(
            function (item) {
                var value = parseInt(item[Y_AXIS_DATA_INDEX]);
                return [item[X_AXIS_DATA_INDEX], isNaN(value) ? NAN_VALUE_STUB : value];
            }
        ));

        //set charts options
        var options = {
            title: this.parameterName,
            width: '100%',
            legend: 'none',
            hAxis: {
                title: 'Date'
            },
            vAxis: {
                //title: this.parameterName
            }
        };

        //create charts container
        var container = document.createElement('div');
        var chart = new google.visualization.LineChart(container);

        chart.draw(data, options);

        return container;
    }
}

