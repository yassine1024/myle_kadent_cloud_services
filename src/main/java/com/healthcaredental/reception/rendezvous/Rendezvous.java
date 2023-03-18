package com.healthcaredental.reception.rendezvous;

import com.healthcaredental.reception.Patient.Patient;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Rendezvous {

    @Id
    private String id;
    @ManyToOne
    private Patient patient;
    private String date;
    private String time;
}
