package com.healthcaredental.reception.cabinet;

import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.rendezvous.Rendezvous;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CabinetVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cabinet_id")
    private Cabinet cabinet;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
