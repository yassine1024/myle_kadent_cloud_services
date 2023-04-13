package com.healthcaredental.reception.rendezvous;

import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.employee.medecin.Medecin;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rendezvous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String time;
    private String complaint;
    private String acteToPerform;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

}
