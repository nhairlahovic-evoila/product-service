# Product Service

This is a simple Spring Boot application for managing products, backed by a PostgreSQL database. The project is structured into different branches to showcase various configurations.

## Branches
- `main`: Contains the base application with PostgreSQL as the primary database.
- `h2-database`: Uses H2 as the database instead of PostgreSQL, useful for local development or testing without external dependencies.
- `cloud-foundry-deploy`: Contains necessary adjustments for deploying the application on Cloud Foundry, including Cloud Foundry-specific data source configuration.

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
