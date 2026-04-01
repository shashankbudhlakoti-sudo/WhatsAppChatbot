package com.chatbot.whatsapp.model;

/**
 * Represents the chatbot's response to a WhatsApp message
 */
public class ChatbotResponse {

    private String status;
    private String reply;
    private String originalMessage;
    private String from;
    private String timestamp;

    // Constructors
    public ChatbotResponse() {}

    public ChatbotResponse(String status, String reply, String originalMessage, String from) {
        this.status = status;
        this.reply = reply;
        this.originalMessage = originalMessage;
        this.from = from;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    // Getters and Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReply() { return reply; }
    public void setReply(String reply) { this.reply = reply; }

    public String getOriginalMessage() { return originalMessage; }
    public void setOriginalMessage(String originalMessage) { this.originalMessage = originalMessage; }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
