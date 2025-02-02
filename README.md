# 📱 WhatsApp Backend Implementation

## 📋 Overview
The **WhatsApp Backend Implementation** is a robust messaging system designed to handle core functionalities such as user management, text messaging, and group creation. This project focuses on providing scalable APIs for managing chats, groups, and user interactions.

## 🚀 Key Features
- 💬 Send and Receive Text Messages
- 👥 Create and Manage Groups
- ✅ User Registration and Validation
- ⚠️ Exception Handling for Robust Error Management

## 🛠️ Tech Stack
- **Java**: Backend development
- **Spring Boot**: REST API framework
- **Maven**: Build and dependency management

## 📂 Project Directory Structure

## ⚡ Getting Started
1. **Clone the Repository:**
   ```bash
   git clone <repository-url>
   ```
2. **Navigate to Project Directory:**
   ```bash
   cd whatsapp-implementation
   ```
3. **Build the Project:**
   ```bash
   ./mvnw clean install
   ```
4. **Run the Application:**
   ```bash
   ./mvnw spring-boot:run
   ```

## 📡 API Endpoints
### User Operations
- `POST /user/register` - Register a new user
- `GET /user/{id}` - Fetch user details

### Messaging Operations
- `POST /message/send` - Send a new message
- `GET /message/{groupId}` - Retrieve messages in a group

### Group Operations
- `POST /group/create` - Create a new group
- `PUT /group/{groupId}/addUser/{userId}` - Add a user to a group
- `DELETE /group/{groupId}/removeUser/{userId}` - Remove a user from a group

## ❗ Exception Handling
Custom exceptions for better error management:
- `UserAlreadyExistsException`
- `InvalidGroupException`
- `UserIsNotAParticipantException`
- `GroupDoesNotExistException`
- `InvalidSenderException`
