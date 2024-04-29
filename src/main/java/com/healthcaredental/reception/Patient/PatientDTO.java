package com.healthcaredental.reception.Patient;

import lombok.Data;

@Data
public class PatientDTO {

    private Patient patient;
    private int confirmedAppointment;
    private int missingConfirmedAppointment;
    private int postponedAppointment;

    public PatientDTO(Patient patient, int confirmedAppointment, int missingConfirmedAppointment, int postponedAppointment) {
        this.patient = patient;
        this.confirmedAppointment = confirmedAppointment;
        this.missingConfirmedAppointment = missingConfirmedAppointment;
        this.postponedAppointment = postponedAppointment;
    }
}
