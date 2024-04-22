package com.healthcaredental.reception.rendezvous;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezvousDetailId implements Serializable {

    @OneToOne
    private Rendezvous rendezvous;
    private String date;
}
