/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.su25.swp391.utils;

import com.su25.swp391.config.GlobalConfig;
import com.su25.swp391.controller.authen.UserGoogleDto;
import com.su25.swp391.entity.Account;
import java.util.Random;

/**
 *
 * @author ADMIN
 */
public class GlobalUtils {

    public static int generateOTP(int length) {
        Random rand = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < length; i++) {
            otp.append(rand.nextInt(10));
        }

        return Integer.parseInt(otp.toString());
    }

    public static Account convertToAccount(UserGoogleDto userGoogleDto) {
        Account account = new Account();
        account.setUser_name(userGoogleDto.getEmail());
        account.setEmail(userGoogleDto.getEmail());
        account.setPassword(""); // Set default or encrypted password
        account.setRole(GlobalConfig.ROLE_USER); // Default role ID, adjust as needed
        return account;
    }
}
    