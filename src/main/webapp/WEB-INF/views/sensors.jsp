<%--
  Created by IntelliJ IDEA.
  User: polesskiy
  Date: 18.03.16
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Data visualisation</title>
    <!--styles-->
    <link rel="stylesheet" href="../resources/styles/bootstrap.css">
    <!--scripts-->
    <script src="../resources/scripts/jquery-2.2.1.js"></script>
    <script src="../resources/scripts/bootstrap.js"></script>
    <script src="../resources/scripts/sensor.js"></script>
    <script src="../resources/scripts/sensor-data.js"></script>
    <script src="../resources/scripts/chart.js"></script>
    <!--google charts-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <!--data from controller-->
    <script type="text/javascript">
        var sensorObjects = JSON.parse(${sensorsJSON});

        var sensors = sensorObjects.map(sensorObj = > Sensor.fromObj(sensorObj)
        )
        ;
        console.log(sensors);
    </script>

</head>
<body>
<div id="sensorsContainer" class="container">

    <!--navbar-->
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="/">DataAcq</a>
                    </div>
                    <div>
                        <ul class="nav navbar-nav">
                            <li><a href="/testuser@gmail.com/sensors.html">Sensors</a></li>
                            <li><a href="/about.html">About</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--sensors charts-->
    <div id="sensorsRow" class="row">
    </div>

    <!--add new sensor panel-->
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading">Add new sensor</div>
                <div class="panel-body">
                    <p>here user can add new sensor and redact its parameters</p>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
<script>
    //download charts lib
    google.charts.load('current', {packages: ['corechart', 'line']});
    google.charts.setOnLoadCallback(function () {
        sensors.forEach(
                function (sensor) {
                    $('#sensorsRow').append(sensor.constructSensorPanel());
                }
        );
    });
</script>
</html>

