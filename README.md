<div align="center">
  
  # Merkand API - Inventory Management System 🚀
  
  **[English](README.md) | [Español](README.es.md)**
  
  [![Java](https://img.shields.io/badge/Java-21-007396?style=flat&logo=openjdk)](https://openjdk.org/)
  [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0-6DB33F?style=flat&logo=springboot)](https://spring.io/projects/spring-boot)
  [![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-4169E1?style=flat&logo=postgresql)](https://www.postgresql.org/)
  [![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
</div>

A robust REST API built with **Spring Boot 4.0** for inventory management and supplier purchase orders in retail environments. Designed as an internal system for employees, focused on productivity, control, and auditability.

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
- **Java 21** - Latest LTS version with modern features
- **Spring Boot 4.0.0** - Enterprise-grade application framework
- **Spring Data JPA** - ORM and database abstraction
- **Spring Security** - Authentication and authorization
- **JWT** - Stateless authentication tokens
- **PostgreSQL 17** - Relational database
- **Maven** - Dependency management and build tool
- **Lombok** - Boilerplate code reduction
- **Swagger/OpenAPI** - API documentation

### Layered Architecture

Modular monolithic application with well-defined layers:

```
src/main/java/com/merkand/api/
├── config/          # Configuration classes (Security, CORS, etc.)
├── controller/      # REST endpoints
├── service/         # Business logic layer
├── repository/      # Data access layer (JPA)
├── entity/          # JPA entities
├── dto/             # Data Transfer Objects
└── security/        # JWT filters and utilities
```

Each layer has clear responsibilities:
- **Controllers** - Handle HTTP requests/responses
- **Services** - Implement business logic
- **Repositories** - Database operations
- **Entities** - Database table mappings
- **DTOs** - API request/response structures

---

## 🔐 Security

### Authentication Flow
1. Client sends credentials to `/auth/login`
2. Server validates and returns JWT token
3. Client includes token in `Authorization: Bearer <token>` header
4. Server validates token for each request

### Protected Endpoints
All endpoints require authentication except:
- `POST /auth/login` - User authentication

### Roles & Permissions
- **ROLE_ADMIN** - Full system access
- **ROLE_OPERATOR** - Limited operational access

---

## 🚀 Getting Started

### Prerequisites
- **Java 21** or higher
- **Maven 3.9+**
- **PostgreSQL 17**
- **Git**

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/FadiDaniel/Merkand-API.git
   cd Merkand-API
   ```

2. **Configure environment variables**
   ```bash
   export JWT_SECRET=your-super-secret-key-here
   export DB_URL=jdbc:postgresql://localhost:5432/merkand
   export DB_USERNAME=your-db-username
   export DB_PASSWORD=your-db-password
   ```

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   # Option 1: Using Maven
   mvn spring-boot:run
   
   # Option 2: Using JAR
   java -jar target/merkand-api.jar
   ```

5. **Access the API**
   ```
   http://localhost:8080
   ```

---

## 📊 Database

### Schema Management
- Tables are automatically generated via JPA on application startup
- Database schema version: **v1.3**

### Test Data
A SQL script with sample data is available for development:
- Location: `src/main/resources/data.sql`
- **Note**: Currently requires manual execution (Flyway/Liquibase integration planned)

### Entity-Relationship Diagram

 <img width="631" alt="ERM-DB" src="https://github.com/user-attachments/assets/d0795a35-53e4-4790-8322-4c23792ec0a7" />

---

## 📖 API Documentation

### Swagger UI
Interactive API documentation available at:
```
http://localhost:8080/swagger-ui.html
```

### Main Endpoints

<details>
<summary><b>Authentication</b></summary>

```http
POST /auth/login
Content-Type: application/json

{
  "username": "test1",
  "password": "test1"
}
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "test1",
  "role": "ROLE_ADMIN"
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

---

## ⚙️ Configuration

### Application Properties
Key configurations in `application.properties` or `application.yml`:

```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false

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
| `DB_URL` | PostgreSQL connection URL | ✅ Yes |
| `DB_USERNAME` | Database username | ✅ Yes |
| `DB_PASSWORD` | Database password | ✅ Yes |

---

## 🧪 Testing

```bash
# Run unit tests
mvn test

# Run integration tests
mvn verify

# Generate coverage report
mvn jacoco:report
```

---

## 📦 Deployment

### Build for Production
```bash
mvn clean package -DskipTests
```

The JAR file will be available at `target/merkand-api.jar`

### Docker Support (Coming Soon)
```bash
# Build image
docker build -t merkand-api .

# Run container
docker run -p 8080:8080 \
  -e JWT_SECRET=your-secret \
  -e DB_URL=jdbc:postgresql://host:5432/merkand \
  merkand-api
```

---

## 🗺️ Roadmap

- [ ] Implement Flyway for database migrations
- [ ] Add Redis for caching
- [ ] Implement refresh tokens
- [ ] Add comprehensive unit tests
- [ ] Create Docker Compose setup
- [ ] Add Prometheus metrics
- [ ] Implement rate limiting
- [ ] Add API versioning

---

## 🔗 Related Projects

- **[Merkand-Client](https://github.com/FadiDaniel/Merkand-client)** - Angular 21 frontend application

---

## 📄 License

See the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Fadi Daniel**
- GitHub: [@FadiDaniel](https://github.com/FadiDaniel)

---

<div align="center">
  <p>Built with ☕ using Spring Boot</p>
</div>
