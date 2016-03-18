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
    <script src="../resources/scripts/charts.js"></script>
    <!--google charts-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {packages: ['corechart', 'line']});
        google.charts.setOnLoadCallback(drawBackgroundColor);
    </script>

</head>
<body>
<div class="container">

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

    <!--test sensor 1-->
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Sensor name
                </div>
                <div id="testSensorPanelBody" class="panel-body">
                    <div id="chart_div"></div>
                    <div id="chart_div1"></div>
                </div>
                <div class="panel-footer">
                    Sensor additional info
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>

</script>
</html>

