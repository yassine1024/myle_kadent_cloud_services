package com.healthcaredental.reception.employee;


import com.healthcaredental.reception.cabinet.Cabinet;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



import java.time.LocalDateTime;

@Entity
@Data
public class Employee {

    @Id
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String phoneNumber;
    //Path to the photo store in local storage
    protected String photo;
    //Path to the employee's document folder
    protected String documentFolder;
    @ManyToOne
    protected Cabinet cabinet;

    @CreationTimestamp
    protected LocalDateTime createdAt;
    @UpdateTimestamp
    protected LocalDateTime updatedAt;
}
