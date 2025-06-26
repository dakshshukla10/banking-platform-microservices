### High-level project blueprint I’m proposing

| Layer             | Service / Component               | Core Tech                                               | Responsibility                                                                           |
| ----------------- | --------------------------------- | ------------------------------------------------------- | ---------------------------------------------------------------------------------------- |
| **Ingress**       | NGINX Ingress (K8s)               | YAML Helm                                               | TLS termination, paths → services                                                        |
| **Auth**          | **`auth-service`**                | Java 21, Spring Boot 3, Spring Security + JWT, Postgres | Login / signup, issues JWTs, validates tokens for all other services                     |
| **Producer MS**   | **`txn-producer`**                | Spring Boot 3, Spring Kafka                             | Receives REST transaction calls, publishes to Kafka topic **`bank.txn.v1`**              |
| **Message Bus**   | Apache Kafka 7.6 (with ZooKeeper) | Docker                                                  | Durable, ordered event stream                                                            |
| **Consumer MS**   | **`txn-consumer`**                | Spring Boot 3, Kafka Listener, JPA                      | Subscribes to `bank.txn.v1`, persists to Postgres, exposes paged + filtered REST queries |
| **DB**            | PostgreSQL 16                     | Docker volume                                           | Stores users + transactions                                                              |
| **Front-end**     | React + TypeScript (Vite)         | Axios, JWT in localStorage                              | Login screen → dashboard table (limit + filter)                                          |
| **Observability** | Prometheus, Grafana, Loki         | Docker Compose                                          | Metrics, logs                                                                            |
| **CI/CD**         | GitHub Actions → Docker → Helm    | Minikube for dev                                        | Build, test, deploy                                                                      |

---

#### Dev workflow

1. **Local stack in Docker Compose**: Postgres 16, Zookeeper, Kafka 7.6, auth-service, producer, consumer.
2. **Unit/CI tests**: H2 in-memory for `contextLoads`, Testcontainers-Postgres for repo tests.
3. **Kubernetes**: Helm charts for each microservice → Minikube; later move to cloud cluster.

---

#### Why this stack

* **Resume-ready**: Spring Boot + Kafka + React covers back-end, message-driven design, and modern front-end—all common in bank/fin-tech roles.
* **Rapid iteration**: Docker Compose for day-one demos; same images deploy unchanged to K8s.
* **Scales for AI add-ons**: Once the pipeline is solid we can drop in LLM calls in the consumer (e.g., natural-language summaries or anomaly explanations) without re-architecting.

---

#### MVP milestones

1. **Week 1–2** Auth service + Postgres, JWT login working end-to-end.
2. **Week 3** Producer publishes demo transactions to Kafka.
3. **Week 4** Consumer stores & serves filtered pages; React dashboard shows first 10 rows with type filter.
4. **Week 5** Docker → Minikube deploy; basic Grafana dashboard.
5. **Week 6** Polish, demo, then branch to AI enrichment features.

That’s the plan we’ll execute—starting with finishing the auth flow tomorrow.
