package com.su25.swp391.entity;

/**
 *
 * @author Dell
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.sql.Date;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Account {
    private Integer id;
    private String email;
    private String mobie;
    private String password;
    private String user_name;
    private String full_name;
    private Date birth_date;
    private String gender;
    private String role;
    private String address;
    private String status;
}
