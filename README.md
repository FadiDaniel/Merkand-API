<div align="center">
  
  # Merkand API - Inventory Management System 🚀
  
  **[English](README.md) | [Español](README.es.md)**
  
  [![Java](https://img.shields.io/badge/Java-21-007396?style=flat&logo=openjdk)](https://openjdk.org/)
  [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0-6DB33F?style=flat&logo=springboot)](https://spring.io/projects/spring-boot)
  [![H2 Database](https://img.shields.io/badge/H2-Database-4169E1?style=flat)](https://www.h2database.com/)
  [![MapStruct](https://img.shields.io/badge/MapStruct-1.6.2-orange?style=flat&logo=mapstruct)](https://mapstruct.org/)
  [![Swagger](https://img.shields.io/badge/Swagger-2.3.0-85EA2D?style=flat&logo=swagger)](https://swagger.io/)
  [![License](https://img.shields.io/badge/License-Copyright-green.svg)](LICENSE)
</div>

>A robust REST API built with **Spring Boot 4.0** for inventory management and supplier purchase orders in retail environments. Designed as an internal system for employees, focused on productivity, control, and auditability.

> [!IMPORTANT]
> This API works in conjunction with the frontend [Merkand-Client](https://github.com/FadiDaniel/Merkand-client) built with Angular 21.

---

## 🎯 System Objectives

- **Internal Inventory Control** - Real-time stock tracking and management
- **Purchase Automation** - Streamlined restock ordering process
- **Movement Auditing** - Complete history of stock movements
- **Low Stock Alerts** - Proactive inventory warnings
- **Data Analytics** - Comprehensive reporting for decision-making

---

## ✨ Key Features

### Core Functionality
- 🔐 **JWT Authentication** - Secure token-based authentication
- 👥 **User Management** - Role-based access control (Admin/Operator)
- 📦 **Product Management** - Complete CRUD operations for inventory
- 🏪 **Supplier Management** - Vendor tracking and management
- 📋 **Purchase Orders** - Create and track supplier orders
- 🔄 **Stock Movements** - IN/OUT/ADJUST transaction logging
- 📊 **Basic Reporting** - Inventory analytics and insights

### User Roles

| Role | Description | Capabilities |
|------|-------------|--------------|
| **ADMIN** | System administrator | Full system access, user creation and management |
| **OPERATOR** | Daily operations | Inventory operations, order and stock movement management |

> [!NOTE]
> **ADMIN** users must be created directly in the database. **OPERATOR** users are created by administrators through the application.

---

## 🏗️ Architecture & Design

### Technology Stack
- **Java 21** - Latest LTS version with modern features (Records, Pattern Matching, etc.)
- **Spring Boot 4.0.0** - Enterprise-grade application framework
- **Spring Data JPA** - ORM and database abstraction
- **Spring Security** - Authentication and authorization
- **JWT** - Stateless authentication tokens
- **H2 Database** - In-memory database for development and testing
- **Maven** - Dependency management and build tool
- **MapStruct 1.6.2** - Type-safe, compile-time object mapping (replaces ModelMapper)
- **Springdoc OpenAPI 2.3.0** - Swagger UI for API documentation
- **Lombok** - Boilerplate code reduction (still used in entities)
- **Validation (Bean Validation 3.x)** - Built-in validation annotations in DTOs

### Layered Architecture
Modular monolithic application with well-defined layers:

```
src/main/java/com/merkand/api/
├── config/          # Configuration classes (Security, CORS, OpenAPI, DataInitializer)
├── controller/      # REST endpoints
├── service/         # Business logic layer
├── repository/      # Data access layer (JPA)
├── entity/          # JPA entities
├── dto/             # Data Transfer Objects (now Java 21 Records with validation)
├── mapper/          # MapStruct mapper interfaces
├── exception/       # Custom exceptions and GlobalExceptionHandler
└── security/        # JWT filters and utilities
```

Each layer has clear responsibilities:
- **Controllers** - Handle HTTP requests/responses
- **Services** - Implement business logic
- **Repositories** - Database operations
- **Entities** - Database table mappings
- **DTOs** - API request/response structures (using Java 21 Records)
- **Mappers** - Type-safe conversion between entities and DTOs (MapStruct)

---

## 🏗️ Current ERM Status

![ERM.png](img/ERM.png)

---

## 🔐 Security

### Authentication Flow
1. Client sends credentials to `/api/auth/login`
2. Server validates and returns JWT token
3. Client includes token in `Authorization: Bearer <token>` header
4. Server validates token for each request

### Protected Endpoints
All endpoints require authentication except:
- `POST /api/auth/login` - User authentication
- `POST /api/auth/register` - Admin-only user registration
- Swagger UI documentation: `/swagger-ui.html`, `/v3/api-docs/**` (public for development/testing)

### Roles & Permissions
- **ROLE_ADMIN** - Full system access
- **ROLE_OPERATOR** - Limited operational access

### Enhanced Security Features
- Password encoding using BCrypt
- Token expiration configured (24 hours)
- Custom Access Denied Handler for consistent 403 responses
- Global Exception Handler for standardized error responses
- CORS configured to allow frontend from `http://localhost:4200`

---

## 🚀 Getting Started

### Prerequisites
- **Java 21** or higher
- **Maven 3.9+**
- **Git**
- **Docker** (optional, for containerized implementation)

### Instalación y Ejecución

#### Opción 1: Ejecución Local (Sin Docker)

1. **Clone el repositorio**
   ```bash
   git clone https://github.com/FadiDaniel/Merkand-API.git
   cd Merkand-API
   ```

2. **Configure environment variables in `application.yml`**
   ```yaml
   key: JWT_SECRET=your-super-secret-key-here
   ```

3. **Compile the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   # Using Maven (recommended for development)
   mvn spring-boot:run
   
   # Or using the generated JAR
   java -jar target/merkand-api.jar
   ```

#### Opción 2: Ejecución con Docker

1. **Clone the repository**
   ```bash
   git clone https://github.com/FadiDaniel/Merkand-API.git
   cd Merkand-API
   ```

2. **Build the Docker image**
   ```bash
   docker build -t merkand-api .
   ```

3. **Run with Docker Compose (no external database required)**
   ```bash
   docker-compose up -d
   ```

4. **Or run standalone (uses in-memory H2 database)**
   ```bash
   docker run -p 8080:8080 \
     -e JWT_SECRET=your-secret \
     merkand-api
   ```

### API Access

Once the application is running:

- **Main API**: http://localhost:8080
- **Swagger UI Documentation**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON Specification**: http://localhost:8080/v3/api-docs
- **OpenAPI YAML Specification**: http://localhost:8080/v3/api-docs.yaml

[📖 Go to interactive documentation](#-api-documentation)

> [!NOTE] The authentication endpoints (`/api/auth/*`) are public. All other endpoints require JWT authentication.

---

## ⚙️ Configuration

### Application Properties
Key configurations in `application.properties` or `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:merkanddb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    database-platform: org.hibernate.dialect.H2Dialect

jwt:
  secret: ${JWT_SECRET}
  expiration: 86400000  # 24 hours

server:
  port: 8080
```

### Environment Variables

| Variable | Description | Required |
|----------|-------------|----------|
| `JWT_SECRET` | Secret key for JWT signing | ✅ Yes |

---

## 🧪 Testing

The project now includes a comprehensive testing strategy inspired by the api-tareas project:

### Test Types
- **Unit Tests** - Using JUnit 5, Mockito, and AssertJ
- **Integration Tests** - Using @SpringBootTest (can be added)
- **Test Coverage** - Targeting >80% with Jacoco

### Running Tests
```bash
# Run unit tests
mvn test

# Run integration tests (if configured)
mvn verify

# Generate coverage report
mvn jacoco:report
```

### Testing Improvements
- Comprehensive unit tests for service layer (example: ProductServImplTest)
- Use of Mockito for dependency mocking
- AssertJ for fluent assertions
- Test data initialization via @DataInitializer component (dev/test profiles)

---

## 📦 Deployment

### Build for Production
```bash
mvn clean package -DskipTests
```

The JAR file will be available at `target/merkand-api.jar`

### Docker Support
The project includes Docker support for containerized deployment:

#### Dockerfile
A multi-stage Dockerfile is provided for optimized builds:
- Uses Maven build stage to compile the application
- Uses OpenJDK 21 runtime for minimal image size
- Runs as non-root user for security
- Includes health check endpoint

#### docker-compose.yml
For easy local development (no external database required):
```yaml
version: '3.8'

services:
  merkand-api:
    build: .
    ports:
      - "8080:8080"
    environment:
      - JWT_SECRET=your-super-secret-key-here
```

#### Build and Run
```bash
# Build the Docker image
docker build -t merkand-api .

# Run with Docker Compose (no external database required)
docker-compose up -d

# Or run standalone Docker container (uses in-memory H2 database)
docker run -p 8080:8080 \
  -e JWT_SECRET=your-secret \
  merkand-api
```

---

## 📖 API Documentation

### Swagger UI
Interactive API documentation available at:
```
http://localhost:8080/swagger-ui.html
```

### OpenAPI JSON/YAML
Raw specification available at:
- JSON: `http://localhost:8080/v3/api-docs`
- YAML: `http://localhost:8080/v3/api-docs.yaml`

### Main Endpoints (Examples)

<details>
<summary><b>Authentication</b></summary>

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "test01",
  "password": "test01"
}
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "test01",
  "role": "ROLE_ADMIN"
}
```

`POST /api/auth/register` (ADMIN only)
```json
{
  "username": "newuser",
  "password": "newpass"
}
```
</details>

<details>
<summary><b>Products</b></summary>

```http
GET    /api/products          # List all products
GET    /api/products/{id}     # Get product by ID
POST   /api/products          # Create new product
PUT    /api/products/{id}     # Update product
DELETE /api/products/{id}     # Delete product
```
</details>

<details>
<summary><b>Orders</b></summary>

```http
GET    /api/orders            # List all orders
GET    /api/orders/{id}       # Get order by ID
POST   /api/orders            # Create new order
PUT    /api/orders/{id}       # Update order
```
</details>

<details>
<summary><b>Suppliers</b></summary>

```http
GET    /api/suppliers         # List all suppliers (Admin only)
POST   /api/suppliers         # Create supplier (Admin only)
PUT    /api/suppliers/{id}    # Update supplier (Admin only)
DELETE /api/suppliers/{id}    # Delete supplier (Admin only)
```
</details>

<details>
<summary><b>Stock Movements</b></summary>

```http
GET    /api/movements         # List all stock movements
GET    /api/movements/{id}    # Get movement by ID
POST   /api/movements         # Register new movement
PUT    /api/movements/{id}    # Update movement
```
</details>

---

## 💡 Recent Improvements

This release incorporates several modern best practices:

- **DTOs as Java 21 Records** - Significantly reduced boilerplate code
- **Bean Validation in DTOs** - Declarative validation (@NotBlank, @Positive, @Email, etc.)
- **MapStruct for Type-Safe Mapping** - Compile-time verified entity-DTO conversion
- **Global Exception Handler** - Consistent error responses across all endpoints
- **Custom Exception Types** - ResourceNotFound, ValidationException, BusinessException
- **Automatic Data Seeding** - DataInitializer component loads test data on startup (dev/test profiles)
- **Swagger/OpenAPI Integration** - Interactive API documentation with JWT auth support
- **Enhanced Unit Testing** - Comprehensive service layer tests with Mockito/JUnit 5
- **Standardized Error Responses** - Include HTTP status, message, and timestamp
- **CORS Configuration** - Properly configured for Angular frontend

---

## 🗺️ Roadmap

- [x] Add support for multiple user roles and permissions
- [x] Create Docker Compose setup for easy local development
- [x] Add comprehensive unit tests for repositories
- [ ] Implement rate limiting to prevent abuse
- [ ] Enhance reporting with advanced analytics
- [ ] Implement file upload/download for product images/documents
- [ ] Implement pagination to control data sending to the front end

---

## 🔗 Related Projects

- **[Merkand-Client](https://github.com/FadiDaniel/Merkand-client)** - Angular 21 frontend application

---

## 📄 License

See the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Fadi**
- GitHub: [@FadiDaniel](https://github.com/FadiDaniel)

---

<div align="center">
  <p>Built with ☕ using Spring Boot</p>
</div>