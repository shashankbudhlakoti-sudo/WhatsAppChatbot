package com.chatbot.whatsapp.controller;

import com.chatbot.whatsapp.model.ChatbotResponse;
import com.chatbot.whatsapp.model.WhatsAppMessage;
import com.chatbot.whatsapp.service.ChatbotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller exposing the /webhook endpoint for WhatsApp message simulation
 */
@RestController
@RequestMapping("/webhook")
@CrossOrigin(origins = "*")
public class WebhookController {

    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    @Autowired
    private ChatbotService chatbotService;

    /**
     * POST /webhook
     * Main endpoint to receive simulated WhatsApp messages and return bot replies
     *
     * Example request body:
     * {
     *   "from": "+919876543210",
     *   "to": "+911234567890",
     *   "message": "Hi",
     *   "messageId": "msg_001",
     *   "timestamp": "2024-01-01T10:00:00"
     * }
     */
    @PostMapping
    public ResponseEntity<ChatbotResponse> receiveMessage(@RequestBody WhatsAppMessage incomingMessage) {
        logger.info("POST /webhook called from: {}", incomingMessage.getFrom());

        if (incomingMessage.getFrom() == null || incomingMessage.getMessage() == null) {
            ChatbotResponse errorResponse = new ChatbotResponse(
                    "error",
                    "Invalid request: 'from' and 'message' fields are required.",
                    null,
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        ChatbotResponse response = chatbotService.processMessage(incomingMessage);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /webhook
     * Webhook verification endpoint (simulates WhatsApp API verification)
     */
    @GetMapping
    public ResponseEntity<Map<String, String>> verifyWebhook(
            @RequestParam(value = "hub.verify_token", required = false) String verifyToken,
            @RequestParam(value = "hub.challenge", required = false) String challenge) {

        String expectedToken = "my_verify_token_12345";

        if (expectedToken.equals(verifyToken) && challenge != null) {
            logger.info("Webhook verified successfully!");
            return ResponseEntity.ok(Map.of("hub.challenge", challenge));
        }

        logger.info("GET /webhook - Health check ping");
        return ResponseEntity.ok(Map.of(
                "status", "active",
                "message", "WhatsApp Chatbot Webhook is running!",
                "version", "1.0.0"
        ));
    }

    /**
     * GET /webhook/messages
     * Returns all logged messages (for demo/debugging purposes)
     */
    @GetMapping("/messages")
    public ResponseEntity<Map<String, Object>> getAllMessages() {
        List<WhatsAppMessage> messages = chatbotService.getAllMessages();
        return ResponseEntity.ok(Map.of(
                "total", messages.size(),
                "messages", messages
        ));
    }

    /**
     * DELETE /webhook/messages
     * Clears all logged messages
     */
    @DeleteMapping("/messages")
    public ResponseEntity<Map<String, String>> clearMessages() {
        chatbotService.clearMessages();
        return ResponseEntity.ok(Map.of("status", "success", "message", "All messages cleared"));
    }
}
