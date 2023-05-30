package com.example.Faida.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Document(collection = "otp")
public class Otp {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Id
    private String id;

    @Indexed(unique = true)
    private String otp;

    @Indexed(expireAfterSeconds = 3600)
    private Date createdAt;

    @DBRef
    private User owner;

    public Otp(String otp, User owner) {
        this.otp = otp;
        this.owner = owner;
    } 

    public void hashOtp() {
        this.otp = encoder.encode(this.otp);
    }

    public boolean compareOtp(String otp) {
        return encoder.matches(otp, this.otp);
    }

    public User getOwner() {
        return owner;
    }
}
