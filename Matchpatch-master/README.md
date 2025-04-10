# Match Patch

## 🧾 Sheet 1: **Project Overview**

| Field                 | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| Project Start Date    | Monday, 07/04/2024                                                          |
| Java Version          | Java 21                                                                     |
| Spring Boot Version   | 3.4.4                                                                       |
| Database              | MySQL                                                                       |
| Architecture          | Monolith or Microservices *(Eureka + Spring Admin)* (optional toggle)       |
| Logging Framework     | SLF4J (with Logback)                                                        |
| Version Control       | Git (Private Repo)                                                          |
| Code Validation       | SonarQube (Static Analysis & Code Quality)                                  |
| Caching (Optional)    | Redis (optional usage for cache optimization)                               |
| Observability         | Zipkin (Explore Distributed Tracing)                                        |
| Documentation         | Swagger UI                                                                  |
| Template Engine       | Velocity (For script generation - .txt files)                               |
| Performance Target    | Optimized up to 500/1000 rows                                               |
| Test Framework        | JUnit / Mockito                                                             |

---

## 📂 Sheet 2: **Tech Stack & Dependencies**

| Dependency                 | Purpose                                         |
|---------------------------|-------------------------------------------------|
| Spring Web                | Create RESTful APIs                            |
| Lombok                    | Reduce boilerplate code                        |
| Spring Data JPA           | ORM / Database Interaction                     |
| Spring Boot DevTools      | Hot reload for dev environment                 |
| MySQL Driver              | MySQL JDBC Connector                           |
| Velocity Engine Core      | Template generation for schema/data .txt files |
| JUnit / Mockito           | Unit Testing / Mocking                         |
| Jakarta Validation        | Bean validation (e.g., `@NotNull`, `@Size`)     |
| Spring Boot Actuator      | Metrics / Health checks (for Spring Admin)     |
| Spring Boot Admin (opt.)  | Microservice Monitoring                        |
| Eureka Server/Client (opt.) | Service Registry (Microservices setup)        |
| Redis (optional)          | In-memory caching                              |
| Spring AOP (optional)     | Logging/Aspect-based validation                |

---

## 🔧 Sheet 3: **Git & Branching Strategy**

| Role           | Branch Name   | Access Level | Notes                                    |
|----------------|---------------|--------------|------------------------------------------|
| Main / Master  | `main`        | Protected    | Final code after review, only merges     |
| Team Member    | `team-member-name`| Developer    | Feature-specific branch                  |

---

## 🧱 Sheet 4: **Project Structure & Conventions**

| Module         | Package Name Example                      | Naming Convention                  |
|----------------|-------------------------------------------|------------------------------------|
| Controller     | `com.estuate.project.controller`          | `UserController`, `SchemaController` |
| Service        | `com.estuate.project.service`             | `UserService`, `ExportService`     |
| Repository     | `com.estuate.project.repository`          | `UserRepository`                   |
| Entity         | `com.estuate.project.entity`              | `User`, `TableMetadata`, etc.      |
| DTO            | `com.estuate.project.dto`                 | `UserDTO`, `SchemaDTO`             |
| Utility        | `com.estuate.project.util`                | `VelocityGenerator`, `DiffUtils`   |
| Config         | `com.estuate.project.config`              | `SwaggerConfig`, `RedisConfig`     |
| Validation     | `com.estuate.project.validation`          | `@ValidSchema`, `SchemaValidator`  |

---

## 📌 Sheet 5: **Features Checklist**

| Feature                        | Status       | Notes                                      |
|--------------------------------|--------------|--------------------------------------------|
| Git Version Control            | ✅ In use     | Private repo with branching                |
| Logging with SLF4J             | ✅ Enabled    | Using Logback or any other impl            |
| Schema Export (Velocity)       | ✅ In scope   | Generate `.txt` using Velocity templates   |
| Structure Comparison           | ✅ In scope   | DiffUtils or custom comparator             |
| Full Data Comparison           | ✅ In scope   | After structure version matches            |
| Swagger UI                     | ✅ Enabled    | For API documentation                      |
| Zipkin (Exploration Phase)     | 🔄 Planned    | To trace service calls (for microservices) |
| Redis Cache (Optional)         | 🔄 Optional   | For table data or metadata caching         |
| Validation Framework           | ✅ Enabled    | Jakarta Validation API                     |
| SonarQube Integration          | 🔄 Planned    | Static code analysis in CI/CD              |
| Performance Optimization       | ✅ Targeted   | For 500–1000 rows                          |
