package com.healthcaredental.reception.functionality;

import com.google.common.hash.Hashing;
import com.healthcaredental.reception.Patient.Patient;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashPatientToString implements HashObjectToString<Patient> {

    @Override
    public  String hasheObjectToString(Patient patient) throws NoSuchAlgorithmException {

        String necessaryPatientToHash=(patient.getFirstName()+patient.getLastName()+patient.getPhoneNumber());

        int hash = Hashing.murmur3_32().hashString(necessaryPatientToHash, StandardCharsets.UTF_8).asInt();
        String hashStr = Integer.toString(hash);
        // Truncate the hash string to 24 characters
        return hashStr;//hashStr.substring(0, 24);

    }



}
