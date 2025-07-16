package com.su25.swp391.controller.chat;

import com.su25.swp391.dal.implement.MessageDAO;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
public class ChatWebSocketEndpoint {
    
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
    private static ConcurrentHashMap<String, Session> userSessions = new ConcurrentHashMap<>();
    private MessageDAO messageDAO = new MessageDAO();
    
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        
        // Get username from query parameter
        String username = getUsername(session);
        if (username != null) {
            userSessions.put(username, session);
            System.out.println("User " + username + " connected to chat");
        }
        
        System.out.println("WebSocket connection opened. Total connections: " + sessions.size());
    }
    
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received message: " + message);
        
        String username = getUsername(session);
        if (username == null) {
            System.err.println("Username not found for session");
            return;
        }
        
        try {
            // Parse message format: "receiver|message"
            String[] parts = message.split("\\|", 2);
            if (parts.length != 2) {
                System.err.println("Invalid message format: " + message);
                return;
            }
            
            String receiver = parts[0];
            String msg = parts[1];
            
            // Save message to database
            boolean saved = messageDAO.saveMessage(username, receiver, msg);
            if (!saved) {
                System.err.println("Failed to save message to database");
                return;
            }
            
            // Send confirmation to sender
            session.getBasicRemote().sendText("You: " + msg);
            
            // Send message to receiver if they are online
            Session receiverSession = userSessions.get(receiver);
            if (receiverSession != null && receiverSession.isOpen()) {
                receiverSession.getBasicRemote().sendText(username + ": " + msg);
                System.out.println("Message delivered to " + receiver);
            } else {
                System.out.println("Receiver " + receiver + " is not online");
            }
            
        } catch (IOException e) {
            System.err.println("Error sending message: " + e.getMessage());
        }
    }
    
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        
        String username = getUsername(session);
        if (username != null) {
            userSessions.remove(username);
            System.out.println("User " + username + " disconnected from chat");
        }
        
        System.out.println("WebSocket connection closed. Total connections: " + sessions.size());
    }
    
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("WebSocket error: " + throwable.getMessage());
        throwable.printStackTrace();
    }
    
    private String getUsername(Session session) {
        String queryString = session.getQueryString();
        if (queryString != null) {
            String[] params = queryString.split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2 && "username".equals(keyValue[0])) {
                    try {
                        return java.net.URLDecoder.decode(keyValue[1], "UTF-8");
                    } catch (Exception e) {
                        System.err.println("Error decoding username: " + e.getMessage());
                    }
                }
            }
        }
        return null;
    }
} 