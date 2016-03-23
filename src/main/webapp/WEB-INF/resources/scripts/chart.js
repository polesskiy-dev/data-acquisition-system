/**
 * Created by polesskiy on 18.03.16.
 */

class Chart {

    constructor(parameterName, parametersArray) {
        this.parameterName = parameterName;
        this.parametersArray = parametersArray;
    }

    drawChart() {
        var data = new google.visualization.DataTable();

        data.addColumn('datetime', 'Date');
        //data.addColumn(Number.isInteger(this.parametersArray[0][1]) ? 'number' : 'string', this.parameterName);
        data.addColumn('number', this.parameterName);

        data.addRows(this.parametersArray.map(
            function (item) {
                var value = parseInt(item[1]);
                console.log([item[0], isNaN(value) ? 0 : value]);
                //return isNaN() ? 0:parseInt(item);

                return [item[0], isNaN(value) ? 0 : value];
            }
        ));

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

        var container = document.createElement('div');
        var chart = new google.visualization.LineChart(container);

        chart.draw(data, options);

        return container;
    }
}

