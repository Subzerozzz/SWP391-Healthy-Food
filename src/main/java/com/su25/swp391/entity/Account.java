package com.su25.swp391.entity;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    private int id;
    private String full_name;
    private String user_name;
    private String email;
    private String password;
    private String address;
    private String role;
    private Boolean status;
    private Date birth_date;
    private String mobile;
    private String gender;
            
    
}