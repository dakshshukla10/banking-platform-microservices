# 📊 **Banking Auth Service - Diagram Specifications**

## **1. System Architecture Diagram**

### **Prompt for AI Image Generation:**
```
Create a professional system architecture diagram for a banking microservices platform showing:

TITLE: "Banking Platform - Auth Service Architecture"

Components (as boxes with icons):
- Client Applications (mobile app, web browser icons)
- API Gateway (gateway icon)
- Auth Service (shield/lock icon, port 8081)
- PostgreSQL Database (database icon)
- Other Microservices (Transaction Producer port 8082, Transaction Consumer port 8083, UI Dashboard port 3000)

Connections:
- Arrows from Client Apps to API Gateway
- Arrow from API Gateway to Auth Service
- Bidirectional arrow between Auth Service and PostgreSQL
- Dotted arrows from Other Services to Auth Service (labeled "JWT Validation")

Color scheme: Blue for auth components, green for database, gray for other services
Include JWT tokens flowing between components
Add security shield icons around auth-related connections
```

---

## **2. JWT Authentication Flow Diagram**

### **Prompt for AI Image Generation:**
```
Create a sequence diagram showing JWT authentication flow:

TITLE: "JWT Authentication Flow - Banking Auth Service"

Actors (vertical lanes):
1. Client (user icon)
2. Auth Service (shield icon)
3. UserDetailsService (service icon)
4. PostgreSQL DB (database icon)
5. JwtUtil (key icon)

Flow (numbered steps with arrows):
1. Client → Auth Service: POST /auth/login {username, password}
2. Auth Service → UserDetailsService: loadUserByUsername()
3. UserDetailsService → PostgreSQL: findByUsername()
4. PostgreSQL → UserDetailsService: User entity
5. UserDetailsService → Auth Service: UserDetails
6. Auth Service → Auth Service: BCrypt.verify(password)
7. Auth Service → JwtUtil: generate(username, roles)
8. JwtUtil → Auth Service: JWT Token
9. Auth Service → Client: {token: "eyJ...", user: {...}}

Use green arrows for success, red for potential failures
Add time progression from top to bottom
Include response codes (200, 401, etc.)
```

---

## **3. Security Filter Chain Diagram**

### **Prompt for AI Image Generation:**
```
Create a horizontal flow diagram showing Spring Security filter chain:

TITLE: "Spring Security Filter Chain - Request Processing"

Flow (left to right with filter boxes):
1. HTTP Request (browser icon)
2. JwtFilter (key icon) - "Extract Bearer Token"
3. Authentication Manager (shield icon) - "Validate Credentials"
4. UserDetailsService (user icon) - "Load User Details"
5. Security Context (lock icon) - "Set Authentication"
6. Controller (API icon) - "Process Business Logic"
7. HTTP Response (checkmark icon)

Below each filter, show what happens:
- JwtFilter: "Parse Authorization header, Extract JWT token"
- Authentication: "Verify token signature, Check expiration"
- UserDetails: "Load user from database, Convert to Spring Security format"
- Security Context: "Store authentication in thread-local context"
- Controller: "Access authenticated user, Process request"

Use blue gradient background, white filter boxes with icons
Add decision diamonds for "Token Valid?" and "User Found?"
```

---

## **4. Database Schema Diagram**

### **Prompt for AI Image Generation:**
```
Create a database schema diagram:

TITLE: "Auth Service - Database Schema"

Table: USERS
┌─────────────────────────────────┐
│ USERS                           │
├─────────────────────────────────┤
│ 🔑 id: UUID (PRIMARY KEY)      │
│ 📧 username: VARCHAR(50) UNIQUE│
│ 🔒 password: VARCHAR(120)      │
│ 👤 roles: VARCHAR(255)         │
│ 📅 created_at: TIMESTAMP       │
│ 📅 updated_at: TIMESTAMP       │
└─────────────────────────────────┘

Relationships:
- Self-referencing for future features (manager relationships)
- Future tables: TRANSACTIONS, ACCOUNTS (shown as dotted boxes)

Indexes:
- PRIMARY: id
- UNIQUE: username
- INDEX: roles (for role-based queries)

Color: Blue header, white body, yellow for keys, green for constraints
Add PostgreSQL logo in corner
Show data types and constraints clearly
```

---

## **5. Microservices Communication Diagram**

### **Prompt for AI Image Generation:**
```
Create a microservices communication diagram:

TITLE: "Banking Platform - Service Communication"

Services (as hexagonal boxes):
- Auth Service (🔐, port 8081)
- Transaction Producer (📤, port 8082) 
- Transaction Consumer (📥, port 8083)
- UI Dashboard (🖥️, port 3000)
- PostgreSQL (🗄️, port 5432)
- Kafka (📊, port 9092)

Communication patterns:
1. Synchronous (solid arrows):
   - UI → Auth Service (login/register)
   - Transaction Producer → Auth Service (validate JWT)
   - Transaction Consumer → Auth Service (validate JWT)

2. Asynchronous (dashed arrows):
   - Transaction Producer → Kafka (publish events)
   - Kafka → Transaction Consumer (consume events)

3. Database connections (thick arrows):
   - Auth Service ↔ PostgreSQL
   - Transaction Consumer ↔ PostgreSQL

Labels on arrows:
- "JWT Validation"
- "REST API"
- "Event Streaming"
- "Database Queries"

Use different colors for different communication types
Add network boundaries and security zones
```

---

## **6. JWT Token Structure Diagram**

### **Prompt for AI Image Generation:**
```
Create a JWT token structure visualization:

TITLE: "JWT Token Structure - Banking Auth Service"

Show JWT as three colored sections:
1. HEADER (Red):
   {
     "alg": "HS256",
     "typ": "JWT"
   }

2. PAYLOAD (Blue):
   {
     "sub": "john_doe",
     "roles": ["USER", "CUSTOMER"],
     "iat": 1640995200,
     "exp": 1641009600
   }

3. SIGNATURE (Green):
   HMACSHA256(
     base64UrlEncode(header) + "." +
     base64UrlEncode(payload),
     secret
   )

Visual representation:
- Show the three parts separated by dots
- Base64 encoding arrows
- Secret key icon for signature
- Clock icon for expiration
- User icon for subject
- Shield icon for roles

Add security notes:
- "4 hour expiration"
- "HMAC-SHA256 signing"
- "Role-based claims"
```

---

## **7. Package Structure Diagram**

### **Prompt for AI Image Generation:**
```
Create a clean package structure diagram:

TITLE: "Auth Service - Package Architecture"

Folder tree structure:
📁 com.example.auth
├── 📁 domain
│   └── 📄 User.java (entity icon)
├── 📁 repository  
│   └── 📄 UserRepository.java (database icon)
├── 📁 security
│   ├── 📄 SecurityConfig.java (shield icon)
│   ├── 📄 JwtUtil.java (key icon)
│   ├── 📄 JwtFilter.java (filter icon)
│   ├── 📄 AppUserDetails.java (user icon)
│   └── 📄 UserDetailsServiceImpl.java (service icon)
├── 📁 config
│   └── 📄 JwtProperties.java (settings icon)
└── 📄 AuthServiceApplication.java (spring boot icon)

Layer annotations:
- Domain Layer (green background)
- Data Access Layer (blue background)  
- Security Layer (red background)
- Configuration Layer (yellow background)
- Application Layer (purple background)

Show dependencies with arrows between layers
Add "Clean Architecture" principles note
```

---

## **8. Error Handling Flow Diagram**

### **Prompt for AI Image Generation:**
```
Create an error handling flow diagram:

TITLE: "Auth Service - Error Handling & Security Flow"

Decision tree format:
1. Request arrives → "Has Authorization header?"
   - NO → Return 401 Unauthorized
   - YES → Continue

2. "Header starts with 'Bearer '?"
   - NO → Return 401 Unauthorized  
   - YES → Extract token

3. "Token signature valid?"
   - NO → Return 403 Forbidden
   - YES → Continue

4. "Token expired?"
   - YES → Return 401 Token Expired
   - NO → Continue

5. "User exists in database?"
   - NO → Return 404 User Not Found
   - YES → Set Security Context

6. "User account enabled?"
   - NO → Return 403 Account Disabled
   - YES → Allow access

Use red for error paths, green for success
Add HTTP status codes
Include security icons (shield, lock, warning)
Show graceful degradation
```

---

## **Usage Instructions:**

1. **Copy each prompt** to ChatGPT or your preferred AI image generator
2. **Specify additional requirements** like:
   - "Make it professional for a technical presentation"
   - "Use corporate blue and white color scheme"
   - "Include icons and make it visually appealing"
   - "Ensure text is readable at presentation size"

3. **For technical accuracy**, you can add:
   - "This is for a Spring Boot banking application"
   - "Follow microservices architecture best practices"
   - "Include security compliance indicators"

Would you like me to create additional diagram specifications for any specific aspects of your auth service?