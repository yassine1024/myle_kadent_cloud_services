package com.healthcaredental.reception.employee.medecin;


import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;


import java.util.List;
import java.util.Set;

@Data
@Entity
public class Medecin extends Employee {

    public Medecin(){

    }
    public Medecin(String id){
        this.id=id;
    }
    private float salary;
    private float recette;
    private float pourcentage;
    @ManyToMany
    @JoinTable(
            name = "patient_treat",
            joinColumns = @JoinColumn(name = "medecin_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Set<Patient> treatedPatients;



}
