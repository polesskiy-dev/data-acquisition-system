# Demo Data acquisition system. #

System allow users to push data from their sensor nodes to central cloud DB,
visualize data on pretty charts.

For example, user has hardware temperature sensor,
he can connect it to system by simple API for pushing sensors data.

Example, save temperature-sensor data in our system,
POST to /user/{user-login}/{sensor-name}/ this:
[{"date":1459364146000,"data":{"Temperature, C":"63","Humidity, %":"59"}}]

User can register new sensor nodes in system, see statistics and change settings of sensors belonging to him.