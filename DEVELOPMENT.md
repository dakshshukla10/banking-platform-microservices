# 🛠️ Development Guide

## Prerequisites

### Required Software
- **Java 21+** - [Download](https://adoptium.net/)
- **Maven 3.8+** - [Download](https://maven.apache.org/download.cgi)
- **Docker & Docker Compose** - [Download](https://www.docker.com/products/docker-desktop)
- **Node.js 18+** - [Download](https://nodejs.org/) (for frontend)
- **Git** - [Download](https://git-scm.com/)

### Recommended Tools
- **IntelliJ IDEA** or **VS Code** for development
- **Postman** or **Insomnia** for API testing
- **DBeaver** or **pgAdmin** for database management

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/dakshshukla10/banking-platform-microservices.git
cd banking-platform-microservices
```

### 2. Start Infrastructure Services
```bash
# Start PostgreSQL, Kafka, and Zookeeper
docker-compose up -d postgres kafka zookeeper

# Verify services are running
docker-compose ps
```

### 3. Run Auth Service
```bash
cd auth-service
mvn clean install
mvn spring-boot:run
```

### 4. Verify Setup
- Auth Service: http://localhost:8081
- Health Check: http://localhost:8081/actuator/health
- API Docs: http://localhost:8081/swagger-ui.html (when implemented)

## 🔧 Development Workflow

### Daily Development
```bash
# Start your day
git pull origin main
git checkout -b feature/your-feature-name

# Make changes and test
mvn test
mvn spring-boot:run

# Commit your work
git add .
git commit -m "feat: implement JWT token validation"
git push origin feature/your-feature-name

# Create Pull Request on GitHub
```

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=AuthServiceTest

# Run integration tests
mvn verify -P integration-tests

# Generate test coverage report
mvn jacoco:report
```

## 🐳 Docker Development

### Build Service Images
```bash
# Build auth service image
cd auth-service
docker build -t banking-platform/auth-service .

# Run with Docker
docker run -p 8081:8081 banking-platform/auth-service
```

### Full Stack with Docker Compose
```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f auth-service

# Stop all services
docker-compose down
```

## 📊 Database Management

### Local PostgreSQL Setup
```bash
# Connect to database
docker exec -it banking-postgres psql -U bank -d banking

# Common commands
\dt          # List tables
\d users     # Describe users table
\q           # Quit
```

### Database Migrations
```bash
# Run Flyway migrations (when implemented)
mvn flyway:migrate

# Check migration status
mvn flyway:info
```

## 🧪 Testing Strategy

### Unit Tests
- Test individual components in isolation
- Mock external dependencies
- Fast execution (< 1 second per test)

### Integration Tests
- Test service interactions
- Use Testcontainers for database
- Test complete workflows

### End-to-End Tests
- Test complete user journeys
- Use real services
- Automated UI testing

## 🔍 Debugging

### Application Debugging
```bash
# Run with debug mode
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"

# Connect debugger to port 5005
```

### Log Configuration
```yaml
# application-dev.yml
logging:
  level:
    com.example.auth: DEBUG
    org.springframework.security: DEBUG
  pattern:
    console: "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
```

## 📈 Performance Monitoring

### Local Monitoring
```bash
# View JVM metrics
curl http://localhost:8081/actuator/metrics

# Health check
curl http://localhost:8081/actuator/health

# Application info
curl http://localhost:8081/actuator/info
```

### Profiling
- Use JProfiler or YourKit for memory analysis
- Enable JFR (Java Flight Recorder) for production profiling
- Monitor garbage collection patterns

## 🔧 IDE Configuration

### IntelliJ IDEA
1. Import as Maven project
2. Set Project SDK to Java 21
3. Enable annotation processing
4. Install recommended plugins:
   - Spring Boot
   - Docker
   - Database Navigator

### VS Code
1. Install Java Extension Pack
2. Install Spring Boot Extension Pack
3. Configure Java runtime path
4. Set up debugging configuration

## 🚨 Troubleshooting

### Common Issues

**Port Already in Use**
```bash
# Find process using port 8081
lsof -i :8081

# Kill process
kill -9 <PID>
```

**Database Connection Issues**
```bash
# Check if PostgreSQL is running
docker ps | grep postgres

# Restart database
docker-compose restart postgres
```

**Maven Build Issues**
```bash
# Clean and rebuild
mvn clean install -U

# Skip tests if needed
mvn clean install -DskipTests
```

## 📚 Learning Resources

### Spring Boot
- [Official Documentation](https://spring.io/projects/spring-boot)
- [Spring Boot Guides](https://spring.io/guides)
- [Baeldung Spring Tutorials](https://www.baeldung.com/spring-boot)

### Microservices
- [Microservices.io](https://microservices.io/)
- [Martin Fowler's Articles](https://martinfowler.com/microservices/)
- [Building Microservices Book](https://www.oreilly.com/library/view/building-microservices/9781491950340/)

### Testing
- [Spring Boot Testing](https://spring.io/guides/gs/testing-web/)
- [Testcontainers](https://www.testcontainers.org/)
- [AssertJ Documentation](https://assertj.github.io/doc/)

## 🤝 Getting Help

1. Check existing [Issues](https://github.com/dakshshukla10/banking-platform-microservices/issues)
2. Search [Stack Overflow](https://stackoverflow.com/questions/tagged/spring-boot)
3. Consult [Spring Boot Documentation](https://spring.io/projects/spring-boot)
4. Ask in project discussions

## 📝 Code Style

### Java Conventions
- Use camelCase for variables and methods
- Use PascalCase for classes
- Use UPPER_SNAKE_CASE for constants
- Maximum line length: 120 characters

### Git Commit Messages
```
feat: add new feature
fix: bug fix
docs: documentation changes
style: formatting changes
refactor: code refactoring
test: adding tests
chore: maintenance tasks
```

Happy coding! 🚀
