package com.chatbot.whatsapp.service;

import com.chatbot.whatsapp.model.ChatbotResponse;
import com.chatbot.whatsapp.model.WhatsAppMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Service that processes incoming WhatsApp messages and generates predefined replies
 */
@Service
public class ChatbotService {

    private static final Logger logger = LoggerFactory.getLogger(ChatbotService.class);

    // In-memory log of all received messages
    private final List<WhatsAppMessage> messageLog = Collections.synchronizedList(new ArrayList<>());

    // Predefined reply mappings (case-insensitive)
    private static final Map<String, String> REPLY_MAP = Map.ofEntries(
            Map.entry("hi", "Hello! How can I help you today?"),
            Map.entry("hello", "Hello! How can I help you today?"),
            Map.entry("hey", "Hello! How can I help you today?"),
            Map.entry("bye", "Goodbye! Have a great day!"),
            Map.entry("goodbye", "Goodbye! Have a great day!"),
            Map.entry("see you", "Goodbye! Have a great day!"),
            Map.entry("help", "Sure! I'm here to help. You can say Hi, Bye, or ask any question!"),
            Map.entry("thanks", "You're welcome! Is there anything else I can help you with?"),
            Map.entry("thank you", "You're welcome! Is there anything else I can help you with?"),
            Map.entry("how are you", "I'm doing great, thank you for asking! How can I assist you?"),
            Map.entry("what is your name", "I'm WhatsApp Chatbot, your virtual assistant!"),
            Map.entry("who are you", "I'm WhatsApp Chatbot, your virtual assistant!")
    );

    /**
     * Process an incoming WhatsApp message and return an appropriate reply
     */
    public ChatbotResponse processMessage(WhatsAppMessage incomingMessage) {
        // Log the incoming message
        logMessage(incomingMessage);

        // Generate reply
        String reply = generateReply(incomingMessage.getMessage());

        logger.info("Reply generated for message from {}: '{}'", incomingMessage.getFrom(), reply);

        return new ChatbotResponse(
                "success",
                reply,
                incomingMessage.getMessage(),
                incomingMessage.getFrom()
        );
    }

    /**
     * Generate a predefined reply based on the incoming message text
     */
    private String generateReply(String message) {
        if (message == null || message.trim().isEmpty()) {
            return "I received an empty message. Please send something!";
        }

        String normalizedMessage = message.trim().toLowerCase();

        // Check for exact matches first
        if (REPLY_MAP.containsKey(normalizedMessage)) {
            return REPLY_MAP.get(normalizedMessage);
        }

        // Check for keyword-based partial matches
        for (Map.Entry<String, String> entry : REPLY_MAP.entrySet()) {
            if (normalizedMessage.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        // Default fallback reply
        return "I received your message: \"" + message + "\". I'm still learning! Try saying Hi or Bye.";
    }

    /**
     * Log the incoming message to memory and console
     */
    private void logMessage(WhatsAppMessage message) {
        messageLog.add(message);
        logger.info("=== INCOMING MESSAGE ===");
        logger.info("From    : {}", message.getFrom());
        logger.info("To      : {}", message.getTo());
        logger.info("Message : {}", message.getMessage());
        logger.info("MsgID   : {}", message.getMessageId());
        logger.info("Time    : {}", message.getTimestamp());
        logger.info("Total messages received: {}", messageLog.size());
        logger.info("========================");
    }

    /**
     * Return all logged messages (for inspection endpoint)
     */
    public List<WhatsAppMessage> getAllMessages() {
        return Collections.unmodifiableList(messageLog);
    }

    /**
     * Clear all logged messages
     */
    public void clearMessages() {
        messageLog.clear();
        logger.info("Message log cleared");
    }
}
