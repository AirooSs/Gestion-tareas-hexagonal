# 🗂️ Gestión de Tareas — API REST con Arquitectura Hexagonal

API REST desarrollada con **Spring Boot 3**, **Java 21** y **PostgreSQL**, aplicando **Arquitectura Hexagonal (Ports & Adapters)** y principios de **Domain-Driven Design (DDD)**.

Proyecto construido con el objetivo de aprender y demostrar buenas prácticas de desarrollo backend en Java.

---

## 🏗️ Arquitectura

El proyecto sigue la arquitectura hexagonal, dividida en tres capas bien diferenciadas:

```
com.gestiontareas/
├── domain/                  # Núcleo del negocio — Java puro, sin dependencias externas
│   ├── model/
│   │   ├── task/            # Entidad Task, Value Objects TaskId y TaskStatus
│   │   ├── project/         # Entidad Project, Value Object ProjectId
│   │   └── user/            # Entidad User, Value Objects UserId y Email
│   ├── port/
│   │   ├── in/              # Puertos de entrada (casos de uso)
│   │   └── out/             # Puertos de salida (interfaces de repositorio)
│   └── exception/           # Excepciones del dominio
│
├── application/             # Casos de uso — orquesta el dominio
│   └── service/             # Implementaciones de los casos de uso
│
└── infrastructure/          # Detalles técnicos — Spring, JPA, REST
    ├── persistence/         # Entidades JPA, repositorios y mappers
    └── web/                 # Controllers REST, DTOs y configuración
```

### Regla de dependencias

```
Infrastructure  →  Application  →  Domain
  (Spring, JPA)    (Servicios)    (Java puro)
```

El dominio no conoce Spring ni JPA. Nunca hay un `@Entity` o `@Component` en la capa de dominio.

---

## 🛠️ Tecnologías

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Spring Boot | 4.0.6 | Framework backend |
| Spring Data JPA | — | Persistencia |
| PostgreSQL | 18 | Base de datos |
| Hibernate | 7.2 | ORM |
| Lombok | — | Reducción de boilerplate |
| JUnit 5 | — | Tests unitarios |
| Mockito | — | Mocking en tests |
| Springdoc OpenAPI | 2.8.8 | Documentación Swagger |
| Maven | — | Gestión de dependencias |

---

## 🚀 Cómo ejecutar el proyecto

### Requisitos previos
- Java 21
- PostgreSQL 18
- Maven

### Pasos

**1. Clona el repositorio:**
```bash
git clone https://github.com/AirooSs/Gestion-tareas-hexagonal.git
cd Gestion-tareas-hexagonal
```

**2. Crea la base de datos en PostgreSQL:**
```sql
CREATE DATABASE gestiontareas;
```

**3. Configura las credenciales en `application.properties`:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gestiontareas
spring.datasource.username=postgres
spring.datasource.password=tu_password
```

**4. Ejecuta la aplicación:**
```bash
./mvnw spring-boot:run
```

**5. Accede a la documentación Swagger:**
```
http://localhost:8080/swagger-ui/index.html
```

---

## 📋 Endpoints de la API

### 👤 Usuarios

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/usuarios` | Crear un usuario |
| GET | `/api/usuarios/{id}` | Obtener usuario por ID |

**Crear usuario:**
```http
POST http://localhost:8080/api/usuarios
Content-Type: application/json

{
    "name": "Fran Soria",
    "email": "fran@gestiontareas.com"
}
```

**Respuesta:**
```json
{
    "id": "8919ee6c-0984-4e4f-b9d4-5f4626140d40",
    "name": "Fran Soria",
    "email": "fran@gestiontareas.com"
}
```

---

### 📁 Proyectos

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/proyectos` | Crear un proyecto |
| GET | `/api/proyectos/usuario/{ownerId}` | Listar proyectos de un usuario |

**Crear proyecto:**
```http
POST http://localhost:8080/api/proyectos
Content-Type: application/json

{
    "name": "Mi primer proyecto",
    "description": "Descripción del proyecto",
    "ownerId": "8919ee6c-0984-4e4f-b9d4-5f4626140d40"
}
```

**Respuesta:**
```json
{
    "id": "20f5942f-754f-46b7-b82b-3617e7830a2e",
    "name": "Mi primer proyecto",
    "description": "Descripción del proyecto",
    "ownerId": "8919ee6c-0984-4e4f-b9d4-5f4626140d40"
}
```

---

### ✅ Tareas

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/tareas` | Crear una tarea |
| GET | `/api/tareas/{id}` | Obtener tarea por ID |
| PATCH | `/api/tareas/{id}/estado` | Actualizar estado de una tarea |
| GET | `/api/tareas/proyecto/{projectId}` | Listar tareas de un proyecto |

**Crear tarea:**
```http
POST http://localhost:8080/api/tareas
Content-Type: application/json

{
    "titulo": "Mi primera tarea",
    "descripcion": "Descripción de la tarea",
    "projectId": "20f5942f-754f-46b7-b82b-3617e7830a2e"
}
```

**Respuesta:**
```json
{
    "id": "29b2b487-9f7b-4cf2-82cd-7ad5a450e284",
    "titulo": "Mi primera tarea",
    "descripcion": "Descripción de la tarea",
    "status": "PENDIENTE",
    "projectId": "20f5942f-754f-46b7-b82b-3617e7830a2e",
    "assignedTo": null
}
```

**Actualizar estado:**
```http
PATCH http://localhost:8080/api/tareas/29b2b487-9f7b-4cf2-82cd-7ad5a450e284/estado
Content-Type: application/json

{
    "estado": "EN_PROGRESO"
}
```

**Estados disponibles:**
- `PENDIENTE`
- `EN_PROGRESO`
- `HECHO`
- `CANCELADO`

---

## 🧪 Tests

El proyecto incluye tests unitarios para la capa de dominio y aplicación usando JUnit 5 y Mockito.

```bash
./mvnw test
```

**Tests incluidos:**
- `CrearTareaServiceTest` — verifica la creación de tareas
- `ObtenerTareaServiceTest` — verifica la obtención y el caso de no encontrado
- `CrearUsuarioServiceTest` — verifica la creación de usuarios
- `CrearProyectoServiceTest` — verifica la creación de proyectos
- `ActualizarEstadoTareaServiceTest` — verifica el cambio de estado
- `EmailTest` — verifica las validaciones del Value Object Email
- `TaskTest` — verifica las reglas de negocio de la entidad Task

---

## 📚 Conceptos aplicados

- **Arquitectura Hexagonal (Ports & Adapters)** — separación total entre dominio e infraestructura
- **Domain-Driven Design (DDD)** — Entities, Value Objects, Aggregates, puertos de repositorio
- **SOLID** — especialmente inversión de dependencias (DIP)
- **API REST** — verbos HTTP correctos, códigos de respuesta, DTOs
- **Manejo global de excepciones** con `@RestControllerAdvice`
- **Validaciones** con Jakarta Bean Validation (`@NotBlank`, `@NotNull`)
- **Documentación** con Springdoc OpenAPI / Swagger UI

---

## 👨‍💻 Autor

**Francisco José Soria Navarrete**  
Desarrollador Web — DAW  
[GitHub](https://github.com/AirooSs)
