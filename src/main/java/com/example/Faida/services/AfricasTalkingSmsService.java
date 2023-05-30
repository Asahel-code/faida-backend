package com.example.Faida.services;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class AfricasTalkingSmsService {

    Dotenv dotenv = Dotenv.configure().load();

    private String username = dotenv.get("AT_USERNAME");

    private String apiKey = dotenv.get("AT_APIKEY");

    public void sendSms(String phoneNumber, String message) {
        // Initialize the Africa's Talking service
        AfricasTalking.initialize(username, apiKey);

        // Get the SMS service
        SmsService smsService = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);

        try {
            // Send the SMS
            List<Recipient> response = smsService.send(message, new String[] {phoneNumber}, true);

            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

