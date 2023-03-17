package com.healthcaredental.reception.functionality;


import java.security.NoSuchAlgorithmException;

public interface HashObjectToString<T> {

    public  String hasheObjectToString(T object) throws NoSuchAlgorithmException;
}
