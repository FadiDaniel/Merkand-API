<div align="center">
  
  # Merkand API - Sistema de Gestión de Inventario 🚀
  
  **[English](README.md) | [Español](README.es.md)**
  
  [![Java](https://img.shields.io/badge/Java-21-007396?style=flat&logo=openjdk)](https://openjdk.org/)
  [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0-6DB33F?style=flat&logo=springboot)](https://spring.io/projects/spring-boot)
  [![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-4169E1?style=flat&logo=postgresql)](https://www.postgresql.org/)
</div>

Una API REST robusta construida con **Spring Boot 4.0** para la gestión de inventario y órdenes de compra a proveedores en entornos de retail. Diseñada como un sistema interno para empleados, enfocada en productividad, control y auditoría.

> [!IMPORTANT]
> Esta API funciona en conjunto con el frontend [Merkand-Client](https://github.com/FadiDaniel/Merkand-client) construido con Angular 21.

---

## 🎯 Objetivos del Sistema

- **Control Interno de Inventario** - Seguimiento y gestión de stock en tiempo real
- **Automatización de Compras** - Proceso optimizado de reabastecimiento
- **Auditoría de Movimientos** - Historial completo de movimientos de stock
- **Alertas de Stock Bajo** - Advertencias proactivas de inventario
- **Análisis de Datos** - Reportes completos para toma de decisiones

---

## ✨ Características Principales

### Funcionalidad Core
- 🔐 **Autenticación JWT** - Autenticación segura basada en tokens
- 👥 **Gestión de Usuarios** - Control de acceso basado en roles (Admin/Operador)
- 📦 **Gestión de Productos** - Operaciones CRUD completas para inventario
- 🏪 **Gestión de Proveedores** - Seguimiento y gestión de vendedores
- 📋 **Órdenes de Compra** - Crear y rastrear órdenes a proveedores
- 🔄 **Movimientos de Stock** - Registro de transacciones ENTRADA/SALIDA/AJUSTE
- 📊 **Reportes Básicos** - Análisis e información de inventario

### Roles de Usuario

| Rol | Descripción | Capacidades |
|-----|-------------|-------------|
| **ADMIN** | Administrador del sistema | Acceso completo al sistema, creación y gestión de usuarios |
| **OPERATOR** | Operaciones diarias | Operaciones de inventario, gestión de órdenes y movimientos de stock |

> [!NOTE]
> Los usuarios **ADMIN** deben crearse directamente en la base de datos. Los usuarios **OPERATOR** son creados por administradores a través de la aplicación.

---

## 🏗️ Arquitectura y Diseño

### Stack Tecnológico
- **Java 21** - Última versión LTS con características modernas
- **Spring Boot 4.0.0** - Framework de aplicaciones de nivel empresarial
- **Spring Data JPA** - ORM y abstracción de base de datos
- **Spring Security** - Autenticación y autorización
- **JWT** - Tokens de autenticación sin estado
- **PostgreSQL 17** - Base de datos relacional
- **Maven** - Gestión de dependencias y herramienta de construcción
- **Lombok** - Reducción de código boilerplate
- **Swagger/OpenAPI** - Documentación de API

### Arquitectura en Capas

Aplicación monolítica modular con capas bien definidas:

```
src/main/java/com/merkand/api/
├── config/          # Clases de configuración (Security, CORS, etc.)
├── controller/      # Endpoints REST
├── service/         # Capa de lógica de negocio
├── repository/      # Capa de acceso a datos (JPA)
├── entity/          # Entidades JPA
├── dto/             # Objetos de Transferencia de Datos
└── security/        # Filtros JWT y utilidades
```

Cada capa tiene responsabilidades claras:
- **Controllers** - Manejan peticiones/respuestas HTTP
- **Services** - Implementan lógica de negocio
- **Repositories** - Operaciones de base de datos
- **Entities** - Mapeo de tablas de base de datos
- **DTOs** - Estructuras de petición/respuesta de API

---

## 🔐 Seguridad

### Flujo de Autenticación
1. Cliente envía credenciales a `/auth/login`
2. Servidor valida y retorna token JWT
3. Cliente incluye token en header `Authorization: Bearer <token>`
4. Servidor valida token en cada petición

### Endpoints Protegidos
Todos los endpoints requieren autenticación excepto:
- `POST /auth/login` - Autenticación de usuario

### Roles y Permisos
- **ROLE_ADMIN** - Acceso completo al sistema
- **ROLE_OPERATOR** - Acceso operacional limitado

---

## 🚀 Primeros Pasos

### Prerrequisitos
- **Java 21** o superior
- **Maven 3.9+**
- **PostgreSQL 17**
- **Git**

### Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/FadiDaniel/Merkand-API.git
   cd Merkand-API
   ```

2. **Configurar variables de entorno**
   ```bash
   export JWT_SECRET=tu-clave-secreta-super-segura
   export DB_URL=jdbc:postgresql://localhost:5432/merkand
   export DB_USERNAME=tu-usuario-db
   export DB_PASSWORD=tu-contraseña-db
   ```

3. **Construir el proyecto**
   ```bash
   mvn clean install
   ```

4. **Ejecutar la aplicación**
   ```bash
   # Opción 1: Usando Maven
   mvn spring-boot:run
   
   # Opción 2: Usando JAR
   java -jar target/merkand-api.jar
   ```

5. **Acceder a la API**
   ```
   http://localhost:8080
   ```

---

## 📊 Base de Datos

### Gestión de Esquema
- Las tablas se generan automáticamente vía JPA al iniciar la aplicación
- Versión del esquema de base de datos: **v1.3**

### Datos de Prueba
Un script SQL con datos de ejemplo está disponible para desarrollo:
- Ubicación: `src/main/resources/data.sql`
- **Nota**: Actualmente requiere ejecución manual (integración con Flyway/Liquibase planeada)

### Diagrama Entidad-Relación
  <img width="631" alt="ERM-DB" src="https://github.com/user-attachments/assets/d0795a35-53e4-4790-8322-4c23792ec0a7" />


---

## 📖 Documentación de la API

### Swagger UI
Documentación interactiva de la API disponible en:
```
http://localhost:8080/swagger-ui.html
```

### Endpoints Principales

<details>
<summary><b>Autenticación</b></summary>

```http
POST /auth/login
Content-Type: application/json

{
  "username": "admin1",
  "password": "admin1"
}
```

Respuesta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "admin1",
  "role": "ROLE_ADMIN"
}
```
</details>

<details>
<summary><b>Productos</b></summary>

```http
GET    /api/products          # Listar todos los productos
GET    /api/products/{id}     # Obtener producto por ID
POST   /api/products          # Crear nuevo producto
PUT    /api/products/{id}     # Actualizar producto
DELETE /api/products/{id}     # Eliminar producto
```
</details>

<details>
<summary><b>Órdenes</b></summary>

```http
GET    /api/orders            # Listar todas las órdenes
GET    /api/orders/{id}       # Obtener orden por ID
POST   /api/orders            # Crear nueva orden
PUT    /api/orders/{id}       # Actualizar orden
```
</details>

<details>
<summary><b>Proveedores</b></summary>

```http
GET    /api/suppliers         # Listar todos los proveedores (Solo Admin)
POST   /api/suppliers         # Crear proveedor (Solo Admin)
PUT    /api/suppliers/{id}    # Actualizar proveedor (Solo Admin)
DELETE /api/suppliers/{id}    # Eliminar proveedor (Solo Admin)
```
</details>

---

## ⚙️ Configuración

### Propiedades de Aplicación
Configuraciones clave en `application.properties` o `application.yml`:

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
  expiration: 86400000  # 24 horas

server:
  port: 8080
```

### Variables de Entorno

| Variable | Descripción | Requerida |
|----------|-------------|-----------|
| `JWT_SECRET` | Clave secreta para firma JWT | ✅ Sí |
| `DB_URL` | URL de conexión PostgreSQL | ✅ Sí |
| `DB_USERNAME` | Usuario de base de datos | ✅ Sí |
| `DB_PASSWORD` | Contraseña de base de datos | ✅ Sí |

---

## 🧪 Testing

```bash
# Ejecutar tests unitarios
mvn test

# Ejecutar tests de integración
mvn verify

# Generar reporte de cobertura
mvn jacoco:report
```

---

## 📦 Despliegue

### Construcción para Producción
```bash
mvn clean package -DskipTests
```

El archivo JAR estará disponible en `target/merkand-api.jar`

### Soporte Docker (Próximamente)
```bash
# Construir imagen
docker build -t merkand-api .

# Ejecutar contenedor
docker run -p 8080:8080 \
  -e JWT_SECRET=tu-secreto \
  -e DB_URL=jdbc:postgresql://host:5432/merkand \
  merkand-api
```

---

## 🗺️ Hoja de Ruta

- [ ] Implementar Flyway para migraciones de base de datos
- [ ] Agregar Redis para caché
- [ ] Implementar tokens de refresco
- [ ] Agregar tests unitarios completos
- [ ] Crear configuración Docker Compose
- [ ] Agregar métricas de Prometheus
- [ ] Implementar limitación de velocidad
- [ ] Agregar versionado de API

---

## 🔗 Proyectos Relacionados

- **[Merkand-Client](https://github.com/FadiDaniel/Merkand-client)** - Aplicación frontend en Angular 21

---

## 📄 Licencia

Ver el archivo [LICENSE](LICENSE) para más detalles.

---

## 👨‍💻 Autor

**Fadi Daniel**
- GitHub: [@FadiDaniel](https://github.com/FadiDaniel)

---

<div align="center">
  <p>Construido con ☕ usando Spring Boot</p>
</div>
