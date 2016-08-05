# checkListService

To build the project run the following command:
  - mvn clean install
  - mvn spring-boot:run (To run the application)
  - By default, the application is configured to run on port 8082. This can be changed the application.properties file.

The application uses H2DB as the database. By default, the database is empty. However, on startup, a database file will be created and persisted on your machine at ~/data/testdb.mv.db

Swagger which is a framework for API, has been integrated with the project. Once the application is running, you can access the Swagger UI http://localhost:8082/swagger-ui.html

To learn more about Swagger go to http://swagger.io/

In the Swagger UI, you will see a list of available APIs and be able to run the API from the page. Below are the available end points as well.

  -GET /checkList/shared/{userName}
    -Use to retrieve all tasks shared for user.
  
  -GET /checkList/userName/{userName}
    -Use to get all the tasks for the user.
  
  -POST /checkList/task
    -Use to create an task entry. Takes a Task object as part of the request.
  
  -PUT /checkList/task
    -Use to update a task. Takes a Task object as part of the request.
  
  -DELETE /checkList/task/{id}
    -Use to delete a task given the task id.
  
  -DELETE /checkList/userName/{userName}
    -Use to delete all tasks for a given user

