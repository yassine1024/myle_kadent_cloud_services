package com.healthcaredental.reception.rendezvous;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezvousDetailId implements Serializable {

    @OneToOne
    private Rendezvous rendezvous;
    private LocalDate date;
}
