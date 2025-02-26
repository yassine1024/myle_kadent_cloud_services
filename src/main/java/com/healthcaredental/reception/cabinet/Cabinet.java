package com.healthcaredental.reception.cabinet;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;


import java.util.Set;

@Entity
@Data
public class Cabinet {

    @Id
    private String id;
    private String name;
    private String field;
    private String address;
    private String phoneNumber;

    @OneToMany(mappedBy = "cabinet")
    @JsonIgnore
    private Set<CabinetVisit> visits;
}
