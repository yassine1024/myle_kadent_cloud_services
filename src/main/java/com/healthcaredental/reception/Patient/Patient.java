package com.healthcaredental.reception.Patient;

import com.healthcaredental.reception.cabinet.CabinetVisit;
import com.healthcaredental.reception.employee.medecin.Medecin;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
public class Patient {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String gender;
    private String job;
    private String age;
    @ManyToMany(mappedBy = "treatedPatients")
    private Set<Medecin> treates;

    @OneToMany(mappedBy = "patient")
    private Set<CabinetVisit> visits;
}
