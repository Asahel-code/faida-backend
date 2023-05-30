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
import com.example.Faida.repository.UserRepository;
import com.example.Faida.response.JwtAuthenticationResponse;

@Controller
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final AfricasTalkingSmsService smsService; 
    private final OtpService otpService;
    private final JwtTokenService tokenService;
    

    public UserController(UserRepository userRepository, UserService userService, AfricasTalkingSmsService smsService, OtpService otpService, JwtTokenService tokenService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.smsService = smsService;
        this.otpService = otpService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> request) {
        String phoneNumber = request.get("phoneNumber");

        // Retrieve user by phone number
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid phone number");
        }

        User user = userOptional.get();
        
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

       
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOTP(@RequestBody Map<String, String> request) {
        String phoneNumber = request.get("phoneNumber");
        String otp = request.get("otp");

        // Retrieve user by phone number
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid phone number");
        }

        User currentUser = userOptional.get();

        // Check if OTP matches
        if (otpService.compareOtpWithCurrentUser(otp, currentUser)) {
            String token = tokenService.generateToken(currentUser);

            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
    }
}