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
        AccountDAO userDao = new AccountDAO();
        String code = request.getParameter("code"); // Dùng code để đổi lấy access token
        String accessToken = getToken(code);  // Dùng token để lấy info người dùng từ Google
        UserGoogleDto userGoogleDto = getUserInfo(accessToken);
        
        // Convert UserGoogleDto to User
        Account user = GlobalUtils.convertToAccount(userGoogleDto);
        
        try {
            //check  user exist by mail
            Account userInDB = userDao.findByEmail(Account.builder().email(user.getEmail()).build());
            //if not exist => insert
            if (userInDB == null) {
                userDao.insert(user);
            }
            HttpSession session = request.getSession();
            user = userDao.findByEmail(Account.builder().email(user.getEmail()).build());
            session.setAttribute(GlobalConfig.SESSION_ACCOUNT, user);
            session.setMaxInactiveInterval(60 * 60 * 24); // Đặt thời gian sống của session là 24 giờ

            Cookie u = new Cookie("userC", user.getEmail()); //tạo cookie để lưu email và password
            Cookie p = new Cookie("passC", user.getPassword());
            u.setMaxAge(60 * 60 * 24); // thời gian lưu cookie
            p.setMaxAge(60 * 60 * 24);
            response.addCookie(p); // Thêm cookie vào response gửi về trình duyệt
            response.addCookie(u);

            response.sendRedirect("home");
            
            
        } catch (Exception e) {
            e.printStackTrace();
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
        System.out.println("---------------------------\n" +  response);
        UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);

        return googlePojo;
    }

}