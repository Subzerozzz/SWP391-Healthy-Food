package com.su25.swp391.utils;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.dal.implement.FoodDAO;
import com.su25.swp391.dal.implement.OrderDAO;
import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.Order;
import com.su25.swp391.entity.OrderItem;
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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EmailUtils {

    public static boolean sendMail(String to, String subject, String content) throws AddressException, MessagingException {
        Properties props = new Properties(); // Properties là một lớp có sẵn trong Java dùng để lưu trữ các cặp khóa – giá trị, trong đó cả key và value đều là kiểu String.
        // Thiết lập các thuộc tính cho phiên gửi mail
        props.put("mail.smtp.host", "smtp.gmail.com");  // SMTP server của Gmail
        props.put("mail.smtp.port", "587"); // Cổng TLS của Gmail  TLS (Transport Layer Security) là một giao thức bảo mật được thiết kế để mã hóa dữ liệu khi truyền qua Internet
        props.put("mail.smtp.auth", "true");  // Xác thực email để gửi 
        props.put("mail.smtp.starttls.enable", "true"); // Bật STARTTLS để bảo mật

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() { //Session.getInstance đại diện cho 1 phiên gửi email và chứa các thông tin từ props và new jakarta.mail.Authenticator() là để ghi đè cho phương thức getPasswordAuthentication()
            @Override
            protected PasswordAuthentication getPasswordAuthentication() { //Phương thức được gọi tự động khi cần xác thực với Gmail.
                return new PasswordAuthentication(GlobalConfig.USERNAME_EMAIL, GlobalConfig.PASSWORD_APP_EMAIL);
            }
        });

        MimeMessage message = new MimeMessage(session); //Tạo ra một email mới (đối tượng MimeMessage) dựa trên Session đã cấu hình trước đó.
        message.setFrom(new InternetAddress(GlobalConfig.USERNAME_EMAIL)); //Thiết lập địa chỉ người gửi (From).
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); //Thiết lập người nhận (To).
        message.setSubject(subject, "UTF-8"); //Thiết lập tiêu đề (subject) của email
        message.setHeader("Content-Type", "text/html; charset=UTF-8"); //Thiết lập header cho email
        message.setContent(content, "text/html; charset=UTF-8"); //Đặt nội dung chính cho email

        Transport.send(message); //Gửi email đi qua SMTP server. Dùng Session đã cấu hình và Authenticator để đăng nhập và gửi
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

    public static boolean sendOrderViaEmail(String to, List<OrderItem> listOrderItem, Integer orderId, boolean isPayment) throws MessagingException {
        FoodDAO foodDao = new FoodDAO();
        OrderDAO orderDao = new OrderDAO();
        Locale localeVN = new Locale("vi", "VN");     
        NumberFormat numberFormat = NumberFormat.getInstance(localeVN);
        numberFormat.setGroupingUsed(true);
        //lấy ra order theo orderID
        Order order = orderDao.findById(orderId);
        String subject = "Xin chào " + order.getFull_name() +", mã đơn hàng của bạn là:" + orderId;
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body style=\"font-family:Arial,sans-serif;line-height:1.6;\">");

        sb.append("<h2 style=\"color:#4caf50;\">Cảm ơn bạn đã đặt hàng!</h2>");
        sb.append("<h3>Mã đơn hàng của bạn: #").append(orderId).append("</h3>");
        sb.append("<h3>Chi tiết đơn hàng:</h3>");
        
        Double total = 0.0;
        for (OrderItem o : listOrderItem) {
            //Lấy ra foodId 
            Food food = foodDao.findById(o.getFood_id());
            //gan vao sb
            sb.append("--").append(food.getName()).append("(x" + o.getQuantity() + ")").append(": ")
                    .append(numberFormat.format(o.getPrice()) + " VNĐ");
            sb.append("<br></br>");
        }
        
        sb.append("<h3>Tổng Tiền: ").append(numberFormat.format(order.getTotal()) + "VNĐ").append("</h3>");
        if(order.getPayment_method().equalsIgnoreCase("cod")){
            sb.append("<div><h3 style='display:inline'>Thanh toán</h3>: Thanh toán khi nhận hàng(COD)</div>");
        }
        else{
            if(isPayment){
                sb.append("<div><h3 style='display:inline'>Thanh toán</h3>: Đã thanh toán qua VNPay.</div>");
            }
            else{
                sb.append("<div><h3 style='display:inline'>Thanh toán</h3>: Thanh toán qua VNPay thất bại, bạn có thể thanh toán trực tiếp khi nhận hàng.</div>");
            }
            
        }
        sb.append("<div><h3 style='display:inline'>Giao đến: </h3>"+ order.getShipping_address() + "</div>");
        sb.append("<h3>Một lần nữa cảm ơn bạn đã sử dụng dịch vụ!</h3>");
        sb.append("<h3>Nếu cần hỗ trợ, vui lòng liên hệ qua hotline: 0819525888😊</h3>");
        
        boolean send = sendMail(to, subject, sb.toString());
        return send;
    }

}
