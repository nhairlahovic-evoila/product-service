# Product Service

This is a simple Spring Boot application for managing products, backed by a PostgreSQL database. The project is structured into different branches to showcase various configurations.

## Branches
- `main`: Contains the base application with PostgreSQL as the primary database.
- `h2-database`: Uses H2 as the database instead of PostgreSQL, useful for local development or testing without external dependencies.
- `cloud-foundry-deploy`: Contains necessary adjustments for deploying the application on Cloud Foundry, including Cloud Foundry-specific data source configuration.
