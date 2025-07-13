package com.su25.swp391.controller.websocket;

import com.su25.swp391.dal.implement.MessageDAO;
import com.su25.swp391.entity.Message;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat")
public class ChatEndpoint {
    // Lưu danh sách session và username tương ứng
    private static final Map<Session, String> sessionUserMap = new ConcurrentHashMap<>();
    private static final Map<String, Session> userSessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        try {
            String query = session.getQueryString();
            String username = getUsernameFromQuery(query);
            if (username == null) {
                session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "Username required"));
                return;
            }

            // Remove old session if user reconnects
            Session oldSession = userSessionMap.get(username);
            if (oldSession != null && oldSession.isOpen()) {
                sessionUserMap.remove(oldSession);
            }

            sessionUserMap.put(session, username);
            userSessionMap.put(username, session);
            System.out.println("User connected: " + username + " (Session: " + session.getId() + ")");

            // Only send chat history to non-admin users
            // Admin gets history from JSP, sending again would cause duplicates
            if (!"admin".equals(username)) {
                String admin = "admin";
                MessageDAO dao = new MessageDAO();
                List<Message> messages = dao.getMessages(username, admin);

                System.out.println("Sending " + messages.size() + " historical messages to user: " + username);
                for (Message m : messages) {
                    String prefix = m.getSender().equals(username) ? "You: " : m.getSender() + ": ";
                    session.getBasicRemote().sendText(prefix + m.getMessage());
                }
            } else {
                System.out.println("Admin connected - skipping history send to prevent duplicates");
            }

        } catch (Exception e) {
            System.err.println("onOpen error: " + e.getMessage());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        try {
            String sender = sessionUserMap.get(session);
            if (sender == null) {
                System.err.println("No username found for session: " + session.getId());
                return;
            }

            System.out.println("Received message from " + sender + ": " + message);

            // Gửi định dạng: receiver|messageContent
            String[] parts = message.split("\\|", 2);
            if (parts.length != 2) {
                System.err.println("Invalid message format from " + sender + ": " + message);
                return;
            }

            String receiver = parts[0];
            String content = parts[1];

            System.out.println("Routing message: " + sender + " -> " + receiver + ": " + content);

            // Lưu vào DB
            MessageDAO dao = new MessageDAO();
            boolean saved = dao.saveMessage(sender, receiver, content);
            
            if (saved) {
                System.out.println("Message saved to database successfully");
            } else {
                System.err.println("Failed to save message to database");
            }

            // Gửi cho người nhận nếu online
            Session receiverSession = userSessionMap.get(receiver);
            if (receiverSession != null && receiverSession.isOpen()) {
                String messageToReceiver = sender + ": " + content;
                receiverSession.getBasicRemote().sendText(messageToReceiver);
                System.out.println("Message delivered to " + receiver + ": " + messageToReceiver);
            } else {
                System.out.println("Receiver " + receiver + " is not online or session is closed");
            }

            // Gửi lại cho người gửi (confirmation)
            String confirmationMessage = "You: " + content;
            session.getBasicRemote().sendText(confirmationMessage);
            System.out.println("Confirmation sent to " + sender + ": " + confirmationMessage);

        } catch (Exception e) {
            System.err.println("onMessage error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        String username = sessionUserMap.remove(session);
        if (username != null) {
            userSessionMap.remove(username);
            System.out.println("User disconnected: " + username + " (Session: " + session.getId() + ")");
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        String username = sessionUserMap.get(session);
        System.err.println("WebSocket error for user " + username + ": " + throwable.getMessage());
        throwable.printStackTrace();
    }

    private String getUsernameFromQuery(String query) {
        if (query == null) return null;
        try {
            String[] params = query.split("&");
            for (String param : params) {
                String[] pair = param.split("=");
                if (pair.length == 2 && pair[0].equals("username")) {
                    return URLDecoder.decode(pair[1], StandardCharsets.UTF_8.name());
                }
            }
        } catch (Exception e) {
            System.err.println("Username parsing failed: " + e.getMessage());
        }
        return null;
    }

    
}
