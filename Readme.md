# 📔 Journal App

A modern Spring Boot REST API for managing personal journal entries with MongoDB persistence.

---

## ✨ Features

- ✅ Create journal entries with automatic timestamps
- ✅ Retrieve all entries or specific entries by ID
- ✅ Update existing journal entries (partial updates supported)
- ✅ Delete journal entries
- ✅ MongoDB integration for document storage
- ✅ RESTful API design
- ✅ Object ID support for MongoDB documents

---

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 21 | JDK |
| **Spring Boot** | 4.0.5 | Framework |
| **Spring Data MongoDB** | Latest | Database ORM |
| **MongoDB** | 5.6.4+ | NoSQL Database |
| **Maven** | Latest | Build Tool |
| **Tomcat** | 11.0.20 | Web Server |

---

## 📋 Prerequisites

Before running this project, ensure you have installed:

- ☕ **Java 21+** – [Download here](https://www.oracle.com/java/technologies/downloads/)
- 🗄️ **MongoDB** – [Installation guide](https://www.mongodb.com/docs/manual/installation/)
- 📦 **Maven** – [Download here](https://maven.apache.org/download.cgi)

### Verify MongoDB is running
```bash
# Windows
netstat -ano | findstr :27017

# Linux/macOS
lsof -i :27017
```

---

## 🚀 Quick Start

### 1. Clone & Navigate
```bash
cd journalApp
```

### 2. Configure Database (Optional)
Edit `src/main/resources/application.properties`:
```properties
spring.application.name=journalApp
server.port=7000

# MongoDB Configuration
spring.mongodb.host=localhost
spring.mongodb.port=27017
spring.mongodb.database=journaldb
```

### 3. Build & Run
```bash
# Build
./mvnw.cmd clean package

# Run
./mvnw.cmd spring-boot:run
```

The app will start on **http://localhost:7000**

---

## 📡 API Endpoints

### Base URL
```
http://localhost:7000/journal
```

### 1️⃣ Get All Entries
```http
GET /journal
```
**Response:**
```json
[
  {
    "id": "507f1f77bcf86cd799439011",
    "title": "My First Entry",
    "content": "Today was great!",
    "date": "2026-04-10T17:35:37"
  }
]
```

### 2️⃣ Create New Entry
```http
POST /journal
Content-Type: application/json

{
  "title": "My First Entry",
  "content": "Today was great!"
}
```
**Response:** `201 Created` with the saved entry including generated `id` and `date`

### 3️⃣ Get Entry by ID
```http
GET /journal/id/{id}
```
**Example:**
```http
GET /journal/id/507f1f77bcf86cd799439011
```

### 4️⃣ Update Entry
```http
PUT /journal/id/{id}
Content-Type: application/json

{
  "title": "Updated Title",
  "content": "Updated content"
}
```
**Note:** Partial updates are supported (only provide fields to update)

### 5️⃣ Delete Entry
```http
DELETE /journal/id/{id}
```
**Response:** `200 OK` with success message

---

## 📁 Project Structure

```
journalApp/
├── src/main/java/com/example/journalApp/
│   ├── JournalAppApplication.java          # Main entry point
│   ├── controller/
│   │   ├── HealthCheckController.java      # Health check endpoint
│   │   └── JournalEntryControllerV2.java   # Journal endpoints
│   ├── service/
│   │   └── JournalEntryService.java        # Business logic
│   ├── repository/
│   │   └── JournalEntryRepo.java           # Data access layer
│   └── entity/
│       └── JournalEntry.java               # Data model
├── src/main/resources/
│   └── application.properties               # Configuration
├── pom.xml                                  # Maven dependencies
└── README.md                                # This file
```

---

## 🔍 Entity Schema

### JournalEntry
```java
{
  "_id": ObjectId,           // MongoDB auto-generated ID
  "title": String,           // Entry title
  "content": String,         // Entry content
  "date": LocalDateTime      // Entry creation/modification date
}
```

---

## 🧪 Testing with cURL

### Create an entry
```bash
curl -X POST http://localhost:7000/journal \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"My Day\",\"content\":\"Had a great day!\"}"
```

### Get all entries
```bash
curl http://localhost:7000/journal
```

### Get entry by ID
```bash
curl http://localhost:7000/journal/id/507f1f77bcf86cd799439011
```

### Update entry
```bash
curl -X PUT http://localhost:7000/journal/id/507f1f77bcf86cd799439011 \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"Updated Title\"}"
```

### Delete entry
```bash
curl -X DELETE http://localhost:7000/journal/id/507f1f77bcf86cd799439011
```

---

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| **Port 7000 already in use** | Change port in `application.properties` or stop conflicting process |
| **MongoDB connection refused** | Ensure MongoDB is running on `localhost:27017` |
| **Build fails** | Run `./mvnw.cmd clean install` to refresh dependencies |
| **No entries returned** | Check MongoDB is connected and database has data |

---

## 📝 Example Workflow

```bash
# 1. Start the app
./mvnw.cmd spring-boot:run

# 2. Create a journal entry
curl -X POST http://localhost:7000/journal \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"Day 1\",\"content\":\"Excited to start!\"}"

# Copy the returned ID, e.g., "507f1f77bcf86cd799439011"

# 3. Retrieve all entries
curl http://localhost:7000/journal

# 4. Update the entry
curl -X PUT http://localhost:7000/journal/id/507f1f77bcf86cd799439011 \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"Day 1 - Updated\"}"

# 5. Delete the entry
curl -X DELETE http://localhost:7000/journal/id/507f1f77bcf86cd799439011
```

---

## 🤝 Contributing

Feel free to fork, modify, and improve this project!

---

## 📄 License

This project is open source and available for educational purposes.

---

## 👨‍💻 Author

Created as a Spring Boot learning project.

---

## 🎯 Future Enhancements

- [ ] User authentication & authorization
- [ ] Search & filter entries
- [ ] Export entries to PDF/CSV
- [ ] Tags & categories for entries
- [ ] Full-text search support
- [ ] API documentation with Swagger/OpenAPI
- [ ] Unit & integration tests
- [ ] Docker containerization

---

**Happy journaling! 📝✨**
