package com.example.Faida.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    
    @Indexed(unique = true)
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

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public boolean getAccountStatus(){
        return accountStatus;
    }
}
