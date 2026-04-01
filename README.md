# WhatsApp Chatbot Backend — Spring Boot

A simple WhatsApp chatbot backend simulation built with **Java** and **Spring Boot** for the Jarurat Care internship assignment.

---

## 🚀 Tech Stack

- Java 17
- Spring Boot 3.2.0
- Maven
- REST API

---

## 📁 Project Structure

```
whatsapp-chatbot/
├── src/
│   ├── main/java/com/chatbot/whatsapp/
│   │   ├── WhatsAppChatbotApplication.java     ← Entry point
│   │   ├── controller/
│   │   │   └── WebhookController.java          ← REST endpoints
│   │   ├── service/
│   │   │   └── ChatbotService.java             ← Business logic + logging
│   │   └── model/
│   │       ├── WhatsAppMessage.java            ← Incoming message model
│   │       └── ChatbotResponse.java            ← Bot reply model
│   └── main/resources/
│       └── application.properties
├── pom.xml
├── render.yaml                                  ← Deploy to Render
└── README.md
```

---

## ▶️ Run Locally

### Prerequisites
- Java 17+
- Maven 3.6+

### Steps

```bash
# Clone the repo
git clone https://github.com/YOUR_USERNAME/whatsapp-chatbot.git
cd whatsapp-chatbot

# Build the project
mvn clean package

# Run the application
java -jar target/whatsapp-chatbot-1.0.0.jar
```

Server starts at: `http://localhost:8080`

---

## 📡 API Endpoints

### 1. POST `/webhook` — Send a Message

**Request:**
```json
{
  "from": "+919876543210",
  "to": "+911234567890",
  "message": "Hi",
  "messageId": "msg_001",
  "timestamp": "2024-01-01T10:00:00"
}
```

**Response:**
```json
{
  "status": "success",
  "reply": "Hello! How can I help you today?",
  "originalMessage": "Hi",
  "from": "+919876543210",
  "timestamp": "2024-01-01T10:00:01"
}
```

---

### 2. GET `/webhook` — Health Check / Webhook Verification

```bash
curl http://localhost:8080/webhook
```

---

### 3. GET `/webhook/messages` — View All Logged Messages

```bash
curl http://localhost:8080/webhook/messages
```

---

### 4. DELETE `/webhook/messages` — Clear Message Log

```bash
curl -X DELETE http://localhost:8080/webhook/messages
```

---

## 💬 Predefined Bot Replies

| User Message | Bot Reply |
|---|---|
| Hi / Hello / Hey | Hello! How can I help you today? |
| Bye / Goodbye / See you | Goodbye! Have a great day! |
| Help | Sure! I'm here to help. You can say Hi, Bye, or ask any question! |
| Thanks / Thank you | You're welcome! Is there anything else I can help you with? |
| How are you | I'm doing great, thank you for asking! How can I assist you? |
| What is your name | I'm WhatsApp Chatbot, your virtual assistant! |
| *(anything else)* | I received your message: "...". I'm still learning! Try saying Hi or Bye. |

All replies are **case-insensitive**.

---

## 🧪 Test with cURL

```bash
# Say Hi
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from":"+919876543210","to":"+911234567890","message":"Hi","messageId":"msg_001","timestamp":"2024-01-01T10:00:00"}'

# Say Bye
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from":"+919876543210","to":"+911234567890","message":"Bye","messageId":"msg_002","timestamp":"2024-01-01T10:01:00"}'

# Ask for Help
curl -X POST http://localhost:8080/webhook \
  -H "Content-Type: application/json" \
  -d '{"from":"+919876543210","to":"+911234567890","message":"Help","messageId":"msg_003","timestamp":"2024-01-01T10:02:00"}'

# View all logged messages
curl http://localhost:8080/webhook/messages
```

---

## ☁️ Deploy on Render (Free)

1. Push code to GitHub
2. Go to [https://render.com](https://render.com) → New → Web Service
3. Connect your GitHub repo
4. Set:
   - **Build Command:** `./mvnw clean package -DskipTests`
   - **Start Command:** `java -jar target/whatsapp-chatbot-1.0.0.jar`
   - **Environment:** Java
5. Click **Deploy**

The `render.yaml` file in this repo auto-configures everything.

---

## 📋 Assignment Checklist

- [x] REST API endpoint `/webhook` accepting POST requests
- [x] Accepts JSON input simulating WhatsApp messages
- [x] Predefined replies: Hi → Hello, Bye → Goodbye (+ more)
- [x] Logs all incoming messages (console + in-memory)
- [x] Runs locally on port 8080
- [x] Ready for deployment on Render
- [x] Unit tests included

---

## 📸 Screenshots

*(Add screenshots of running app here for submission)*

---

## 👤 Author

Submitted for Jarurat Care — Java Developer Internship Assignment
