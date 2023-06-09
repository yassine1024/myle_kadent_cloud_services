package com.healthcaredental.reception.cabinet;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Cabinet {

    @Id
    private String id;
    private String name;
    private String field;
    private String address;
    private String phoneNumber;

}
