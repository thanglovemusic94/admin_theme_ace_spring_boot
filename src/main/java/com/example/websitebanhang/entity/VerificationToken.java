package com.example.websitebanhang.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table
public class VerificationToken{
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String token;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;


    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
