# Sporty Shoes

Pre-requisites:
1. MySQL 8 and above
2. Create demo database in MySQL
   ```
   create database demo;
   ```
3. Run the application and then login into mysql to create admin user
    ```
   use demo;
   insert into user values(0, 'men', 'admin', '$2a$10$lc7uLt9ZV3kRT5D38iQIwu.fdKXBh36c5YtWUzIRDNJkZVbGgUria', 'admin', 'ADMIN');
   ```
   admin username/password is `admin/admin`
4. Insert shoe info manually through mysql. Example:
    ```
   insert into shoe(id, name, category, imagePath, price) values (1, 'Adidas', 'men', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/626aefae60f647a496e6aef900bab3bf_9366/ADIDAS_ADIZERO_SL_RUNNING_SHOES_White_GV9095_01_standard.jpg',  160.00);
   ```

Start the application by running SportyShoesWebApplication class. Visit http://localhost:8080 to access the 
Sporty Shoes e-commerce site!

**Design doc:**<br/>
This project is based on Spring Boot 2.2.7
We are following Spring MVC to render the view on frontend.

**Model:**
Following are the models used to capture information from database and pass to the view.

**User:**
Represents registered users of the application

```
{
    "id": 1,
    "name": "John Smith",
    "username": "john",
    "password": "john123",
    "category": "men",
    "role": "USER"
}
```

**Shoe:**
Represents Shoe product

```
{
    "id": 1,
    "name": "Adidas Jumper",
    "category": "men",
    "imagePath": "http://url",
    "price": 150.00
}
```

**Purchase:**
Represents purchase made by a user

```
{
    "id": 1,
    "shoeId": 1,
    "username": "john",
    "purchaseDate": "2023-01-01",
    "category": "men"
}
```

**Report:**
Represents purchase report for admin

```
{
    "shoeName": "Adidas Jumper",
    "price": 150.00,
    "quantity": 1,
    "datePurchased": "2023-01-01",
    "category": "men"
}
```

**View:**
Views are based on Thymeleaf and are html files located under `src/main/resources/templates` folder

**Controller:**

**LoginController**: contains login, register and logout endpoints<br/>
`/signup` - opens signup view<br/>
`/login` - opens login view<br/>
`/logout` - to clear session and logout user from current session<br/>
`/register` - registers new user<br/>

**ShoeController:**<br/>
`/` - opens shoes view with filter options, logout button, manage option (if current user is admin)<br/>
/`{id}/buy` - buys shoe represented by {id} by current logged in user<br/>

**AdminController:**<br/>
`/admin` - opens admin view<br/>
`/manage/users` - view to manage existing users of the application<br/>
`/manage/shoes` - view to manage shoes inventory<br/>
`/manage/purchases` - view to verify purchases made by existing users<br/>
`/manage/password` - view to change password<br/>
`/manage/shoes/change` - to update shoes information<br/>
`/manage/shoes/password` - to change existing admin password<br/>

**JSON API spec** is available at http://localhost:8080/v2/api-docs <br/>
Visual representation of api docs is available at http://localhost:8080/swagger-ui/ <br/>

**Design walkthrough:**<br/>
This is a Spring Boot application and the entry point is in SportyShoesWebApplication.java class<br/>
Data is stored in MySQL relational database with database named demo.<br/>

**Repository:**<br/>
All the repository classes in the project are interfaces to data access layer (database) through Hibernate.<br/>

**Service:**<br/>
All the service classes act as an abstraction to access data layer so in future when a new data source is introduced, 
there's minimal code change.<br/>

**Controller:**<br/>
Controllers are entry points for user requests that either return a View to render a page OR to access/update 
information in the database through use of Service layer.
