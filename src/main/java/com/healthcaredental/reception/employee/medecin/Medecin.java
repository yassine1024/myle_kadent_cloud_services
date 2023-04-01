package com.healthcaredental.reception.employee.medecin;


import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.employee.Employee;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
public class Medecin extends Employee {

    private float salary;
    private float recette;
    private float pourcentage;

}
