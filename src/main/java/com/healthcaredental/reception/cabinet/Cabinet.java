package com.healthcaredental.reception.cabinet;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
