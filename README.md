# 🏦 Banking Realtime Transaction Processing System

A modern, scalable real-time banking transaction processing system built with microservices architecture, event-driven design, and cloud-native technologies.

## 🏗️ Architecture Overview

This system implements a distributed banking platform with the following components:

- **Auth Service**: JWT-based authentication and authorization
- **Transaction Producer**: REST API for transaction ingestion with Kafka publishing
- **Transaction Consumer**: Event processing and data persistence
- **UI Dashboard**: React-based real-time transaction monitoring
- **Infrastructure**: Kubernetes deployment with observability

## 🛠️ Tech Stack

| Layer | Technology | Purpose |
|-------|------------|---------|
| **Backend** | Java 21, Spring Boot 3 | Microservices foundation |
| **Security** | Spring Security, JWT | Authentication & authorization |
| **Database** | PostgreSQL 16 | Primary data store |
| **Messaging** | Apache Kafka 7.6 | Event streaming |
| **Frontend** | React, TypeScript | User interface |
| **Containerization** | Docker, Docker Compose | Local development |
| **Orchestration** | Kubernetes, Helm | Production deployment |
| **Monitoring** | Prometheus, Grafana | Observability |

## 📁 Project Structure

```
banking-realtime/
├── auth-service/          # Authentication microservice
├── txn-producer/          # Transaction producer service
├── txn-consumer/          # Transaction consumer service
├── ui-dashboard/          # React frontend
├── simulator/             # Transaction simulation tools
├── infra/                 # Infrastructure as code
└── Project-Details/       # Architecture diagrams and documentation
```

## 🚀 Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL 16
- Node.js 18+ (for frontend)

### Local Development Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd banking-realtime
   ```

2. **Start infrastructure services**
   ```bash
   docker-compose up -d postgres kafka zookeeper
   ```

3. **Run auth service**
   ```bash
   cd auth-service
   mvn spring-boot:run
   ```

4. **Access the application**
   - Auth Service: http://localhost:8081
   - API Documentation: http://localhost:8081/swagger-ui.html

## 📋 Development Roadmap

### Phase 1: Foundation (Weeks 1-2) ✅
- [x] Project structure setup
- [x] Auth service basic configuration
- [ ] JWT authentication implementation
- [ ] User management APIs

### Phase 2: Core Services (Weeks 3-4)
- [ ] Transaction producer service
- [ ] Kafka integration
- [ ] Transaction consumer service
- [ ] Database schema design

### Phase 3: Frontend & Integration (Week 5)
- [ ] React dashboard implementation
- [ ] Real-time transaction display
- [ ] Service integration testing

### Phase 4: Deployment & Monitoring (Week 6)
- [ ] Docker containerization
- [ ] Kubernetes deployment
- [ ] Monitoring setup
- [ ] CI/CD pipeline

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run specific service tests
cd auth-service && mvn test
```

## 📊 Monitoring

- **Metrics**: Prometheus metrics available at `/actuator/prometheus`
- **Health**: Health checks at `/actuator/health`
- **Logs**: Structured logging with correlation IDs

## 🤝 Contributing

1. Create a feature branch from `main`
2. Make your changes
3. Add tests for new functionality
4. Submit a pull request

## 📄 License

This project is for educational purposes.

---

**Built with ❤️ for learning modern banking system architecture**
