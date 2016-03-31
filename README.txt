# Demo Data acquisition system. #

System allow users to push data from their sensor nodes to central cloud DB,
and visualize data on pretty charts.

For example, user has hardware temperature sensor,
he can connect it by simple API to push and store sensors data like:
[{"date":1459364146000,"data":{"Temperature, C":"63","Humidity, %":"59"}}]
in servers database.

User can register new sensor nodes in system, see statistics and change settings of sensors belonging to him.