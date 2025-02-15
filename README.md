# Product Service

This is a simple Spring Boot application for managing products, backed by a PostgreSQL database. The project is structured into different branches to showcase various configurations.

## Branches
- `main`: Contains the base application with PostgreSQL as the primary database.
- `h2-database`: Uses H2 as the database instead of PostgreSQL, useful for local development or testing without external dependencies.
- `cloud-foundry-deploy`: Contains necessary adjustments for deploying the application on Cloud Foundry, including Cloud Foundry-specific data source configuration.
- `full-stack`: Integrates the Angular frontend as a Git submodule.

## Running the Application

### Standard Run

To run the application locally, follow these steps:

**1. Set up the Database**: 
You need to set up the PostgreSQL database manually. Create a database named `test-db` and configure the credentials as required in `application.properties`.

**2. Build the project:** If you are using Maven:
```sh
./mvnw clean install
```

**3. Run the Spring Boot application:** You can run the application in your IDE (e.g., IntelliJ IDEA) by clicking on the Run button or by using the Maven Wrapper from the command line:
```sh
./mvnw spring-boot:run
```

### Run with Docker Compose

Run the following command to start the application and PostgreSQL database using Docker Compose:
```sh
docker-compose up --build
```

If you want, you may set the following environment variables in the `docker-compose.yml` file:

- `SPRING_PROFILES_ACTIVE`: Use "test" for regular tests or "seed" to populate the database with sample data.




## Testing the Application

After starting the application, you can use tools like Postman or cURL to interact with the API.

**1. Add a Product**

POST `http://localhost:8080/api/products`

Request Body:
```json
{
  "id": 1,
  "name": "Test Product Name",
  "description": "Test Product description",
  "price": 10.5
}
```

**2. Get All Products**

GET `/api/products`

Example cURL Command:

```bash
curl http://localhost:8080/api/products
```
