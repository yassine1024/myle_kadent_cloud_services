package com.healthcaredental.reception.Patient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.healthcaredental.reception.cabinet.CabinetVisit;
import com.healthcaredental.reception.employee.medecin.Medecin;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
    private String birthDate;
    private String email;
    @ManyToMany(mappedBy = "treatedPatients")
    private Set<Medecin> treates;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private Set<CabinetVisit> visits;

    public String getAge() {
        if (birthDate == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime birthDateTime = OffsetDateTime.parse(birthDate, formatter);
        LocalDate birthDateLocal = birthDateTime.toLocalDate();
        return String.valueOf(Period.between(birthDateLocal, LocalDate.now()).getYears());
    }
}
