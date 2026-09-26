# 📋 TaskFlow — Full-Stack Task Management Application

A full-stack task management web application built with **React + Vite + Tailwind CSS** on the frontend and **Spring Boot 3 (Java 21+) + Spring Security + JWT + MySQL / H2** on the backend.

---

## 🏗️ Architecture Overview

```
                  ┌─────────────────────────┐
                  │       React App         │
                  │ Vite + Tailwind CSS     │
                  │ React Router + Context  │
                  └────────────┬────────────┘
                               │
                          Axios / HTTP
                               │
                               ▼
                  ┌─────────────────────────┐
                  │     Spring Boot API     │
                  │                         │
                  │  AuthController         │
                  │  TaskController         │
                  │  UserController         │
                  │  Security / JWT Filter  │
                  │  Service Layer          │
                  │  JPA Repositories       │
                  └────────────┬────────────┘
                               │
                        Hibernate / JPA
                               │
                               ▼
                  ┌─────────────────────────┐
                  │    MySQL 8.0 / H2       │
                  └─────────────────────────┘
```

---

## ✨ Features

- **Authentication & Authorization**:
  - Secure registration and login using BCrypt password hashing and JJWT (JSON Web Token).
  - Stateless session management with custom `JwtAuthenticationFilter`.
  - Strict user-level task authorization: Users can only see, create, edit, and delete their own tasks.
- **Task Management (Full CRUD)**:
  - Create tasks with title, description, priority (`LOW`, `MEDIUM`, `HIGH`), status (`TODO`, `IN_PROGRESS`, `COMPLETED`), and due dates.
  - Quick status dropdown changes directly on task cards.
  - Edit tasks via an interactive modal form.
  - Delete tasks with confirmation dialogs.
- **Dynamic Filtering, Search, & Pagination**:
  - Full-text search across titles and descriptions.
  - Filter tasks by status and priority.
  - Sort by created date, due date, title, or priority (Asc / Desc).
  - Server-side pageable pagination.
- **Interactive Dashboard**:
  - Real-time statistics counters (Total, Completed, In Progress, Pending).
  - Multi-segment visual progress bar displaying task completion rate percentage.
  - Quick action buttons and recent task grid.
- **User Profile Management**:
  - View account credentials, assigned role (`ROLE_USER`), registration date.
  - Update user display name.
- **Responsive Modern UI**:
  - Styled with Tailwind CSS, Lucide icons, responsive drawer navigation for mobile and desktop sidebar.

---

## 📁 Project Structure

```
Task Management Application/
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── assets/
│   │   ├── components/
│   │   │   ├── LoadingSpinner.jsx
│   │   │   ├── Navbar.jsx
│   │   │   ├── ProtectedRoute.jsx
│   │   │   ├── Sidebar.jsx
│   │   │   ├── TaskCard.jsx
│   │   │   ├── TaskFilter.jsx
│   │   │   ├── TaskForm.jsx
│   │   │   └── TaskModal.jsx
│   │   ├── context/
│   │   │   └── AuthContext.jsx
│   │   ├── hooks/
│   │   │   └── useAuth.js
│   │   ├── pages/
│   │   │   ├── Dashboard.jsx
│   │   │   ├── Login.jsx
│   │   │   ├── NotFound.jsx
│   │   │   ├── Profile.jsx
│   │   │   ├── Register.jsx
│   │   │   └── Tasks.jsx
│   │   ├── services/
│   │   │   ├── api.js
│   │   │   ├── authService.js
│   │   │   └── taskService.js
│   │   ├── utils/
│   │   │   ├── constants.js
│   │   │   └── helpers.js
│   │   ├── App.jsx
│   │   ├── index.css
│   │   └── main.jsx
│   ├── index.html
│   ├── package.json
│   ├── postcss.config.js
│   ├── tailwind.config.js
│   ├── vite.config.js
│   ├── Dockerfile
│   └── nginx.conf
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/taskmanager/
│   │   │   │   ├── config/
│   │   │   │   │   └── WebConfig.java
│   │   │   │   ├── controller/
│   │   │   │   │   ├── AuthController.java
│   │   │   │   │   ├── TaskController.java
│   │   │   │   │   └── UserController.java
│   │   │   │   ├── dto/
│   │   │   │   │   ├── LoginRequest.java
│   │   │   │   │   ├── LoginResponse.java
│   │   │   │   │   ├── RegisterRequest.java
│   │   │   │   │   ├── TaskRequest.java
│   │   │   │   │   ├── TaskResponse.java
│   │   │   │   │   ├── TaskStatsResponse.java
│   │   │   │   │   └── UserResponse.java
│   │   │   │   ├── entity/
│   │   │   │   │   ├── Task.java
│   │   │   │   │   ├── TaskPriority.java
│   │   │   │   │   ├── TaskStatus.java
│   │   │   │   │   └── User.java
│   │   │   │   ├── exception/
│   │   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   │   └── UnauthorizedException.java
│   │   │   │   ├── repository/
│   │   │   │   │   ├── TaskRepository.java
│   │   │   │   │   └── UserRepository.java
│   │   │   │   ├── security/
│   │   │   │   │   ├── CustomUserDetailsService.java
│   │   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   │   ├── JwtService.java
│   │   │   │   │   └── SecurityConfig.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── AuthService.java
│   │   │   │   │   ├── TaskService.java
│   │   │   │   │   └── UserService.java
│   │   │   │   └── TaskManagerApplication.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       ├── application-dev.properties
│   │   │       └── application-mysql.properties
│   │   └── test/
│   ├── pom.xml
│   ├── Dockerfile
│   └── .env
│
├── docker-compose.yml
├── .gitignore
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

- **Java**: 21 or later
- **Maven**: 3.8 or later
- **Node.js**: v18 or later
- **MySQL**: (Optional for local MySQL, otherwise runs in zero-config `dev` mode with H2 in-memory DB)

---

### 1. Running the Backend

```bash
cd backend
```

#### Option A: Instant Zero-Config Run (Default H2 in-memory database)
```bash
mvn spring-boot:run
```
> The API will be available at `http://localhost:8080`.
> You can inspect the in-memory database at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:taskmanagementdb`, User: `sa`, Password: empty).

#### Option B: Run with MySQL
1. Ensure MySQL is running on your machine or in Docker.
2. In `backend/src/main/resources/application.properties` (or via command line):
```properties
spring.profiles.active=mysql
```
Or start via:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql -Dspring-boot.run.arguments="--DB_PASSWORD=your_mysql_password"
```

---

### 2. Running the Frontend

```bash
cd frontend
npm install
npm run dev
```
> The frontend will start at `http://localhost:5173`.
> Vite will automatically proxy `/api` requests to `http://localhost:8080`.

---

### 3. Running with Docker Compose (Optional)

```bash
docker compose up --build
```
This spins up:
- MySQL container on port `3306`
- Spring Boot container on port `8080`
- Nginx + React container on port `5173`

---

## 📡 REST API Documentation

### Authentication (`/api/auth`)

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `POST` | `/api/auth/register` | Register new user | No |
| `POST` | `/api/auth/login` | Log in and receive JWT | No |
| `POST` | `/api/auth/logout` | Log out | No |
| `GET` | `/api/auth/me` | Get current authenticated user | Yes (Bearer) |

#### Sample Register Request (`POST /api/auth/register`)
```json
{
  "name": "Utkarsh",
  "email": "utkarsh@example.com",
  "password": "password123"
}
```

#### Sample Login Response (`POST /api/auth/login`)
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsIn...",
  "tokenType": "Bearer",
  "user": {
    "id": 1,
    "name": "Utkarsh",
    "email": "utkarsh@example.com",
    "role": "ROLE_USER",
    "createdAt": "2026-09-26T13:30:00"
  }
}
```

---

### Tasks (`/api/tasks`)

All task endpoints require `Authorization: Bearer <token>`.

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/tasks` | Get paginated list of tasks (supports `search`, `status`, `priority`, `page`, `size`, `sortBy`, `direction`) |
| `GET` | `/api/tasks/{id}` | Get specific task by ID |
| `POST` | `/api/tasks` | Create new task |
| `PUT` | `/api/tasks/{id}` | Update existing task |
| `DELETE` | `/api/tasks/{id}` | Delete task |
| `GET` | `/api/tasks/stats` | Get dashboard statistics & completion rate |

#### Sample Create Task Request (`POST /api/tasks`)
```json
{
  "title": "Learn Spring Security",
  "description": "Implement JWT authentication and role-based access control",
  "priority": "HIGH",
  "status": "TODO",
  "dueDate": "2026-10-01"
}
```

#### Sample Filtering Query
```http
GET /api/tasks?status=IN_PROGRESS&priority=HIGH&page=0&size=10&sortBy=dueDate&direction=asc
```

---

### User Profile (`/api/users`)

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/users/profile` | Get current user's profile |
| `PUT` | `/api/users/profile` | Update profile details (e.g. name) |

---

## 🔒 Security & Data Isolation Architecture

- Passwords are encrypted with **BCrypt** prior to database persistence.
- JWT tokens are signed using **HS256** and verified on every incoming request.
- The authenticated user's email is extracted from the JWT token in `JwtAuthenticationFilter` and injected into Spring Security's `SecurityContext`.
- **Strict Data Isolation**: When querying, updating, or deleting tasks (`/api/tasks/{id}`), the backend validates that `task.getUser().getId()` matches the authenticated user ID. Any attempt by User A to access User B's task results in an immediate **`403 Forbidden`**.
