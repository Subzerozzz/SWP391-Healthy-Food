/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.su25.swp391.controller.authen;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.AccountDAO;
import com.su25.swp391.entity.Account;
import com.su25.swp391.utils.GlobalUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

@WebServlet(name = "LoginGoogleHandler", urlPatterns = {"/LoginGoogleHandler"})
public class LoginGoogleHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AccountDAO userDao = new AccountDAO();
            String code = request.getParameter("code");

            if (code == null || code.isEmpty()) {
                response.sendRedirect("home");
                return;
            }

            String accessToken = getToken(code);
            if (accessToken == null || accessToken.isEmpty()) {
                response.sendRedirect("home");
                return;
            }

            UserGoogleDto userGoogleDto = getUserInfo(accessToken);
            if (userGoogleDto == null || userGoogleDto.getEmail() == null) {
                response.sendRedirect("home");
                return;
            }

            Account user = GlobalUtils.convertToAccount(userGoogleDto);
            

            // Check user tồn tại chưa
            Account userInDB = userDao.findByEmail(Account.builder().email(user.getEmail()).build());

            if (userInDB == null) {
                int insertResult = userDao.insert(user);
                if (insertResult == -1) {
                    response.sendRedirect("home"); // hoặc thông báo lỗi hợp lý
                    return;
                }
            }

            // Lấy lại thông tin user từ DB (dù là mới hay cũ)
            user = userDao.findByEmail(Account.builder().email(user.getEmail()).build());

            if (user == null) {
                response.sendRedirect("home.jsp");
                return;
            }

            // Tạo session và cookie
            HttpSession session = request.getSession();
            session.setAttribute(GlobalConfig.SESSION_ACCOUNT, user);
            session.setMaxInactiveInterval(60 * 60 * 24);

            Cookie u = new Cookie("userC", user.getEmail());
            Cookie p = new Cookie("passC", user.getPassword());
            u.setMaxAge(60 * 60 * 24);
            p.setMaxAge(60 * 60 * 24);
            response.addCookie(u);
            response.addCookie(p);

            response.sendRedirect("home");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(GlobalConfig.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form()
                        .add("client_id", GlobalConfig.GOOGLE_CLIENT_ID) //ID ứng dụng trên Google API.
                        .add("client_secret", GlobalConfig.GOOGLE_CLIENT_SECRET) //Bí mật ứng dụng.
                        .add("redirect_uri", GlobalConfig.GOOGLE_REDIRECT_URI) //URL redirect đã đăng ký với Google.
                        .add("code", code) //mã Google gửi về.
                        .add("grant_type", GlobalConfig.GOOGLE_GRANT_TYPE).build()) // loại grant (ở đây là authorization_code).
                .execute().returnContent().asString();
        // Parse JSON trả về lấy access_token
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = GlobalConfig.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        System.out.println("---------------------------\n" + response);
        UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);

        return googlePojo;
    }

}
