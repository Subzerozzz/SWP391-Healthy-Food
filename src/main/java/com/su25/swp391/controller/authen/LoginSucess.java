/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.authen;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author kieud
 */
@WebServlet(name = "LoginSucess", urlPatterns = { "/logout", "/profile", "/changepassword" })
public class LoginSucess extends HttpServlet {

    private static final String LOGIN_PAGE = "view/Authen/Login.jsp";
    private static final String HOME_PAGE = "view/homePage/home.jsp";
    private static final String OTP_PAGE = "view/Authen/OTP.jsp";

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/logout":
                logoutDoGet(request, response);
                break;
            // xử lý profile hoặc changepassword nếu cần
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    } 

    private void logoutDoGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // lấy session hiện tại, không tạo mới
        String url = null;

        if (session != null) {
            session.removeAttribute("account"); // xoá attribute "account"
            session.invalidate(); // huỷ toàn bộ session nếu cần
        }
        url = HOME_PAGE;
        request.getRequestDispatcher(url).forward(request, response);
        return;
    }

}
