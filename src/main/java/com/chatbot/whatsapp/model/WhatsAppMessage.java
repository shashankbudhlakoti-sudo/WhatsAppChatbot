package com.chatbot.whatsapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an incoming WhatsApp message payload
 */
public class WhatsAppMessage {

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    @JsonProperty("message")
    private String message;

    @JsonProperty("messageId")
    private String messageId;

    @JsonProperty("timestamp")
    private String timestamp;

    // Constructors
    public WhatsAppMessage() {}

    public WhatsAppMessage(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    // Getters and Setters
    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "WhatsAppMessage{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", message='" + message + '\'' +
                ", messageId='" + messageId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
