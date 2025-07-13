package com.su25.swp391.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
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
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String email;
    private String password;
    private String full_name;
    private String user_name;
    private Date birth_date;
    private String gender;
    private String role;
    private String address;
    private String mobile;
    private String status;
}
