# Code Evaluation Service

## Purpose
Execute submitted code in a sandbox environment.

## Endpoints
- **Submit Code**: `POST /api/code/submit`
- **Get Submission**: `GET /api/code/{id}`

## Setup Instructions
### Prerequisites
- Java 17 or higher
- PostgreSQL Database

### Database Configuration
1. Create a PostgreSQL database named `codeevaldb`.
2. Update `src/main/resources/application.properties` with your PostgreSQL credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/codeevaldb
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### Running the Application
```bash
./mvnw spring-boot:run
```

## Future Work
- Docker sandbox integration for enhanced security and isolation.
