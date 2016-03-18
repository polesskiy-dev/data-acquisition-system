/**
 * Created by polesskiy on 18.03.16.
 */

var drawBackgroundColor = function () {
    var data = new google.visualization.DataTable();
    data.addColumn('number', 'X');
    data.addColumn('number', 'testSensor');

    data.addRows([
        [1, 50],
        [2, 55],
        [3, 45]
    ]);

    var options = {
        hAxis: {
            title: 'Time'
        },
        vAxis: {
            title: 'Temperature'
        }
    };

    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
    chart.draw(data, options);

    var chart1 = new google.visualization.LineChart(document.getElementById('chart_div1'));
    chart1.draw(data, options);
}
