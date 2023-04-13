package com.healthcaredental.reception.rendezvous;

public enum ErrorRendezvous {

    NBR_RDV_LIMIT("Vous avez atteint la limite des rendezvous!"),
    MISSING_PARAMETER("Missing parameter"),
    DATABASE_ERROR("Database error");

    private final String message;

    ErrorRendezvous(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
