package com.example.Faida.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Faida.models.Otp;

@Repository
public interface OtpRepository extends MongoRepository<Otp, String> {
    Otp findByToken(String otp);
}

