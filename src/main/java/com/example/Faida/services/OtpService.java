package com.example.Faida.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Faida.repository.OtpRepository;
import com.example.Faida.models.Otp;
import com.example.Faida.models.User;

@Service
public class OtpService {
    private final OtpRepository otpRepository;

    @Autowired
    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public String generateOTP() {
        Random random = new Random();

        int otpNumber = random.nextInt(90000) + 10000;

        String otp = String.valueOf(otpNumber);

        return otp;
    }

    public void saveOtp(Otp otp) {
        otp.hashOtp();

        otpRepository.save(otp);
    }

    public void deleteOtp(Otp otp) {
        otpRepository.delete(otp);
    }

    public boolean compareOtpWithCurrentUser(String otp, User currentUser) {
        Otp currentOtp = otpRepository.findByOwner(currentUser);
        if (currentOtp != null) {
            deleteOtp(currentOtp);
            return true;
        }
        return false;
    }

}
