package com.healthcaredental.reception.employee;


import com.healthcaredental.reception.cabinet.Cabinet;
import com.healthcaredental.reception.employeeType.EmployeeType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

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
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
