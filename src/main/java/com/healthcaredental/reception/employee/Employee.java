package com.healthcaredental.reception.employee;


import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.cabinet.Cabinet;
import com.healthcaredental.reception.employeeType.EmployeeType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Employee {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    @ManyToOne
    private EmployeeType employeeType;
    @ManyToOne
    private Cabinet cabinet;

    @ManyToMany
    private List<Patient> patients ;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
