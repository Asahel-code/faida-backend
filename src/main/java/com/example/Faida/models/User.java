package com.example.Faida.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean accountStatus;

    public User(String firstName, String lastName, String phoneNumber, boolean accountStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.accountStatus = accountStatus;
    }

    public String getId() {
        return id;
    }

    public String getUserName(){
        return firstName + " " + lastName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public boolean getAccountStatus(){
        return accountStatus;
    }
}
