/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.config;
import com.su25.swp391.entity.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author manhpthe172481
 */
public class GlobalConfig {

    public static final double[] LOW = {10.0, 18.4};
    public static final double[] NORMAL = {18.5, 24.9};
    public static final double[] OVERWEIGHT = {25.0, 29.9};
    public static final double[] OBESE = {30.0, 50.0};
    public static final double[] ALL = {0.0, 51.0}; // Mặc định lọc toàn bộ
 
    public static final String STATUS_REQUEST_DONE = "Done";
    public static final String STATUS_REQUEST_NOT_DONE = "Not done";
    public static final Integer SIZE_PAGE =10;
    public static final String STATUS_RESULT_REJECT = "Reject";
    public static final String STATUS_RESULT_ACCEPT = "Accept";

    /**
     * SESSION_ACCOUNT: Đối tượng tài khoản được lưu trong phiên làm việc
     * (session)
     */
    public static final String SESSION_ACCOUNT = "account";

    /**
     * USERNAME_EMAIL: Địa chỉ email được sử dụng để gửi email từ ứng dụng
     */
    public static final String USERNAME_EMAIL = "kieuducmanh2004vinhphuc@gmail.com";

    /**
     * PASSWORD_APP_EMAIL: Mật khẩu của địa chỉ email được sử dụng để gửi email
     * từ ứng dụng
     */
    public static final String PASSWORD_APP_EMAIL = "undg wjzo ydsv jqci";

    /**
     * GOOGLE_CLIENT_ID: ID của ứng dụng khách (client) được cấp bởi Google để
     * xác thực
     */
    public static String GOOGLE_CLIENT_ID = "603741791751-77ab6u1qf89i36uqfudjqc08agjs9obm.apps.googleusercontent.com";

    /**
     * GOOGLE_CLIENT_SECRET: Khóa bí mật của ứng dụng khách được cấp bởi Google
     * để xác thực
     */
    public static String GOOGLE_CLIENT_SECRET = "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";

    /**
     * GOOGLE_REDIRECT_URI: URI được sử dụng để đổi mã xác thực (authorization
     * code) lấy từ Google thành mã truy cập (access token)
     */
    public static String GOOGLE_REDIRECT_URI = "http://localhost:9999/SWP391-Healthy-Food/LoginGoogleHandler";

    /**
     * GOOGLE_LINK_GET_TOKEN: Liên kết để trao đổi mã xác thực từ Google để lấy
     * mã truy cập
     */
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    /**
     * GOOGLE_LINK_GET_USER_INFO: Liên kết để lấy thông tin người dùng từ Google
     * bằng cách sử dụng mã truy cập
     */
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    /**
     * GOOGLE_GRANT_TYPE: Loại cấp phép được sử dụng trong quy trình xác thực
     * Google (authorization code)
     */
    public static String GOOGLE_GRANT_TYPE = "authorization_code";

    public static final String ROLE_USER = "customer";

    public static final String ROLE_ADMIN = "admin";

    public static final String ROLE_STAFF = "staff";

    public static final String ORDER_STATUS_PENDING = "pending";

    public static final String ORDER_STATUS_CANCELLED = "cancelled";

    public static final String ORDER_STATUS_COMPLETED = "completed";

    public static final String ORDER_STATUS_ACCEPTED = "accepted";

    public static final String PAYMENT_METHOD_VNPAY = "vnpay";

    public static final String PAYMENT_METHOD_COD = "cod";

    /**
     * ORDER_TYPE_WHOLE_SALE: wholesale Đơn hàng khi mua combo, mua sỉ
     */
    public static final String ORDER_TYPE_WHOLE_SALE = "wholesale";

    /**
     * ORDER_TYPE_RETAIL: retail Đơn hàng khi mua lẻ
     */
    public static final String ORDER_TYPE_RETAIL = "retail";

}


