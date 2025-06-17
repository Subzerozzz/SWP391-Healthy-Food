/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.utils;

/**
 *
 * @author Hang
 */
import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.utils.GlobalUtils;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailUtils {

    /**
     * Gửi email HTML với tiêu đề và nội dung đến địa chỉ email đích
     * @param to Địa chỉ người nhận
     * @param subject Tiêu đề email
     * @param content Nội dung email (có thể chứa HTML)
     * @return true nếu gửi thành công, false nếu lỗi
     */
   public static boolean sendMail(String to, String subject, String content) {
    try {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GlobalConfig.USERNAME_EMAIL, GlobalConfig.PASSWORD_APP_EMAIL);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(GlobalConfig.USERNAME_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setContent(content, "text/html; charset=UTF-8");

        Transport.send(message);
        return true;
    } catch (Exception e) {
        e.printStackTrace(); // In lỗi ra log
        return false;
    }
}
  public static String sendAccountMail(String to, String username, String password) {
    String subject = "Tai Khoan truy cap he thong:";
    String content = "<h3>Xin chào,</h3>"
            + "<p>Bạn đã được cấp một tài khoản để truy cập hệ thống:</p>"
            + "<p><strong>Tên đăng nhập:</strong> " + username + "</p>"
            + "<p><strong>Mật khẩu:</strong> " + password + "</p>"
            + "<p>Hãy đăng nhập và thay đổi mật khẩu ngay sau lần đăng nhập đầu tiên.</p>"
            + "<br><p>Trân trọng!</p>";

    boolean sent = sendMail(to, subject, content);
    if (sent) {
        return "Gửi tài khoản qua email thành công!";
    } else {
        return "Gửi email thất bại!";
    }
}
}
