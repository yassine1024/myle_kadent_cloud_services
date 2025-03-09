package com.healthcaredental.reception.auth;

import com.healthcaredental.reception.cabinet.Cabinet;
import com.healthcaredental.reception.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}