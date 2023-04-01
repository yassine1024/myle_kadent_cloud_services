package com.healthcaredental.reception.employee;


import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.cabinet.Cabinet;
import com.healthcaredental.reception.employeeType.EmployeeType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
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
    //Path to the photo store in local storage
    private String photo;
    //Path to the employee's document folder
    private String documentFolder;
    @ManyToOne
    private EmployeeType employeeType;
    @ManyToOne
    private Cabinet cabinet;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
