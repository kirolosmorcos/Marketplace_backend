# Marketplace Backend

A Java-based marketplace backend built with **Spring Boot**, **Spring Data JPA**, **Hibernate**, and **PostgreSQL**. The project implements RESTful APIs and a custom horizontal database sharding strategy to distribute marketplace item records across two independent PostgreSQL databases.

## Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.4.5
- **ORM:** Hibernate / Spring Data JPA
- **Databases:** PostgreSQL
- **Build Tool:** Maven
- **Architecture:** Layered Architecture

## Key Features

- RESTful backend APIs for marketplace operations.
- User, item, and order-related backend functionality.
- Two independent PostgreSQL database connections.
- Custom horizontal sharding for marketplace item records.
- Cross-database item retrieval, updates, and deletion.
- Repository abstraction that hides database routing from the service layer.

## Horizontal Database Sharding

One of the main features of this project is the implementation of **horizontal database sharding** across two independent PostgreSQL databases.

Instead of storing all marketplace items in a single database, item records are distributed between DB1 and DB2 according to their IDs.

### Architecture

- Configured two independent PostgreSQL DataSources.
- Created separate EntityManagerFactory configurations for each database.
- Configured independent transaction managers.
- Implemented dedicated Spring Data JPA repositories for each database.
- Introduced a shared repository abstraction to handle database routing.

### Sharding Strategy

Items are distributed based on ID parity:

| Item ID | Database |
|---|---|
| Even ID | DB1 |
| Odd ID | DB2 |

### Data Access and Routing

The shared `ItemRepository` handles communication with both databases and provides:

- **Insert:** Assigns an item ID and routes the item to the corresponding database.
- **Read:** Searches across both databases when retrieving items.
- **Retrieve All:** Combines item records from both databases.
- **Update:** Identifies the database containing the item and performs the update.
- **Delete:** Locates and deletes the item from the appropriate database.
- **Search:** Combines results from both databases for supported queries.

The service layer interacts with the shared repository abstraction rather than directly accessing individual database repositories. This separates business logic from the sharding implementation.

## Project Structure

```text
src/main/java/com/example/Market_place/
│
├── DAL_Layer/
│   ├── DB1/
│   │   ├── config/
│   │   └── repository/
│   │
│   ├── DB2/
│   │   ├── config/
│   │   └── repository/
│   │
│   ├── Models/
│   └── Repositories/
│       └── Interfaces/
│
├── BLL_Layer/
│
├── Controllers/
│
└── ...
```

The project follows a layered structure that separates database access, business logic, and API controllers.

## Running the Project

### Prerequisites

- Java 17 or later
- Maven
- Two PostgreSQL databases

### Configuration

Configure the two PostgreSQL connections in `application.properties`:

- Primary database: `spring.datasource.*`
- Secondary database: `second.datasource.*`

Provide database credentials through environment variables or another secure configuration mechanism. Do not commit passwords or secrets to GitHub.

### Build and Run

Clone the repository:

```bash
git clone https://github.com/kirolosmorcos/Marketplace_backend.git
cd Marketplace_backend
```

Build the project:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

The application is configured to run on port `8084`.

## API Endpoints

Some of the available item endpoints include:

| Method | Endpoint | Description |
|---|---|---|
| POST | `/item/add/{userId}` | Add an item |
| GET | `/item` | Retrieve items |
| GET | `/item/{id}` | Retrieve an item by ID |
| GET | `/item/{userId}/items` | Retrieve a user's items |
| GET | `/item/statistics/{userId}` | Retrieve item statistics |

## Future Improvements

- Introduce concurrency-safe distributed ID generation.
- Improve transaction coordination across databases.
- Add automated integration tests for database routing.
- Add API documentation using OpenAPI / Swagger.
- Introduce Docker Compose for easier local deployment.

---

**Author:** Kirolos Morcos

**Repository:** [Marketplace Backend](https://github.com/kirolosmorcos/Marketplace_backend)
