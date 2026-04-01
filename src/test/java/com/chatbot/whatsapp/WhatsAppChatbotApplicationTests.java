package com.chatbot.whatsapp;

import com.chatbot.whatsapp.model.ChatbotResponse;
import com.chatbot.whatsapp.model.WhatsAppMessage;
import com.chatbot.whatsapp.service.ChatbotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WhatsAppChatbotApplicationTests {

    @Autowired
    private ChatbotService chatbotService;

    @BeforeEach
    void setUp() {
        chatbotService.clearMessages();
    }

    @Test
    void contextLoads() {
        assertNotNull(chatbotService);
    }

    @Test
    void testHiReply() {
        WhatsAppMessage msg = new WhatsAppMessage("+919876543210", "+911234567890", "Hi");
        ChatbotResponse response = chatbotService.processMessage(msg);
        assertEquals("Hello! How can I help you today?", response.getReply());
        assertEquals("success", response.getStatus());
    }

    @Test
    void testByeReply() {
        WhatsAppMessage msg = new WhatsAppMessage("+919876543210", "+911234567890", "Bye");
        ChatbotResponse response = chatbotService.processMessage(msg);
        assertEquals("Goodbye! Have a great day!", response.getReply());
    }

    @Test
    void testHelloReply() {
        WhatsAppMessage msg = new WhatsAppMessage("+919876543210", "+911234567890", "hello");
        ChatbotResponse response = chatbotService.processMessage(msg);
        assertEquals("Hello! How can I help you today?", response.getReply());
    }

    @Test
    void testUnknownMessageReply() {
        WhatsAppMessage msg = new WhatsAppMessage("+919876543210", "+911234567890", "random text xyz");
        ChatbotResponse response = chatbotService.processMessage(msg);
        assertTrue(response.getReply().contains("I received your message"));
    }

    @Test
    void testMessageLogging() {
        WhatsAppMessage msg1 = new WhatsAppMessage("+91111", "+91222", "Hi");
        WhatsAppMessage msg2 = new WhatsAppMessage("+91333", "+91222", "Bye");
        chatbotService.processMessage(msg1);
        chatbotService.processMessage(msg2);
        assertEquals(2, chatbotService.getAllMessages().size());
    }

    @Test
    void testCaseInsensitiveReply() {
        WhatsAppMessage msg = new WhatsAppMessage("+919876543210", "+911234567890", "HI");
        ChatbotResponse response = chatbotService.processMessage(msg);
        assertEquals("Hello! How can I help you today?", response.getReply());
    }
}
