package com.healthcaredental.reception.functionality;

import com.healthcaredental.reception.Patient.Patient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPatientToToString implements HashObjectToString<Patient> {

    @Override
    public  String hasheObjectToString(Patient patient) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] objectBytes = patient.toString().getBytes();
        byte[] hashBytes = md.digest(objectBytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }



}
