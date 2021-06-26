
#Generic product CRUD application.

Connection to a database is required, if you prefer, run the command:

docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.23

The main class path:
com.student.StudentApplication

The application port is 8080

I recommend using java version 11.

For more information use the swagger documentation:
http://localhost:8080/swagger-ui.html