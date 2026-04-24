# Pricing API - Technical Test

This project implements a REST API to retrieve the applicable price for a product
based on a given date, brand, and product identifier.

The system applies business rules to determine the correct price when multiple price records overlap,
selecting the one with the highest priority.

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 in-memory database
- JUnit 5
- MockMvc

1. Clone the repository:
   git clone <repo-url>

2. Navigate into the project:
   cd demo-testTask-ropa

3. Run the application:
   mvn spring-boot:run

To run the tests:
   mvn clean test

API Endpoint:
    GET /prices

Query parameters:
- productId (Long)
- brandId (Long)
- date (ISO format: yyyy-MM-ddTHH:mm:ss)

Example request:
    GET /prices?productId=35455&brandId=1&date=2020-06-14T16:00:00

Example response:
    {
        "productId": 35455,
        "brandId": 1,
        "priceList": 2,
        "startDate": "2020-06-14T15:00:00",
        "endDate": "2020-06-14T18:30:00",
        "price": 25.45
    }

- The system retrieves all price records matching:
    - productId
    - brandId
    - application date within startDate and endDate

- If multiple prices are found, the one with the highest priority is selected.

- Only one price is returned per request.

The application uses an H2 in-memory database.

Initial data is loaded via data.sql and includes overlapping price records to validate priority selection.

The following scenarios are covered:

1. 2020-06-14 10:00 → Price = 35.50
2. 2020-06-14 16:00 → Price = 25.45
3. 2020-06-14 21:00 → Price = 35.50
4. 2020-06-15 10:00 → Price = 30.50
5. 2020-06-16 21:00 → Price = 38.95

The project follows a layered architecture:

- Controller → handles HTTP requests
- Service → contains business logic
- Repository → handles data access
- DTO → defines API responses

- Used Spring Data JPA derived queries to simplify repository logic
- Returned a single result using priority-based selection
- Used DTOs to avoid exposing entity models
- Used MockMvc for integration-style testing

Swagger UI available at:
http://localhost:8080/swagger-ui.html
