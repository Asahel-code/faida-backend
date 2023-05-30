package com.example.Faida.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;

@Document(collection = "otp")
public class Otp {
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
        this.otp = BCrypt.hashpw(this.otp, BCrypt.gensalt(8));
    }

    public boolean compareOtp(String otp) {
        return BCrypt.checkpw(otp, this.otp);
    }

    public User getOwner() {
        return owner;
    }
}
