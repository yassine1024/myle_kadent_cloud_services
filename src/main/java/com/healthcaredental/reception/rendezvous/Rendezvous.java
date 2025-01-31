package com.healthcaredental.reception.rendezvous;

import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.cabinet.CabinetVisit;
import com.healthcaredental.reception.employee.medecin.Medecin;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "rendezvous")
    private Set<CabinetVisit> visits;
}
