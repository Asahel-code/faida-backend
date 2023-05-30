package com.example.Faida.services;

import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;


@Configuration
public class AfricasTalkingSmsService {

    private Dotenv dotenv;

    private String username;

    private String apiKey;

    public AfricasTalkingSmsService() {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        dotenv = Dotenv.configure().load();
        username = dotenv.get("AT_USERNAME");
        apiKey = dotenv.get("AT_APIKEY");
    }

    public void sendSms(String phoneNumber, String message) {
        // Initialize the Africa's Talking service
        AfricasTalking.initialize(username, apiKey);

        // Get the SMS service
        SmsService smsService = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);

        try {
            // Send the SMS
            List<Recipient> response = smsService.send(message, new String[] { phoneNumber }, true);

            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
