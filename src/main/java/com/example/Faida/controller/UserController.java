package com.example.Faida.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.*;

import com.example.Faida.models.User;
import com.example.Faida.models.Otp;
import com.example.Faida.services.AfricasTalkingSmsService;
import com.example.Faida.services.OtpService;
import com.example.Faida.services.UserService;
import com.example.Faida.services.JwtTokenService;
import com.example.Faida.response.JwtAuthenticationResponse;

@Controller
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final AfricasTalkingSmsService smsService; 
    private final OtpService otpService;
    private final JwtTokenService tokenService;
    

    public UserController(UserService userService, AfricasTalkingSmsService smsService, OtpService otpService, JwtTokenService tokenService) {
        this.userService = userService;
        this.smsService = smsService;
        this.otpService = otpService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> request) {
        String phoneNumber = request.get("phoneNumber");

        // Retrieve user by phone number
        try {
            User user = userService.getUserByPhoneNumber(phoneNumber);
            
            if(user.getAccountStatus()){
                String otp = otpService.generateOTP();
    
                Otp userOtp = new Otp(otp, user);
        
                smsService.sendSms(phoneNumber, otp);
        
                otpService.saveOtp(userOtp);
                userService.saveUser(user);
        
                return ResponseEntity.ok("OTP sent successfully");
            }
            else{
                return ResponseEntity.badRequest().body("Your account hasn't been activated");
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
           
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOTP(@RequestBody Map<String, String> request) {
        String phoneNumber = request.get("phoneNumber");
        String otp = request.get("otp");

        try {
            User user = userService.getUserByPhoneNumber(phoneNumber);
            // Check if OTP matches
        if (otpService.compareOtpWithCurrentUser(otp, user)) {
            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}