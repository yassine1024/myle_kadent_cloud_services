package com.healthcaredental.reception.rendezvous;

import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.cabinet.CabinetVisit;
import com.healthcaredental.reception.employee.medecin.Medecin;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Data
public class Rendezvous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String complaint;
    private String acteToPerform;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

}
