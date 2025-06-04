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

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(GlobalConfig.USERNAME_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
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

        return otp+"";
    }
    
//    public static void main(String[] args) {
//        sendMail("kieuducmanh2004vinhphuc@gmail.com","test gửi email","Hello");
//    }
}