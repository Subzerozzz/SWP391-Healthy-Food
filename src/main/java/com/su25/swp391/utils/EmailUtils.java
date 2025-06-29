package com.su25.swp391.utils;

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

public class EmailUtils {

    public static boolean sendMail(String to, String subject, String content) throws AddressException, MessagingException {
        Properties props = new Properties();
        // Thiết lập các thuộc tính cho phiên gửi mail
        props.put("mail.smtp.host", "smtp.gmail.com");  // SMTP server của Gmail
        props.put("mail.smtp.port", "587"); // Cổng TLS của Gmail
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Bật STARTTLS để bảo mật

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GlobalConfig.USERNAME_EMAIL, GlobalConfig.PASSWORD_APP_EMAIL);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(GlobalConfig.USERNAME_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject, "UTF-8");
        message.setHeader("Content-Type", "text/html; charset=UTF-8");
        message.setContent(content, "text/html; charset=UTF-8");

        Transport.send(message);
        return true;
    }

    public static String sendOTPMail(String to) {
        int otp = GlobalUtils.generateOTP(6); // Sử dụng phương thức generateOTP để tạo OTP
        String subject = "Mã OTP";
        String content = "Mã OTP của bạn là: " + otp + "<br><br>Mã OTP sẽ hết hạn sau 5 phút.";

        try {
            sendMail(to, subject, content);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return otp + "";
    }
     public static String sendAccountMail(String to, String username, String password) throws MessagingException {
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

    public static void main(String[] args) {
        try {
            sendMail("kieuducmanh2004vinhphuc@gmail.com", "test gửi email", "Hello");
        } catch (MessagingException ex) {
            Logger.getLogger(EmailUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
