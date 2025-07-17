package com.su25.swp391.controller.chat;

import com.su25.swp391.config.GlobalConfig;
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
import java.util.List;

@WebServlet(name = "ChatController", urlPatterns = {"/chatpage"})
public class ChatController extends HttpServlet {

    private MessageDAO messageDAO = new MessageDAO();
    private static final String CHAT_PAGE = "/view/chat/chat.jsp";
    private static final String LOGIN_PAGE = "/view/authen/login.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);
        
        // Check if user is logged in
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // Get chat history between current user and admin
        String username = account.getUser_name();
        String admin = "admin";
        
        try {
            List<Message> messages = messageDAO.getMessages(username, admin);
            request.setAttribute("messages", messages);
            request.setAttribute("currentUser", username);
            request.setAttribute("receiver", admin);
        } catch (Exception e) {
            System.err.println("Error getting chat history: " + e.getMessage());
            request.setAttribute("error", "Could not load chat history");
        }
        
        request.getRequestDispatcher(CHAT_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
} 