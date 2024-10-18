# Task Service Spring

## Description
Task Service Spring is a microservice developed using Spring Boot that manages tasks within the application. This service provides an API for creating, updating, deleting, and retrieving tasks, as well as interacting with the User Service to obtain user information.

## Technologies
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Feign Client API

## Running the Project
To run the project, follow these steps:
1. Clone the repository:
   ```bash
   git clone <URL>
2. Navigate to the project directory:
   ```bash
   cd task_service_spring
4. Start the application:
   ```bash
   ./mvnw spring-boot:run
5. The Task Service will be available at http://localhost:8081/api/tasks.

## Endpoints
# Tasks
GET /api/tasks
Retrieve a list of all tasks.

POST /api/tasks
Create a new task.
The request body should contain JSON with task information.

PUT /api/tasks/{id}
Update a task by its identifier.
The request body should contain JSON with the updated task information.

DELETE /api/tasks/{id}
Delete a task by its identifier.

# Users
GET /api/tasks/users
Retrieve a list of all users.

GET /api/tasks/users/{id}
Get user information by identifier.

---

You can customize these files according to your project structure, specific requirements, and implementation details. If you need additional sections such as "Installation" or "Configuration," please let me know!
