My Calories App

### Prerequisites
1. Intellij
2. Java 17
3. MySQL8
4. Maven 3.8.6
5. Node v16.15.1

### Database
    I am using a locally running MySQL Server 8, for Development.  <br>
    Create a Schema and configure application properties to use your own datasource.
    Liquibase is used to automatically migrate DB

### Modules
1. Model <br>
    Contains the Model/Entity as well as the Repository classes needed to communicate with the DB <br>
    Important things to notice: The project is modularized, so you need to let the  Rest API Module know where 
    to find the Entity classes by using @EntityScan and where to find the JPA Repositories by using the 
    @EnableJpaRepositories annotation on a @Configuration Class

2. Rest <br>
   Contains the Rest Api implementation. Service classes use the Model Repositories to communicate to the Database.
   A Controller exposes paths for Http Requests to use those Services. This follows the MVC pattern.

3. Front-End
   Angular App

4. 