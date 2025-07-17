package com.su25.swp391.controller.chat;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.dal.implement.MessageDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.entity.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminChatController", urlPatterns = {"/admin/chat"})
public class AdminChatController extends HttpServlet {

    private MessageDAO messageDAO = new MessageDAO();
    private AccountDAO accountDAO = new AccountDAO();
    private static final String ADMIN_CHAT_PAGE = "/view/admin/admin_chat.jsp";
    private static final String LOGIN_PAGE = "/view/authen/login.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
        
        // Check if user is logged in and is admin
        if (account == null || !account.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String action = request.getParameter("action");
        String selectedUser = request.getParameter("user");
        
        try {
            // Get list of users who have chatted with admin
            List<String> chatUsers = getChatUsers();
            request.setAttribute("chatUsers", chatUsers);
            
            // If a user is selected, get chat history
            if (selectedUser != null && !selectedUser.isEmpty()) {
                List<Message> messages = messageDAO.getMessages(selectedUser, "admin");
                request.setAttribute("messages", messages);
                request.setAttribute("selectedUser", selectedUser);
            }
            
            request.setAttribute("currentUser", "admin");
            
        } catch (Exception e) {
            System.err.println("Error in AdminChatController: " + e.getMessage());
            // Viet 
            request.setAttribute("error", "Could not load chat data");
        }
        
        request.getRequestDispatcher(ADMIN_CHAT_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    private List<String> getChatUsers() {
        List<String> users = new ArrayList<>();
        List<Message> allMessages = messageDAO.findAll();
        
        for (Message message : allMessages) {
            if (message.getSender().equals("admin") && !users.contains(message.getReceiver())) {
                users.add(message.getReceiver());
            } else if (message.getReceiver().equals("admin") && !users.contains(message.getSender())) {
                users.add(message.getSender());
            }
        }
        
        return users;
    }
} 