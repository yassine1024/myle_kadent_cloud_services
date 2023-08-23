package com.healthcaredental.reception.rendezvous;

public enum MessagesRendezvous {

    NBR_RDV_LIMIT("Vous avez atteint la limite des rendezvous!"),
    ADD_RDV_SUCCESS("Le rendezvous a été ajouté avec succés."),
    PATIENT_EXIST("Le patient a déjà un rendez-vous aujourd'hui !"),
    DATABASE_ERROR("Database error");

    private final String message;

    MessagesRendezvous(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
